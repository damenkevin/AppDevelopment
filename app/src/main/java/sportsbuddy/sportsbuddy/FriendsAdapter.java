package sportsbuddy.sportsbuddy;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by s165700 on 3/26/2018.
 */

public class FriendsAdapter extends BaseAdapter {
    private Context context;
    private String[] values1 = {"Bob", "Jim", "Janette"};
    private String[] values2 = {"Male", "Male", "Female"};
    private int[] values3 = {19, 21, 20};
    private int[] values4 = {R.drawable.matches, R.drawable.com_facebook_profile_picture_blank_portrait, R.drawable.com_facebook_profile_picture_blank_square};
    View view;
    private LayoutInflater layoutInflater;

    private int layout;
    private ArrayList<AppUser> userList;


    public FriendsAdapter(Context context, int layout, ArrayList<AppUser> userList) {
        this.context = context;
        this.layout = layout;
        this.userList = userList;
    }

    //change the getcount to count the userlist instead of values1
    @Override
    public int getCount() {
        return values1.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
            view = new View(context);
            view = layoutInflater.inflate(R.layout.friend_item, null);

            TextView textNameProfile = view.findViewById(R.id.textNameProfile);
            TextView gender = view.findViewById(R.id.gender);
            TextView age = view.findViewById(R.id.age);
            ImageButton imageButton = view.findViewById(R.id.profileImg);

            textNameProfile.setText(values1[i]);
            gender.setText(values2[i]);
            age.setText(Integer.toString(values3[i]));
            imageButton.setImageResource(values4[i]);
        }


        return view;
    }
}
