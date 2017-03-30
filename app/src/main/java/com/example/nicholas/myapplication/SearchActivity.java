package com.example.nicholas.myapplication;

import android.icu.text.IDNA;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;

public class SearchActivity extends AppCompatActivity {


    public Button locationButton;
    public Button priceButton;
    public Button sizeButton;
    public Button amenitiesButton;
    public ImageButton SearchButtonSmall;
    public ImageButton InfoButtonSmall;

    public void init(){

        // Code to be placed in RECEIVING class's init()
        Intent i = getIntent();

        // Create a new object, cast the intent's stored object
        // getIntExtra / getSerializable based on object type.
        // getExtra("<package name>.<object name>", <valueifobject is not found>);
        // setContentView / TextView is just formatting.
        int a = (int) i.getIntExtra("java.lang.Integer.integerObject",0);
        setContentView(R.layout.activity_search);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(Integer.toString(a));


        locationButton = (Button)findViewById(R.id.locationButton);
        locationButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                Intent location = new Intent(SearchActivity.this,Search_Loc_Activity.class);

                startActivity(location);
            }
        });

        priceButton = (Button)findViewById(R.id.priceButton);
        priceButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                Intent price = new Intent(SearchActivity.this,Search_Price_Activity.class);

                startActivity(price);
            }
        });

        sizeButton = (Button)findViewById(R.id.sizeButton);
        sizeButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                Intent size = new Intent(SearchActivity.this,Search_Size_Activity.class);

                startActivity(size);
            }
        });

        amenitiesButton = (Button)findViewById(R.id.amenitiesButton);
        amenitiesButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                Intent ameni = new Intent(SearchActivity.this,Search_Ameni_Activity.class);

                startActivity(ameni);
            }
        });

        SearchButtonSmall = (ImageButton) findViewById(R.id.SearchButtonSmall);
        SearchButtonSmall.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                Intent query = new Intent(SearchActivity.this,ResultsActivity.class);

                startActivity(query);
            }
        });
        InfoButtonSmall = (ImageButton) findViewById(R.id.InfoButtonSmall);
        InfoButtonSmall.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                Intent info = new Intent(SearchActivity.this,Info.class);

                startActivity(info);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        String s = "Search";
        setTitle(s);
        init();

    }

    public void onDisplay(View v) {
        String s = "Cleared";
        Toast.makeText(SearchActivity.this,s,Toast.LENGTH_LONG).show();
    }
}
