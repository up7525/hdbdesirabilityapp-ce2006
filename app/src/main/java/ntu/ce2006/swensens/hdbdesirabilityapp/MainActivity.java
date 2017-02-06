package ntu.ce2006.swensens.hdbdesirabilityapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import ntu.ce2006.swensens.hdbdesirabilityapp.searchlist.location.Location;
import ntu.ce2006.swensens.hdbdesirabilityapp.searchlist.location.LocationHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandList);
        Location location = new LocationHandler();
    }
}
