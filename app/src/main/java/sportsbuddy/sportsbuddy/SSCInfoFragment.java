package sportsbuddy.sportsbuddy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s165700 on 2/28/2018.
 */

public class SSCInfoFragment extends Fragment implements OnItemSelectedListener {

    // implement spinner element
    Spinner locationSpinner;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ssc_info_page, container, false);

        
        TextView activityLink = (TextView) view.findViewById(R.id.link1);
        activityLink.setText(Html.fromHtml("@string/activity_link"));
        activityLink.setMovementMethod(LinkMovementMethod.getInstance());

        TextView occupancyLink = (TextView) view.findViewById(R.id.link2);
        occupancyLink.setText(Html.fromHtml("@string/occupancy_link"));
        occupancyLink.setMovementMethod(LinkMovementMethod.getInstance());

        locationSpinner = (Spinner) view.findViewById(R.id.locationSpinner);



        // set click listener for spinner
        locationSpinner.setOnItemSelectedListener(SSCInfoFragment.this);

        // add elements for the spinner
        List<String> locationList = new ArrayList<String>();
        locationList.add("Studentsportcenter");
        locationList.add("SSC Swimming pool");
        locationList.add("Hockey-/Tennispaviljoen");
        locationList.add("Sportpark Hondsheuvels");
        locationList.add("Rowing-accomodation");
        locationList.add("Bow and arrow court");
        locationList.add("Icesportcenter Eindhoven");


        // create an adapter for the spinner
        ArrayAdapter<String> locationSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, locationList);
        locationSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //set the data adapter to the spinner
        locationSpinner.setAdapter(locationSpinnerAdapter);
        
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) view).setTextColor(getResources().getColor(R.color.colorAccent));
        String location = locationSpinner.getItemAtPosition(position).toString();
        TextView addressOut = (TextView) getView().findViewById(R.id.addressOutText);
        TextView phoneOut = (TextView) getView().findViewById(R.id.phoneOutText);
        TextView webOut = (TextView) getView().findViewById(R.id.webOutText);
        TextView facilOut = (TextView) getView().findViewById(R.id.facilOutText);
        TextView monOut = (TextView) getView().findViewById(R.id.monOutText);
        TextView tueOut = (TextView) getView().findViewById(R.id.tueOutText);
        TextView wedOut = (TextView) getView().findViewById(R.id.wedOutText);
        TextView thuOut = (TextView) getView().findViewById(R.id.thuOutText);
        TextView friOut = (TextView) getView().findViewById(R.id.friOutText);
        TextView satOut = (TextView) getView().findViewById(R.id.satOutText);
        TextView sunOut = (TextView) getView().findViewById(R.id.sunOutText);

        switch (location) {
            case "Studentsportcenter":
                addressOut.setText("Onze Lieve Vrouwestraat 1");
                phoneOut.setText("(040) 2473232");
                webOut.setText("sciinfo@tue.nl");
                facilOut.setText("3 sporthalls, 1 gymnasticshall, 1 dojo, 1 training field, 5 racketball courts, 1 swimming pool, 1 fitness area, 2 cardiofitness areas, 1 sauna, 1 outdoor climbing wall, 1 indoor climbing wall, 1 climbing tower, 1 outdoor bow and arrow court");
                monOut.setText("08:00 - 23:00");
                tueOut.setText("08:00 - 23:00");
                wedOut.setText("08:00 - 23:00");
                thuOut.setText("08:00 - 23:00");
                friOut.setText("08:00 - 23:00");
                satOut.setText("09:00 - 19:00");
                sunOut.setText("09:00 - 17:00");
                break;

            case "SSC Swimming pool":
                addressOut.setText("Onze Lieve Vrouwestraat 1");
                phoneOut.setText("(040) 2473232");
                webOut.setText("sciinfo@tue.nl");
                facilOut.setText("1 swimming pool");
                monOut.setText("07:30 - 8:30, 12:00 - 13:30, 17:30 - 18:30");
                tueOut.setText("12:00 - 13:30, 16:30 - 18:30");
                wedOut.setText("07:30 - 08:30, 12:00 - 13:30, 16:45 - 18:00, 21:30 - 23:00");
                thuOut.setText("12:00 - 13:30, 16:30 - 18:30");
                friOut.setText("07:30 - 08:30, 12:00 - 13:30, 16:30 - 18:00");
                satOut.setText("13:15 - 15:00");
                sunOut.setText("10:30 - 12:00");

                break;

            case "Hockey-/Tennispaviljoen":
                addressOut.setText("Onze Lieve Vrouwestraat 1");
                phoneOut.setText("(040) 2473644");
                webOut.setText("sciinfo@tue.nl");
                facilOut.setText("1 hockey field, 11 tennis courts, 1 jeu de boules court");
                monOut.setText("09:00 - 23:00");
                tueOut.setText("09:00 - 23:00");
                wedOut.setText("09:00 - 23:00");
                thuOut.setText("09:00 - 23:00");
                friOut.setText("09:00 - 23:00");
                satOut.setText("09:00 - 21:00");
                sunOut.setText("10:00 - 17:00 (only in April, May, June, September and October)");

                break;

            case "Sportpark Hondsheuvels":
                addressOut.setText("J.C. Dirkxpad 3");
                phoneOut.setText("N/A");
                webOut.setText("sciinfo@tue.nl");
                facilOut.setText("4 soccer fields, 1 athletics court, 1 golf driving range");
                monOut.setText("09:00 - 21:00");
                tueOut.setText("09:00 - 21:00");
                wedOut.setText("09:00 - 21:00");
                thuOut.setText("09:00 - 21:00");
                friOut.setText("09:00 - 21:00");
                satOut.setText("09:00 - 18:00");
                sunOut.setText("10:00 - 18:00 (only in April, May, June, September en October)");


                break;

            case "Rowing-accomodation":
                addressOut.setText("Kanaaldijk Zuid 50");
                phoneOut.setText("(040) 2438853");
                webOut.setText("www.esrtheta.nl");
                facilOut.setText("N/A");
                monOut.setText("N/A");
                tueOut.setText("N/A");
                wedOut.setText("N/A");
                thuOut.setText("N/A");
                friOut.setText("N/A");
                satOut.setText("N/A");
                sunOut.setText("N/A");



                break;

            case "Bow and arrow court":
                addressOut.setText("Amazonelaan 4");
                phoneOut.setText("(06) 22641348");
                webOut.setText("www.eshdavinci.nl");
                facilOut.setText("N/A");
                monOut.setText("N/A");
                tueOut.setText("N/A");
                wedOut.setText("N/A");
                thuOut.setText("N/A");
                friOut.setText("N/A");
                satOut.setText("N/A");
                sunOut.setText("N/A");


                break;

            case "Icesportcenter Eindhoven":
                addressOut.setText("Antoon Coolenlaan 3");
                phoneOut.setText("(040) 2381200");
                webOut.setText("www.ijssportcentrum.nl");
                facilOut.setText("N/A");
                monOut.setText("N/A");
                tueOut.setText("N/A");
                wedOut.setText("N/A");
                thuOut.setText("N/A");
                friOut.setText("N/A");
                satOut.setText("N/A");
                sunOut.setText("N/A");

                break;
        }

    }

    public void onNothingSelected(AdapterView<?> parent) {
        TextView addressOut = (TextView) getView().findViewById(R.id.addressOutText);
        addressOut.setText("");
        TextView phoneOut = (TextView) getView().findViewById(R.id.phoneOutText);
        phoneOut.setText("");
        TextView webOut = (TextView) getView().findViewById(R.id.webOutText);
        webOut.setText("");
        TextView facilOut = (TextView) getView().findViewById(R.id.facilOutText);
        facilOut.setText("");
        TextView monOut = (TextView) getView().findViewById(R.id.monOutText);
        monOut.setText("");
        TextView tueOut = (TextView) getView().findViewById(R.id.tueOutText);
        tueOut.setText("");
        TextView wedOut = (TextView) getView().findViewById(R.id.wedOutText);
        wedOut.setText("");
        TextView thuOut = (TextView) getView().findViewById(R.id.thuOutText);
        thuOut.setText("");
        TextView friOut = (TextView) getView().findViewById(R.id.friOutText);
        friOut.setText("");
        TextView satOut = (TextView) getView().findViewById(R.id.satOutText);
        satOut.setText("");
        TextView sunOut = (TextView) getView().findViewById(R.id.sunOutText);
        sunOut.setText("");


    }
}                                                                                                                                                                                                                                                                                           