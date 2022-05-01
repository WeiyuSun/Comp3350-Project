/**
 * class: AccessUsers
 * Author: Taran
 *
 * Remark: interact database for user's data(personal information)
 */
package comp3350.budgetingApp.controller;

import java.util.ArrayList;

import comp3350.budgetingApp.Application.Services;
import comp3350.budgetingApp.objects.Entry;
import comp3350.budgetingApp.objects.User;
import comp3350.budgetingApp.persistence.hsqldb.CredentialPersistence;
import comp3350.budgetingApp.persistence.hsqldb.UserPersistence;

public class AccessUsers {
    private final UserPersistence userPersistence;
    private final CredentialPersistence credentialPersistence;
    public static User user;
    public static ArrayList<Entry> entries;

    public AccessUsers() {
        userPersistence = Services.getUserPersistence();
        credentialPersistence = Services.getCredentialPersistence();
    }

    public User getUser(String userID) { return userPersistence.getSingleUser(userID); }
    public ArrayList<User> getUsers() { return (userPersistence.getAll()); }

    public User getUserByPassword(String username, String password){
        User user = getUser(username);

        if(user != null && user.getPassword().equals(password)){
            return user;
        }

        return null;
    }

    public void addUser(User newUser) {
        userPersistence.addUser(newUser);
        credentialPersistence.postCredentials(newUser);
    }

    public boolean checkSecurityAnswer(String inputAnswer){
        return user != null && user.getSecurityAnswer().equals(inputAnswer);
    }


    public void updateUserProfile(String userID, String name, String email, int age, String phone) { userPersistence.updateUser(userID, name, email, age, phone); }
    public void updateUserCredentials(String userID, String type, String newValue) { credentialPersistence.setSingleValue(userID, type, newValue); }
    public void updateSingleEntityUserProfile(String userID, String type, String newValue) { userPersistence.setSingleEntity(userID, type, newValue);}
}
