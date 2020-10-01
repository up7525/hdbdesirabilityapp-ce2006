package ntu.ce2006.swensens.hdbdesirabilityapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;

import ntu.ce2006.swensens.hdbdesirabilityapp.R;

/**
 * Search Location Activity allows user to choose HDB locations
 * @author Faith, Nicholas, Chester
 *
 */

public class Search_Loc_Activity extends AppCompatActivity {

    /**
     * initialisation of Search Location Activity
     * @param savedInstanceState restore the activity state to a previous state using the data stored in this bundle if it exists
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__loc_);
        setTitle("Set Locations");
    }

    /**
     * when the activity is paused, the state of the checkboxes are saved
     */
    @Override
    public void onPause() {
        super.onPause();
        save(( findViewById(R.id.AngMoKio)).isChecked(), "AngMoKio");
        save(( findViewById(R.id.Bedok)).isChecked(), "Bedok");
        save(( findViewById(R.id.Bishan)).isChecked(), "Bishan");
        save(( findViewById(R.id.BukitBatok)).isChecked(), "BukitBatok");
        save(( findViewById(R.id.BukitMerah)).isChecked(), "BukitMerah");
        save(( findViewById(R.id.BukitPanjang)).isChecked(), "BukitPanjang");
        save(( findViewById(R.id.BukitTimah)).isChecked(), "BukitTimah");
        save(( findViewById(R.id.CentralArea)).isChecked(), "CentralArea");
        save(( findViewById(R.id.ChoaChuKang)).isChecked(), "ChoaChuKang");
        save(( findViewById(R.id.Clementi)).isChecked(), "Clementi");
        save(( findViewById(R.id.Geylang)).isChecked(), "Geylang");
        save(( findViewById(R.id.Hougang)).isChecked(), "Hougang");
        save(( findViewById(R.id.JurongEast)).isChecked(), "JurongEast");
        save(( findViewById(R.id.JurongWest)).isChecked(), "JurongWest");
        save(( findViewById(R.id.KallangWhampoa)).isChecked(), "KallangWhampoa");
        save(( findViewById(R.id.MarineParade)).isChecked(), "MarineParade");
        save(( findViewById(R.id.PasirRis)).isChecked(), "PasirRis");
        save(( findViewById(R.id.Punggol)).isChecked(), "Punggol");
        save(( findViewById(R.id.Queenstown)).isChecked(), "Queenstown");
        save(( findViewById(R.id.Sembawang)).isChecked(), "Sembawang");
        save(( findViewById(R.id.Sengkang)).isChecked(), "Sengkang");
        save(( findViewById(R.id.Serangoon)).isChecked(), "Serangoon");
        save(( findViewById(R.id.Tampines)).isChecked(), "Tampines");
        save(( findViewById(R.id.ToaPayoh)).isChecked(), "ToaPayoh");
        save(( findViewById(R.id.Woodlands)).isChecked(), "Woodlands");
        save(( findViewById(R.id.Yishun)).isChecked(), "Yishun");
    }

    /**
     * when the activity is resumed, the state of the checkboxes are loaded
     */
    @Override
    public void onResume() {
        super.onResume();
        ( findViewById(R.id.AngMoKio)).setChecked(load("AngMoKio"));
        ( findViewById(R.id.Bedok)).setChecked(load("Bedok"));
        ( findViewById(R.id.Bishan)).setChecked(load("Bishan"));
        ( findViewById(R.id.BukitBatok)).setChecked(load("BukitBatok"));
        ( findViewById(R.id.BukitMerah)).setChecked(load("BukitMerah"));
        ( findViewById(R.id.BukitPanjang)).setChecked(load("BukitPanjang"));
        ( findViewById(R.id.BukitTimah)).setChecked(load("BukitTimah"));
        ( findViewById(R.id.CentralArea)).setChecked(load("CentralArea"));
        ( findViewById(R.id.ChoaChuKang)).setChecked(load("ChoaChuKang"));
        ( findViewById(R.id.Clementi)).setChecked(load("Clementi"));
        ( findViewById(R.id.Geylang)).setChecked(load("Geylang"));
        ( findViewById(R.id.Hougang)).setChecked(load("Hougang"));
        ( findViewById(R.id.JurongEast)).setChecked(load("JurongEast"));
        ( findViewById(R.id.JurongWest)).setChecked(load("JurongWest"));
        ( findViewById(R.id.KallangWhampoa)).setChecked(load("KallangWhampoa"));
        ( findViewById(R.id.MarineParade)).setChecked(load("MarineParade"));
        ( findViewById(R.id.PasirRis)).setChecked(load("PasirRis"));
        ( findViewById(R.id.Punggol)).setChecked(load("Punggol"));
        ( findViewById(R.id.Queenstown)).setChecked(load("Queenstown"));
        ( findViewById(R.id.Sembawang)).setChecked(load("Sembawang"));
        ( findViewById(R.id.Sengkang)).setChecked(load("Sengkang"));
        ( findViewById(R.id.Serangoon)).setChecked(load("Serangoon"));
        ( findViewById(R.id.Tampines)).setChecked(load("Tampines"));
        ( findViewById(R.id.ToaPayoh)).setChecked(load("ToaPayoh"));
        ( findViewById(R.id.Woodlands)).setChecked(load("Woodlands"));
        ( findViewById(R.id.Yishun)).setChecked(load("Yishun"));
    }
    /**
     * this saves the state of the checkbox through a SharedPreferences object
     * @param ch state of the checkbox
     * @param name name of the checkbox
     */
    private void save(boolean ch, String name) {
        SharedPreferences sharedPreferences = getSharedPreferences("x",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(name, ch);
        editor.commit();
    }

    /**
     * this loads the state of the checkbox through a SharedPreferences object
     * @param name name of the checkbox
     * @return returns the state of the checkbox
     */
    private boolean load(String name) {
        SharedPreferences sharedPreferences = getSharedPreferences("x",Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(name, false);
    }


}
