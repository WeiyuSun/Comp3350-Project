/**
 * class: ChangeAgeActivity
 * Author: Weiyu Sun
 *
 * Remark: get new Age from user and store it in database
 */
package comp3350.budgetingApp.UI;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import comp3350.budgetingApp.controller.AccessUsers;
import comp3350.budgetingApp.R;

public class ChangeAgeActivity extends AppCompatActivity {

    private Button updateBtn;
    private EditText changeAgeEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_age);

        initView();
        setUpdateBtn();
    }

    private void initView(){
        updateBtn = findViewById(R.id.change_inf_btn);
        changeAgeEt = findViewById(R.id.change_age_et);
    }

    /**
     * store the new age and goes to ViewInfActivity
     */
    public void setUpdateBtn(){
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newAge = changeAgeEt.getText().toString();

                if(UIChecker.isAgeValid(newAge, ChangeAgeActivity.this)){
                    AccessUsers accessUsers = new AccessUsers();
                    accessUsers.updateSingleEntityUserProfile(AccessUsers.user.getUserName(), "age", newAge);
                    AccessUsers.user = accessUsers.getUser(AccessUsers.user.getUserName());
                    Toast.makeText(ChangeAgeActivity.this, "Success to change age", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangeAgeActivity.this, AccountActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}