package comp3350.budgetingApp.persistence;

import java.util.ArrayList;

import comp3350.budgetingApp.objects.User;

public interface UserPersistence {
    //Any Type of GET Request Include:Name, Email, Age, Phone From User Table
    User getSingleUser(String userID);

    //Any Type of GET Request Include:Name, Email, Age, Phone From User Table
    void setSingleEntity(String userID, String type, String newValue);

    ArrayList<User> getAll();

    //Post New User Credentials
    void addUser(User newUser);

    //Post New User Credentials
    void updateUser(String userID, String name, String email, int age, String phone);
}
