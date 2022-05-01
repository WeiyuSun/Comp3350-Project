/**
 * class ViewInfActivity
 * Author: Weiyu
 *
 * Remark: view user information
 */
package comp3350.budgetingApp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import comp3350.budgetingApp.R;
import comp3350.budgetingApp.controller.AccessUsers;

public class ViewInfActivity extends AppCompatActivity {

    private Button logoutBtn;
    private TextView nameTv, usernameTv, ageTv, phoneTv, emailTv;
    private RelativeLayout nameLayout, ageLayout, phoneLayout, emailLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inf);

        onBackPressed();
        initView();
        showInf();
        setLogoutBtn();

        changeName();
        changeAge();
        changeEmail();
        changPhone();
    }

    private void initView() {
        logoutBtn = findViewById(R.id.inf_logout_btn);
        nameTv = findViewById(R.id.inf_name_content);
        usernameTv = findViewById(R.id.inf_username);
        ageTv = findViewById(R.id.inf_age_content);
        phoneTv = findViewById(R.id.inf_phone_content);
        emailTv = findViewById(R.id.inf_email_content);

        nameLayout = findViewById(R.id.inf_name_block);
        ageLayout = findViewById(R.id.inf_age_block);
        phoneLayout = findViewById(R.id.inf_phone_block);
        emailLayout = findViewById(R.id.inf_email_block);
    }

    /**
     * get user information from database and show
     */
    private void showInf() {
        AccessUsers accessUsers = new AccessUsers();
        usernameTv.setText("@" + AccessUsers.user.getUserName());
        nameTv.setText(AccessUsers.user.getName());
        ageTv.setText(AccessUsers.user.getAge() + "");
        phoneTv.setText(AccessUsers.user.getPhone());
        emailTv.setText(AccessUsers.user.getEmail());
    }

    /**
     * log out once clicked
     */
    private void setLogoutBtn() {
        logoutBtn.setOnClickListener(view -> {
            Intent intent = new Intent(ViewInfActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Success to logout", Toast.LENGTH_SHORT).show();
//            DatabaseController.defaultUser = null;
        });
    }

    /**
     * the four method below can jump to related page and change user information
     */

    private void changeName(){
        nameLayout.setOnClickListener(view -> startActivity(new Intent(ViewInfActivity.this, ChangeNameActivity.class)));
    }

    private void changeAge(){
        ageLayout.setOnClickListener(view -> startActivity(new Intent(ViewInfActivity.this, ChangeAgeActivity.class)));
    }

    private void changeEmail(){
        emailLayout.setOnClickListener(view -> startActivity(new Intent(ViewInfActivity.this, ChangeEmailActivity.class)));
    }

    private void changPhone(){
        phoneLayout.setOnClickListener(view -> startActivity(new Intent(ViewInfActivity.this, ChangePhoneActivity.class)));
    }

    // the user cannot go back in this page
    @Override
    public void onBackPressed(){}
}