package ntu.ce2006.swensens.hdbdesirabilityapp;

import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;

import ntu.ce2006.swensens.hdbdesirabilityapp.R;

public class Search_Price_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__price_);
        setTitle("Set Price");
    }

    @Override
    public void onPause() {
        super.onPause();

        // item = text field
        String minPriceString = ((EditText) findViewById(R.id.MinPriceInput)).getText().toString();
        minPriceString = sanitizePrice(minPriceString);

        // item = text field
        String maxPriceString = ((EditText) findViewById(R.id.MaxPriceInput)).getText().toString();
        maxPriceString = sanitizePrice(maxPriceString);

        // "NULLSTRING" = user has not inputted price

        if(minPriceString.equalsIgnoreCase("NULLSTRING") | maxPriceString.equalsIgnoreCase("NULLSTRING")){
            if(minPriceString.equalsIgnoreCase("NULLSTRING") & maxPriceString.equalsIgnoreCase("NULLSTRING")){
                save("MinPriceInput","NULLSTRING");
                save("MaxPriceInput","NULLSTRING");
            }
            else if(minPriceString.equalsIgnoreCase("NULLSTRING")){
                save("MinPriceInput","NULLSTRING");
                save("MaxPriceInput", maxPriceString);
            }
            else if(maxPriceString.equalsIgnoreCase("NULLSTRING")){
                save("MinPriceInput",minPriceString);
                save("MaxPriceInput","NULLSTRING");
            }
        }
        else if(Integer.parseInt(maxPriceString) > Integer.parseInt(minPriceString)) {
            save("MinPriceInput", minPriceString);
            save("MaxPriceInput", maxPriceString);
        }
        else if (Integer.parseInt(maxPriceString) < Integer.parseInt(minPriceString)){
            save("MinPriceInput", maxPriceString);
            save("MaxPriceInput", minPriceString);
        }
        else{
            if(Integer.parseInt(minPriceString) > 0) {
                save("MinPriceInput", Integer.toString(Integer.parseInt(minPriceString) - 1));
                save("MaxPriceInput", minPriceString);
            }
            else{
                save("MinPriceInput", Integer.toString(0));
                save("MaxPriceInput", Integer.toString(1));
            }

        }
    }

    private String sanitizePrice(String inputString){
        // convert user input into proper range.
        // 1. Remove non-numeric characters.
        // 2. If user has not inputted price, save as "NULLSTRING"
        // 3. If user's inputted price exceeds max, save as 2,000,000
        // 4. If user's inputted price is below min, save as 0

        // remove non-numeric characters
        inputString.replaceAll("[^0-9.]", "");

        if(inputString.length() == 0)
            return "NULLSTRING";

        // convert to number
        long price = Integer.parseInt(inputString);

        // set within limits
        if(price > 2000000)
            return "2000000";
        if(price < 0)
            return "0";

        return Long.toString(price);
    }


    @Override
    public void onResume() {
        super.onResume();

        EditText tempEditText;
        String savedString;

        // item = text field with number input
        tempEditText = (EditText) findViewById(R.id.MinPriceInput);
        savedString = load("MinPriceInput");
        if(!savedString.equalsIgnoreCase("NULLSTRING"))
            tempEditText.setText(savedString);
        else
            tempEditText.setText("");

        // item = text field with number input
        tempEditText = (EditText) findViewById(R.id.MaxPriceInput);
        savedString = load("MaxPriceInput");
        if(!savedString.equalsIgnoreCase("NULLSTRING"))
            tempEditText.setText(savedString);
        else
            tempEditText.setText("");
    }

    private void save(String itemName, String itemString) {
        // item = int
        SharedPreferences sharedPreferences = getSharedPreferences("x",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(itemName,itemString);
        editor.commit();
    }

    private String load(String itemName) {
        SharedPreferences sharedPreferences = getSharedPreferences("x",Context.MODE_PRIVATE);
        return sharedPreferences.getString(itemName,"NULLSTRING");
    }
}
