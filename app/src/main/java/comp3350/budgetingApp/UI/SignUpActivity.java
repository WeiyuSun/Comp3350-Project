/**
 * class: SignUpActivity
 * Author: Weiyu Sun
 *
 * class: sign up an account by user input
 */
package comp3350.budgetingApp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import comp3350.budgetingApp.R;

public class SignUpActivity extends AppCompatActivity {

    // for sign up we need user: name, username, email, phone number, age and password
    private String name;
    private String userName;
    private String email;
    private String phone;
    private int age;
    private String password;

    EditText inputtedName, inputtedUserName, inputtedEmail, inputtedPhone, inputtedAge, inputtedPassword;
    Button toSq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();

        jumpToSignIn();
        jumpToSq();
    }

    private void initView() {
        inputtedName = findViewById(R.id.sign_up_name_et);
        inputtedUserName = findViewById(R.id.sign_up_username_et);
        inputtedEmail = findViewById(R.id.sign_up_email_tv);
        inputtedPhone = findViewById(R.id.sign_up_phone_et);
        inputtedAge = findViewById(R.id.sign_up_age_et);
        inputtedPassword = findViewById(R.id.sign_up_password_et);
        toSq = findViewById(R.id.sign_up_to_sq_btn);
    }

    // a button to go back to sign in page
    private void jumpToSignIn() {
        TextView signUpTv = findViewById(R.id.log_in_tv);
        signUpTv.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    /**
     * check if user information is valid,
     * if valid assign it by variable and send them to next page,
     * then go to next page to set security question and answer
     *
     */
    private void jumpToSq() {
        toSq.setOnClickListener(view -> {
            if (assignParameters(inputtedName.getText().toString(), inputtedUserName.getText().toString(),
                    inputtedPhone.getText().toString(), inputtedEmail.getText().toString(),
                    inputtedAge.getText().toString(), inputtedPassword.getText().toString())) {

                Intent intent = new Intent(SignUpActivity.this, SecurityQuestionActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("userName", userName);
                intent.putExtra("phone", phone);
                intent.putExtra("email", email);
                intent.putExtra("age", age);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        });
    }

    /**
     * assign all user information by related variables
     *
     * @param name
     * @param userName
     * @param phone
     * @param email
     * @param age
     * @param password
     * @return
     */
    private boolean assignParameters(String name, String userName, String phone, String email, String age, String password) {
        boolean isValidInf = UIChecker.isNameValid(name, this) &&
                UIChecker.isUserNameValid(userName, this) &&
                UIChecker.isPhoneValid(phone, this) &&
                UIChecker.isEmailValid(email, this) &&
                UIChecker.isAgeValid(age, this) &&
                UIChecker.isPasswordValid(password, this);

        if (isValidInf) {
            this.name = name;
            this.age = Integer.parseInt(age);
            this.userName = userName;
            this.password = password;
            this.phone = phone;
            this.email = email;
        }

        return isValidInf;
    }
}
