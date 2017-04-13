package ntu.ce2006.swensens.hdbdesirabilityapp.activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;

import ntu.ce2006.swensens.hdbdesirabilityapp.R;

/**
 * Search Size Activity allows user to choose HDB sizes
 * @author Faith, Nicholas, Chester
 *
 */

public class Search_Size_Activity extends AppCompatActivity {

    /**
     * initialisation of Search Size Activity
     * @param savedInstanceState restore the activity state to a previous state using the data stored in this bundle if it exists
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__size_);
        setTitle("Set Size");
    }

    /**
     * when the activity is paused, the state of the checkboxes are saved
     */
    @Override
    public void onPause() {
        super.onPause();
        save(((CheckBox) findViewById(R.id.TwoRoomCheckBox)).isChecked(), "TwoRoomCheckBox");
        save(((CheckBox) findViewById(R.id.ThreeRoomCheckBox)).isChecked(), "ThreeRoomCheckBox");
        save(((CheckBox) findViewById(R.id.FourRoomCheckBox)).isChecked(), "FourRoomCheckBox");
        save(((CheckBox) findViewById(R.id.FiveRoomCheckBox)).isChecked(), "FiveRoomCheckBox");
        save(((CheckBox) findViewById(R.id.ExecutiveCheckBox)).isChecked(), "ExecutiveCheckBox");
    }

    /**
     * when the activity is resumed, the state of the checkboxes are loaded
     */
    @Override
    public void onResume() {
        super.onResume();
        ((CheckBox) findViewById(R.id.TwoRoomCheckBox)).setChecked(load("TwoRoomCheckBox"));
        ((CheckBox) findViewById(R.id.ThreeRoomCheckBox)).setChecked(load("ThreeRoomCheckBox"));
        ((CheckBox) findViewById(R.id.FourRoomCheckBox)).setChecked(load("FourRoomCheckBox"));
        ((CheckBox) findViewById(R.id.FiveRoomCheckBox)).setChecked(load("FiveRoomCheckBox"));
        ((CheckBox) findViewById(R.id.ExecutiveCheckBox)).setChecked(load("ExecutiveCheckBox"));
    }

    /**
     * this saves the state of the checkbox through a SharedPreferences object
     * @param ch state of the checkbox
     * @param name name of the checkbox
     */
    private void save(boolean ch, String name) {
        getSharedPreferences("x",Context.MODE_PRIVATE).edit().putBoolean(name,ch).commit();
    }

    /**
     * this loads the state of the checkbox through a SharedPreferences object
     * @param name name of the checkbox
     * @return returns the state of the checkbox
     */
    private boolean load(String name) {
        return getSharedPreferences("x",Context.MODE_PRIVATE).getBoolean(name, false);
    }
}
