/**
 * class: SecurityQuestionActivity
 * Author: Weiyu Sun
 *
 * Remarks: set security question and answer for user
 */
package comp3350.budgetingApp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.budgetingApp.R;
import comp3350.budgetingApp.controller.AccessUsers;
import comp3350.budgetingApp.objects.User;

public class SecurityQuestionActivity extends AppCompatActivity {
    private EditText qEt, aEt; // security question and answer
    private Button signUpBtn;
    private String name, userName, phone, email, password;
    private int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_question);

        initView();
        catchParameters();
        signUp();
    }

    private void initView() {
        qEt = findViewById(R.id.sq_q);
        aEt = findViewById(R.id.sq_a);

        signUpBtn = findViewById(R.id.sign_up_btn);
    }

    /*
    get personal information from sign up page, after security question and answer set, the
    new account will be created in this page
     */
    private void catchParameters() {
        name = getIntent().getStringExtra("name");
        userName = getIntent().getStringExtra("userName");
        phone = getIntent().getStringExtra("phone");
        email = getIntent().getStringExtra("email");

        // -1 is impossible value for age,
        // if you find any accounts' age is -1, means there is bug in code
        age = getIntent().getIntExtra("age", -1);

        password = getIntent().getStringExtra("password");
    }

    /*
    create account for uer bu inputted info
     */
    private void signUp() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (createNewUser(qEt.getText().toString(),
                        aEt.getText().toString())) {
                    Toast.makeText(SecurityQuestionActivity.this, "Success to create account!", Toast.LENGTH_SHORT).show();
                    Intent toLogIn = new Intent(SecurityQuestionActivity.this, LoginActivity.class);
                    startActivity(toLogIn);
                }
            }
        });
    }

    /*
    a helper method to create a new account for user
     */
    private boolean createNewUser(String q, String a) {
        boolean isValid = UIChecker.isSecurityQuestionValid(q, this) &&
                UIChecker.isSecurityAnswerValid(a, this);

        if (isValid) {
            User user = new User(name, age, userName, password, phone, email, q, a);
            AccessUsers au = new  AccessUsers();
            au.addUser(user);
        }

        return isValid;
    }
}