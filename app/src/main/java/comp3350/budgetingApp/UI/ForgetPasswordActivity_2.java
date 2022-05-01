/**
 * class: ForgetPasswordActivity_2
 * Author: Weiyu Sun
 *
 * Remark: let user answer the security questions to reset password
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

public class ForgetPasswordActivity_2 extends AppCompatActivity {

    private TextView q;
    private EditText a;
    private Button continueBtn;
    private String securityQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password2);

        initView();
        jumpToForgetPasswordActivity_3();
    }

    private void initView() {
        q = findViewById(R.id.fg_2_q);

        securityQuestion = AccessUsers.user.getSecurityAnswer();

        q.setText(securityQuestion);

        a = findViewById(R.id.fg_2_a);

        continueBtn = findViewById(R.id.fg_2_continue_btn);
    }

    /**
     * get answer from user, if answer correct, go to next page to reset password,
     * if not send msg to user
     */
    private void jumpToForgetPasswordActivity_3() {
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputtedA = a.getText().toString();
                if (UIChecker.isSecurityAnswerValid(inputtedA, ForgetPasswordActivity_2.this)) {

                    if (new AccessUsers().checkSecurityAnswer(inputtedA)) {
                        Intent intent = new Intent(ForgetPasswordActivity_2.this, ForgetPasswordActivity_3.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ForgetPasswordActivity_2.this, "Verification failed, please enter correct answers", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}