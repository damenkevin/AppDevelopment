package sportsbuddy.sportsbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
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

    private Context context;
    private ArrayList<AppUser> users;
    private ArrayList<Request> requests;
    private int[] values4 = {R.drawable.matches, R.drawable.com_facebook_profile_picture_blank_portrait, R.drawable.com_facebook_profile_picture_blank_square};
    View view;
    private LayoutInflater layoutInflater;



    public RequestsAdapter(Context context, ArrayList<AppUser> users, ArrayList<Request> requests){
        this.context = context;
        this.users = users;
        this.requests = requests;

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
    public View getView(int i, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
            view = new View(context);
            view = layoutInflater.inflate(R.layout.request_item, null);



            TextView textNameProfile = view.findViewById(R.id.textNameProfile);
            TextView sports = view.findViewById(R.id.Sports);
            TextView level = view.findViewById(R.id.Level);
            ImageButton imageButton = view.findViewById(R.id.profileImg);

            textNameProfile.setText(users.get(i).getName());
            sports.setText(requests.get(i).getSportingActivity());
            level.setText(requests.get(i).getLevel());
            //imageButton.setImageResource(values4[i]);

        }
        return view;
    }

    public void updateRequests(ArrayList<AppUser> users, ArrayList<Request> requests){
        this.users = users;
        this.requests = requests;
    }

}
