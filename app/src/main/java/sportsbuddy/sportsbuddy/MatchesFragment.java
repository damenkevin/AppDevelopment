package sportsbuddy.sportsbuddy;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by s165700 on 2/28/2018.
 */

/**
 * Basic composition: 2 Buttons 1 GridView with 2 Adapters.
 * When either of the buttos is pressed it changes the adapter of the gridView.
 * For example: When the buttonViewRequests button is clicked the gridView changes its adapter from
 * matchesAdapter to requestsAdapter. The adapter on its turn loads the information, if any, and displays it in the gridView.
 */
public class MatchesFragment extends Fragment {

    MatchesAdapter matchesAdapter;
    RequestsAdapter requestsAdapter;
    GridView gridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches_layout, container, false);

        Button buttonViewRequests = (Button) view.findViewById(R.id.buttonViewRequests);
        Button buttonViewMatches = (Button) view.findViewById(R.id.buttonViewMatches);
        gridView = (GridView) view.findViewById(R.id.gridView);


        buttonViewRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gridView.setAdapter(matchesAdapter);
            }
        });

        buttonViewMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gridView.setAdapter(requestsAdapter);
            }
        });

        return view;
    }
}
