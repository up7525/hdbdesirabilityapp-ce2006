package ntu.ce2006.swensens.hdbdesirabilityapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;

import ntu.ce2006.swensens.hdbdesirabilityapp.R;

public class PinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        String s = "Pins";
        setTitle(s);
    }

    @Override
    public void onPause() {
        super.onPause();

        String itemString;

        // item = text field
        itemString = ((EditText) findViewById(R.id.Pin1Input)).getText().toString();
        itemString = sanitizePostalCode(itemString);
        save("Pin1Input",itemString);

        // item = text field
        itemString = ((EditText) findViewById(R.id.Pin2Input)).getText().toString();
        itemString = sanitizePostalCode(itemString);
        save("Pin2Input",itemString);

        // item = text field
        itemString = ((EditText) findViewById(R.id.Pin3Input)).getText().toString();
        itemString = sanitizePostalCode(itemString);
        save("Pin3Input",itemString);
    }

    private String sanitizePostalCode(String inputString){

        // remove non-numeric characters
        inputString.replaceAll("[^0-9.]", "");

        // truncate to 6 numbers only
        if(inputString.length() > 6)
            inputString = inputString.substring(0,6);

        if(Integer.parseInt(inputString) >= 0 && Integer.parseInt(inputString) <= 999999)
            return inputString;

        return "";
    }


    @Override
    public void onResume() {
        super.onResume();

        EditText tempEditText;
        String savedString;

        // item = text field with number input
        tempEditText = (EditText) findViewById(R.id.Pin1Input);
        savedString = load("Pin1Input");
        tempEditText.setText(savedString);

        // item = text field with number input
        tempEditText = (EditText) findViewById(R.id.Pin2Input);
        savedString = load("Pin2Input");
        tempEditText.setText(savedString);

        // item = text field with number input
        tempEditText = (EditText) findViewById(R.id.Pin3Input);
        savedString = load("Pin3Input");
        tempEditText.setText(savedString);
    }

    private void save(String itemName, String itemString) {
        // item = int
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(itemName,itemString);
        editor.commit();
    }

    private String load(String itemName) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getString(itemName,"hello");
    }
}