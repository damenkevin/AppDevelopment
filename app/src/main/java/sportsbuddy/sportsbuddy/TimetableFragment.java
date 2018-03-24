package sportsbuddy.sportsbuddy;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.eunsiljo.timetablelib.data.TimeData;
import com.github.eunsiljo.timetablelib.data.TimeGridData;
import com.github.eunsiljo.timetablelib.data.TimeTableData;
import com.github.eunsiljo.timetablelib.view.TimeTableView;
import com.github.eunsiljo.timetablelib.viewholder.TimeTableItemViewHolder;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by s165700 on 2/28/2018.
 */

public class TimetableFragment extends Fragment {
    DatabaseHandler databaseHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timetable_page, container, false);
        Button addNewSlotButton = (Button) view.findViewById(R.id.buttonAddNewSlot);
        databaseHandler = DatabaseHandler.getDatabaseHandler();
        setPopUpDialog(addNewSlotButton);
        initLayout(view);
        //initListener();
        initData();
        return view;
    }

    /**
     * Sets the onClick listener to the button supposed to create the popup
     * and manages the popup
     */
    void setPopUpDialog(Button _button) {
        _button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.addtimeslot_popup);
                Button addButton = (Button) dialog.findViewById(R.id.buttonSetTimeslot);
                //TODO: Add spinners with values from strings file and remove textFields.
                final EditText activityText = (EditText) dialog.findViewById(R.id.textActivity);
                final EditText dayText = (EditText) dialog.findViewById(R.id.textDay);
                final EditText timeFromText = (EditText) dialog.findViewById(R.id.textFrom);
                final EditText timeToText = (EditText) dialog.findViewById(R.id.textTo);


                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String activity = activityText.getText().toString().trim();
                        String day = dayText.getText().toString().trim();
                        String timeFrom = timeFromText.getText().toString().trim();
                        String timeTo = timeToText.getText().toString().trim();
                        databaseHandler.addNewTimeSlotToServerDatabase(activity, day, timeFrom, timeTo);
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        });
    }

    /*timetable adapted from https://github.com/EunsilJo/TimeTable*/
    private View btnMode;
    private TimeTableView timeTable;

    private ArrayList<TimeTableData> mShortSamples = new ArrayList<>();

    private List<String> mShortHeaders = Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");

    private long mNow = 8;


    private void initLayout(View view) {
        btnMode = view.findViewById(R.id.btnMode);
        timeTable = (TimeTableView) view.findViewById(R.id.timeTable);
    }

    private void initData() {
        DatabaseHandler.getUserTimeTableFromServer(new Callback<List<UserTimeTable>>() {
            @Override
            public void call(List<UserTimeTable> data) {
                loadData();
                //initShortSamples();

                timeTable.setStartHour(8);
                timeTable.setShowHeader(true);
                timeTable.setTableMode(TimeTableView.TableMode.SHORT);

                DateTime now = DateTime.now();
                mNow = now.withTimeAtStartOfDay().getMillis();

                timeTable.setTimeTable(mNow, mShortSamples);
            }
        });

    }


    private void toogleMode() {
        btnMode.setActivated(!btnMode.isActivated());
    }

    // =============================================================================
    // Date format
    // =============================================================================

    private long getMillis(String day) {
        DateTime date = getDateTimePattern().parseDateTime(day);
        return date.getMillis();
    }

    private DateTimeFormatter getDateTimePattern() {
        return DateTimeFormat.forPattern("HH:mm");
    }

    // =============================================================================
    // Toast
    // =============================================================================

    private void showToast(Activity activity, String msg) {
        Toast toast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        if (v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }

    private void loadData() {
        List<UserTimeTable> data = DatabaseHandler.getUserTimeTable();

        Map<String, ArrayList<TimeData>> timeDataMap = new LinkedHashMap<>();

        //creates empty lists
        timeDataMap.put("Mon", new ArrayList<TimeData>());
        timeDataMap.put("Tue", new ArrayList<TimeData>());
        timeDataMap.put("Wed", new ArrayList<TimeData>());
        timeDataMap.put("Thu", new ArrayList<TimeData>());
        timeDataMap.put("Fri", new ArrayList<TimeData>());
        timeDataMap.put("Sat", new ArrayList<TimeData>());
        timeDataMap.put("Sun", new ArrayList<TimeData>());

        int i = 0;
        for (UserTimeTable item : data) {
            if (mShortHeaders.contains(item.getDay())) {
                TimeData timeData = new TimeData(i++, item.getActivity(), R.color.color_table_1, getMillis(item.getTimeFrom()), getMillis(item.getTimeTo()));
                timeDataMap.get(item.getDay()).add(timeData);
            }

        }

        ArrayList<TimeTableData> tables = new ArrayList<>();
        //loops trough the map and displays what is linked for each entry.
        for (Map.Entry<String, ArrayList<TimeData>> entry : timeDataMap.entrySet()) {
            tables.add(new TimeTableData(entry.getKey(), entry.getValue()));
        }
        mShortSamples.addAll(tables);
    }
}

