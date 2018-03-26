package sportsbuddy.sportsbuddy;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s165700 on 2/28/2018.
 */

public class ProfilePageActivity extends Activity implements OnItemSelectedListener{
    private TextView nameText;
    private TextView ageText;
    private TextView aboutText;
    private DatabaseHandler databaseHandler;
    private UserInformation userInformation;
    String gender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        nameText = (TextView) findViewById(R.id.textNameProfile);
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

                // implement spinner element
                Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner);

                // set click listener for spinner
                spinner.setOnItemSelectedListener(ProfilePageActivity.this);

                // add elements for the spinner
                List<String> genderList = new ArrayList<String>();
                genderList.add("Male");
                genderList.add("Female");

                // create an adapter for the spinner
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, genderList);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //set the data adapter to the spinner
                spinner.setAdapter(spinnerAdapter);
                final EditText editName = (EditText) dialog.findViewById(R.id.editProfileName);
                final EditText editAge = (EditText) dialog.findViewById(R.id.editProfileAge);
                final EditText editAbout = (EditText) dialog.findViewById(R.id.editProfileAbout);
                Button buttonSaveChanges = (Button) dialog.findViewById(R.id.buttonSaveChanges);

                buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = editName.getText().toString().trim();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // when selecting a spinner item
        gender = parent.getItemAtPosition(position).toString().trim();
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

}
