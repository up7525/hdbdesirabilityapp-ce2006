package ntu.ce2006.swensens.hdbdesirabilityapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ntu.ce2006.swensens.hdbdesirabilityapp.R;
import ntu.ce2006.swensens.hdbdesirabilityapp.data.DbHandler;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.FlatManager;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.ResultAsyncCallback;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.result.Flat;

/**Loads the screen on the Android screen related to Query History
 * @author Faith, Nicholas, Chester
 *
 */

public class HistoryActivity extends AppCompatActivity implements ResultAsyncCallback<List<Flat>> {

    public ImageButton SearchButtonSmall, InfoButtonSmall, ClearButtonSmall;
    public DbHandler database;
    public List<Query> listOfQueries;
    public Query userQuery;
    private static HistoryActivity historyActivity;
    private RadioButton[] radioButtonArray;

    /** Initialisation of History activity
     *  @param savedInstanceState restore the activity state to a previous state using the data stored in this bundle if it exists
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("History");
        radioButtonArray = new RadioButton[5];
        radioButtonArray[0] = (RadioButton) findViewById(R.id.History1);
        radioButtonArray[1] = (RadioButton) findViewById(R.id.History2);
        radioButtonArray[2] = (RadioButton) findViewById(R.id.History3);
        radioButtonArray[3] = (RadioButton) findViewById(R.id.History4);
        radioButtonArray[4] = (RadioButton) findViewById(R.id.History5);

        /** Retrieve queries from DbHandler
         */
        retrieveQueries();

        /**Display the buttons for user to make selection
         */
        displayButtons();
        HistoryActivity.historyActivity = HistoryActivity.this;
        init();
    }
    /**
     *  Using DbHandler to attain queries made by user in the form of a list
     */
    private void retrieveQueries() {
        database = new DbHandler(getApplicationContext());
        listOfQueries = database.getAllQueries();
    }
    /**
     *  Displaying of history choice for users
     *  Limited to 5 choices
     */
    private void displayButtons() {
        for(int i = 0; i < 5; i++){
            radioButtonArray[i].setVisibility(View.INVISIBLE);
            if(listOfQueries.size() > i) {
                radioButtonArray[i].setVisibility(View.VISIBLE);
                radioButtonArray[i].setText(listOfQueries.get(i).getDesc());
            }
        }
    }
    /**
     * Initializes buttons and their transitions to other activities.
     */
    public void init() {
        SearchButtonSmall = (ImageButton) findViewById(R.id.SearchButtonSmall);
        SearchButtonSmall.setOnClickListener(new View.OnClickListener() {
            /** Upon clicking of the button, this method is called
             * @param v current view
             */
            @Override
            public void onClick(View v) {
                boolean isChecked = false;
                for(int i = 0; i < radioButtonArray.length; i++){
                    if(radioButtonArray[i].isChecked()){
                        isChecked = true;
                        new FlatManager(HistoryActivity.historyActivity, listOfQueries.get(i)).execute();
                        break;
                    }
                }
                if(!isChecked)
                    showAlert(v, "Please choose a query!");
            }
        });
        /**
         *  Shows the dialog message popup of the following text message
         */

        InfoButtonSmall = (ImageButton) findViewById(R.id.InfoButtonSmall);
        InfoButtonSmall.setOnClickListener(new View.OnClickListener() {
            /** Upon clicking of the button, this method is called
             * @param v current view
             */
            @Override
            public void onClick(View v) {
                showAlert(v, "Previously saved queries shown here. Select a query and then press the search icon to generate a new search.");
            }
        });

        ClearButtonSmall = (ImageButton) findViewById(R.id.ClearButtonSmall);
        ClearButtonSmall.setOnClickListener(new View.OnClickListener() {
            /** Upon clicking of the button, this method is called
             * @param v current view
             */
            @Override
            public void onClick(View v) {
                if (database.getQueryCount() > 0) {
                    database.deleteAllQuery();
                    for(int i = 0; i < radioButtonArray.length; i++)
                        radioButtonArray[i].setVisibility(View.INVISIBLE);
                    Toast.makeText(HistoryActivity.this, "Cleared all saved queries.", Toast.LENGTH_LONG).show();
                    Intent returnToMain = new Intent(HistoryActivity.this, MainActivity.class);
                    startActivity(returnToMain);
                } else {
                    showAlert(v, "No saved queries!");
                }
            }
        });
    }
    /**
     *  On escaping the History screen
     */
    public void onPause() {
        super.onPause();
    }


    /**
     *  On resuming back to the History screen
     */
    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * Displays an alert on the screen.
     * @param v = current view.
     * @param displayString = string to be displayed in the alert.
     */
    public void showAlert(View v, String displayString) {
        AlertDialog.Builder info = new AlertDialog.Builder(this);
        info.setMessage(displayString).create();
        info.show();
    }

    /**
     * Callback function to retrieve AsyncTask.execute() results
     *
     * @param result result to be returned from AsyncTask executions
     */
    @Override
    public void onTaskComplete(List<Flat> result) {
        List<Flat> listOfFlats;
        ArrayList<String> listOfFlatsString = new ArrayList<String>();
        listOfFlats = result;
        if (listOfFlats != null)
            if (listOfFlats.size() > 0) {
                // convert listOfFlats to String BEFORE sending to ResultsActivity
                for (int i = 0; i < listOfFlats.size(); i++)
                    listOfFlatsString.add(listOfFlats.get(i).toString());
                listOfFlatsString.add("");
            }
        if (listOfFlatsString.size() > 0) {
            Intent intentFlat = new Intent(HistoryActivity.this, ResultsActivity.class);
            intentFlat.putStringArrayListExtra("java.util.List<java.lang.String>", listOfFlatsString);
            intentFlat.putExtra("ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query", userQuery);
            startActivity(intentFlat);
        } else {
            showAlert(findViewById(android.R.id.content), "No results found using selected filters.");
        }

    }
}
