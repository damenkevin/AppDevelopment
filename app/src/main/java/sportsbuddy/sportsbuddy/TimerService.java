package sportsbuddy.sportsbuddy;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

//notification imports
import android.support.v4.app.NotificationCompat;
import android.view.View;

import java.util.ArrayList;

public class TimerService extends IntentService {

    NotificationCompat.Builder notification;
    private static final int uniqueID = 465615; //the notification needs an unique ID to link to this app

    NotificationChannel mChannel; //for android 8 and above a channel is MANDATORY
    public static final String channelID = "my_channel_id465132465";

    //the arrayLists where the matches get stored
    private ArrayList<Match> matches;
    private ArrayList<Match> oldMatches;

    private ArrayList<String> smatches;
    private ArrayList<String> soldMatches;



    public TimerService() {
        super("Timer Service");
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.v("timer", "Timer service has started.");

        //this line can only be executed on android 8 or higher, so it has to be in the if
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            mChannel = new NotificationChannel(channelID, "MyChannel", NotificationManager.IMPORTANCE_HIGH);
        }

        notification = new NotificationCompat.Builder(this, channelID); //maybe the channel will give errors with android versions below 8,
        //so then make an if statement, that runs this line without channelID if android version below 8

        //here the matches and old matches arrays need to get filled with the current matches. (so the comparison can then start)



        //temporarily
        smatches = new ArrayList<>(0);
        setOldMatches();

    }

    public void createNotification(){
        //build the notification\
        notification = new NotificationCompat.Builder(this);
        notification.setSmallIcon(R.drawable.matches);
        notification.setTicker("This is the ticker"); //idk what the ticker does tho
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("New match or request!"); //these have to be translatable to dutch
        notification.setContentText("Click to check it out!");
        notification.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notification.setChannelId(channelID); //TRY IF THIS WORKS ON ANDROID LOWER THAN 8
        notification.setAutoCancel(true);

        //when the user clicks the notification the user gets sent to the main activity
        Intent intent = new Intent(this, MainActivity.class);//sends the user to the main activity
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        //build notification and issues it
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //this has to be iff'd. the line can not be executed on android versions below 8
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            nm.createNotificationChannel(mChannel);
        }
        nm.notify(uniqueID, notification.build());
    }

    //pulls the matches from the database
    public void pullMatches(){
        smatches.add("1");
        smatches.add("2");
        smatches.add("3");
        smatches.add("4");
    }


    public void setOldMatches(){
        //pulls all the current matches (again)
        pullMatches();

        //set the current matches to the old Matches
        soldMatches = new ArrayList<>(smatches);
    }

    public void test(int i){
        if (i > 1){
            smatches.add("6");
        }
        if (i > 2){
            smatches.add("7");
        }
        if (i > 4){
            smatches.add("10");
        }
        if (i > 8){
            smatches.add("11");
        }
    }

    public void test2(int i){
        if (i > 1){
            soldMatches.add("6");
        }
        if (i > 2){
            soldMatches.add("7");
        }
        if (i > 4){
            soldMatches.add("10");
        }
        if (i > 8){
            soldMatches.add("11");
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //for(int i = 0 ; i < 5; i++){
        int i = 0;
        boolean timerOn = true;
        while (timerOn){
            Log.v("timer", "i = " + i); //just shows the i in the log
            i++;
            test2(i-1);
            //here the program needs to check if there are new matches or requests, if so, then run createNotification()
            pullMatches();
            test(i);

            //remove all the old Matches from the arraylist, to only get a list of the current matches
            smatches.removeAll(soldMatches);
            //if there are new matches create a notification and add the matches to the old matches array
            if (smatches.size() > 0 ){
                //create the notification
                createNotification();
                Log.v("timer", String.valueOf(smatches));
                //add the new matches to the old matches arrayList
                setOldMatches();
            }


            try{
                Thread.sleep(5000);
            } catch (Exception e) {
                timerOn = false;
            }

            //just here so i doesn't get too big
            if (i == 10){
                i = 0;
            }
        }
    }
}
