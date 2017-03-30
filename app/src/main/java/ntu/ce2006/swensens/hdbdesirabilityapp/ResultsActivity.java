package ntu.ce2006.swensens.hdbdesirabilityapp;

import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;

import java.util.ArrayList;

import ntu.ce2006.swensens.hdbdesirabilityapp.R;
import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {
    private ListView lv;
    public Button saveQueryButton;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        setTitle("Results");
        lv = (ListView) findViewById(R.id.List);

        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add("1");
        your_array_list.add("2");
        your_array_list.add("3");
        your_array_list.add("4");
        your_array_list.add("5");
        your_array_list.add("6");
        your_array_list.add("7");
        your_array_list.add("8");
        your_array_list.add("9");
        your_array_list.add("10");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list );

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