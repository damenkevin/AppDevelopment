package sportsbuddy.sportsbuddy;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

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
    private static DatabaseReference usersRef;
    private static SQLiteHelper sqLiteHelper;

    private static UserInformation userInformation;
    private static List<UserTimeTable> userTimeTable;


    //To make sure only one instance of the DatabaseHandler is created
    protected static DatabaseHandler getDatabaseHandler() {
        if (!isSetUp) {
            database = FirebaseDatabase.getInstance();
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            timeTableRef = database.getReference("TimeTableSlot");
            usersRef = database.getReference("UsersInfo");
            userTimeTable = new ArrayList<>();
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
    public static void addNewTimeSlotToServerDatabase(String sport, String day, String timeFrom, String timeTo) {
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
        sqLiteHelper.insertTimeTableSlotDetails(newTimeTableSlot.getKey(), sport, day, timeFrom, timeTo);
    }

    /**
     * Checks for matches of a specific type
     *
     * @param activity
     * @param day
     * @param timeFrom
     * @param timeTo
     */
    public static void checkForMatches(final String activity, final String day, final String timeFrom, final String timeTo) {
        timeTableRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //If the current event we are looking at is not the same user
                    if (!snapshot.child("User").getValue().equals(firebaseUser.getUid())) {
                        if (snapshot.child("Event").child("Activity").getValue().equals(activity)) {
                            if (snapshot.child("Event").child("Day").getValue().equals(day)) {
                                //TODO: Compare to values from local database
                                if (Integer.valueOf(snapshot.child("Event").child("TimeFrom").getValue().toString()) < 10) {

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


    //Upadates the server and local database with the new user info
    public static void updateUserInfo(String name, String gender, String age, String about) {
        usersRef.child(firebaseUser.getUid()).child("Name").setValue(name);
        usersRef.child(firebaseUser.getUid()).child("Gender").setValue(gender);
        usersRef.child(firebaseUser.getUid()).child("Age").setValue(age);
        usersRef.child(firebaseUser.getUid()).child("About").setValue(about);
        sqLiteHelper.updatePersonalProfileData(FirebaseAuth.getInstance().getUid(), name, age, gender, about);
    }

    //Gets a user from the online database. Not used ATM
    //TODO: Adapt and use this when viewing other profiles
    public void getUserInfoFromServer(String uID) {
        usersRef.child(uID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    if (snapshot.getKey().equals("Name")) {
                        userInformation.setName(snapshot.getValue().toString());
                    }
                    if (snapshot.getKey().equals("Age")) {
                        userInformation.setAge(snapshot.getValue().toString());
                    }
                    if (snapshot.getKey().equals("Gender")) {
                        userInformation.setGender(snapshot.getValue().toString());
                    }
                    if (snapshot.getKey().equals("About")) {
                        userInformation.setAbout(snapshot.getValue().toString());
                    }
                }
                usersRef.removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //gets user time table from database.
    public static void getUserTimeTableFromServer(final Callback<List<UserTimeTable>> callback) {
        timeTableRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //creates an empty list
                userTimeTable = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    if (Objects.equals(snapshot.child("User").getValue(), firebaseUser.getUid())) {

                        String activity = snapshot.child("Event").child("Activity").getValue().toString();
                        String day = snapshot.child("Event").child("Day").getValue().toString();
                        String timeFrom = snapshot.child("Event").child("TimeFrom").getValue().toString();
                        String timeTo = snapshot.child("Event").child("TimeTo").getValue().toString();

                        UserTimeTable timeSlot = new UserTimeTable(activity, day, timeFrom, timeTo);

                        userTimeTable.add(timeSlot);
                    }
                }
                callback.call(userTimeTable);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }


    //returns an instance of the user information filled with data from the local database
    public static UserInformation getUserInfoFromLocal() {
        String name = "blank";
        String age = "blank";
        String gender = "blank";
        String about = "blank";
        Cursor cursor = sqLiteHelper.getData("SELECT COUNT(*) FROM Profile");
        boolean empty = true;
        if (cursor != null && cursor.moveToFirst()) {
            empty = (cursor.getInt(0) == 0);
        }
        cursor.close();
        Log.d("Reached", String.valueOf(empty));
        if (empty) {
            sqLiteHelper.insertPersonalProfileInfo(FirebaseAuth.getInstance().getUid(), "Blank", "0", "Blank", "Blank");
            Log.d("Filled The table", "");
        } else {
            cursor = sqLiteHelper.getData("SELECT * FROM PROFILE");
            while (cursor.moveToNext()) {
                name = cursor.getString(2);
                age = cursor.getString(3);
                gender = cursor.getString(4);
                about = cursor.getString(5);
                Log.d("Name is: ", name);
                Log.d("age is: ", age);
                Log.d("gender is: ", gender);
                Log.d("about is: ", about);
            }
        }
        userInformation = new UserInformation(FirebaseAuth.getInstance().getUid(), name, age, gender, about);
        return userInformation;
    }

    /**
     * This method is used to get the friends id's from the database in the FriendsActivity
     * After the static method setFriendsList in FriendsActivity is called
     * @param userList
     */
    public void getFriendsListIDS(final ArrayList<String> userList, final FriendsActivity activity){
        DatabaseReference friendsRef = database.getReference("FriendsList").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        friendsRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datum : dataSnapshot.getChildren()){
                    if(!userList.contains(datum.getKey())){
                        userList.add(datum.getKey());
                    }
                }
                activity.setFriendsListIDS(userList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * This method updates the Friends list containing of AppUsers from the Database, comparing it to the ArrayList of string of users
     * @param userListIDS
     * @param userList
     * @param activity
     */
    public void getFriendsListUsers(final ArrayList<String> userListIDS, final ArrayList<AppUser> userList, final  FriendsActivity activity){
        DatabaseReference usersRef = database.getReference("UsersInfo");
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datum : dataSnapshot.getChildren()){
                    Log.e("Checking:",String.valueOf(datum.child("Name").getValue()));
                    AppUser user = new AppUser(dataSnapshot.getKey(),null, null);
                    //If the user's id is in the friends list
                    if(userListIDS.contains(datum.getKey())){
                        user.setName(String.valueOf(datum.child("Name").getValue()));
                        user.setUID(datum.getKey());
                        if(!userList.contains(user)){
                            Log.e("Added ","^^^");
                            userList.add(user);
                        }

                    }
                }
                activity.setFriendsList(userList);


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

    //sets up the local database.
    public static void setSqLiteHelper(SQLiteHelper _sqLiteHelper) {
        sqLiteHelper = _sqLiteHelper;
        sqLiteHelper.queryData
                ("CREATE TABLE IF NOT EXISTS Slots(Id INTEGER PRIMARY KEY AUTOINCREMENT, slotID VARCHAR, activity VARCHAR, day VARCHAR, timeFrom VARCHAR, timeTo VARCHAR)");
        sqLiteHelper.queryData
                ("CREATE TABLE IF NOT EXISTS Profile(Id INTEGER PRIMARY KEY AUTOINCREMENT, uID VARCHAR, name VARCHAR, age VARCHAR, gender VARCHAR, about VARCHAR)");

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

    public static UserInformation getUserInformation() {
        return userInformation;
    }

    public static void setUserInformation(UserInformation userInformation) {
        DatabaseHandler.userInformation = userInformation;
    }

    public static List<UserTimeTable> getUserTimeTable() {
        return userTimeTable;
    }
    /*
     * <--- Gertters and setters END --->
     */
}
