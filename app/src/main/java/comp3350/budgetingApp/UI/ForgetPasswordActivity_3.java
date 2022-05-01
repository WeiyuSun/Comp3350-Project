/**
 * class: ForgetPasswordActivity_3
 * Author: Weiyu Sun
 *
 * Remark:get new password and reset
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

public class ForgetPasswordActivity_3 extends AppCompatActivity {

    private EditText newPasswordEt, confirmPassWordEt;
    private Button updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password3);

        initView();
        update();
    }

    private void initView() {
        newPasswordEt = findViewById(R.id.fg_3_new_password_et);
        confirmPassWordEt = findViewById(R.id.fg_3_confirm_et);
        updateBtn = findViewById(R.id.fg_3_btn);
    }

    /**
     * get new password from user, and check if it valid
     * if is valid, store it,
     * if not send msg to user
     */
    private void update() {
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  String newPassword = newPasswordEt.getText().toString();
                  String confirmPassword = confirmPassWordEt.getText().toString();

                if (UIChecker.isPasswordValid(newPassword, ForgetPasswordActivity_3.this)) {
                    if (newPassword.equals(confirmPassword)) {
                        new AccessUsers().updateUserCredentials(AccessUsers.user.getUserName(), "password", newPassword);
                        Intent intent = new Intent(ForgetPasswordActivity_3.this, LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(ForgetPasswordActivity_3.this, "Success to change password!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ForgetPasswordActivity_3.this, "Password are not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}