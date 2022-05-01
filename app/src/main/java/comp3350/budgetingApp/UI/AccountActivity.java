/**
 * class: AccountActivity
 * Author: Weiyu Sun
 *
 * Remark: hold the fragment(navigation bar)
 */
package comp3350.budgetingApp.UI;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import comp3350.budgetingApp.R;

public class AccountActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    protected static int HOME = 1;
    protected static int TOOLS = 2;
    protected static int ME = 3;
    private static int NOT_SELECTED = -1;
    private static int selected = NOT_SELECTED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        onBackPressed();

        bottomNavigationView = findViewById(R.id.bottomNav);

       /*
       default fragment is HOME, usually used when user login
        */
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HomeFragment()).commit();
        }

        /*
        choose fragment when user click button of nav bar
         */
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment = null;

                switch (menuItem.getItemId()) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.me:
                        fragment = new MeFragment();
                        break;
                    case R.id.tools:
                        fragment = new ToolsFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
                return true;
            }
        });
    }

    protected static void selectFragment(int selected){
        AccountActivity.selected = selected;
    }

    // the user cannot go back in this page
    @Override
    public void onBackPressed() {
    }
}