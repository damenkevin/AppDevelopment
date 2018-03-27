package sportsbuddy.sportsbuddy;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.eunsiljo.timetablelib.data.TimeData;
import com.github.eunsiljo.timetablelib.data.TimeTableData;
import com.github.eunsiljo.timetablelib.view.TimeTableView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by s165700 on 2/28/2018.
 */

public class TimetableFragment extends Fragment implements OnItemSelectedListener{
    DatabaseHandler databaseHandler;
    String sport;
    String day;
    String hourFrom;
    String minuteFrom;
    String hourTo;
    String minuteTo;
    String timeFrom;
    String timeTo;

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

    public void Test(){

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

                // implement spinner element for days
                Spinner daySpinner = (Spinner) dialog.findViewById(R.id.daySpinner);

                // set click listener for spinner
                daySpinner.setOnItemSelectedListener(TimetableFragment.this);

                // add elements for the spinner
                List<String> dayList = new ArrayList<String>();
                dayList.add("Monday");
                dayList.add("Tuesday");
                dayList.add("Wednesday");
                dayList.add("Thursday");
                dayList.add("Friday");
                dayList.add("Saturday");
                dayList.add("Sunday");

                // create an adapter for the spinner
                ArrayAdapter<String> daySpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, dayList);
                daySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //set the data adapter to the spinner
                daySpinner.setAdapter(daySpinnerAdapter);


                // implement spinner element for sports
                Spinner sportSpinner = (Spinner) dialog.findViewById(R.id.sportSpinner);

                // set click listener for spinner
                sportSpinner.setOnItemSelectedListener(TimetableFragment.this);

                // add elements for the spinner
                List<String> sportList = new ArrayList<String>();
                sportList.add("Fitness");
                sportList.add("Racketball");
                sportList.add("Tennis");
                sportList.add("Hockey");
                sportList.add("Soccer");
                sportList.add("Judo");
                sportList.add("Swimming");

