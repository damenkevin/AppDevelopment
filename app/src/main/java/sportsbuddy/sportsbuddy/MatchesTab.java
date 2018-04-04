package sportsbuddy.sportsbuddy;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by s166928 on 27 Mar 2018.
 */

public class MatchesTab extends MatchesFragment {
    private static int bottomViewHeight;
    DatabaseHandler databaseHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches_layout, container, false);
        gridView = (GridView) view.findViewById(R.id.gridView);
        databaseHandler = DatabaseHandler.getDatabaseHandler();

        MatchesAdapter matchesAdapter = new MatchesAdapter(this.getContext());
        gridView.setAdapter(matchesAdapter);
        LinearLayout linearLayout = view.findViewById(R.id.matchesLayout);
        Log.e("Spacing is:", String.valueOf(bottomViewHeight));
        linearLayout.setPadding(0,0,0, bottomViewHeight);


        return view;
    }

    public void compareMatches(ArrayList<Match> serverMatches){
        ArrayList<Match> localMatches = databaseHandler.getMatchesFromLocal();
        //TODO: Check if server matches are not already in local matches.

    }

    public static void setBottomViewHeight(int i){
        bottomViewHeight = i;
    }
}
