package sportsbuddy.sportsbuddy;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by s165700 on 4/17/2018.
 */

public class MessagingActivity extends Activity {
    private static AppUser appUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages_layout);
        TextView friendName = (TextView) findViewById(R.id.friendNameText);
        friendName.setText(appUser.getName());
    }

    public static void setAppUser(AppUser _appUser){
        appUser = _appUser;
    }
}
