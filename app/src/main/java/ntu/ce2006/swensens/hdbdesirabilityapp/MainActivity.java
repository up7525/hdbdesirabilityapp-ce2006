package ntu.ce2006.swensens.hdbdesirabilityapp;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;
import ntu.ce2006.swensens.hdbdesirabilityapp.data.db.dbconfig.DbHandler;

import ntu.ce2006.swensens.hdbdesirabilityapp.HistoryActivity;
import ntu.ce2006.swensens.hdbdesirabilityapp.PinActivity;
import ntu.ce2006.swensens.hdbdesirabilityapp.R;
import ntu.ce2006.swensens.hdbdesirabilityapp.SearchActivity;

public class MainActivity extends AppCompatActivity {

    public Button searchMenuButton;
    public Button pinButton;
    public Button historyButton;

    public void init(){
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

                Intent makeHistory = new Intent(MainActivity.this,HistoryActivity.class);

                startActivity(makeHistory);
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
