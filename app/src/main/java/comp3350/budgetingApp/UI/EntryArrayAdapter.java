package comp3350.budgetingApp.UI;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

import comp3350.budgetingApp.R;
import comp3350.budgetingApp.objects.Entry;
import comp3350.budgetingApp.objects.Expense;
import comp3350.budgetingApp.objects.Income;

public class EntryArrayAdapter extends ArrayAdapter<Entry> {

    private Context context;
    private ArrayList<Entry> entries;

    public EntryArrayAdapter(Context context, int res, ArrayList<Entry> entries) {
        super(context, res, entries);
        this.context = context;
        this.entries = entries;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the property we are displaying
        Entry entry = entries.get(position);
        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.property_layout, null);
        View view = inflater.inflate(R.layout.listtextview_activity, null);

        TextView name = (TextView) view.findViewById(R.id.textViewName);
        TextView amount = (TextView) view.findViewById(R.id.textViewAmount);

        //set price and rental attributes
        if(entry instanceof Expense) {
            amount.setText("-$" + String.valueOf(Math.abs(entry.getAmount())));
            amount.setTextColor(Color.RED);
        }
        if(entry instanceof Income) {
            amount.setText("$" + String.valueOf(Math.abs(entry.getAmount())));
            amount.setTextColor(Color.GREEN);
        }
        name.setText(String.valueOf(entry.getName()));
        return view;
    }

}
