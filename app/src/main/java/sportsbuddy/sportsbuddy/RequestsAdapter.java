package sportsbuddy.sportsbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by s165700 on 3/5/2018.
 */

/**
 * Creates the items to be displayed as requests.
 */

public class RequestsAdapter extends BaseAdapter {

    private RequestsTab requestsTab;
    private Context context;
    private ArrayList<AppUser> users;
    private ArrayList<Request> requests;
    private int[] values4 = {R.drawable.matches, R.drawable.com_facebook_profile_picture_blank_portrait, R.drawable.com_facebook_profile_picture_blank_square};
    View view;
    private LayoutInflater layoutInflater;



    public RequestsAdapter(Context context, ArrayList<AppUser> users, ArrayList<Request> requests, RequestsTab requestsTab){
        this.context = context;
        this.users = users;
        this.requests = requests;
        this.requestsTab = requestsTab;

        //get the requests from the database


    }

    //change the getcount to count the amuont of requests instaad of values1
    @Override
    public int getCount() {
        return requests.size();
    }

    @Override
    public Object getItem(int i) {
        return requests.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
            view = new View(context);
            view = layoutInflater.inflate(R.layout.request_item, null);
            String timeFromString;
            String timeToString;
            TextView textNameProfile = view.findViewById(R.id.textNameProfile);
            TextView sports = view.findViewById(R.id.Sports);
            TextView level = view.findViewById(R.id.Level);
            TextView timeFrom = view.findViewById(R.id.timeFromRequest);
            TextView timeTo = view.findViewById(R.id.timeToRequest);
            ImageView imageButton = view.findViewById(R.id.profileImg);
            Button acceptButton = view.findViewById(R.id.btnAccept);
            Button denyButton = view.findViewById(R.id.btnDeny);
            textNameProfile.setText(users.get(i).getName());
            sports.setText(requests.get(i).getSportingActivity());
            level.setText(requests.get(i).getLevel());

            //Proper time display:
            timeFromString = requests.get(i).getTimeFromOverlap();
            if(timeFromString.length() == 3){
                timeFromString = "From: " + timeFromString.charAt(0) + ":" + timeFromString.charAt(1) + timeFromString.charAt(2);
            } else if(timeFromString.length() == 4){
                timeFromString = "From: " + timeFromString.charAt(0) + timeFromString.charAt(1) + ":" + timeFromString.charAt(2) + timeFromString.charAt(3);
            }
            timeToString = requests.get(i).getTimeToOverlap();
            if(timeToString.length() == 3){
                timeToString = "To: " + timeToString.charAt(0) + ":" + timeToString.charAt(1) + timeToString.charAt(2);
            } else if(timeToString.length() == 4){
                timeToString = "To: " + timeToString.charAt(0) + timeToString.charAt(1) + ":" + timeToString.charAt(2) + timeToString.charAt(3);
            }
            timeFrom.setText(timeFromString);
            timeTo.setText(timeToString);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requestsTab.visitProfile(users.get(i));
                }
            });

            acceptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            denyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
        return view;
    }

    public void updateRequests(ArrayList<AppUser> users, ArrayList<Request> requests){
        this.users = users;
        this.requests = requests;
    }

}
