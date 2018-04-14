package sportsbuddy.sportsbuddy;

import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

/**
 * Created by s163869 on 13-4-2018.
 */

public class LanguageHelper {

    public static void changeLocale(Resources res, String locale) {

        Configuration config;
        config = new Configuration(res.getConfiguration());

        switch (locale) {
            case "nl":
                config.locale = new Locale("nl");
            case "en":
                config.locale = new Locale("en");
        }
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    public static String getLocale(Resources res) {

        Configuration config;
        config = new Configuration(res.getConfiguration());

        return config.locale.getLanguage();
    }
}

