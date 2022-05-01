/**
 * class: ChangeEmailActivity
 * Author: Weiyu Sun
 *
 * Remark: get new name from user and store it in database
 */
package comp3350.budgetingApp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import comp3350.budgetingApp.controller.AccessUsers;
import comp3350.budgetingApp.R;

public class ChangeNameActivity extends AppCompatActivity {

    private Button update;
    private EditText changeNameEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);

        initView();
        setUpdate();
    }

    private void initView(){
        update = findViewById(R.id.change_inf_btn);
        changeNameEt = findViewById(R.id.change_name_et);
    }


    /**
     * store the new mame and goes to ViewInfActivity
     *
     * if name invalid, send msg to user.
     */
    private void setUpdate(){
        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String newName = changeNameEt.getText().toString();

                if(UIChecker.isNameValid(newName, ChangeNameActivity.this)){
                    AccessUsers accessUsers = new AccessUsers();
                    accessUsers.updateSingleEntityUserProfile(AccessUsers.user.getUserName(), "name", newName);
                    AccessUsers.user = accessUsers.getUser(AccessUsers.user.getUserName());
                    Toast.makeText(ChangeNameActivity.this, "Success to change name", Toast.LENGTH_SHORT).show();

                    Log.d("new name: ", AccessUsers.user.getName());
                    startActivity(new Intent(ChangeNameActivity.this, AccountActivity.class));
                }
            }
        });
    }
}