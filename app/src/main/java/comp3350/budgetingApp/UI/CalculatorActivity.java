/**
 * class: CalculatorActivity
 * Author: Yucong
 *
 * Remark: a financial calculator
 */
package comp3350.budgetingApp.UI;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import comp3350.budgetingApp.R;
import comp3350.budgetingApp.controller.Calculator;

public class CalculatorActivity extends AppCompatActivity {

    Spinner spinner1;
    EditText row1Edit;
    EditText row2Edit;
    Button total;

    EditText row4Edit;

    Button detail;
    TextView calTotal;
    TextView displayDetails;

    private void initSpinner() {
         /** declare the components of the information getter**/

        spinner1 = (Spinner) findViewById(R.id.sp1);
        String[] years = getResources().getStringArray(R.array.years);

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, years);
        spinner1.setAdapter(yearAdapter);
        spinner1.setSelection(0);
        spinner1.setPrompt("Please Set your Mortgage Year");

    }


    double inputTotal;
    String buytotal;/**Total price of item**/
    String percent; /**Mortgage percentage**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        initSpinner();
        row1Edit = (EditText) findViewById(R.id.row1Edit);
        row2Edit = (EditText) findViewById(R.id.row2Edit);
        total = (Button) findViewById(R.id.calTotal);

        calTotal = (TextView) findViewById(R.id.showtotal);
        detail = (Button) findViewById(R.id.detail);
        displayDetails = (TextView) findViewById(R.id.displayDetails);
        row4Edit = (EditText) findViewById(R.id.row7Edit);

        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buytotal = row1Edit.getText().toString();
                percent = row2Edit.getText().toString();
                if (TextUtils.isEmpty(buytotal) || TextUtils.isEmpty(percent))/**Test NULL Input**/
                {
                    Toast.makeText(CalculatorActivity.this, "Please provide full information", Toast.LENGTH_LONG).show();
                } else if (!UIChecker.isNum(buytotal) || !UIChecker.isNum(percent)) {//is this number?
                    Toast.makeText(CalculatorActivity.this, "Invalid input", Toast.LENGTH_LONG).show();
                } else if (Double.parseDouble(percent) > 100) {//adjust the total amount is over 100%?
                    Toast.makeText(CalculatorActivity.this, "Total percentage of mortgage cannot be over 100%", Toast.LENGTH_LONG).show();
                } else if (UIChecker.isNum(buytotal) && UIChecker.isNum(percent)) {
                    inputTotal = Calculator.Total(buytotal, percent);/** call calculate method from logic layer**/
                    calTotal.setText("Your total Mortgage is " + String.format("%.2f", inputTotal) + "k");
                }
            }
        });

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String text = Calculator.displayDetail(row4Edit, spinner1, inputTotal);/** call calculate method from logic layer**/
                displayDetails.setText(text);


                if (calTotal.getText().toString().equals("The Mortgage total amount：*** *10K")) {// Test Mortage total amount is calculated?
                    Toast.makeText(CalculatorActivity.this, "Please calculate total amount of Mortgage", Toast.LENGTH_LONG).show();
                } else if (row1Edit.getText().toString().equals(buytotal) == false || row2Edit.getText().toString().equals(percent) == false) {//Change amount of percent?
                    Toast.makeText(CalculatorActivity.this, "Your percentage of Mortgage is changed, please re-calculate total amount of Mortgage ", Toast.LENGTH_LONG).show();
                }
            }

        });

        row1Edit.addTextChangedListener(new TextWatcher() {
            int oldlength = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {//Force Users type in float
                int length = charSequence.length();
                if (length > oldlength) {
                    char newchar = charSequence.charAt(i);
                    if ((newchar < '0' && newchar > '9') && newchar != '.') {
                        if (i != length - 1)
                            row1Edit.setText(charSequence.subSequence(0, i).toString() + charSequence.subSequence(i + 1, length).toString());
                        else
                            row1Edit.setText(charSequence.subSequence(0, length - 1));
                    }
                }
                oldlength = length;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        row2Edit.addTextChangedListener(new TextWatcher() {
            int oldlength = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int length = charSequence.length();
                if (length > oldlength) {
                    char newchar = charSequence.charAt(i);
                    if ((newchar < '0' && newchar > '9') && newchar != '.') {
                        if (i != length - 1)
                            row2Edit.setText(charSequence.subSequence(0, i).toString() + charSequence.subSequence(i + 1, length).toString());
                        else
                            row2Edit.setText(charSequence.subSequence(0, length - 1));
                    }
                }
                oldlength = length;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        row4Edit.addTextChangedListener(new TextWatcher() {
            int oldlength = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int length = charSequence.length();
                if (length > oldlength) {
                    char newchar = charSequence.charAt(i);
                    if ((newchar < '0' && newchar > '9') && newchar != '.') {
                        if (i != length - 1)
                            row4Edit.setText(charSequence.subSequence(0, i).toString() + charSequence.subSequence(i + 1, length).toString());
                        else
                            row4Edit.setText(charSequence.subSequence(0, length - 1));
                    }
                }
                oldlength = length;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
