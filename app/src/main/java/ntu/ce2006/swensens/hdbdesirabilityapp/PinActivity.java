package ntu.ce2006.swensens.hdbdesirabilityapp;
import android.app.AlertDialog.*;
import android.content.*;
import android.os.*;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;

import java.util.List;

import ntu.ce2006.swensens.hdbdesirabilityapp.data.db.dbconfig.DbHandler;
import ntu.ce2006.swensens.hdbdesirabilityapp.pin.Pin;

import static ntu.ce2006.swensens.hdbdesirabilityapp.R.id.ClearButtonSmall;


public class PinActivity extends AppCompatActivity {

    public ImageButton ClearButtonSmall;
    public ImageButton InfoButtonSmall;
    public DbHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        setTitle("Pins");
        Context context = getApplicationContext();
        database = new DbHandler(context);
        init();
    }
    public void init(){
        ClearButtonSmall = (ImageButton) findViewById(R.id.ClearButtonSmall2);
        ClearButtonSmall.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
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

                showAlert(v);
            }
        });

    }
    public void showAlert(View v) {
        AlertDialog.Builder info = new AlertDialog.Builder(this);
        String alert1 = "Enter postal code of desired locations.";
        String alert2 = "Application will calculate the distance between the pinned locations and the flats searched.";
        info.setMessage(alert1+"\n"+"\n"+"\n"+
                alert2
        ).create();
        info.show();

    }
    @Override
    public void onPause() {
        super.onPause();

        String itemString;

        // item = text field
        itemString = ((EditText) findViewById(R.id.Pin1Input)).getText().toString();
        itemString = sanitizePostalCode(itemString);
        Pin newPin = new Pin(0,itemString,itemString);
        database.addPin(newPin);
        // save("Pin1Input",itemString);

//        // item = text field
//        itemString = ((EditText) findViewById(R.id.Pin2Input)).getText().toString();
//        itemString = sanitizePostalCode(itemString);
//        newPin = new Pin(0,itemString,itemString);
//        database.addPin(newPin);
//        // save("Pin2Input",itemString);
//
//        // item = text field
//        itemString = ((EditText) findViewById(R.id.Pin3Input)).getText().toString();
//        itemString = sanitizePostalCode(itemString);
//        newPin = new Pin(0,itemString,itemString);
//        database.addPin(newPin);
//        // save("Pin3Input",itemString);
    }

    private String sanitizePostalCode(String inputString){

        // remove non-numeric characters
        inputString.replaceAll("[^0-9.]", "");

        // truncate to 6 numbers only
        if(inputString.length() > 6)
            inputString = inputString.substring(0,6);

        if(inputString.length() > 0)
            if(Integer.parseInt(inputString) >= 0 && Integer.parseInt(inputString) <= 999999)
                return inputString;

        return "";
    }


    @Override
    public void onResume() {
        super.onResume();

        EditText tempEditText;
        String savedString;

        List<Pin> listOfPins = database.getAllPins();

        // item = text field with number input
        tempEditText = (EditText) findViewById(R.id.Pin1Input);
        // savedString = load("Pin1Input");
        savedString =listOfPins.get(0).getPostalcode();
        tempEditText.setText(savedString);

//        // item = text field with number input
//        tempEditText = (EditText) findViewById(R.id.Pin2Input);
//        // savedString = load("Pin2Input");
//        savedString =listOfPins.get(1).getPostalcode();
//        tempEditText.setText(savedString);
//
//        // item = text field with number input
//        tempEditText = (EditText) findViewById(R.id.Pin3Input);
//        // savedString = load("Pin3Input");
//        savedString =listOfPins.get(2).getPostalcode();
//        tempEditText.setText(savedString);
    }

    private void save(String itemName, String itemString) {
        // item = int
        SharedPreferences sharedPreferences = getSharedPreferences("Pins",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(itemName,itemString);
        editor.commit();
    }

    private String load(String itemName) {
        SharedPreferences sharedPreferences = getSharedPreferences("Pins",Context.MODE_PRIVATE);
        return sharedPreferences.getString(itemName,"");
    }

}