package sportsbuddy.sportsbuddy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by s166928 on 27 Mar 2018.
 */

public class MatchesTab extends MatchesFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches_layout, container, false);
        gridView = (GridView) view.findViewById(R.id.gridView);

        MatchesAdapter matchesAdapter = new MatchesAdapter(this.getContext());
        gridView.setAdapter(matchesAdapter);

        return view;
    }
}
