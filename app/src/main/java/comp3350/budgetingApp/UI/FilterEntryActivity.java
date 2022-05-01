package comp3350.budgetingApp.UI;

import android.app.backup.FullBackupDataOutput;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import comp3350.budgetingApp.R;
import comp3350.budgetingApp.controller.AccessEntries;
import comp3350.budgetingApp.controller.AccessUsers;
import comp3350.budgetingApp.objects.Entry;

public class FilterEntryActivity extends AppCompatActivity {

    Button filterBtn;
    Button backBtn;
    Button resetBtn;
    RadioButton expRB, incRB, allRB;
    EditText min, max;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_entries_activity);
        filterBtn = findViewById(R.id.entryFilterB);
        backBtn = findViewById(R.id.filterEntryBackBtn);
        resetBtn = findViewById(R.id.resetFilterB);
        expRB = findViewById(R.id.filterExpRB);
        incRB = findViewById(R.id.filterIncRB);
        allRB = findViewById(R.id.filterAllRB);
        min = findViewById(R.id.inputMin);
        max = findViewById(R.id.inputMax);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FilterEntryActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            AccessEntries ae = new AccessEntries();
            @Override
            public void onClick(View view) {
                AccessUsers.entries.clear();
                AccessUsers.entries = ae.getEntries(AccessUsers.user.getUserName(), 2);
                Intent intent = new Intent(FilterEntryActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String minValue = min.getText().toString();
                String maxValue = max.getText().toString();
                if(minValue.isEmpty()) {
                    Toast.makeText(FilterEntryActivity.this, "Pleas fill the min field", Toast.LENGTH_SHORT).show();
                } else if(maxValue.isEmpty()) {
                    Toast.makeText(FilterEntryActivity.this, "Pleas fill the max field", Toast.LENGTH_SHORT).show();
                } else {
                    if( Integer.parseInt(maxValue) < 1000000 ) {
                        int type = 2;
                        if (expRB.isChecked())
                            type = 0;
                        if (incRB.isChecked())
                            type = 1;

                        int minE = Integer.parseInt(minValue);
                        int maxE = Integer.parseInt(maxValue);

                        AccessEntries ae = new AccessEntries();
                        ArrayList<Entry> temp = ae.getEntriesBetween(AccessUsers.user.getUserName(),maxE, minE, type);
                        if(temp.size() == 0) {
                            Toast.makeText(FilterEntryActivity.this, "No Entry Found under this range.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(FilterEntryActivity.this, "Number" + temp.size() + type, Toast.LENGTH_SHORT).show();
                            AccessUsers.entries.clear();
                            AccessUsers.entries = ae.getEntriesBetween(AccessUsers.user.getUserName(), maxE, minE, type);
                            System.out.println(minE +" : " + maxE);
                            System.out.println(type);
                            for(int i = 0; i < AccessUsers.entries.size(); i++) {

                                System.out.println(AccessUsers.entries.get(i).getClass());
                                System.out.println(AccessUsers.entries.get(i).getName());
                            }
                            Intent intent = new Intent(FilterEntryActivity.this, ListActivity.class);
                            startActivity(intent);
                        }

                    } else {
                        Toast.makeText(FilterEntryActivity.this, "Max Limit: 1000000", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}
