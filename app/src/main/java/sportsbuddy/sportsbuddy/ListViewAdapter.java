package sportsbuddy.sportsbuddy;

/**
 * Created by s166928 on 19 Apr 2018.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    private List<ActivityClass> activitiesList = null;
    private ArrayList<ActivityClass> arraylist;

    public ListViewAdapter(Context context,
                           List<ActivityClass> activitiesList) {
        this.context = context;
        this.activitiesList = activitiesList;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<ActivityClass>();
        this.arraylist.addAll(activitiesList);
    }

    public class ViewHolder {
        TextView activity;
        TextView time;
        TextView facility;
    }

    @Override
    public int getCount() {
        return activitiesList.size();
    }

    @Override
    public Object getItem(int position) {
        return activitiesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.activity = (TextView) view.findViewById(R.id.activity);
            holder.time = (TextView) view.findViewById(R.id.time);
            holder.facility = (TextView) view.findViewById(R.id.facility);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.activity.setText(activitiesList.get(position).getActivity());
        holder.time.setText(activitiesList.get(position).getTime());
        holder.facility.setText(activitiesList.get(position).getFacility());
        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, SingleItemView.class);
                // Pass all data activity
                intent.putExtra("activity",
                        (activitiesList.get(position).getActivity()));
                // Pass all data time
                intent.putExtra("time",
                        (activitiesList.get(position).getTime()));
                // Pass all data facility
                intent.putExtra("facility",
                        (activitiesList.get(position).getFacility()));
                // Start SingleItemView Class
                context.startActivity(intent);
            }
        });
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        activitiesList.clear();
        if (charText.length() == 0) {
            activitiesList.addAll(arraylist);
        } else {
            for (ActivityClass wp : arraylist) {
                if (wp.getActivity().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    activitiesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}

