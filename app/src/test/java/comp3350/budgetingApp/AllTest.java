package comp3350.budgetingApp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.budgetingApp.Controller.AccessEntryTest;
import comp3350.budgetingApp.Controller.AccessUserTest;
import comp3350.budgetingApp.controller.EntryTest;
import comp3350.budgetingApp.controller.UserTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        EntryTest.class,
        UserTest.class,
        AccessUserTest.class,
        AccessEntryTest.class
})
public class AllTest {}
