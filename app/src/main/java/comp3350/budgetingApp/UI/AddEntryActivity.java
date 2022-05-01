/**
 * class: AddEntryActivity
 * Author: Taran
 *
 * Remark: add new entry(income or exp) to list
 */
package comp3350.budgetingApp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.budgetingApp.R;
import comp3350.budgetingApp.controller.AccessEntries;
import comp3350.budgetingApp.controller.AccessUsers;
import comp3350.budgetingApp.objects.Entry;
import comp3350.budgetingApp.objects.Expense;
import comp3350.budgetingApp.objects.Income;

public class AddEntryActivity extends AppCompatActivity {

    Button addBtn;
    Button backBtn;
    EditText name, amount;
    RadioButton expRB, incRB;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_entry);
        name = findViewById(R.id.inputName);
        amount = findViewById(R.id.inputNumber);
        expRB = findViewById(R.id.expRB);
        incRB = findViewById(R.id.incRB);
        addBtn = findViewById(R.id.AddButton);
        backBtn = findViewById(R.id.addEntryBackBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddEntryActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String entryName = name.getText().toString();
                String entryAmountString = amount.getText().toString();
                float entryAmount = 0;
                if(!entryAmountString.isEmpty())
                    entryAmount = Float.parseFloat(entryAmountString);

                if (entryName.isEmpty()) {
                    Toast.makeText(AddEntryActivity.this, "Pleas fill the name field", Toast.LENGTH_SHORT).show();
                }
                else if (entryAmount == 0) {
                    Toast.makeText(AddEntryActivity.this, "Pleas fill the amount field", Toast.LENGTH_SHORT).show();
                } else {
                    Entry entry;
                    if (incRB.isChecked())
                        entry = new Income(entryName, entryAmount);
                    else
                        entry = new Expense(entryName, -entryAmount);

                    AccessEntries ae = new AccessEntries();
                    ae.addEntry(AccessUsers.user.getUserName(), entry);
                    AccessUsers.entries = ae.getEntries(AccessUsers.user.getUserName(), 2);
                    Intent intent = new Intent(AddEntryActivity.this, ListActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}
