package sportsbuddy.sportsbuddy;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by s165700 on 3/19/2018.
 */

public class ViewProfileActivity extends Activity {
    DatabaseHandler databaseHandler = DatabaseHandler.getDatabaseHandler();
    private String name;
    private String age;
    private String gender;
    private String about;
    private Bitmap image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewprofile_layout);
        ImageView profilePic = (ImageView) findViewById(R.id.viewProfilePic);
        TextView nameText = (TextView) findViewById(R.id.viewProfileName);
        TextView ageText = (TextView) findViewById(R.id.viewProfileAge);
        TextView genderText = (TextView) findViewById(R.id.viewProfileGender);
        TextView aboutText = (TextView) findViewById(R.id.viewProfileAbout);
        Button buttonSendFriendRequest = (Button) findViewById(R.id.buttonAddToFriends);
        Button buttonSendMessage = (Button) findViewById(R.id.buttonSendMessage);

        buttonSendFriendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Open a new messaging activity or something or send an email or something Í don't know.
            }
        });
    }

    public void updateUserInfo(AppUser _user){

    }
}
