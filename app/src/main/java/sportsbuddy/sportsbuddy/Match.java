package sportsbuddy.sportsbuddy;

/**
 * Created by s165700 on 3/27/2018.
 */

public class Match {
    private String UIDone;
    private String UIDtwo;
    private String sportingActivity;
    private String timeFromOverlap;
    private String timeToOverlap;

    public Match(String UIDone, String UIDtwo, String sportingActivity, String timeFromOverlap, String timeToOverlap) {
        this.UIDone = UIDone;
        this.UIDtwo = UIDtwo;
        this.sportingActivity = sportingActivity;
        this.timeFromOverlap = timeFromOverlap;
        this.timeToOverlap = timeToOverlap;
    }

    public String getUIDone() {
        return UIDone;
    }

    public String getUIDtwo() {
        return UIDtwo;
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
}
