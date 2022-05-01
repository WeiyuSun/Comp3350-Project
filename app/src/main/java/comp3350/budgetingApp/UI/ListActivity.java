package comp3350.budgetingApp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import comp3350.budgetingApp.R;
import comp3350.budgetingApp.controller.AccessEntries;
import comp3350.budgetingApp.controller.AccessUsers;
import comp3350.budgetingApp.objects.Entry;

public class ListActivity  extends AppCompatActivity {
    ArrayAdapter<Entry> entryArrayAdapter;
    ListView view;
    Button addBtn;
    Button filterBtn;
    Button homeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        AccessEntries ae = new AccessEntries();
        view = findViewById(R.id.entriesList);
        addBtn = findViewById(R.id.entryAddBtn);
        filterBtn = findViewById(R.id.entryFilterBtn);
        homeBtn = findViewById(R.id.listHomeBackBtn);
        if(AccessUsers.entries == null)
            AccessUsers.entries = ae.getEntries(AccessUsers.user.getUserName(), 2);
        entryArrayAdapter = new EntryArrayAdapter(this, 0, AccessUsers.entries);
        view.setAdapter(entryArrayAdapter);
        view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(AccessUsers.user.getUserName());

                ArrayList<Entry> tempE= ae.getEntries(AccessUsers.user.getUserName(), 2);
                for( int z = 0; z < tempE.size(); z++) {
                    if(tempE.get(z).getEntryID().equals(AccessUsers.entries.get(i).getEntryID()))
                        System.out.println("we found it");
                    System.out.println(tempE.get(z).getEntryID());
                }

                ae.removeEntry(AccessUsers.entries.get(i).getEntryID());

                tempE= ae.getEntries(AccessUsers.user.getUserName(), 2);
                for( int z = 0; z < tempE.size(); z++) {
                    if(tempE.get(z).getEntryID().equals(AccessUsers.entries.get(i).getEntryID()))
                        System.out.println("we found it");
                }
                AccessUsers.entries.remove(i);
                entryArrayAdapter.notifyDataSetChanged();
                return true;
            }
        });

        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(ListActivity.this, AddEntryActivity.class);
            startActivity(intent);
        });

        filterBtn.setOnClickListener(view -> {
            Intent intent = new Intent(ListActivity.this, FilterEntryActivity.class);
            startActivity(intent);
        });

        homeBtn.setOnClickListener(view -> {
            Intent intent = new Intent(ListActivity.this, AccountActivity.class);
            startActivity(intent);
        });
    }
}
