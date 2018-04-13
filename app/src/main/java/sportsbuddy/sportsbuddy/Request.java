package sportsbuddy.sportsbuddy;

/**
 * Created by s165700 on 4/11/2018.
 */

public class Request {
    //The user id in server of the person you have a match with
    private String UID;
    private String sportingActivity;
    private String day;
    private String timeFromOverlap;
    private String timeToOverlap;
    private String level;

    //Indicates whether the match is already accepted/declined or not
    private boolean handled;

    public Request(String UID, String sportingActivity, String day, String timeFromOverlap, String timeToOverlap, boolean handled) {
        this.UID = UID;
        this.day = day;
        this.sportingActivity = sportingActivity;
        this.timeFromOverlap = timeFromOverlap;
        this.timeToOverlap = timeToOverlap;
        this.handled = handled;
    }


    public String getUID() {
        return UID;
    }

    public String getSportingActivity() {
        return sportingActivity;
    }

    public String getTimeFromOverlap() {
        return timeFromOverlap;
    }

    public String getTimeToOverlap() {
        return timeToOverlap;
    }

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
