/**
 * class: HomeFragment
 * Author: Weiyu Sun, Taran, Abhi
 *
 * Remark: the home fragment, show home page to user
 */
package comp3350.budgetingApp.UI;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import comp3350.budgetingApp.R;
import comp3350.budgetingApp.controller.AccessEntries;
import comp3350.budgetingApp.controller.AccessUsers;
import comp3350.budgetingApp.objects.Entry;
import comp3350.budgetingApp.objects.Expense;
import comp3350.budgetingApp.objects.Income;

public class HomeFragment extends Fragment {

    RelativeLayout userList;
    TextView netIncome, totalIncome, totalExpenses, welcomeMsg;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceSate){
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceSate){
        super.onViewCreated(view, saveInstanceSate);
        initView(view);
        setUserList();
        setTextViews();
    }

    /*
    initialize Views
     */
    private void initView(View view){
        userList = view.findViewById(R.id.user_entries_home);
        netIncome = view.findViewById(R.id.netIncomeText);
        totalExpenses = view.findViewById(R.id.totalExpenseText);
        totalIncome = view.findViewById(R.id.totalIncomeText);
        welcomeMsg = view.findViewById(R.id.welcomeText);
    }

    private void setUserList(){
        userList.setOnClickListener(view -> {
            AccessEntries ae = new AccessEntries();
            AccessUsers.entries = ae.getEntries(AccessUsers.user.getUserName(), 2);

            Intent intent = new Intent(getActivity(), ListActivity.class);
            startActivity(intent);
        });
    }

    private void setTextViews(){
        AccessEntries ae = new AccessEntries();
        if(ae.getEntries(AccessUsers.user.getUserName(),2).size() == 0) {
            welcomeMsg.setText("Welcome!");
            netIncome.setText("0");
            totalIncome.setText("0");
            totalExpenses.setText("0");
        } else {
            welcomeMsg.setText("Welcome Back!");
            ArrayList<Entry> temp = ae.getEntries(AccessUsers.user.getUserName(),2);
            netIncome.setText(getEntryDetails(2));
            totalIncome.setText(getEntryDetails(1));
            totalExpenses.setText(getEntryDetails(0));
            totalExpenses.setTextColor(Color.RED);
            totalIncome.setTextColor(Color.GREEN);
        }

    }

    //type: 0(Exp), 1(Inc), 2(net)
    private String getEntryDetails(int detailType) {
        float sum = 0;
        AccessEntries ae = new AccessEntries();
        ArrayList<Entry> temp = ae.getEntries(AccessUsers.user.getUserName(),detailType);
        for( int i = 0; i < temp.size(); i++) {
            Entry curr = temp.get(i);
            if(curr instanceof Income)
                sum = sum + curr.getAmount();
            if(curr instanceof Expense)
                sum = sum + curr.getAmount();
        }
        return "$"+sum;
    }
}
