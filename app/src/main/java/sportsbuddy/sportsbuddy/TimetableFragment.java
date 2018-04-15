package sportsbuddy.sportsbuddy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.eunsiljo.timetablelib.data.TimeData;
import com.github.eunsiljo.timetablelib.data.TimeGridData;
import com.github.eunsiljo.timetablelib.data.TimeTableData;
import com.github.eunsiljo.timetablelib.view.TimeTableView;
import com.github.eunsiljo.timetablelib.viewholder.TimeTableItemViewHolder;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
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

public class TimetableFragment extends Fragment implements OnItemSelectedListener {
    DatabaseHandler databaseHandler;
    String sport;
    String day;
    String hourFrom;
    String minuteFrom;
    String hourTo;
    String minuteTo;
    String timeFrom;
    String timeTo;
    String level;
    List<String> fromHourList;
    List<String> fromMinuteList;
    List<String> toHourList;
    List<String> toMinuteList;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timetable_page, container, false);
        FloatingActionButton addNewSlotButton = (FloatingActionButton ) view.findViewById(R.id.buttonAddNewSlot);
        databaseHandler = DatabaseHandler.getDatabaseHandler();
        setPopUpDialog(addNewSlotButton);
        initLayout(view);
        initListener();
        initData();
        return view;
    }

    public void Test() {

    }

    /**
     * Sets the onClick listener to the button supposed to create the popup
     * and manages the popup
     */
    void setPopUpDialog(FloatingActionButton  _button) {
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

                // add elements for the hour spinners
                fromHourList = new ArrayList<String>();
                fromHourList.add("08");
                fromHourList.add("09");
                fromHourList.add("10");
                fromHourList.add("11");
                fromHourList.add("12");
                fromHourList.add("13");
                fromHourList.add("14");
                fromHourList.add("15");
                fromHourList.add("16");
                fromHourList.add("17");
                fromHourList.add("18");
                fromHourList.add("19");
                fromHourList.add("20");
                fromHourList.add("21");
                fromHourList.add("22");

                // create an adapter for the spinner
                ArrayAdapter<String> fromHSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, fromHourList);
                fromHSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //set the data adapter to the spinner
                fromHSpinner.setAdapter(fromHSpinnerAdapter);


                // implement spinner element for fromMinute
                Spinner fromMSpinner = (Spinner) dialog.findViewById(R.id.fromMSpinner);

                // set click listener for spinner
                fromMSpinner.setOnItemSelectedListener(TimetableFragment.this);

                // add elements for the spinner
                fromMinuteList = new ArrayList<String>();
                fromMinuteList.add("00");
                fromMinuteList.add("05");
                fromMinuteList.add("10");
                fromMinuteList.add("15");
                fromMinuteList.add("20");
                fromMinuteList.add("25");
                fromMinuteList.add("30");
                fromMinuteList.add("35");
                fromMinuteList.add("40");
                fromMinuteList.add("45");
                fromMinuteList.add("50");
                fromMinuteList.add("55");


                // create an adapter for the spinner
                ArrayAdapter<String> fromMSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, fromMinuteList);
                fromMSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //set the data adapter to the spinner
                fromMSpinner.setAdapter(fromMSpinnerAdapter);


                // implement spinner element for toHour
                Spinner toHSpinner = (Spinner) dialog.findViewById(R.id.toHSpinner);

                // set click listener for spinner
                toHSpinner.setOnItemSelectedListener(TimetableFragment.this);


                // add elements for the hour spinners
                toHourList = new ArrayList<String>();
                toHourList.add("08");
                toHourList.add("09");
                toHourList.add("10");
                toHourList.add("11");
                toHourList.add("12");
                toHourList.add("13");
                toHourList.add("14");
                toHourList.add("15");
                toHourList.add("16");
                toHourList.add("17");
                toHourList.add("18");
                toHourList.add("19");
                toHourList.add("20");
                toHourList.add("21");
                toHourList.add("22");

                // create an adapter for the spinner
                ArrayAdapter<String> toHSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, toHourList);
                toHSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //set the data adapter to the spinner
                toHSpinner.setAdapter(toHSpinnerAdapter);

                //set default
                toHSpinner.setSelection(14);
                hourTo = toHourList.get(14);


                // implement spinner element for toMinute
                Spinner toMSpinner = (Spinner) dialog.findViewById(R.id.toMSpinner);

                // set click listener for spinner
                toMSpinner.setOnItemSelectedListener(TimetableFragment.this);

                // add elements for the spinner
                toMinuteList = new ArrayList<String>();
                toMinuteList.add("00");
                toMinuteList.add("05");
                toMinuteList.add("10");
                toMinuteList.add("15");
                toMinuteList.add("20");
                toMinuteList.add("25");
                toMinuteList.add("30");
                toMinuteList.add("35");
                toMinuteList.add("40");
                toMinuteList.add("45");
                toMinuteList.add("50");
                toMinuteList.add("55");

                // create an adapter for the spinner
                ArrayAdapter<String> toMSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, toMinuteList);
                toMSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //set the data adapter to the spinner
                toMSpinner.setAdapter(toMSpinnerAdapter);

                //set default
                toMSpinner.setSelection(11);
                minuteTo = toMinuteList.get(11);

                // implement spinner element for level
                Spinner levelSpinner = (Spinner) dialog.findViewById(R.id.levelSpinner);

                // set click listener for spinner
                levelSpinner.setOnItemSelectedListener(TimetableFragment.this);

                // add elements for the spinner
                List<String> levelList = new ArrayList<String>();
                levelList.add("Beginner");
                levelList.add("Intermediate");
                levelList.add("Advanced");
                levelList.add("Expert");


                // create an adapter for the spinner
                ArrayAdapter<String> levelSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, levelList);
                levelSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                //set the data adapter to the spinner
                levelSpinner.setAdapter(levelSpinnerAdapter);


                Button addButton = (Button) dialog.findViewById(R.id.buttonSetTimeslot);
                //TODO: Add spinners with values from strings file and remove textFields.

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timeFrom = hourFrom + ":" + minuteFrom;
                        timeTo = hourTo + ":" + minuteTo;
                        switch (day) {

                            case "Monday":
                                day = "Mon";
                                break;

                            case "Tuesday":
                                day = "Tue";
                                break;

                            case "Wednesday":
                                day = "Wed";
                                break;

                            case "Thursday":
                                day = "Thu";
                                break;

                            case "Friday":
                                day = "Fri";
                                break;

                            case "Saturday":
                                day = "Sat";
                                break;

                            case "Sunday":
                                day = "Sun";
                                break;
                        }

                        databaseHandler.addNewTimeSlotToServerDatabase(sport, level, day, timeFrom, timeTo);
                        dialog.dismiss();
                        //to make sure that the timetable is updated once a new timeslot is added
                        mShortSamples.clear();
                        initData();
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

    /*Removes timeslot from timetable*/
    private void initListener() {

        timeTable.setOnTimeItemClickListener(new TimeTableItemViewHolder.OnTimeItemClickListener() {
            @Override
            public void onTimeItemClick(final View view, int position, TimeGridData item) {
                final TimeData time = item.getTime();
                new AlertDialog.Builder(view.getContext())
                        .setTitle(time.getTitle() + "  " + new DateTime(time.getStartMills()).toString(getDateTimePattern()) + "-" + new DateTime(time.getStopMills()).toString(getDateTimePattern()))
                        .setMessage("Do you want to remove this timeslot?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                DatabaseHandler.removeTimeSlot(time.getKey().toString());
                                //to make sure that the timetable is updated once a new timeslot is added
                                mShortSamples.clear();
                                initData();

                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }

    private void initLayout(View view) {
        timeTable = (TimeTableView) view.findViewById(R.id.timeTable);
    }

    private void initData() {
        DatabaseHandler.getUserTimeTableFromServer(new Callback<List<UserTimeTable>>() {
            @Override
            public void call(List<UserTimeTable> data) {
                loadData();
                //initShortSamples();

                timeTable.setStartHour(6);
                timeTable.setShowHeader(true);
                timeTable.setTableMode(TimeTableView.TableMode.SHORT);

                DateTime now = DateTime.now();
                mNow = now.withTimeAtStartOfDay().getMillis();

                timeTable.setTimeTable(mNow, mShortSamples);
            }
        });

    }

    // =============================================================================
    // Date format
    // =============================================================================

    private long getMillis(String day) {
        //get current date, with the given time
        DateTime date = DateTime.now();
        LocalTime time = getDateTimePattern().parseDateTime(day).toLocalTime();
        date = date.withTime(time);
        //timeslots are in millisecond
        return date.getMillis();
    }

    private DateTimeFormatter getDateTimePattern() {
        return DateTimeFormat.forPattern("HH:mm");
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

        for (UserTimeTable item : data) {
            if (mShortHeaders.contains(item.getDay())) {
                TimeData timeData = new TimeData(item.getKey(), item.getActivity(), R.color.color_table_1, getMillis(item.getTimeFrom()), getMillis(item.getTimeTo()));
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

            case R.id.levelSpinner:
                ((TextView) view).setTextColor(Color.BLACK);
                level = parent.getItemAtPosition(position).toString().trim();

                break;

            case R.id.fromHSpinner:
                ((TextView) view).setTextColor(Color.BLACK);
                toHourList.clear();
                toHourList.add("08");
                toHourList.add("09");
                toHourList.add("10");
                toHourList.add("11");
                toHourList.add("12");
                toHourList.add("13");
                toHourList.add("14");
                toHourList.add("15");
                toHourList.add("16");
                toHourList.add("17");
                toHourList.add("18");
                toHourList.add("19");
                toHourList.add("20");
                toHourList.add("21");
                toHourList.add("22");
                hourFrom = parent.getItemAtPosition(position).toString().trim();
                int hF = Integer.parseInt(hourFrom);
                for (int i = toHourList.size() - 1; i >= 0; i--) {
                    int hT = Integer.parseInt(toHourList.get(i));
                    if (hT < hF) {
                        toHourList.remove(i);
                    }
                }

                break;

            case R.id.fromMSpinner:
                ((TextView) view).setTextColor(Color.BLACK);
                toMinuteList.clear();
                toMinuteList.add("00");
                toMinuteList.add("05");
                toMinuteList.add("10");
                toMinuteList.add("15");
                toMinuteList.add("20");
                toMinuteList.add("25");
                toMinuteList.add("30");
                toMinuteList.add("35");
                toMinuteList.add("40");
                toMinuteList.add("45");
                toMinuteList.add("50");
                toMinuteList.add("55");
                minuteFrom = parent.getItemAtPosition(position).toString().trim();
                int mF = Integer.parseInt(minuteFrom);
                hF = Integer.parseInt(hourFrom);
                int hT = Integer.parseInt(hourTo);
                if (hF == hT) {
                    for (int i = toMinuteList.size() - 1; i >= 0; i--) {
                        int mT = Integer.parseInt(toMinuteList.get(i));
                        if (mT <= mF) {
                            toMinuteList.remove(i);
                        }
                    }
                }


                break;

            case R.id.toHSpinner:
                ((TextView) view).setTextColor(Color.BLACK);
                fromHourList.clear();
                fromHourList.add("08");
                fromHourList.add("09");
                fromHourList.add("10");
                fromHourList.add("11");
                fromHourList.add("12");
                fromHourList.add("13");
                fromHourList.add("14");
                fromHourList.add("15");
                fromHourList.add("16");
                fromHourList.add("17");
                fromHourList.add("18");
                fromHourList.add("19");
                fromHourList.add("20");
                fromHourList.add("21");
                fromHourList.add("22");
                hourTo = parent.getItemAtPosition(position).toString().trim();
                hT = Integer.parseInt(hourTo);
                for (int i = fromHourList.size() - 1; i >= 0; i--) {
                    hF = Integer.parseInt(fromHourList.get(i));
                    if (hF > hT) {
                        fromHourList.remove(i);
                    }
                }

                break;

            case R.id.toMSpinner:
                ((TextView) view).setTextColor(Color.BLACK);
                fromMinuteList.clear();
                fromMinuteList.add("00");
                fromMinuteList.add("05");
                fromMinuteList.add("10");
                fromMinuteList.add("15");
                fromMinuteList.add("20");
                fromMinuteList.add("25");
                fromMinuteList.add("30");
                fromMinuteList.add("35");
                fromMinuteList.add("40");
                fromMinuteList.add("45");
                fromMinuteList.add("50");
                fromMinuteList.add("55");
                minuteTo = parent.getItemAtPosition(position).toString().trim();
                int mT = Integer.parseInt(minuteTo);
                hF = Integer.parseInt(hourFrom);
                hT = Integer.parseInt(hourTo);
                if (hF == hT) {
                    for (int i = fromMinuteList.size() - 1; i >= 0; i--) {
                        mF = Integer.parseInt(fromMinuteList.get(i));
                        if (mF >= mT) {
                            fromMinuteList.remove(i);
                        }
                    }
                }

                break;

        }

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }


}