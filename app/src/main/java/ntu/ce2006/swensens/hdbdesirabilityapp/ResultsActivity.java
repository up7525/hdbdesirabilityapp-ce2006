package ntu.ce2006.swensens.hdbdesirabilityapp;

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

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        setTitle("Results");
        lv = (ListView) findViewById(R.id.List);


        // Retrieve Flat list
        Intent i = getIntent();
        ArrayList<String> listOfFlats = (ArrayList<String>) i.getStringArrayListExtra("java.util.List<java.lang.String>");
        userQuery = (Query) i.getSerializableExtra("userQuery");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listOfFlats);
        lv.setAdapter(arrayAdapter);
        init();
    }

    public void init(){
        database = new DbHandler(getApplicationContext());
        userQueryCount = database.getQueryCount();
        saveQueryButton = (Button) findViewById(R.id.SaveQuery);
        saveQueryButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                database.addQuery(userQueryCount+1,userQuery);
                Toast.makeText(ResultsActivity.this,"Query saved.",Toast.LENGTH_LONG).show();
                // TODO save query to database
            }
        });
    }
}