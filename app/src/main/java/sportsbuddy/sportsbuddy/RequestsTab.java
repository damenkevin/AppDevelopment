package sportsbuddy.sportsbuddy;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
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
    RequestsAdapter requestsAdapter;
    ArrayList<Request> requests = new ArrayList<Request>();
    ArrayList<AppUser> requestsUsers = new ArrayList<AppUser>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches_layout, container, false);
        gridView = (GridView) view.findViewById(R.id.gridView);

        requestsAdapter = new RequestsAdapter(this.getContext(),requestsUsers,requests);
        gridView.setAdapter(requestsAdapter);
        databaseHandler.getRequests(RequestsTab.this);
        return view;
    }

    public void setRequests(ArrayList<Request> _requests, ArrayList<AppUser> _requestUsers){
        requests = _requests;
        requestsUsers = _requestUsers;
        requestsAdapter.updateRequests(requestsUsers,requests);
        requestsAdapter.notifyDataSetChanged();
        DebugRequests();
    }

    public void DebugRequests(){
        for(int i=0; i< requests.size(); i++){
            Log.e("Request From", requestsUsers.get(i).getName());
            Log.e("Level", requests.get(i).getLevel());
        }
    }
}
