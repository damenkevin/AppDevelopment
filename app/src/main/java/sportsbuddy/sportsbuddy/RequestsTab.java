package sportsbuddy.sportsbuddy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by s166928 on 27 Mar 2018.
 */

public class RequestsTab extends MatchesFragment {
    DatabaseHandler databaseHandler = DatabaseHandler.getDatabaseHandler();
    ArrayList<Request> requests = new ArrayList<Request>();
    ArrayList<AppUser> requestsUsers = new ArrayList<AppUser>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches_layout, container, false);
        gridView = (GridView) view.findViewById(R.id.gridView);

        RequestsAdapter requestsAdapter = new RequestsAdapter(this.getContext());
        gridView.setAdapter(requestsAdapter);
        databaseHandler.getRequests(RequestsTab.this);
        return view;
    }

    public void setRequests(ArrayList<Request> _requests, ArrayList<AppUser> _requestUsers){
        requests = _requests;
        requestsUsers = _requestUsers;
    }
}
