package sportsbuddy.sportsbuddy;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by s165700 on 3/14/2018.
 */


public class DatabaseTestingActivity extends Activity {
    DatabaseHandler databaseHandler;
    DatabaseReference timeTableRef;
    FirebaseUser user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_testing_layout);
        Button testButton1 = (Button) findViewById(R.id.buttonTest1);
        Button testButton2 = (Button) findViewById(R.id.buttonTest2);
        Button testButton3 = (Button) findViewById(R.id.buttonTest3);
        Button testButton4 = (Button) findViewById(R.id.buttonTest4);

        final TextView testText = (TextView) findViewById(R.id.testText);
        testButton1.setText("Get database values");
        testButton2.setText("Add new Timetable slot");
        databaseHandler = DatabaseHandler.getDatabaseHandler();

        testButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timeTableRef = databaseHandler.getTimeTableRef();
                user = databaseHandler.getFirebaseUser();
            }
        });

        testButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference childRef = timeTableRef.push().getRef();
                childRef.child("UserID").setValue(user.getUid());


            }
        });

        testButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        testButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
