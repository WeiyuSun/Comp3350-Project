/**
 * class: LoginActivity
 * Author: Weiyu Sun
 *
 * Remark: log into user account
 */
package comp3350.budgetingApp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.budgetingApp.R;
import comp3350.budgetingApp.controller.AccessUsers;

public class  LoginActivity extends AppCompatActivity {

    private EditText userNameInput, passwordInput;
    private Button loginBtn;
    private TextView forgetPassword;
    private TextView signUpTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        onBackPressed();
        initView();
        login();
        jumpToForgetPassword();
        jumpToSignUp();
    }

    private void initView() {
        userNameInput = findViewById(R.id.log_in_user_name_et);
        passwordInput = findViewById(R.id.log_in_password_et);
        loginBtn = findViewById(R.id.login_btn);
        forgetPassword = findViewById(R.id.log_in_reset_key_tv);
        signUpTv = findViewById(R.id.sign_up_tv);
    }

    /**
     * login through username and password
     */
    private void login() {
        loginBtn.setOnClickListener(view -> {
            AccessUsers accessUsers = new AccessUsers();
            AccessUsers.user = accessUsers.getUserByPassword(userNameInput.getText().toString(), passwordInput.getText().toString());
            if (AccessUsers.user != null) {
                Log.d("currUser:", AccessUsers.user.toString());
                Toast.makeText(LoginActivity.this, "Success to login", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(LoginActivity.this, "The user name or password is incorrect", Toast.LENGTH_SHORT).show();
            }
        });
        }

    /**
     * once clicked, user can reset password
     */
    private void jumpToForgetPassword() {
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity_1.class);
                startActivity(intent);
            }
        });
    }

    /**
     * once clicked user can sign up a new account
     */
    private void jumpToSignUp() {
        signUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    // user cannot go back in this page
    @Override
    public void onBackPressed() { }

}