package ntu.ce2006.swensens.hdbdesirabilityapp.activity;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;

import java.util.*;

import ntu.ce2006.swensens.hdbdesirabilityapp.R;
import ntu.ce2006.swensens.hdbdesirabilityapp.data.DbHandler;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;

import static ntu.ce2006.swensens.hdbdesirabilityapp.R.id.InfoButtonSmall;

public class ResultsActivity extends AppCompatActivity {
    private ListView lv;
    public ImageButton saveQueryButton, InfoButtonSmall;
    public DbHandler database;
    public Query userQuery;
    public int userQueryCount;
    public ArrayList<String> listOfFlats;
    public boolean isSaved;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        setTitle("Results");
        Intent i = getIntent();
        listOfFlats = i.getStringArrayListExtra("java.util.List<java.lang.String>");
        userQuery = (Query) i.getSerializableExtra("ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query");
        displayFlats();
        init();
    }

    public void displayFlats(){
        lv = (ListView) findViewById(R.id.List);
        if(listOfFlats != null && listOfFlats.size() > 0){
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listOfFlats);
            lv.setAdapter(arrayAdapter);
        }
    }

    public void init(){
        database = new DbHandler(getApplicationContext());
        saveQueryButton = (ImageButton) findViewById(R.id.SaveQuery);
        saveQueryButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                if(!(database.getAllQueries().contains(userQuery))){ // query doesn't exist in database yet
                    if(database.getQueryCount() >= 5){ // overwrite oldest query
                        List<Query> listOfQueries = database.getAllQueries();
                        database.deleteAllQuery();      // database cleared; to be written to later
                        listOfQueries.remove(0);        // remove oldest query
                        listOfQueries.add(userQuery);   // append current entry to end (newest)
                        for(int i = 0; i < listOfQueries.size(); i++)   // re-add everything to database
                            database.addQuery(i,listOfQueries.get(i));
                    }
                    else{ // fewer than 5 queries
                        database.addQuery(database.getQueryCount(),userQuery);
                    }
                    isSaved = true;
                    Toast.makeText(ResultsActivity.this,"Query saved.",Toast.LENGTH_LONG).show();
                }
                else{
                    saveQueryButton.setVisibility(View.INVISIBLE);
                    Toast.makeText(ResultsActivity.this,"Query already exists in database.",Toast.LENGTH_LONG).show();
                }
            }
        });
        InfoButtonSmall = (ImageButton) findViewById(R.id.InfoButtonSmall);
        InfoButtonSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayString;
                displayString = "The higher the score, the more matching amenities there are.";
                showAlert(v, displayString);
            }
        });

    }
    public void showAlert(View v, String displayString) {
        AlertDialog.Builder info = new AlertDialog.Builder(this);
        info.setMessage(displayString).create().show();
    }

}
