package sportsbuddy.sportsbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import bolts.Bolts;

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

        requestsAdapter = new RequestsAdapter(this.getContext(),requestsUsers,requests,this);
        gridView.setAdapter(requestsAdapter);
        databaseHandler.getRequests(RequestsTab.this);
        return view;
    }

    public void setRequests(ArrayList<Request> _requests, ArrayList<AppUser> _requestUsers){
        requests = _requests;
        requestsUsers = _requestUsers;
        requestsAdapter.updateRequests(requestsUsers,requests);
        requestsAdapter.notifyDataSetChanged();
    }

    public void visitProfile(AppUser appUser){
        ViewProfileActivity.setUserToDisplay(appUser);
        Intent intent = new Intent(getActivity(), ViewProfileActivity.class);
        startActivity(intent);
    }

    public void handleRequest(AppUser appUser, Request request, Boolean isAccepted){
        requests.remove(request);
        requestsUsers.remove(appUser);
        requestsAdapter.updateRequests(requestsUsers,requests);
        requestsAdapter.notifyDataSetChanged();
        databaseHandler.setRequestHandled(appUser, request, isAccepted);
        if(isAccepted){
            databaseHandler.addToFriends(appUser.getUID());
            Toast.makeText(getActivity(), "Accepted friend request with: " + appUser.getName(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Friend request denied!", Toast.LENGTH_SHORT).show();
        }
    }

}
