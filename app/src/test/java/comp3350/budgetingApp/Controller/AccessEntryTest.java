package comp3350.budgetingApp.Controller;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.io.SessionOutputBuffer;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;

import comp3350.budgetingApp.Application.Services;
import comp3350.budgetingApp.controller.AccessEntries;
import comp3350.budgetingApp.controller.AccessUsers;
import comp3350.budgetingApp.objects.Entry;
import comp3350.budgetingApp.objects.Expense;
import comp3350.budgetingApp.objects.Income;
import comp3350.budgetingApp.objects.User;
import comp3350.budgetingApp.persistence.hsqldb.GeneralPersistence;

public class AccessEntryTest {
    GeneralPersistence gp = null;
    AccessUsers au = null;
    AccessEntries ae = null;
    @Before
    public void initializeDB() {
        gp = Services.getGeneralPersistence();
        au = new AccessUsers();
        ae = new AccessEntries();
    }

    @Test
    public void testMain() {
        assertNotNull(gp);
        assertNotNull(au);
        gp.createTable();

        //For testing only
        gp.clearAllTables();

        User newUser = new User("Sam", 7, "Sam21", "password", "20000000","sam@gmail.com", "what is my name?", "Sam");
        au.addUser(newUser);

        Entry newE = new Expense("food", -21);
        ae.addEntry(newUser.getUserName(), newE);

        assertEquals(ae.getEntries(newUser.getUserName(), 0).get(0).getName(), newE.getName());

        Entry newI = new Income("Work", 25);
        ae.addEntry(newUser.getUserName(), newI);

        assertEquals(ae.getEntries(newUser.getUserName(), 1).get(0).getName(), newI.getName());

        ae.removeEntry(newI.getEntryID());
        //should return no entry
        assertEquals(0, ae.getEntries(newUser.getUserName(), 1).size());

        ae.addEntry(newUser.getUserName(), newI);
        Entry newE2 = new Expense("food", -212);
        ae.addEntry(newUser.getUserName(), newE2);
        Entry newE3 = new Expense("fooddd", -2112);
        ae.addEntry(newUser.getUserName(), newE3);
        ae.addEntry(newUser.getUserName(), new Income("work", 1234));
        ArrayList<Entry> newList =  ae.getEntriesBetween(newUser.getUserName(), 200000, 1, 1);
        System.out.println(newList.size());
        for(int i = 0; i < newList.size(); i++) {
            System.out.println(newList.get(i).getName());
        }
        gp.createTable();
    }
}
