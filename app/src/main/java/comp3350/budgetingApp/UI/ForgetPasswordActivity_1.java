/**
 * class: ForgetPasswordActivity_1
 * Author: Weiyu Sun
 *
 * Remark: get username from user to reset password
 */
package comp3350.budgetingApp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import comp3350.budgetingApp.controller.AccessUsers;
import comp3350.budgetingApp.R;

public class ForgetPasswordActivity_1 extends AppCompatActivity {

    private EditText userName;
    private Button continueBtn;
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password1);

        initView();
        jumpToForgetWordActivity2();
        jumpToLogIn();
    }

    /**
     * get the username and check if it is valid.
     * if valid, go to next page, if not, send msg to user
     */
    private void jumpToForgetWordActivity2() {
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputtedUserName = userName.getText().toString();
                AccessUsers accessUsers = new AccessUsers();
                AccessUsers.user = accessUsers.getUser(inputtedUserName);
                if (AccessUsers.user != null) {
                    Intent intent = new Intent(ForgetPasswordActivity_1.this, ForgetPasswordActivity_2.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ForgetPasswordActivity_1.this, "Inputted user name does not exist", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /**
     * maybe, the user can remember to the password,
     * so click this to go back to login page
     */
    private void jumpToLogIn() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgetPasswordActivity_1.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        userName = findViewById(R.id.fg_user_name_et);
        continueBtn = findViewById(R.id.fg_1_btn);
        login = findViewById(R.id.fg_1_log_in);
    }
}