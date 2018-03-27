package sportsbuddy.sportsbuddy;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Adapter;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by s165700 on 3/26/2018.
 */

public class FriendsActivity extends Activity {
    DatabaseHandler databaseHandler = DatabaseHandler.getDatabaseHandler();
    private ArrayList<String> userListIDS;
    private ArrayList<AppUser> userList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_layout);
        userList = new ArrayList<AppUser>();
        userListIDS = new ArrayList<String>();

        //------------------------
        GridView gridView = (GridView) findViewById(R.id.grid_friends);

        databaseHandler.getFriendsListIDS(userListIDS,this);

        FriendsAdapter adapter = new FriendsAdapter(FriendsActivity.this,R.layout.friends_layout, userList);
        gridView.setAdapter(adapter);
    }

    public void setFriendsListIDS(ArrayList<String> _userListIDS){
        userListIDS = _userListIDS;
        databaseHandler.getFriendsListUsers(userListIDS, userList, this);
    }

    public void setFriendsList(ArrayList<AppUser> _userList){
        userList = _userList;
    }
}
