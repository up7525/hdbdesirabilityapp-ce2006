package ntu.ce2006.swensens.hdbdesirabilityapp;
import android.content.*;
import android.os.*;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.List;
import ntu.ce2006.swensens.hdbdesirabilityapp.data.db.dbconfig.DbHandler;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.FlatManager ;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.result.Flat;

public class HistoryActivity extends AppCompatActivity {

    public ImageButton SearchButtonSmall, InfoButtonSmall, ClearButtonSmall;
    public DbHandler database;
    public List<Query> listOfQueries;
    public int queryCount;
    public Query userQuery;
    RadioButton one, two, three, four, five;
    Query Q1, Q2, Q3, Q4, Q5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("History");

        // set radiobuttons
        one = (RadioButton) findViewById(R.id.History1);
        two = (RadioButton) findViewById(R.id.History2);
        three = (RadioButton) findViewById(R.id.History3);
        four = (RadioButton) findViewById(R.id.History4);
        five = (RadioButton) findViewById(R.id.History5);

        // retrieve queries from database
        database = new DbHandler(getApplicationContext());
        listOfQueries = database.getAllQueries();
        queryCount = listOfQueries.size();

        // set visibilities of radiobuttons depending on how many queries there are
        if(queryCount == 0){
            one.setVisibility(View.INVISIBLE);
            two.setVisibility(View.INVISIBLE);
            three.setVisibility(View.INVISIBLE);
            four.setVisibility(View.INVISIBLE);
            five.setVisibility(View.INVISIBLE);
            Toast.makeText(HistoryActivity.this,"No saved queries!",Toast.LENGTH_LONG).show();
            Intent returnToMain = new Intent(HistoryActivity.this, MainActivity.class);
            startActivity(returnToMain);
        }
        else if (queryCount == 1){
            two.setVisibility(View.INVISIBLE);
            three.setVisibility(View.INVISIBLE);
            four.setVisibility(View.INVISIBLE);
            five.setVisibility(View.INVISIBLE);

            Q1 = listOfQueries.get(0);
            one.setText(convertQueryToDescription(Q1));
        }
        else if(queryCount == 2){
            three.setVisibility(View.INVISIBLE);
            four.setVisibility(View.INVISIBLE);
            five.setVisibility(View.INVISIBLE);

            Q1 = listOfQueries.get(0);
            Q2 = listOfQueries.get(1);
            one.setText(convertQueryToDescription(Q1));
            two.setText(convertQueryToDescription(Q2));

        }
        else if(queryCount == 3){
            four.setVisibility(View.INVISIBLE);
            five.setVisibility(View.INVISIBLE);

            Q1 = listOfQueries.get(0);
            Q2 = listOfQueries.get(1);
            Q3 = listOfQueries.get(2);
            one.setText(convertQueryToDescription(Q1));
            two.setText(convertQueryToDescription(Q2));
            three.setText(convertQueryToDescription(Q3));
        }
        else if(queryCount == 4){
            five.setVisibility(View.INVISIBLE);

            Q1 = listOfQueries.get(0);
            Q2 = listOfQueries.get(1);
            Q3 = listOfQueries.get(2);
            Q4 = listOfQueries.get(3);
            one.setText(convertQueryToDescription(Q1));
            two.setText(convertQueryToDescription(Q2));
            three.setText(convertQueryToDescription(Q3));
            four.setText(convertQueryToDescription(Q4));
        }
        else{ // 5 or more queries BUT display only 5
            Q1 = listOfQueries.get(0);
            Q2 = listOfQueries.get(1);
            Q3 = listOfQueries.get(2);
            Q4 = listOfQueries.get(3);
            Q5 = listOfQueries.get(4);
            one.setText(convertQueryToDescription(Q1));
            two.setText(convertQueryToDescription(Q2));
            three.setText(convertQueryToDescription(Q3));
            four.setText(convertQueryToDescription(Q4));
            five.setText(convertQueryToDescription(Q5));
        }
        init();
    }

    public String convertQueryToDescription(Query userQuery){
        String displayString = "";
        if(userQuery.getLocationFilters().size() > 0)
            displayString = displayString + userQuery.getLocationFilters().get(0) + "...; ";
        if(userQuery.getSizeFilters().size() > 0)
            displayString = displayString + userQuery.getSizeFilters().get(0) + "...; ";
        if(userQuery.getAmenitiesFilters().size() > 0){
            for(int u = 0; u < userQuery.getAmenitiesFilters().size(); u++){
                if(u != (userQuery.getAmenitiesFilters().size()-1))
                    displayString = displayString + userQuery.getAmenitiesFilters().get(u) + " + ";
                else
                    displayString = displayString + userQuery.getAmenitiesFilters().get(u);
            }
        }
        return displayString;
    }

    public void init(){
        SearchButtonSmall = (ImageButton) findViewById(R.id.SearchButtonSmall);
        SearchButtonSmall.setOnClickListener(new View.OnClickListener()  {
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
                if(oneChecked | twoChecked | threeChecked | fourChecked | fiveChecked){
                    if(oneChecked)
                        userQuery = Q1;
                    else if(twoChecked)
                        userQuery = Q2;
                    else if(threeChecked)
                        userQuery = Q3;
                    else if(fourChecked)
                        userQuery = Q4;
                    else if(fiveChecked)
                        userQuery = Q5;

                    FlatManager flatManager = new FlatManager(userQuery);
                    List<Flat> listOfFlats;
                    ArrayList<String> listOfFlatsString = new ArrayList<String>();

                    try {
                        listOfFlats = flatManager.getFlats();
                        if(listOfFlats != null)
                            if(listOfFlats.size() > 0){
                                // convert listOfFlats to String BEFORE sending to ResultsActivity
                                for(int i = 0; i < listOfFlats.size(); i++)
                                    listOfFlatsString.add(listOfFlats.get(i).toString());
                                listOfFlatsString.add("");
                            }
                        if(listOfFlatsString.size() > 0) {
                            Intent intentFlat = new Intent(HistoryActivity.this, ResultsActivity.class);
                            intentFlat.putStringArrayListExtra("java.util.List<java.lang.String>", listOfFlatsString);
                            intentFlat.putExtra("ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query",(Serializable) userQuery);
                            startActivity(intentFlat);
                        }
                        else
                            showAlertQuick(v, "No results found using selected filters.");

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        Log.d("", "Exception", e);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    showAlertQuick(v, "Please choose a query!");
                }
            }
        });
        InfoButtonSmall = (ImageButton) findViewById(R.id.InfoButtonSmall);
        InfoButtonSmall.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                showAlertQuick(v, "Previously saved queries shown here. Select a query and then press the search icon to generate a new search.");
            }
        });
        ClearButtonSmall = (ImageButton) findViewById(R.id.ClearButtonSmall);
        ClearButtonSmall.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                if(database.getQueryCount() > 0){
                    database.deleteAllQuery();
                    one.setVisibility(View.INVISIBLE);
                    two.setVisibility(View.INVISIBLE);
                    three.setVisibility(View.INVISIBLE);
                    four.setVisibility(View.INVISIBLE);
                    five.setVisibility(View.INVISIBLE);
                    Toast.makeText(HistoryActivity.this,"Cleared all saved queries.",Toast.LENGTH_LONG).show();
                }
                else{
                    showAlertQuick(v, "No saved queries!");
                }
            }
        });
    }


    public void showQuery(View v, Query userQuery) {
        AlertDialog.Builder info = new AlertDialog.Builder(this);
        info.setMessage(userQuery.getLocationFilters().get(0).toString()).create();
        info.show();
    }

    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void showAlertQuick(View v, String displayString){
        AlertDialog.Builder info = new AlertDialog.Builder(this);
        info.setMessage(displayString).create();
        info.show();
    }
}