package sportsbuddy.sportsbuddy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;

import java.util.ArrayList;

/**
 * Created by s166928 on 27 Mar 2018.
 */

/**
 * Short Explanation of algorithm for getting matches:
 * Get slots from local
 * For each slot get matches from server
 * Compare matches from server to matches already stored locally
 * Filter out handled matches(to avoid seeing old matches)
 * Store matches that are not already in local db in local DB with handled:false
 * Add all matches that are not handled in matchesToDisplay array
 * When a match is Accepted/Declined a call to setMatchHandled method must be made with
 * corresponding match and wheter it is accepted or declined.
 *
 */
public class MatchesTab extends MatchesFragment {
    private static int bottomViewHeight;
    DatabaseHandler databaseHandler;
    private ArrayList<Match> matchesToDisplay;
    private ArrayList<AppUser> appUsersToDisplay;
    MatchesAdapter matchesAdapter;
    private static ArrayList<Match> matches;
    private static ArrayList<AppUser> appUsers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches_layout, container, false);
        gridView = (GridView) view.findViewById(R.id.gridView);
        databaseHandler = DatabaseHandler.getDatabaseHandler();
        matchesToDisplay = new ArrayList<Match>();
        appUsersToDisplay = new ArrayList<AppUser>();

        matchesAdapter = new MatchesAdapter(this.getContext(), appUsersToDisplay, matchesToDisplay, MatchesTab.this);
        gridView.setAdapter(matchesAdapter);
        //TODO: make on item click listeners to the gridView from where one can view the match profile
        searchForMatches();
        return view;
    }

    /**
     * Gets the user timetable from the local DB and then sends it to the datbase handler to check for matches
     */
    private void searchForMatches(){
        ArrayList<UserTimeTable> userTimeTableArrayList = databaseHandler.getSlotsFromLocal();
        databaseHandler.checkForMatches(userTimeTableArrayList, this);
    }

    public void displayMatches(ArrayList<Match> matches){
        matchesToDisplay = matches;
        /**
         * Use this for debugging. It shows the matches you have.
         */
        //DEBUGGING START
        int i = 0;
        Log.e("Now showing", "Your Matches:");
        for(Match match : matchesToDisplay){
            Log.e("Match number ", String.valueOf(i));
            Log.e("Day ", match.getDay());
            Log.e("Sport ", match.getSportingActivity());
            Log.e("From ", match.getTimeFromOverlap());
            Log.e("To ", match.getTimeToOverlap());
            i++;
        }
        Log.e("Total of", String.valueOf(i) + " matches");
        //DEBUGGING END

    }

    public void updateMatches(ArrayList<AppUser> _appUsersToDisplay, ArrayList<Match> _matches){
        this.matchesToDisplay = _matches;
        this.appUsersToDisplay = _appUsersToDisplay;
        matchesAdapter.updateMatchesList(appUsersToDisplay,matchesToDisplay);
        matchesAdapter.notifyDataSetChanged();
        matches = _matches;
        appUsers = _appUsersToDisplay;
    }



    public void setMatchHandled(AppUser appUser, Match match, boolean isAccepted){
        appUsersToDisplay.remove(appUser);
        matchesToDisplay.remove(match);
        matchesAdapter.updateMatchesList(appUsersToDisplay,matchesToDisplay);
        matchesAdapter.notifyDataSetChanged();
        databaseHandler.setMatchHandled(match);
        if(isAccepted){
            databaseHandler.sendMatchRequest(match);
            Toast.makeText(getActivity(), "A request is sent to: " + appUser.getName(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Match has been deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewProfile(AppUser appUser){
        ViewProfileActivity.setUserToDisplay(appUser);
        Intent intent = new Intent(getActivity(), ViewProfileActivity.class);
        startActivity(intent);
    }

    public static ArrayList<Match> getMatches(){
        return matches;
    }

    public static ArrayList<AppUser> getAppUsers(){
        return appUsers;
    }
}
