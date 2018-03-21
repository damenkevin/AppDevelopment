package sportsbuddy.sportsbuddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by s165700 on 3/21/2018.
 */

public class VerifyEmailActivity extends Activity {
    boolean isVerified;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_email_layout);
        Button verifyButton = (Button) findViewById(R.id.buttonVerified);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().getCurrentUser().reload()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                isVerified = FirebaseAuth.getInstance().getCurrentUser().isEmailVerified();
                                Log.e("Verified:", String.valueOf(isVerified));
                                if(!isVerified){
                                    Toast.makeText(VerifyEmailActivity.this, "Please verify your email to continue", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(VerifyEmailActivity.this, "Success.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(VerifyEmailActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });

            }
        });
    }
}
