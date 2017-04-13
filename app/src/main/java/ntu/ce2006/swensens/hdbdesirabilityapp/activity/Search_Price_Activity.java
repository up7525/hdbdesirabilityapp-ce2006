package ntu.ce2006.swensens.hdbdesirabilityapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;

import ntu.ce2006.swensens.hdbdesirabilityapp.R;

/**
 * @author Faith, Nicholas, Chester
 *
 */

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
        String minPriceString = sanitizePrice(((EditText) findViewById(R.id.MinPriceInput)).getText().toString());
        String maxPriceString = sanitizePrice(((EditText) findViewById(R.id.MaxPriceInput)).getText().toString());
        savePrices(maxPriceString, minPriceString);
    }

    private void savePrices(String maxPriceString, String minPriceString){
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
        restoreText((EditText) findViewById(R.id.MinPriceInput), "MinPriceInput");
        restoreText((EditText) findViewById(R.id.MaxPriceInput), "MaxPriceInput");
    }

    private void restoreText(EditText textField, String varName){
        if(!(load(varName)).equalsIgnoreCase("NULLSTRING"))
            textField.setText(load(varName));
        else
            textField.setText("");
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
