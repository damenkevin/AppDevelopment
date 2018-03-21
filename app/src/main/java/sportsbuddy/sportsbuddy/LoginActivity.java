package sportsbuddy.sportsbuddy;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import static com.firebase.ui.auth.ui.email.CheckEmailFragment.TAG;

/**
 * Created by s165700 on 2/28/2018.
 */

/**
 * Login Activity, pretty much self-explanatory. Uses Firebase auth system to login. Does not use its own layout.
 * This activity is just used as a reference activity. It is called when the user opens the application, and when logging out.
 * This activity's only purpose is to manage the calls to the firebase auth system.
 * The provided firebase AuthSystem creates a layout on its own, this, ONLY provides the methods that need to be used for logging in.
 * CURRENT METHODS:
 * -google sign up
 * -email sign up with email verification.
 * TODO: Finish email sign up build
 * TODO: Add facebook sign up
 * TODO: Add Twitter sign up
 *
 */
public class LoginActivity extends Activity {
    //integer for reference to the RequestCode
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build());
        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.logo)
                        .build(),
                RC_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(!(user ==  null)){
                    Log.e("Verified =" , String.valueOf(user.isEmailVerified()));
                    if(user.isEmailVerified() == false){
                        user.sendEmailVerification()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(LoginActivity.this, VerifyEmailActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                });
                    } else {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

            } else {
                // Sign in failed, check response for error code
                // ...
            }
        }
    }
}
