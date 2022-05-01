package comp3350.budgetingApp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.budgetingApp.UI.AddEntryActivityTest;
import comp3350.budgetingApp.UI.SignUpActivity;
import comp3350.budgetingApp.UI.contactInfoActivityTest;
import comp3350.budgetingApp.UI.deleteEntryActivityTest;
import comp3350.budgetingApp.UI.forgetPasswordTest;
import comp3350.budgetingApp.UI.mortgageActivityTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddEntryActivityTest.class,
        mortgageActivityTest.class,
        contactInfoActivityTest.class,
        deleteEntryActivityTest.class,
        forgetPasswordTest.class,
        //add more as needed
})
public class AllAcceptanceTests
{}