                // create an adapter for the spinner
                ArrayAdapter<String> sportSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, sportList);
                sportSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //set the data adapter to the spinner
                sportSpinner.setAdapter(sportSpinnerAdapter);



                // implement spinner element for fromHour
                Spinner fromHSpinner = (Spinner) dialog.findViewById(R.id.fromHSpinner);

                // set click listener for spinner
                fromHSpinner.setOnItemSelectedListener(TimetableFragment.this);

                // add elements for the spinner
                List<String> hourList = new ArrayList<String>();
                hourList.add("00");
                hourList.add("01");
                hourList.add("02");
                hourList.add("03");
                hourList.add("04");
                hourList.add("05");
                hourList.add("07");
                hourList.add("08");
                hourList.add("09");
                hourList.add("10");
                hourList.add("11");
                hourList.add("12");
                hourList.add("13");
                hourList.add("14");
                hourList.add("15");
                hourList.add("16");
                hourList.add("17");
                hourList.add("18");
                hourList.add("19");
                hourList.add("20");
                hourList.add("21");
                hourList.add("22");
                hourList.add("23");

                // create an adapter for the spinner
                ArrayAdapter<String> fromHSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, hourList);
                fromHSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //set the data adapter to the spinner
                fromHSpinner.setAdapter(fromHSpinnerAdapter);


                // implement spinner element for fromMinute
                Spinner fromMSpinner = (Spinner) dialog.findViewById(R.id.fromMSpinner);

                // set click listener for spinner
                fromMSpinner.setOnItemSelectedListener(TimetableFragment.this);

                // add elements for the spinner
                List<String> minuteList = new ArrayList<String>();
                minuteList.add("00");
                minuteList.add("01");
                minuteList.add("02");
                minuteList.add("03");
                minuteList.add("04");
                minuteList.add("05");
                minuteList.add("06");
                minuteList.add("07");
                minuteList.add("08");
                minuteList.add("09");
                minuteList.add("10");
                minuteList.add("11");
                minuteList.add("12");
                minuteList.add("13");
                minuteList.add("14");
                minuteList.add("15");
                minuteList.add("16");
                minuteList.add("17");
                minuteList.add("18");
                minuteList.add("19");
                minuteList.add("20");
                minuteList.add("21");
                minuteList.add("22");
                minuteList.add("23");
                minuteList.add("24");
                minuteList.add("25");
                minuteList.add("26");
                minuteList.add("27");
                minuteList.add("28");
                minuteList.add("29");
                minuteList.add("30");
                minuteList.add("31");
                minuteList.add("32");
                minuteList.add("33");
                minuteList.add("34");
                minuteList.add("35");
                minuteList.add("36");
                minuteList.add("37");
                minuteList.add("38");
                minuteList.add("39");
                minuteList.add("40");
                minuteList.add("41");
                minuteList.add("42");
                minuteList.add("43");
                minuteList.add("44");
                minuteList.add("45");
                minuteList.add("46");
                minuteList.add("47");
                minuteList.add("48");
                minuteList.add("49");
                minuteList.add("50");
                minuteList.add("51");
                minuteList.add("52");
                minuteList.add("53");
                minuteList.add("54");
                minuteList.add("55");
                minuteList.add("56");
                minuteList.add("57");
                minuteList.add("58");
                minuteList.add("59");


                // create an adapter for the spinner
                ArrayAdapter<String> fromMSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, minuteList);
                fromMSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //set the data adapter to the spinner
                fromMSpinner.setAdapter(fromMSpinnerAdapter);


                // implement spinner element for toHour
                Spinner toHSpinner = (Spinner) dialog.findViewById(R.id.toHSpinner);

                // set click listener for spinner
                toHSpinner.setOnItemSelectedListener(TimetableFragment.this);


                // create an adapter for the spinner
                ArrayAdapter<String> toHSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, hourList);
                toHSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //set the data adapter to the spinner
                toHSpinner.setAdapter(toHSpinnerAdapter);


                // implement spinner element for toMinute
                Spinner toMSpinner = (Spinner) dialog.findViewById(R.id.toMSpinner);

                // set click listener for spinner
                toMSpinner.setOnItemSelectedListener(TimetableFragment.this);

                // create an adapter for the spinner
                ArrayAdapter<String> toMSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, minuteList);
                toMSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //set the data adapter to the spinner
                toMSpinner.setAdapter(toMSpinnerAdapter);



                Button addButton = (Button) dialog.findViewById(R.id.buttonSetTimeslot);
                //TODO: Add spinners with values from strings file and remove textFields.

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timeFrom = hourFrom + ":" + minuteFrom;
                        timeTo = hourTo + ":" + minuteTo;
                        databaseHandler.addNewTimeSlotToServerDatabase(sport, day, timeFrom, timeTo);
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
       // btnMode = view.findViewById(R.id.btnMode);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // when selecting a spinner

        switch (parent.getId()) {

            case R.id.daySpinner:
                ((TextView) view).setTextColor(Color.BLACK);
                day = parent.getItemAtPosition(position).toString().trim();

                break;

            case R.id.sportSpinner:
                ((TextView) view).setTextColor(Color.BLACK);
                sport = parent.getItemAtPosition(position).toString().trim();

                break;

            case R.id.fromHSpinner:
                ((TextView) view).setTextColor(Color.BLACK);
                hourFrom = parent.getItemAtPosition(position).toString().trim();

                break;

            case R.id.fromMSpinner:
                ((TextView) view).setTextColor(Color.BLACK);
                minuteFrom = parent.getItemAtPosition(position).toString().trim();

                break;

            case R.id.toHSpinner:
                ((TextView) view).setTextColor(Color.BLACK);
                hourTo = parent.getItemAtPosition(position).toString().trim();

                break;

            case R.id.toMSpinner:
                ((TextView) view).setTextColor(Color.BLACK);
                minuteTo = parent.getItemAtPosition(position).toString().trim();

                break;


        }

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }


}