package sportsbuddy.sportsbuddy;

/**
 * Created by s166928 on 19 Apr 2018.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleItemView extends Activity {
    // Declare Variables
    String activity;
    String time;
    String facility;
    String flag;
    String position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_item.xml
        setContentView(R.layout.listview_item);

        Intent i = getIntent();
        // Get the result of activity
        activity = i.getStringExtra("activity");
        // Get the result of time
        time = i.getStringExtra("time");
        // Get the result of facility
        facility = i.getStringExtra("facility");

        // Locate the TextViews in listview_item.xml
        TextView txtrank = (TextView) findViewById(R.id.activity);
        TextView txtcountry = (TextView) findViewById(R.id.time);
        TextView txtpopulation = (TextView) findViewById(R.id.facility);

        // Set results to the TextViews
        txtrank.setText(activity);
        txtcountry.setText(time);
        txtpopulation.setText(facility);

    }
}
