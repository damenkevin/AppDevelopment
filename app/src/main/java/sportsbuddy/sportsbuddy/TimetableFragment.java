package sportsbuddy.sportsbuddy;

import android.app.Dialog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
        return view;
    }

    /**
     * Sets the onClick listener to the button supposed to create the popup
     * and manages the popup
     */
    void setPopUpDialog(Button _button){
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
}
