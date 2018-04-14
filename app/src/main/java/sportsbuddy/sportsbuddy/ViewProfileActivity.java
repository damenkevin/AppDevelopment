package sportsbuddy.sportsbuddy;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by s165700 on 3/19/2018.
 */

public class ViewProfileActivity extends AppCompatActivity {
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
        String profPic = userToDisplay.getprofilePicture();
        byte[] bytes = new byte[0];
        if (profPic != "") {
            try {
                bytes = Base64.decode(profPic,Base64.DEFAULT);
            } catch(Exception e) {
                e.getMessage();
            }
        }

        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        if (bitmap == null) {

        } else {
            profilePic.setImageBitmap(bitmap);
        }

    }

    public void updateUserInfo(AppUser _user){

    }

    public static void setUserToDisplay(AppUser appUser){
        userToDisplay = appUser;
    }
}
