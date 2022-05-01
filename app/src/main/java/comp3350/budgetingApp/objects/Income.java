/**
 * class Income
 * Author: abhi
 *
 * Remark: User Income
 */
package comp3350.budgetingApp.objects;
public class Income extends Entry {
    public Income(String name, float amount) {
        super(name, amount);
        assert (amount > 0);
    }
}