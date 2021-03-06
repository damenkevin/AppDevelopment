package sportsbuddy.sportsbuddy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

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
    View view;
    private LayoutInflater layoutInflater;
    private ArrayList<AppUser> appUsers;
    private ArrayList<Match> matches;
    private MatchesTab matchesTab;



    public MatchesAdapter(Context context, ArrayList<AppUser> appUsers, ArrayList<Match> matches, MatchesTab matchesTab){
        this.context = context;
        this.appUsers = appUsers;
        this.matches = matches;
        this.matchesTab = matchesTab;
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
        return i;
    }


    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = new View(context);
        view = layoutInflater.inflate(R.layout.match_item, null);
        String timeFromString;
        String timeToString;
        TextView textNameProfile = view.findViewById(R.id.textNameProfile);
        TextView sports = view.findViewById(R.id.Sports);
        TextView level = view.findViewById(R.id.Level);
        TextView timeFrom = view.findViewById(R.id.timeFromMatches);
        TextView timeTo = view.findViewById(R.id.timeToMatches);
        ImageButton imageButton = view.findViewById(R.id.profileImg);
        Button sendButton = view.findViewById(R.id.buttonSendRequest);
        Button deleteButton = view.findViewById(R.id.buttonDeleteMatch);

        //Proper time display:
        timeFromString = matches.get(i).getTimeFromOverlap();
        if (timeFromString.length() == 3) {
            timeFromString = "From: " + timeFromString.charAt(0) + ":" + timeFromString.charAt(1) + timeFromString.charAt(2);
        } else if (timeFromString.length() == 4) {
            timeFromString = "From: " + timeFromString.charAt(0) + timeFromString.charAt(1) + ":" + timeFromString.charAt(2) + timeFromString.charAt(3);
        }
        timeToString = matches.get(i).getTimeToOverlap();
        if (timeToString.length() == 3) {
            timeToString = "To: " + timeToString.charAt(0) + ":" + timeToString.charAt(1) + timeToString.charAt(2);
        } else if (timeToString.length() == 4) {
            timeToString = "To: " + timeToString.charAt(0) + timeToString.charAt(1) + ":" + timeToString.charAt(2) + timeToString.charAt(3);
        }

        textNameProfile.setText(appUsers.get(i).getName());
        sports.setText(matches.get(i).getSportingActivity());
        if (matches.get(i).getMatchUser1().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
            level.setText(matches.get(i).getLevelUser2());
        } else {
            level.setText(matches.get(i).getLevelUser1());
        }
        timeFrom.setText(timeFromString);
        timeTo.setText(timeToString);
        String profPic = appUsers.get(i).getprofilePicture();
        byte[] bytes = new byte[0];
        if (profPic != "") {
            try {
                bytes = Base64.decode(profPic, Base64.DEFAULT);
            } catch (Exception e) {
                e.getMessage();
            }
        }

        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        if (bitmap == null) {

        } else {
            imageButton.setImageBitmap(bitmap);
        }
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matchesTab.viewProfile(appUsers.get(i));
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matchesTab.setMatchHandled(appUsers.get(i), matches.get(i), true);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matchesTab.setMatchHandled(appUsers.get(i), matches.get(i), false);
            }
        });


        //imageButton.setImageResource(values4[i]);
        return view;

    }

    public void updateMatchesList(ArrayList<AppUser> appUsers, ArrayList<Match> matches){
        this.appUsers = appUsers;
        this.matches = matches;
        Log.e("Size in adapter", String.valueOf(matches.size()));
        Log.e("Size in adapter", String.valueOf(appUsers.size()));
    }
}
