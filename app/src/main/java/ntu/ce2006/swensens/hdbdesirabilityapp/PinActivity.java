package ntu.ce2006.swensens.hdbdesirabilityapp;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;

import ntu.ce2006.swensens.hdbdesirabilityapp.data.db.dbconfig.DbHandler;
import ntu.ce2006.swensens.hdbdesirabilityapp.pin.Pin;

public class PinActivity extends AppCompatActivity {

    public ImageButton ClearButtonSmall, InfoButtonSmall;
    public DbHandler database;
    public Pin p1, p2, p3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database = new DbHandler(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        setTitle("Pins");
        init();
    }
    public void init(){

        ClearButtonSmall = (ImageButton) findViewById(R.id.ClearButtonSmall2);
        ClearButtonSmall.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                database.deleteAllPin();
                ((EditText) findViewById(R.id.Pin1Input)).setText("");
                ((EditText) findViewById(R.id.Pin2Input)).setText("");
                ((EditText) findViewById(R.id.Pin3Input)).setText("");
                Toast.makeText(PinActivity.this,"Cleared Pins.",Toast.LENGTH_LONG).show();
            }
        });
        InfoButtonSmall = (ImageButton) findViewById(R.id.InfoButtonSmall);
        InfoButtonSmall.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                String displayString;
                String alert1 = "Enter postal code of desired locations.";
                String alert2 = "Application will calculate the distance between the pinned locations and the flats searched.";
                displayString = alert1+"\n"+"\n"+"\n"+alert2;
                showAlert(v, displayString);
            }
        });

    }

    public void showAlert(View v, String displayString){
        AlertDialog.Builder info = new AlertDialog.Builder(this);
        info.setMessage(displayString).create().show();
    }

    @Override
    public void onPause() {
        super.onPause();

        p1 = new Pin(1,"","");
        p2 = new Pin(2,"","");
        p3 = new Pin(3,"","");
        String itemString;

        itemString = ((EditText) findViewById(R.id.Pin1Input)).getText().toString();
        itemString = sanitizePostalCode(itemString);
        p1.setPostalcode(itemString);

        itemString = ((EditText) findViewById(R.id.Pin2Input)).getText().toString();
        itemString = sanitizePostalCode(itemString);
        p2.setPostalcode(itemString);

        itemString = ((EditText) findViewById(R.id.Pin3Input)).getText().toString();
        itemString = sanitizePostalCode(itemString);
        p3.setPostalcode(itemString);

        database.addPin(1,p1);
        database.addPin(2,p2);
        database.addPin(3,p3);
    }

    private String sanitizePostalCode(String inputString){
        inputString.replaceAll("[^0-9.]", "");
        if(inputString.length() > 6)
            inputString = inputString.substring(0,6);
        if(inputString.length() > 0)
            if(Integer.parseInt(inputString) >= 0 && Integer.parseInt(inputString) <= 2000000)
                return inputString;
        return "";
    }


    @Override
    public void onResume() {
        super.onResume();
        if(database.getPinCount() > 0){ // in case initial startup and onPause() hasn't been run yet
            ((EditText) findViewById(R.id.Pin1Input)).setText(database.getPin(1).getPostalcode());
            ((EditText) findViewById(R.id.Pin2Input)).setText(database.getPin(2).getPostalcode());
            ((EditText) findViewById(R.id.Pin3Input)).setText(database.getPin(3).getPostalcode());
            database.deleteAllPin();
        }
    }
}