package ntu.ce2006.swensens.hdbdesirabilityapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;

import ntu.ce2006.swensens.hdbdesirabilityapp.R;

/**
 * Search Price Activity allows user to choose minimum and maximum HDB prices
 * @author Faith, Nicholas, Chester
 *
 */

public class Search_Price_Activity extends AppCompatActivity {

    /**
     * initialisation of Search Price Activity
     * @param savedInstanceState restore the activity state to a previous state using the data stored in this bundle if it exists
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__price_);
        setTitle("Set Price");
    }

    /**
     * when the activity is paused, the prices are saved
     */
    @Override
    public void onPause() {
        super.onPause();
        String minPriceString = sanitizePrice(((EditText) findViewById(R.id.MinPriceInput)).getText().toString());
        String maxPriceString = sanitizePrice(((EditText) findViewById(R.id.MaxPriceInput)).getText().toString());
        savePrices(maxPriceString, minPriceString);
    }

    /**
     * this saves the prices
     * @param maxPriceString maximum price
     * @param minPriceString minimum price
     */
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

    /**
     * this converts the price input by user into proper range
     * @param inputString price entered by user
     * @return price within proper range
     */
    private String sanitizePrice(String inputString){
        inputString.replaceAll("[^0-9.]", "");
        if(inputString.length() == 0)
            return "NULLSTRING";
        long price = Integer.parseInt(inputString);
        if(price > 2000000)
            return "2000000";
        if(price < 0)
            return "0";
        return Long.toString(price);
    }

    /**
     * when the activity is resumed, the prices input by user are restored
     */
    @Override
    public void onResume() {
        super.onResume();
        restoreText((EditText) findViewById(R.id.MinPriceInput), "MinPriceInput");
        restoreText((EditText) findViewById(R.id.MaxPriceInput), "MaxPriceInput");
    }

    /**
     * this restores the price input by the user
     * @param textField name of textbox to be set
     * @param varName name of variable used to store the price input by user in SharedPreferences
     */
    private void restoreText(EditText textField, String varName){
        if(!(load(varName)).equalsIgnoreCase("NULLSTRING"))
            textField.setText(load(varName));
        else
            textField.setText("");
    }

    /**
     * this saves the string input by the user
     * @param itemName name of string to be saved
     * @param itemString actual string to be saved
     */
    private void save(String itemName, String itemString) {
        SharedPreferences sharedPreferences = getSharedPreferences("x",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(itemName,itemString);
        editor.commit();
    }

    /**
     * this returns the stored string that was saved
     * @param itemName name of string that was saved
     * @return stored string that was saved
     */
    private String load(String itemName) {
        SharedPreferences sharedPreferences = getSharedPreferences("x",Context.MODE_PRIVATE);
        return sharedPreferences.getString(itemName,"NULLSTRING");
    }
}
