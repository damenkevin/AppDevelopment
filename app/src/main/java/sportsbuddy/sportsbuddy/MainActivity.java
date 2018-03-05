package sportsbuddy.sportsbuddy;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }


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
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        }
        if(id == R.id.action_profile){
            Intent intent = new Intent(getApplicationContext(), ProfilePageActivity.class);
            startActivity(intent);
        }
        /**
         * Creates an Alert dialog asking to confirm log out.
         */
        if(id == R.id.action_log_out){
            CharSequence[] items = {"Confirm", "Cancel"};
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getApplicationContext());
            alertDialog.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(i == 0){
                        //Confirm pressed
                        //TODO: Log out and remove access token when this is implemented
                        dialogInterface.dismiss();
                    }else{
                        //Cancel pressed
                        dialogInterface.dismiss();
                    }
                }
            });
            alertDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            /**
             * We are not using these, please use the Fragment classes for the individual pages. :)
             */
            /**
             * The Matches Page
             */
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1){
                View rootView = inflater.inflate(R.layout.matches_layout, container, false);

                return rootView;
            }

            /**
             * The ssc info page
             */
             else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2){
                View rootView = inflater.inflate(R.layout.ssc_info_page, container, false);

                return rootView;
            }

            /**
             * The timetable page
             */
            else{
                View rootView = inflater.inflate(R.layout.timetable_page, container, false);

                return rootView;
            }
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    MatchesFragment matchesFragment = new MatchesFragment();
                    return matchesFragment;
                case 1:
                    SSCInfoFragment sscInfoFragment = new SSCInfoFragment();
                    return sscInfoFragment;
                case 2:
                    TimetableFragment timetableActivity = new TimetableFragment();
                    return  timetableActivity;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Matches";
                case 1:
                    return "SSC";
                case 2:
                    return "Timetable";
            }
            return null;
        }
    }
}
