/**
 * class Entry
 * Author: abhi
 *
 * Remark: an Entry is Income or Expense in this project
 */
package comp3350.budgetingApp.objects;

import java.util.UUID;

public class Entry {
    private String entryID;
    private String name;
    private float amount;

    public Entry(String name,float amount) {
        this.name = name;
        UUID key = UUID.randomUUID();
        this.entryID = key.toString();
        this.amount = amount;
    }
    public String getName() { return name; }
    public float getAmount() { return amount; }
    public String getEntryID() { return entryID; }
    public void setName(String name) { this.name = name; }
    public void setAmount(float amount) { this.amount = amount; }
    public void setEntryID(String entryID) { this.entryID = entryID; }
}

