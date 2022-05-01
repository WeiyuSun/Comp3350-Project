/**
 * class: MeFragment
 * Author: Weiyu Sun
 *
 * Remark: show personal information page
 */
package comp3350.budgetingApp.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import comp3350.budgetingApp.R;
import comp3350.budgetingApp.controller.AccessUsers;


public class MeFragment extends Fragment {
    private Button logoutBtn;
    private TextView nameTv, usernameTv, ageTv, phoneTv, emailTv;
    private RelativeLayout nameLayout, ageLayout, phoneLayout, emailLayout;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceSate){
        return inflater.inflate(R.layout.fragment_me, container, false);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceSate){
        super.onViewCreated(view, saveInstanceSate);
        initView(view);
        showInf();

        // buttons to other pages(logout and, change personal information)
        setLogoutBtn();
        changeAge();
        changeName();
        changeEmail();
        changPhone();
    }


    /*
    initialize views
     */
    private void initView(View view) {
        logoutBtn = view.findViewById(R.id.inf_logout_btn);
        nameTv = view.findViewById(R.id.inf_name_content);
        usernameTv = view.findViewById(R.id.inf_username);
        ageTv = view.findViewById(R.id.inf_age_content);
        phoneTv = view.findViewById(R.id.inf_phone_content);
        emailTv = view.findViewById(R.id.inf_email_content);

        nameLayout = view.findViewById(R.id.inf_name_block);
        ageLayout = view.findViewById(R.id.inf_age_block);
        phoneLayout = view.findViewById(R.id.inf_phone_block);
        emailLayout = view.findViewById(R.id.inf_email_block);
    }

    /**
     * get user information from database and show
     */
    @SuppressLint("SetTextI18n")
    private void showInf() {
        usernameTv.setText("@" + AccessUsers.user.getUserName());
        nameTv.setText( AccessUsers.user.getName());
        ageTv.setText( AccessUsers.user.getAge() + "");
        phoneTv.setText( AccessUsers.user.getPhone());
        emailTv.setText( AccessUsers.user.getEmail());
    }

    /**
     * log out once clicked
     */
    private void setLogoutBtn() {
        logoutBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity() , LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getActivity(), "Success to logout", Toast.LENGTH_SHORT).show();
             AccessUsers.user = null;
        });
    }

    /**
     * the four method below can jump to related page and change user information
     */
    private void changeName(){
        nameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChangeNameActivity.class));
            }
        });
    }

    private void changeAge(){
        ageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChangeAgeActivity.class));
            }
        });
    }

    private void changeEmail(){
        emailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChangeEmailActivity.class));
            }
        });
    }

    private void changPhone(){
        phoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChangePhoneActivity.class));
            }
        });
    }
}
