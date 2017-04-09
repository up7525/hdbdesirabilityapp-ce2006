package ntu.ce2006.swensens.hdbdesirabilityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ntu.ce2006.swensens.hdbdesirabilityapp.data.DbHandler;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.FlatManager;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.ResultAsyncCallback;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.result.Flat;

public class HistoryActivity extends AppCompatActivity implements ResultAsyncCallback<List<Flat>> {

    public ImageButton SearchButtonSmall, InfoButtonSmall, ClearButtonSmall;
    public DbHandler database;
    public List<Query> listOfQueries;
    public int queryCount;
    public Query userQuery;
    private static HistoryActivity historyActivity;
    RadioButton one, two, three, four, five;
    Query Q1, Q2, Q3, Q4, Q5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("History");
        retrieveQueries();
        displayButtons();
        HistoryActivity.historyActivity = HistoryActivity.this;
        init();
    }

    private void retrieveQueries() {
        // retrieve queries from database
        database = new DbHandler(getApplicationContext());
        listOfQueries = database.getAllQueries();
        queryCount = listOfQueries.size();
    }


    private void displayButtons() {
        // set radiobuttons
        one = (RadioButton) findViewById(R.id.History1);
        two = (RadioButton) findViewById(R.id.History2);
        three = (RadioButton) findViewById(R.id.History3);
        four = (RadioButton) findViewById(R.id.History4);
        five = (RadioButton) findViewById(R.id.History5);

        // set visibilities of radiobuttons depending on how many queries there are

        if (queryCount == 0) {
            one.setVisibility(View.INVISIBLE);
            two.setVisibility(View.INVISIBLE);
            three.setVisibility(View.INVISIBLE);
            four.setVisibility(View.INVISIBLE);
            five.setVisibility(View.INVISIBLE);
            Toast.makeText(HistoryActivity.this, "No saved queries!", Toast.LENGTH_LONG).show();
            Intent returnToMain = new Intent(HistoryActivity.this, MainActivity.class);
            startActivity(returnToMain);
        } else if (queryCount == 1) {
            two.setVisibility(View.INVISIBLE);
            three.setVisibility(View.INVISIBLE);
            four.setVisibility(View.INVISIBLE);
            five.setVisibility(View.INVISIBLE);

            Q1 = listOfQueries.get(0);
            one.setText(Q1.getDesc());
        } else if (queryCount == 2) {
            three.setVisibility(View.INVISIBLE);
            four.setVisibility(View.INVISIBLE);
            five.setVisibility(View.INVISIBLE);

            Q1 = listOfQueries.get(0);
            Q2 = listOfQueries.get(1);
            one.setText(Q1.getDesc());
            two.setText(Q2.getDesc());

        } else if (queryCount == 3) {
            four.setVisibility(View.INVISIBLE);
            five.setVisibility(View.INVISIBLE);

            Q1 = listOfQueries.get(0);
            Q2 = listOfQueries.get(1);
            Q3 = listOfQueries.get(2);
            one.setText(Q1.getDesc());
            two.setText(Q2.getDesc());
            three.setText(Q3.getDesc());
        } else if (queryCount == 4) {
            five.setVisibility(View.INVISIBLE);

            Q1 = listOfQueries.get(0);
            Q2 = listOfQueries.get(1);
            Q3 = listOfQueries.get(2);
            Q4 = listOfQueries.get(3);
            one.setText(Q1.getDesc());
            two.setText(Q2.getDesc());
            three.setText(Q3.getDesc());
            four.setText(Q4.getDesc());
        } else { // 5 or more queries BUT display only 5
            Q1 = listOfQueries.get(0);
            Q2 = listOfQueries.get(1);
            Q3 = listOfQueries.get(2);
            Q4 = listOfQueries.get(3);
            Q5 = listOfQueries.get(4);
            one.setText(Q1.getDesc());
            two.setText(Q2.getDesc());
            three.setText(Q3.getDesc());
            four.setText(Q4.getDesc());
            five.setText(Q5.getDesc());
        }
    }

    public void init() {
        SearchButtonSmall = (ImageButton) findViewById(R.id.SearchButtonSmall);
        SearchButtonSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean oneChecked = one.isChecked();
                boolean twoChecked = two.isChecked();
                boolean threeChecked = three.isChecked();
                boolean fourChecked = four.isChecked();
                boolean fiveChecked = five.isChecked();

                // if at least ONE checked
                // prevents situation where no queries are displayed
                // but user tries to be funny and presses search anyway
                if (oneChecked | twoChecked | threeChecked | fourChecked | fiveChecked) {
                    if (oneChecked)
                        userQuery = Q1;
                    else if (twoChecked)
                        userQuery = Q2;
                    else if (threeChecked)
                        userQuery = Q3;
                    else if (fourChecked)
                        userQuery = Q4;
                    else if (fiveChecked)
                        userQuery = Q5;

                    new FlatManager(HistoryActivity.historyActivity, userQuery).execute();

                } else {
                    showAlert(v, "Please choose a query!");
                }
            }
        });
        InfoButtonSmall = (ImageButton) findViewById(R.id.InfoButtonSmall);
        InfoButtonSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert(v, "Previously saved queries shown here. Select a query and then press the search icon to generate a new search.");
            }
        });
        ClearButtonSmall = (ImageButton) findViewById(R.id.ClearButtonSmall);
        ClearButtonSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (database.getQueryCount() > 0) {
                    database.deleteAllQuery();
                    one.setVisibility(View.INVISIBLE);
                    two.setVisibility(View.INVISIBLE);
                    three.setVisibility(View.INVISIBLE);
                    four.setVisibility(View.INVISIBLE);
                    five.setVisibility(View.INVISIBLE);
                    Toast.makeText(HistoryActivity.this, "Cleared all saved queries.", Toast.LENGTH_LONG).show();
                    Intent returnToMain = new Intent(HistoryActivity.this, MainActivity.class);
                    startActivity(returnToMain);
                } else {
                    showAlert(v, "No saved queries!");
                }
            }
        });
    }

    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

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