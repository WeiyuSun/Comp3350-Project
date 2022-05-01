package comp3350.budgetingApp.persistence;

import java.util.ArrayList;

import comp3350.budgetingApp.objects.Entry;

public interface EntriesPersistence {
    //Any Type of GET Request Include: Expenses(0), Incomes(1), Both(2) From Credentials Table
    ArrayList<Entry> getEntries(String userID, int type);

    ArrayList<Entry> getEntriesFilter(String userID, float max, float min, int type);

    //Post New User Credentials
    void postEntry(String userID, Entry newEntry);

    void deleteEntry(String entryID);
}
