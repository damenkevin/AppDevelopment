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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches_layout, container, false);
        gridView = (GridView) view.findViewById(R.id.gridView);
        databaseHandler = DatabaseHandler.getDatabaseHandler();
        matchesToDisplay = new ArrayList<Match>();

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

    /**
     * Called from DatabaseHandler
     * Receives the matches from the server and compares them to local matches to avoid match
     * duplication and save match status - accepted or declined
     * @param serverMatches
     */
    //TODO: Compare matches to requests to make sure you are not displaying matches that you already have a request received for.
    public void compareMatches(ArrayList<Match> serverMatches){
        ArrayList<Match> localMatches = databaseHandler.getMatchesFromLocal();
        ArrayList<Match> matchesToFillIn = new ArrayList<Match>();
        for(Match serverMatch : serverMatches){
            for(Match localMatch : localMatches){
                //Check if the match is stored in the local DB
                if(serverMatch.getUID().equals(localMatch.getUID()) &&
                        serverMatch.getSportingActivity().equals(localMatch.getSportingActivity()) &&
                        serverMatch.getDay().equals(localMatch.getDay()) &&
                        serverMatch.getTimeFromOverlap().equals(localMatch.getTimeFromOverlap()) &&
                        serverMatch.getTimeToOverlap().equals(localMatch.getTimeToOverlap())){
                    //Then the match is already stored in the local db
                    if(!localMatch.isHandled()){
                        //If the match is not handled already then add it to the display matches
                        //check if it is not already filled in
                        if(!matchesToDisplay.contains(serverMatch)){
                            matchesToDisplay.add(serverMatch);
                        }
                    }
                } else {
                    //check if it is already in
                    if(!matchesToDisplay.contains(serverMatch)){
                        //and display it
                        matchesToDisplay.add(serverMatch);
                    }
                }
            }
        }//If local db is empty then display all server matches and add them to the local DB
        if(localMatches.isEmpty()){
            matchesToFillIn = serverMatches;
            matchesToDisplay = serverMatches;
        }
        databaseHandler.fillInLocalMatches(matchesToFillIn);
        databaseHandler.getMatchUsers(matchesToDisplay, MatchesTab.this);
        displayMatches();
    }

    private void displayMatches(){
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

    public void updateUsersToDisplay(ArrayList<AppUser> appUsersToDisplay){
        matchesAdapter.updateMatchesList(appUsersToDisplay,matchesToDisplay);
        matchesAdapter.notifyDataSetChanged();
    }


    //Use this method when a match is accepted or removed.
    private void setMatchHandled(Match match, boolean isAccepted){
        databaseHandler.setMatchHandled(match);
        if(isAccepted){
            databaseHandler.sendMatchRequest(match);
        }
    }

    public void viewProfile(AppUser appUser){
        ViewProfileActivity.setUserToDisplay(appUser);
        Intent intent = new Intent(getActivity(), ViewProfileActivity.class);
        startActivity(intent);
    }
}
