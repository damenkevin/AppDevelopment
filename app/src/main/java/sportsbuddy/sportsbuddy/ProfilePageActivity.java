package sportsbuddy.sportsbuddy;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by s165700 on 2/28/2018.
 */

public class ProfilePageActivity extends Activity {
    private TextView nameText;
    private TextView genderText;
    private TextView ageText;
    private TextView aboutText;
    private DatabaseHandler databaseHandler;
    private UserInformation userInformation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        nameText = (TextView) findViewById(R.id.textNameProfile);
        genderText = (TextView) findViewById(R.id.textGenderProfile);
        ageText = (TextView) findViewById(R.id.textAgeProfile);
        aboutText = (TextView) findViewById(R.id.textAboutProfile);
        databaseHandler = DatabaseHandler.getDatabaseHandler();
        ImageButton editProfileButton = (ImageButton) findViewById(R.id.editProfileButton);
        setEditProfileButton(editProfileButton);
        updatePersonalProfile();

    }

    /**
     * Updates personal profile from databaseHandler(localDatabase)
     * @param
     */
    private void updatePersonalProfile(){
        userInformation = databaseHandler.getUserInfoFromLocal();
        nameText.setText(userInformation.getName());
        ageText.setText(userInformation.getAge());
        genderText.setText(userInformation.getGender());
        aboutText.setText(userInformation.getAbout());
        Log.e("asdasasdasdasdasdasd",userInformation.getAge());

    }

    /**
     * Sets an onClick listener to the editProfile button, creates a pop up(dialog)
     * then sends the changes to the DatabaseHandler
     * @param button
     */
    private void setEditProfileButton(ImageButton button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(ProfilePageActivity.this);
                dialog.setContentView(R.layout.editprofile_popup);
                final EditText editName = (EditText) dialog.findViewById(R.id.editProfileName);
                final EditText editGender = (EditText) dialog.findViewById(R.id.editProfileGender);
                final EditText editAge = (EditText) dialog.findViewById(R.id.editProfileAge);
                final EditText editAbout = (EditText) dialog.findViewById(R.id.editProfileAbout);
                Button buttonSaveChanges = (Button) dialog.findViewById(R.id.buttonSaveChanges);

                buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = editName.getText().toString().trim();
                        String gender = editGender.getText().toString().trim();
                        String age = editAge.getText().toString().trim();
                        String about = editAbout.getText().toString().trim();
                        databaseHandler.updateUserInfo(name, gender, age, about);
                        updatePersonalProfile();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

}
