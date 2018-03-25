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
    public UserTimeTable(String activity, String day, String timeFrom, String timeTo) {
        this.activity = activity;
        this.day = day;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    private String activity;
    private String day;

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

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    private String timeFrom;
    private String timeTo;
}
