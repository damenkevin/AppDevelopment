package sportsbuddy.sportsbuddy;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
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

    private FragmentTabHost tabHost;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tabHost = new FragmentTabHost(getActivity());
        tabHost.setup(getActivity(), getChildFragmentManager(), R.layout.matches_layout);

        Bundle arg1 = new Bundle();
        arg1.putInt("Arg for Frag1", 1);
        tabHost.addTab(tabHost.newTabSpec(getResources().getString(R.string.matches)).
                        setIndicator(getResources().getString(R.string.matches)),
                MatchesTab.class, arg1);

        Bundle arg2 = new Bundle();
        arg2.putInt("Arg for Frag2", 2);
        tabHost.addTab(tabHost.newTabSpec(getResources().getString(R.string.requests)).
                        setIndicator(getResources().getString(R.string.requests)),
                RequestsTab.class, arg2);


        return tabHost;
    }
}
