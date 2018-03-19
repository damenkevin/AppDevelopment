package sportsbuddy.sportsbuddy;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by s165700 on 3/19/2018.
 */

/**
 * Private variables to hold information about the databases.
 * Used for easier communication between server and local database.
 * Methods for database communication.
 * NOTE: All interaction with any of the databases should be done through this class to keep things consistent.
 * You should not call methods directly from SQLiteHelper class and should not create any more instances of it.
 */
public class DatabaseHandler {
    private static boolean isSetUp = false;
    private static DatabaseHandler databaseHandler = new DatabaseHandler();

    private static FirebaseDatabase database;
    private static FirebaseUser firebaseUser;
    private static DatabaseReference timeTableRef;

    private static SQLiteHelper sqLiteHelper;

    //To make sure only one instance of the DatabaseHandler is created
    protected static DatabaseHandler getDatabaseHandler(){
        if(!isSetUp){
            database = FirebaseDatabase.getInstance();
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            timeTableRef = database.getReference("TimeTableSlot");
            isSetUp = true;
        }

        return databaseHandler;
    }

    /*
     * <--- Helper methods START --->
     */

    /*
     * <--- Helper methods END --->
     */

    /*
     * <--- Database management methods START --->
     */

    /**
     * Adds a new Timeslot to the server database
     */
    public static void addNewTimeSlotToServerDatabase(String sport, String day, String timeFrom, String timeTo){
        //Create a timetable slot in the server database with a unique ID
        DatabaseReference newTimeTableSlot = timeTableRef.push().getRef();
        //Add the unique userID which is final and consistent with the database
        newTimeTableSlot.child("User").setValue(firebaseUser.getUid());
        //Add the rest of the slot information to an Event child with corresponding names
        newTimeTableSlot.child("Event").child("Activity").setValue(sport);
        newTimeTableSlot.child("Event").child("Day").setValue(day);
        newTimeTableSlot.child("Event").child("TimeFrom").setValue(timeFrom);
        newTimeTableSlot.child("Event").child("TimeTo").setValue(timeTo);
        //Insert into the local database
        sqLiteHelper.insertTimeTableSlotDetails(newTimeTableSlot.getKey(),sport,day,timeFrom,timeTo);
    }

    /**
     * Checks for matches of a specific type
     * @param activity
     * @param day
     * @param timeFrom
     * @param timeTo
     */
    public static void checkForMatches(final String activity,final String day, final String timeFrom, final String timeTo){
        timeTableRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //If the current event we are looking at is not the same user
                    if(!snapshot.child("User").getValue().equals(firebaseUser.getUid())){
                        if(snapshot.child("Event").child("Activity").getValue().equals(activity)){
                            if(snapshot.child("Event").child("Day").getValue().equals(day)){
                                //TODO: Compare to values from local database
                                if(Integer.valueOf(snapshot.child("Event").child("TimeFrom").getValue().toString()) < 10){

                                }
                            }
                        }
                    }
                }
                timeTableRef.addListenerForSingleValueEvent(null);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /*
     * <--- Database management methods END --->
     */

    /*
     * <--- Getters and setters START --->
     */

    public static void setSqLiteHelper(SQLiteHelper _sqLiteHelper){
        sqLiteHelper = _sqLiteHelper;
        sqLiteHelper.queryData
                ("CREATE TABLE IF NOT EXISTS Slots(Id INTEGER PRIMARY KEY AUTOINCREMENT, slotID VARCHAR, activity VARCHAR, day VARCHAR, timeFrom VARCHAR, timeTo VARCHAR)");

    }
    public static FirebaseDatabase getDatabase() {
        return database;
    }

    public static void setDatabase(FirebaseDatabase database) {
        DatabaseHandler.database = database;
    }

    public static DatabaseReference getTimeTableRef() {
        return timeTableRef;
    }

    public static void setTimeTableRef(DatabaseReference timeTableRef) {
        DatabaseHandler.timeTableRef = timeTableRef;
    }

    public static FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public static void setFirebaseUser(FirebaseUser firebaseUser) {
        DatabaseHandler.firebaseUser = firebaseUser;
    }


    /*
     * <--- Gertters and setters END --->
     */
}
