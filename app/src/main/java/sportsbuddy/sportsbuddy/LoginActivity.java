package sportsbuddy.sportsbuddy;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by s165700 on 2/28/2018.
 */

/**
 * Login Activity, pretty much self-explanatory. We could add login from social networks.
 *
 */
//TODO: Add Social login, Facebook Google etc...
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        Button loginButton = (Button) findViewById(R.id.buttonLogin);
        Button registerButton = (Button) findViewById(R.id.buttonRegister);
        final EditText usernameText = (EditText) findViewById(R.id.textUsername);
        final EditText passwordText = (EditText) findViewById(R.id.textPassword);



        //The login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginUsername = usernameText.getText().toString().trim();
                String loginPassword = passwordText.getText().toString().trim();
                //TODO: Check username and password from server and then approve
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //The register button
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getApplicationContext());
                dialog.setContentView(R.layout.register_dialog);
                EditText usernameRegText = (EditText) dialog.findViewById(R.id.textUsernameReg);
                EditText emailRegText = (EditText) dialog.findViewById(R.id.textEmailReg);
                final EditText passwordRegText = (EditText) dialog.findViewById(R.id.textPasswordReg);
                final EditText passwordRegRepText = (EditText) dialog.findViewById(R.id.textPasswordRegRep);

                Button tryRegButton = (Button) dialog.findViewById(R.id.register);

                tryRegButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //If the two given passwords match
                        if(passwordRegText.getText().toString().trim().equals(passwordRegRepText.getText().toString().trim())){
                            // TODO: Check if username is free and send an approval email to the given one and Register user in the server database.
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "The passwords do not match", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();


            }
        });
    }
}
