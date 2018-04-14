package sportsbuddy.sportsbuddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

/**
 * Created by s165700 on 2/28/2018.
 */

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /**Used for dark theme**/
        boolean nm = PreferenceManager.getDefaultSharedPreferences(this.getApplication()).getBoolean("NIGHT_MODE", false);
        AppCompatDelegate.setDefaultNightMode(nm ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                        Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
