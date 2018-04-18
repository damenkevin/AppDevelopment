package sportsbuddy.sportsbuddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Adapter;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by s165700 on 3/26/2018.
 */

public class FriendsActivity extends AppCompatActivity {
    DatabaseHandler databaseHandler = DatabaseHandler.getDatabaseHandler();
    private ArrayList<String> userListIDS;
    private ArrayList<AppUser> userList;
    FriendsAdapter friendsAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_layout);
        userList = new ArrayList<AppUser>();
        userListIDS = new ArrayList<String>();

        //------------------------
        GridView gridView = (GridView) findViewById(R.id.grid_friends);
        friendsAdapter = new FriendsAdapter(FriendsActivity.this,R.layout.friends_layout, userList, FriendsActivity.this);
        gridView.setAdapter(friendsAdapter);
        databaseHandler.getFriendsListIDS(userListIDS,this);
    }

    public void setFriendsListIDS(ArrayList<String> _userListIDS){
        userListIDS = _userListIDS;
        databaseHandler.getFriendsListUsers(userListIDS, userList, this);
    }

    public void setFriendsList(ArrayList<AppUser> _userList){
        Log.e("Upadting", "Adapter");
        userList = _userList;
        friendsAdapter.upadateFriendsList(userList);
        friendsAdapter.notifyDataSetChanged();
    }

    public void sendText(AppUser appUser){
        MessagingActivity messagingActivity = new MessagingActivity();
        MessagingActivity.setAppUser(appUser);
        Intent intent = new Intent(FriendsActivity.this, MessagingActivity.class);
        startActivity(intent);
    }
}
