/**
 * class: ChangePhoneActivity
 * Author: Weiyu Sun
 * <p>
 * Remark: get new phone number from user and store it in database
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

public class ChangePhoneActivity extends AppCompatActivity {

    private Button updateBtn;
    private EditText changePhoneEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);

        initView();
        setUpdateBtn();
    }

    private void initView() {
        updateBtn = findViewById(R.id.change_inf_btn);
        changePhoneEt = findViewById(R.id.change_phone_et);
    }

    /**
     * store the new phone and goes to ViewInfActivity
     *
     * if phone invalid, send msg to user.
     */
    private void setUpdateBtn() {
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPhone = changePhoneEt.getText().toString();

                if (UIChecker.isPhoneValid(newPhone, ChangePhoneActivity.this)) {
                    AccessUsers accessUsers = new AccessUsers();
                    accessUsers.updateSingleEntityUserProfile(AccessUsers.user.getUserName(), "phone", newPhone);
                    AccessUsers.user = accessUsers.getUser(AccessUsers.user.getUserName());
                    Toast.makeText(ChangePhoneActivity.this, "Success to change phone number", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ChangePhoneActivity.this, AccountActivity.class));
                }
            }
        });
    }
}
