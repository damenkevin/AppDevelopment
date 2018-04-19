package sportsbuddy.sportsbuddy;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;


/**
 * Created by s166928 on 19 Apr 2018.
 */

public class SearchActivity extends AppCompatActivity {
    ListView listview;
    ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    static String ACTIVITY = "activity";
    static String TIME = "time";
    static String FACILITY = "facility";
    EditText editsearch;
    private InputStream stream;
    private List<ActivityClass> activitiesList = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from search_page.xml
        setContentView(R.layout.search_page);
        // Execute DownloadXML AsyncTask
        new DownloadXML().execute();
        stream = getResources().openRawResource(R.raw.test);
    }

    // DownloadXML AsyncTask
    private class DownloadXML extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(SearchActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Android Search Filter XML Parse");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            activitiesList = new ArrayList<ActivityClass>();
            XMLParser parser = new XMLParser();
            // Retrive nodes from the given website URL in XMLParser.class
            String xml = parser
                    .getXmlFromUrl(stream);
            // Retrive DOM element
            Document doc = parser.getDomElement(xml);

            try {
                // Locate the NodeList name
                NodeList nl = doc.getElementsByTagName("ranking");
                for (int i = 0; i < nl.getLength(); i++) {
                    Element e = (Element) nl.item(i);
                    ActivityClass map = new ActivityClass();
                    map.setActivity(parser.getValue(e, ACTIVITY));
                    map.setTime(parser.getValue(e, TIME));
                    map.setFacility(parser.getValue(e, FACILITY));

                    activitiesList.add(map);
                }
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.listview);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(SearchActivity.this, activitiesList);
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
            // Locate the EditText in listview_main.xml
            editsearch = (EditText) findViewById(R.id.search);

            // Capture Text in EditText
            editsearch.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable arg0) {
                    String text = editsearch.getText().toString()
                            .toLowerCase(Locale.getDefault());
                    adapter.filter(text);
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1,
                                              int arg2, int arg3) {
                }

                @Override
                public void onTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                }
            });
        }
    }
}
