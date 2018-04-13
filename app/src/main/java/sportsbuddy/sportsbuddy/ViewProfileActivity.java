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

    private static AppUser userToDisplay;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewprofile_layout);
        ImageView profilePic = (ImageView) findViewById(R.id.viewProfilePic);
        TextView nameText = (TextView) findViewById(R.id.viewProfileName);
        TextView ageText = (TextView) findViewById(R.id.viewProfileAge);
        TextView genderText = (TextView) findViewById(R.id.viewProfileGender);
        TextView aboutText = (TextView) findViewById(R.id.viewProfileAbout);
        nameText.setText(userToDisplay.getName());
        ageText.setText(userToDisplay.getAge());
        genderText.setText(userToDisplay.getGender());
        aboutText.setText(userToDisplay.getAbout());

    }

    public void updateUserInfo(AppUser _user){

    }

    public static void setUserToDisplay(AppUser appUser){
        userToDisplay = appUser;
    }
}
