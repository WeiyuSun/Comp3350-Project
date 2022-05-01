/**
 * class: ContactAdvisorActivity
 * Author: Weiyu
 *
 * Remark: show the financial advisor info to user
 */
package comp3350.budgetingApp.UI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.budgetingApp.R;

public class ContactAdvisorActivity extends AppCompatActivity {

    private TextView name1, phone1, email1, name2, phone2, email2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_advisor);

        initView();
        setContactInf();
    }

    private void initView(){
        name1 = findViewById(R.id.ad_name_1_detail);
        phone1 = findViewById(R.id.ad_phone_1_detail);
        email1 = findViewById(R.id.ad_email_1_detail);

        name2 = findViewById(R.id.ad_name_2_detail);
        phone2 = findViewById(R.id.ad_phone_2_detail);
        email2 = findViewById(R.id.ad_email_2_detail);
    }

    @SuppressLint("SetTextI18n")
    private void setContactInf(){
        name1.setText("Weiyu Sun");
        phone1.setText("123-456-7890");
        email1.setText("sunw1@myumanitoba.ca");

        name2.setText("Tom");
        phone2.setText("098-765-4321");
        email2.setText("tom12.myumanitoba.ca");
    }
}