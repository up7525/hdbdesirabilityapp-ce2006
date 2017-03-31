package ntu.ce2006.swensens.hdbdesirabilityapp;

import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;
import android.app.*;
import java.io.*;
import java.util.*;

public class HistoryActivity extends AppCompatActivity {

    public ImageButton SearchButtonSmall;
    public ImageButton InfoButtonSmall;
    public ImageButton ClearButtonSmall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("History");
        init();
    }

    public void init(){

        // Code to be placed in RECEIVING class's init()
        Intent i = getIntent();

        // Create a new object, cast the intent's stored object
        // getIntExtra / getSerializable based on object type.
        // getExtra("<package name>.<object name>", <valueifobject is not found>);
        // setContentView / TextView is just formatting.
        int a = (int) i.getIntExtra("java.lang.Integer.integerObject",0);
//        setContentView(R.layout.activity_search);
//        TextView textView = (TextView) findViewById(R.id.textView);
//        textView.setText(Integer.toString(a));



        SearchButtonSmall = (ImageButton) findViewById(R.id.SearchButtonSmall);
        SearchButtonSmall.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
//                Query userQuery = createUserQuery();
//                FlatManager flatManager = new FlatManager(userQuery);
//                // TODO how to call flatManager.makeQuery
//
//                try {
//                    listOfFlats = flatManager.getFlats();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                Intent query = new Intent(HistoryActivity.this,ResultsActivity.class);
                startActivity(query);
            }
        });
        InfoButtonSmall = (ImageButton) findViewById(R.id.InfoButtonSmall);
        InfoButtonSmall.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                showAlert(v);
            }
        });
        ClearButtonSmall = (ImageButton) findViewById(R.id.ClearButtonSmall);
        ClearButtonSmall.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                String s = "Cleared all saved queries.";
                Toast.makeText(HistoryActivity.this,s,Toast.LENGTH_LONG).show();
                // TODO clear saved queries somehow
            }
        });
    }

    public void showAlert(View v) {
        AlertDialog.Builder info = new AlertDialog.Builder(this);
        info.setMessage("Hi Chester").create();
        info.show();
    }
}
