/**
 * class: ToolsFragment
 * Author: Weiyu Sun
 *
 * Remark: a fragment to show the tools list(like contact advisor, calculator)
 */
package comp3350.budgetingApp.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import comp3350.budgetingApp.R;

public class ToolsFragment extends Fragment {
    RelativeLayout contactAdvisorBlock, calculatorBlock;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceSate){
        return inflater.inflate(R.layout.fragment_tools, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceSate){
        super.onViewCreated(view, saveInstanceSate);
        initView(view);
        setContactAdvisor();
        setCalculator();
    }

    private void initView(View view){
        contactAdvisorBlock = view.findViewById(R.id.tools_contact_advisor_block);
        calculatorBlock = view.findViewById(R.id.tools_calculator_block);
    }

    /*
    jump to advisor page
     */
    private void setContactAdvisor(){
        contactAdvisorBlock.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ContactAdvisorActivity.class);
            startActivity(intent);
        });
    }

    /*
    jump to calculator page
     */
    private void setCalculator(){
        calculatorBlock.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), CalculatorActivity.class);
            startActivity(intent);
        });
    }
}
