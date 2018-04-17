package sportsbuddy.sportsbuddy;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by s165700 on 3/26/2018.
 */

public class FriendsAdapter extends BaseAdapter {
    private Context context;
    private int[] values4 = {R.drawable.matches, R.drawable.com_facebook_profile_picture_blank_portrait, R.drawable.com_facebook_profile_picture_blank_square};
    View view;
    private LayoutInflater layoutInflater;

    private int layout;
    private ArrayList<AppUser> userList;
    FriendsActivity friendsActivity;


    public FriendsAdapter(Context context, int layout, ArrayList<AppUser> userList, FriendsActivity activity) {
        this.context = context;
        this.layout = layout;
        this.userList = userList;
        this.friendsActivity = activity;
    }

    //change the getcount to count the userlist instead of values1
    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
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
            view = layoutInflater.inflate(R.layout.friend_item, null);

            TextView textNameProfile = view.findViewById(R.id.textNameProfile);
            TextView gender = view.findViewById(R.id.gender);
            TextView age = view.findViewById(R.id.age);
            ImageButton imageButton = view.findViewById(R.id.profileImg);
            Button buttonSendText = view.findViewById(R.id.buttonSendText);
            textNameProfile.setText(userList.get(i).getName());
            gender.setText(userList.get(i).getAbout());
            age.setText(userList.get(i).getAge());
            //imageButton.setImageResource(userList.get(i).getprofilePicture());
            buttonSendText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    friendsActivity.sendText(userList.get(i));
                }
            });

        }


        return view;
    }

    public void upadateFriendsList(ArrayList<AppUser> userList){
        this.userList = userList;
    }
}
