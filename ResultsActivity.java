package ntu.ce2006.swensens.hdbdesirabilityapp;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;

import java.io.Serializable;
import java.util.*;

import ntu.ce2006.swensens.hdbdesirabilityapp.data.db.dbconfig.DbHandler;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.FlatManager;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.result.Flat;

public class ResultsActivity extends AppCompatActivity {
    private ListView lv;
    public Button saveQueryButton;
    public DbHandler database;
    public Query userQuery;
    public int userQueryCount;
    public ArrayList<String> listOfFlats;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        setTitle("Results");
        lv = (ListView) findViewById(R.id.List);


        // Retrieve Flat list
        Intent i = getIntent();
        listOfFlats = i.getStringArrayListExtra("java.util.List<java.lang.String>");
        userQuery = (Query) i.getSerializableExtra("ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query");

        if(listOfFlats != null){
            if(listOfFlats.size() > 0){
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listOfFlats);
                lv.setAdapter(arrayAdapter);
            }
        }
        init();
    }

    public void init(){
        database = new DbHandler(getApplicationContext());
        saveQueryButton = (Button) findViewById(R.id.SaveQuery);
        saveQueryButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                if(database.getQueryCount() > 4){ // overwrite oldest query
                    List<Query> listOfQueries = database.getAllQueries();
                    database.deleteAllQuery();
                    listOfQueries.remove(0);
                    listOfQueries.add(userQuery);
                    for(int i = 0; i < listOfQueries.size(); i++)
                        database.addQuery(i,listOfQueries.get(i));
                }
                else{
                    database.addQuery(database.getQueryCount(),userQuery);
                }
                Toast.makeText(ResultsActivity.this,"Query saved.",Toast.LENGTH_LONG).show();
                // TODO save query to database
            }
        });
    }

}