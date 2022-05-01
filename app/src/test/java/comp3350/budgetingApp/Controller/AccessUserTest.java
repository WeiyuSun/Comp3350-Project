package comp3350.budgetingApp.Controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import comp3350.budgetingApp.Application.Services;
import comp3350.budgetingApp.controller.AccessUsers;
import comp3350.budgetingApp.objects.User;
import comp3350.budgetingApp.persistence.hsqldb.GeneralPersistence;

public class AccessUserTest {
    GeneralPersistence gp = null;
    AccessUsers au = null;
    @Before
    public void initializeDB() {
        gp = Services.getGeneralPersistence();
        au = new AccessUsers();
    }

    @Test
    public void testMain() {
        assertNotNull(gp);
        assertNotNull(au);
        gp.createTable();

        //For testing only
        gp.clearAllTables();

        User newUser = new User("Sam", 7, "Sam21", "password", "20000000","sam@gmail.com", "what is my name?", "Sam");

        //We have not added anything yet.
        //Testing get Method
        assertNull(au.getUser("Sam21"));

        //After Adding User
        au.addUser(newUser);
        assertNotNull(au.getUser("Sam21"));

        //testing getUsers()
        assertEquals(1, au.getUsers().size());
        assertEquals("Sam", au.getUsers().get(0).getName());
    }
}
