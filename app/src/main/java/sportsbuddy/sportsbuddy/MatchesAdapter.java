package sportsbuddy.sportsbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by s165700 on 3/5/2018.
 */

/**
 * Creates the items to be displayed as matches
 */
public class MatchesAdapter extends BaseAdapter {

    private Context context;
    private int[] values4 = {R.drawable.matches, R.drawable.com_facebook_profile_picture_blank_portrait, R.drawable.com_facebook_profile_picture_blank_square};
    View view;
    private LayoutInflater layoutInflater;
    private ArrayList<AppUser> appUsers;
    private ArrayList<Match> matches;



    public MatchesAdapter(Context context, ArrayList<AppUser> appUsers, ArrayList<Match> matches){
        this.context = context;
        this.appUsers = appUsers;
        this.matches = matches;
    }

    //change the getcount to count the amount of matches instead of values1
    @Override
    public int getCount() {
        return matches.size();
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
        view = new View(context);
        view = layoutInflater.inflate(R.layout.match_item, null);
        TextView textNameProfile = view.findViewById(R.id.textNameProfile);
        TextView sports = view.findViewById(R.id.Sports);
        TextView level = view.findViewById(R.id.Level);
        ImageButton imageButton = view.findViewById(R.id.profileImg);

        textNameProfile.setText(appUsers.get(i).getName());
        sports.setText(matches.get(i).getSportingActivity());
        level.setText(matches.get(i).getLevel());
        //imageButton.setImageResource(values4[i]);
        return view;
    }

    public void updateMatchesList(ArrayList<AppUser> appUsers, ArrayList<Match> matches){
        this.appUsers = appUsers;
        this.matches = matches;
    }
}
