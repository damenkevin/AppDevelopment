package sportsbuddy.sportsbuddy;

import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by s165700 on 3/26/2018.
 */

public class FriendsAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<AppUser> userList;

    public FriendsAdapter(Context context, int layout, ArrayList<AppUser> userList) {
        this.context = context;
        this.layout = layout;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return 0;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
