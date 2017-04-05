package ntu.ce2006.swensens.hdbdesirabilityapp;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.content.*;
import android.widget.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import ntu.ce2006.swensens.hdbdesirabilityapp.data.api.GovDataAPIImpl;
import ntu.ce2006.swensens.hdbdesirabilityapp.data.db.dbconfig.DbHandler;

import ntu.ce2006.swensens.hdbdesirabilityapp.HistoryActivity;
import ntu.ce2006.swensens.hdbdesirabilityapp.PinActivity;
import ntu.ce2006.swensens.hdbdesirabilityapp.R;
import ntu.ce2006.swensens.hdbdesirabilityapp.SearchActivity;

public class MainActivity extends AppCompatActivity {

    // For Tag
    private static final String TAG = "MainActivity";

    public static Context appContext;

    public Button searchMenuButton;
    public Button pinButton;
    public Button historyButton;

    public void init(){
        MainActivity.appContext = getApplicationContext();
        File path = MainActivity.appContext.getFilesDir();
        File file = new File(path, "resale-flat-price.json");
        if (!file.exists()) {
            GovDataAPIImpl govDataAPI = new GovDataAPIImpl();
            try {
                govDataAPI.updateData(file);
            } catch (Exception e) {
                Log.d(TAG, "Error Retrieving API and File Not Created", e);
                // Display on Screen to let user know an error has happened
            }
        }

        setTitle("");

        searchMenuButton = (Button)findViewById(R.id.searchMenuButton);
        searchMenuButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                // Code to be added in SENDING class, under button press call
                // When clicking button, make a new intent
                Intent makeQuery = new Intent(MainActivity.this,SearchActivity.class);
                int a = 3;

                // intent.putExtra("<object package name>.<object name>", <actual object>);
                // putExtra / putSerializableExtra depending on object type
                makeQuery.putExtra("java.lang.Integer.integerObject", a);
                startActivity(makeQuery);
            }
        });

        pinButton = (Button)findViewById(R.id.pinButton);
        pinButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                Intent pinMake = new Intent(MainActivity.this,PinActivity.class);

                startActivity(pinMake);
            }
        });

        historyButton = (Button)findViewById(R.id.historyButton);
        historyButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                DbHandler database = new DbHandler(getApplicationContext());
                if(database.getQueryCount() == 0)
                    Toast.makeText(MainActivity.this,"No saved queries!",Toast.LENGTH_LONG).show();
                else{
                    Intent makeHistory = new Intent(MainActivity.this,HistoryActivity.class);
                    startActivity(makeHistory);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


}
