/**
 * class: ChangeEmailActivity
 * Author: Weiyu Sun
 *
 * Remark: get new email from user and store it in database
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

public class ChangeEmailActivity extends AppCompatActivity {

    private Button uploadBtn;
    private EditText changeEmailEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        initView();
        setUploadBtn();
    }

    private void initView(){
        uploadBtn = findViewById(R.id.change_inf_btn);
        changeEmailEt = findViewById(R.id.change_email_et);
    }

    /**
     * store the new email and goes to ViewInfActivity
     */
    private void setUploadBtn(){
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEmail = changeEmailEt.getText().toString();

                if(UIChecker.isEmailValid(newEmail, ChangeEmailActivity.this)){
                    AccessUsers accessUsers = new AccessUsers();
                    accessUsers.updateSingleEntityUserProfile(AccessUsers.user.getUserName(), "email", newEmail);
                    AccessUsers.user = accessUsers.getUser(AccessUsers.user.getUserName());
                    Toast.makeText(ChangeEmailActivity.this, "Success to change email", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ChangeEmailActivity.this, AccountActivity.class));
                }
            }
        });
    }
}