/**
 * class: AccessUsers
 * Author: Taran
 *
 * Remark: interact database for Entries' data(income and expense information)
 */
package comp3350.budgetingApp.controller;

import java.util.ArrayList;

import comp3350.budgetingApp.Application.Services;
import comp3350.budgetingApp.objects.Entry;
import comp3350.budgetingApp.persistence.hsqldb.EntriesPersistence;

public class AccessEntries {
    private final EntriesPersistence entriesPersistence;

    public AccessEntries() {
        entriesPersistence = Services.getEntriesPersistence();
    }

    public ArrayList<Entry> getEntries(String userID, int type) { return (entriesPersistence.getEntries(userID, type)); }
    public void addEntry(String userID,Entry newEntry) { entriesPersistence.postEntry(userID,newEntry); }
    public void removeEntry(String entryID) { entriesPersistence.deleteEntry(entryID); }
    public ArrayList<Entry> getEntriesBetween(String userID, float max, float min, int type) { return entriesPersistence.getEntriesFilter(userID, max, min, type);}
}
