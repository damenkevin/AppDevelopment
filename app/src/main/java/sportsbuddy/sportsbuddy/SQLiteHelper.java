package sportsbuddy.sportsbuddy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by s165700 on 3/19/2018.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database= getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertTimeTableSlotDetails(String slotID,String level, String activity, String day,String timeFrom, String timeTo){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Slots VALUES (NULL,?,?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, slotID);
        statement.bindString(2,level);
        statement.bindString(3, activity);
        statement.bindString(4, day);
        statement.bindString(5, timeFrom);
        statement.bindString(6, timeTo);
        statement.executeInsert();
        database.close();
    }

    public void insertPersonalProfileInfo(String uID, String name, String age, String gender, String about, String profilePicture){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Profile VALUES (NULL,?,?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, uID);
        statement.bindString(2, name);
        statement.bindString(3, age);
        statement.bindString(4, gender);
        statement.bindString(5, about);
        statement.bindString(6, profilePicture);
        statement.executeInsert();
        database.close();
    }

    public void insertMatch(String UID,String level, String sportingActivity, String day, String timeFromOverlap, String timeToOverlap){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Matches VALUES (NULL,?,?,?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, UID);
        statement.bindString(2, level);
        statement.bindString(3, sportingActivity);
        statement.bindString(4, day);
        statement.bindString(5, timeFromOverlap);
        statement.bindString(6, timeToOverlap);
        statement.bindString(7, "false");
        statement.executeInsert();
        database.close();
    }

    public void updatePersonalProfileData(String uID, String name, String age, String gender, String about){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE Profile SET uID = ?, name = ?, age = ?, gender = ?, about = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        int id = 1;
        statement.bindString(1, uID);
        statement.bindString(2, name);
        statement.bindString(3, age);
        statement.bindString(4, gender);
        statement.bindString(5, about);
        statement.bindDouble(6,(double) id);
        statement.execute();
        database.close();
    }

    public void updateProfilePicture(String uID, String profilePicture){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE Profile SET uID = ?, profilePicture = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        int id = 1;
        statement.bindString(1, uID);
        statement.bindString(2, profilePicture);
        statement.bindDouble(3,(double) id);
        statement.execute();
        database.close();
    }

    public void setMatchHandled(String UID,String level, String sportingActivity, String day, String timeFromOverlap, String timeToOverlap){
        SQLiteDatabase database = getWritableDatabase();
<<<<<<< HEAD
        String sql = "UPDATE Matches SET handled = ? WHERE UID = ?,level = ?, sportingActivity = ?, day = ?, timeFromOverlap = ?, timeToOverlap = ?";
=======
        String sql = "UPDATE Matches SET handled = ? WHERE UID = ?, level = ?, sportingActivity = ?, day = ?, timeFromOverlap = ?, timeToOverlap = ?";
>>>>>>> 02d4715262a30a23829a6f503473b8ef879213fb
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1, "true");
        statement.bindString(2, UID);
        statement.bindString(3,level);
        statement.bindString(4, sportingActivity);
        statement.bindString(5, day);
        statement.bindString(6, timeFromOverlap);
        statement.bindString(7, timeToOverlap);
        statement.execute();
        database.close();
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
}
