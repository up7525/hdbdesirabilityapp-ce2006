package ntu.ce2006.swensens.hdbdesirabilityapp;

import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;

import java.io.Serializable;
import java.util.*;

import ntu.ce2006.swensens.hdbdesirabilityapp.search.FlatManager;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.result.Flat;

public class ResultsActivity extends AppCompatActivity {
    private ListView lv;
    public Button saveQueryButton;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        setTitle("Results");
        lv = (ListView) findViewById(R.id.List);

//        List<Flat> flats = new ArrayList<>();
//        List<String> your_array_list = new ArrayList<String>();

        Intent i =getIntent();
        List<Flat> altListOfFlats = null;
        List<Flat> listOfFlats = (List<Flat>) i.getSerializableExtra("java.util.List<ntu.ce2006.swensens.hdbdesirabilityapp.search.result.Flat>");
//        // Code to be placed in RECEIVING class's init()
//        Intent i = getIntent();
//
//        // Create a new object, cast the intent's stored object
//        // getIntExtra / getSerializable based on object type.
//        // getExtra("<package name>.<object name>", <valueifobject is not found>);
//        // setContentView / TextView is just formatting.
//        int a = (int) i.getIntExtra("java.lang.Integer.integerObject",0);
//        setContentView(R.layout.activity_search);
//        TextView textView = (TextView) findViewById(R.id.textView);
//        textView.setText(Integer.toString(a));

        List<String> listOfStrings = new ArrayList<String>();
        for(int k = 0; k < listOfFlats.size(); k++)
            listOfStrings.add(listOfFlats.get(k).toString());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listOfStrings);
        lv.setAdapter(arrayAdapter);
        init();
    }

    public void init(){
        saveQueryButton = (Button) findViewById(R.id.SaveQuery);
        saveQueryButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                Toast.makeText(ResultsActivity.this,"Query saved.",Toast.LENGTH_LONG).show();
                // TODO save query to database
            }
        });
    }
}