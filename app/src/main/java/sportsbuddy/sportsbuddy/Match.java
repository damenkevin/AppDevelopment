package sportsbuddy.sportsbuddy;

/**
 * Created by s165700 on 3/27/2018.
 */

public class Match {
    //The user id in server of the person you have a match with
    private String matchUser1;
    private String matchUser2;
    private String levelUser1;
    private String levelUser2;
    private String sportingActivity;
    private String day;
    private String timeFromOverlap;
    private String timeToOverlap;
    //Indicates whether the match is already accepted/declined or not
    private boolean handled;

    public Match(String matchUser1, String matchUser2,String levelUser1, String levelUser2, String sportingActivity, String day, String timeFromOverlap, String timeToOverlap, boolean handled) {
        this.matchUser1 = matchUser1;
        this.matchUser2 = matchUser2;
        this.levelUser1 = levelUser1;
        this.levelUser2 = levelUser2;
        this.day = day;
        this.sportingActivity = sportingActivity;
        this.timeFromOverlap = timeFromOverlap;
        this.timeToOverlap = timeToOverlap;
        this.handled = handled;
    }

    public String getMatchUser1() {
        return matchUser1;
    }

    public void setMatchUser1(String matchUser1) {
        this.matchUser1 = matchUser1;
    }

    public String getMatchUser2() {
        return matchUser2;
    }

    public void setMatchUser2(String matchUser2) {
        this.matchUser2 = matchUser2;
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

    public String getLevelUser1() {
        return levelUser1;
    }

    public void setLevelUser1(String levelUser1) {
        this.levelUser1 = levelUser1;
    }

    public String getLevelUser2() {
        return levelUser2;
    }

    public void setLevelUser2(String levelUser2) {
        this.levelUser2 = levelUser2;
    }


}
