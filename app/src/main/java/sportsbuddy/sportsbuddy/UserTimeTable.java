package sportsbuddy.sportsbuddy;

/**
 * Created by s165700 on 3/19/2018.
 */

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Holds user info information
 * Used for easier communication between classes
 */
public class UserTimeTable {

    private String key;
    private String level;
    private String activity;
    private String day;
    private String timeFrom;
    private String timeTo;
    public UserTimeTable(String key,String level, String activity, String day, String timeFrom, String timeTo) {
        this.level = level;
        this.activity = activity;
        this.day = day;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.key = key;
    }


    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
