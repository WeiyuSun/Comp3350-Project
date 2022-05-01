package comp3350.budgetingApp.controller;

import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.budgetingApp.objects.Entry;
import comp3350.budgetingApp.objects.Expense;
import comp3350.budgetingApp.objects.Income;

public class EntryTest {

    @Test
    public void testEntryIncome() {
        Entry entry;

        System.out.println("\n Testing Entry-Income Class");

        entry = new Income("Investment", 25);

        assertNotNull(entry);
        //Getters
        assertEquals("Investment", entry.getName());
        assertEquals(25, entry.getAmount(), 0.0);

        //Setters
        entry.setAmount(100);
        entry.setName("Assets");

        assertEquals("Assets", entry.getName());
        assertEquals(100, entry.getAmount(), 0.0);
    }

    @Test
    public void testEntryExpense() {
        Entry entry;

        System.out.println("\n Testing Entry-Expense Class");

        entry = new Expense("Food", -25);

        assertNotNull(entry);

        //Getters
        assertEquals( "Food", entry.getName());
        assertEquals(-25, entry.getAmount(), 0.0);

        //Setters
        entry.setAmount(-100);
        entry.setName("Travel");

        assertEquals("Travel", entry.getName());
        assertEquals(-100, entry.getAmount(), 0.0);

        System.out.println("\n Finished EntryTest!");
    }
}
