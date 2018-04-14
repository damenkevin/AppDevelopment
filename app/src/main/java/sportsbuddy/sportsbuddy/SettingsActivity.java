package sportsbuddy.sportsbuddy;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

/**
 * Created by s165700 on 3/5/2018.
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        Switch toggle = (Switch) findViewById(R.id.swLang);
        toggle.setChecked(LanguageHelper.getLocale(this.getResources()).equals("nl"));
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    LanguageHelper.changeLocale(SettingsActivity.this.getResources(), "en");

                } else {
                    LanguageHelper.changeLocale(SettingsActivity.this.getResources(), "nl");
                }
                doReload();
            }
        });

        Switch toggleThm = (Switch) findViewById(R.id.swThm);
        toggleThm.setChecked(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES);
        toggleThm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getApplication()).edit();
                edit.putBoolean("NIGHT_MODE", isChecked);
                edit.commit();
                doReload();
            }
        });

    }

    private void doReload() {
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}