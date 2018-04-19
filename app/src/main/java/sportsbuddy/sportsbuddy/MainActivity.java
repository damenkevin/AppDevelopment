package sportsbuddy.sportsbuddy;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * Hey guys! Please always leave good comments to your code, so it's easy for others to understand :)
 * Also please do not commit anything unless you are 100% sure it works!
 */

/**
 * This class is pretty much finished, please do not modify unless you know what you are doing!
 */

/**
 * Handles the ViewPagerAdapter. The 3 fragments: MatchesFragment, SSCInfoFragment and Timetable fragment should be modified from their
 * separate classes. Please do not make changes to them from here to keep things consistent :)
 */
public class MainActivity extends AppCompatActivity {
    private static final String SELECTED_ITEM = "menu_matches";
    private BottomNavigationView mBottomNav;
    private Toolbar toolbar;
    private int mSelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set up local database and init databaseHandler
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getApplicationContext(), "LocalDB.sqlite",null,1);
        DatabaseHandler databaseHandler = DatabaseHandler.getDatabaseHandler();
        databaseHandler.setSqLiteHelper(sqLiteHelper);

        mBottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mBottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.setTitle(getResources().getString(R.string.menu_matches));
        loadFragment(new MatchesFragment());
        mSelectedItem = R.id.menu_matches;


        //starts the timer in the background that checks for new matches
        Intent intent = new Intent(this, TimerService.class);
        startService(intent);



    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            String string;
            FragmentManager fragmentManager = getSupportFragmentManager();
            switch (item.getItemId()) {
                case R.id.menu_matches:
                    mSelectedItem = R.id.menu_matches;
                    string = getResources().getString(R.string.menu_matches);
                    toolbar.setTitle(string);
                    fragment = new MatchesFragment();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment).commit();
                    return true;
                case R.id.menu_sscinfo:
                    mSelectedItem = R.id.menu_matches;
                    string = getResources().getString(R.string.menu_sscinfo);
                    toolbar.setTitle(string);
                    fragment = new SSCInfoFragment();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment).commit();
                    return true;
                case R.id.menu_timetable:
                    mSelectedItem = R.id.menu_matches;
                    string = getResources().getString(R.string.menu_timetable);
                    toolbar.setTitle(string);
                    fragment = new TimetableFragment();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, fragment).commit();
                    return true;
            }

            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Which of the menu buttons has been pressed
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        if(id == R.id.action_profile){
            Intent intent = new Intent(MainActivity.this, ProfilePageActivity.class);
            startActivity(intent);
        }
        if(id == R.id.action_friends_list){
            Intent intent = new Intent(MainActivity.this, FriendsActivity.class);
            startActivity(intent);
        }
        /**
         * Creates an Alert dialog asking to confirm log out.
         */
        if(id == R.id.action_log_out){
            //TODO: It's good enough for initial implementation but later switch it to alert dialog and figure out why alertDialog does not work with the basic theme.
            final Dialog logOutDialog = new Dialog(this);
            logOutDialog.setContentView(R.layout.singout_popup);
            final Button logoutBtn = (Button) logOutDialog.findViewById(R.id.btn_log_out);
            Button logoutCancelBtn = (Button) logOutDialog.findViewById(R.id.btn_log_out_cancel);
            logoutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AuthUI.getInstance()
                            .signOut(MainActivity.this)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                public void onComplete(@NonNull Task<Void> task) {
                                    logOutDialog.dismiss();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                }
                            });
                }
            });

            logoutCancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    logOutDialog.dismiss();
                }
            });
            logOutDialog.getWindow().setLayout((int) (getResources().getDisplayMetrics().widthPixels*0.9),
                    (int) (getResources().getDisplayMetrics().heightPixels*0.3));
            logOutDialog.show();


        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            startActivity(intent);
            finish();
            System.exit(0);
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }

    }
}