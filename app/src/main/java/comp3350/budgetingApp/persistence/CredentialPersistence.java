package comp3350.budgetingApp.persistence;

import java.sql.SQLException;

import comp3350.budgetingApp.objects.User;

public interface CredentialPersistence {
    //Any Type of GET Request Include: Password, Question, Answer From Credentials Table
    String getSingleEntity(String userID, String type) throws SQLException;

    //Post New User Credentials
    void postCredentials(User newUser);

    //Any Type of SET Request Include:Name, Email, Age, Phone From User Table
    void setSingleValue(String userID, String type, String newValue);
}
