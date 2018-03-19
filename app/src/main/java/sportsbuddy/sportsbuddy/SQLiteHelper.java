package sportsbuddy.sportsbuddy;

import android.content.Context;
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

    public void insertTimeTableSlotDetails(String slotID, String activity, String day,String timeFrom, String timeTo){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Slots VALUES (NULL,?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, slotID);
        statement.bindString(2, activity);
        statement.bindString(3, day);
        statement.bindString(4, timeFrom);
        statement.bindString(5, timeTo);
        statement.executeInsert();

    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
