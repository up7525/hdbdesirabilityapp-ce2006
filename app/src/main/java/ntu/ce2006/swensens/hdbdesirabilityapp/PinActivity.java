package ntu.ce2006.swensens.hdbdesirabilityapp;
import android.app.AlertDialog.*;
import android.content.*;
import android.os.*;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import static ntu.ce2006.swensens.hdbdesirabilityapp.R.id.ClearButtonSmall;


public class PinActivity extends AppCompatActivity {

    public ImageButton ClearButtonSmall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                ((EditText) findViewById(R.id.Pin1Input)).setText("");
                ((EditText) findViewById(R.id.Pin2Input)).setText("");
                ((EditText) findViewById(R.id.Pin3Input)).setText("");
                Toast.makeText(PinActivity.this,"Cleared Pins.",Toast.LENGTH_LONG).show();
            }
        });
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