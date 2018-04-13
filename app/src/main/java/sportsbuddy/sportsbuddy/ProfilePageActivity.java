package sportsbuddy.sportsbuddy;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by s165700 on 2/28/2018.
 */

public class ProfilePageActivity extends Activity implements OnItemSelectedListener,
        Imageutils.ImageAttachmentListener{
    private TextView nameText;
    private TextView ageText;
    private TextView aboutText;
    private TextView genderText;
    private DatabaseHandler databaseHandler;
    private AppUser appUser;
    String gender;
    ImageView iv_attachment;
    private Bitmap bitmap;
    private String file_name;
    Imageutils imageutils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        nameText = (TextView) findViewById(R.id.textNameProfile);
        ageText = (TextView) findViewById(R.id.textAgeProfile);
        genderText = (TextView) findViewById(R.id.textGenderProfile);
        aboutText = (TextView) findViewById(R.id.textAboutProfile);
        databaseHandler = DatabaseHandler.getDatabaseHandler();
        ImageButton editProfileButton = (ImageButton) findViewById(R.id.editProfileButton);
        setEditProfileButton(editProfileButton);

        imageutils =new Imageutils(this);

        iv_attachment=(ImageView)findViewById(R.id.imageView);

        iv_attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageutils.imagepicker(1);
            }
        });
        updatePersonalProfile();

    }

    /**
     * Updates personal profile from databaseHandler(localDatabase)
     * @param
     */
    private void updatePersonalProfile(){
        appUser = databaseHandler.getUserInfoFromLocal();
        nameText.setText(appUser.getName());
        ageText.setText(appUser.getAge());
        aboutText.setText(appUser.getAbout());
        genderText.setText(appUser.getGender());
        String profPic = appUser.getprofilePicture();
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
            iv_attachment.setImageBitmap(bitmap);
        }
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
        ((TextView) view).setTextColor(Color.BLACK);
        gender = parent.getItemAtPosition(position).toString().trim();
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        imageutils.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        imageutils.request_permission_result(requestCode, permissions, grantResults);
    }

    @Override
    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
        this.bitmap=file;
        this.file_name=filename;
        if (file.getWidth() >= file.getHeight()){

            file = Bitmap.createBitmap(
                    file,
                    file.getWidth()/2 - file.getHeight()/2,
                    0,
                    file.getHeight(),
                    file.getHeight()
            );

        }else{

            file = Bitmap.createBitmap(
                    file,
                    0,
                    file.getHeight()/2 - file.getWidth()/2,
                    file.getWidth(),
                    file.getWidth()
            );
        }
        iv_attachment.setImageBitmap(file);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        file.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        String profilePictureString = Base64.encodeToString(byteArray, Base64.DEFAULT);
        databaseHandler.updateProfilePicture(profilePictureString);

        String path =  Environment.getExternalStorageDirectory() + File.separator + "ImageAttach" + File.separator;
        imageutils.createImage(file,filename,path,false);

    }


}
