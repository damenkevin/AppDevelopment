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

/**
 * Created by s165700 on 3/5/2018.
 */

/**
 * Creates the items to be displayed as matches
 */
public class MatchesAdapter extends BaseAdapter {

    private Context context;
    private String[] values1 = {"Bob", "Bob", "Bill"};
    private String[] values2 = {"Tennis", "Fitness", "Swimming"};
    private String[] values3 = {"Intermediate", "Beginner", "Beginner"};
    private int[] values4 = {R.drawable.matches, R.drawable.com_facebook_profile_picture_blank_portrait, R.drawable.com_facebook_profile_picture_blank_square};
    View view;
    private LayoutInflater layoutInflater;



    public MatchesAdapter(Context context){
        this.context = context;

        //get the matches from the database


    }

    //change the getcount to count the amount of matches instead of values1
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
            view = layoutInflater.inflate(R.layout.match_item, null);



            TextView textNameProfile = view.findViewById(R.id.textNameProfile);
            TextView sports = view.findViewById(R.id.Sports);
            TextView level = view.findViewById(R.id.Level);
            ImageButton imageButton = view.findViewById(R.id.profileImg);

            textNameProfile.setText(values1[i]);
            sports.setText(values2[i]);
            level.setText(values3[i]);
            //imageButton.setImageResource(values4[i]);

        }
        return view;
    }
}
