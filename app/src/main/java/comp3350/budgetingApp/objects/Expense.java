/**
 * class Income
 * Author: abhi
 *
 * Remark: User Expense
 */
package comp3350.budgetingApp.objects;

public class Expense extends Entry {
    public Expense(String name, float amount) {
        super(name, amount);
        assert (amount < 0);
    }
}

