package ntu.ce2006.swensens.hdbdesirabilityapp.activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;

import ntu.ce2006.swensens.hdbdesirabilityapp.R;

/**
 * @author Faith, Nicholas, Chester
 *
 */

public class Search_Size_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__size_);
        setTitle("Set Size");
    }

    @Override
    public void onPause() {
        super.onPause();
        save(((CheckBox) findViewById(R.id.TwoRoomCheckBox)).isChecked(), "TwoRoomCheckBox");
        save(((CheckBox) findViewById(R.id.ThreeRoomCheckBox)).isChecked(), "ThreeRoomCheckBox");
        save(((CheckBox) findViewById(R.id.FourRoomCheckBox)).isChecked(), "FourRoomCheckBox");
        save(((CheckBox) findViewById(R.id.FiveRoomCheckBox)).isChecked(), "FiveRoomCheckBox");
        save(((CheckBox) findViewById(R.id.ExecutiveCheckBox)).isChecked(), "ExecutiveCheckBox");
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CheckBox) findViewById(R.id.TwoRoomCheckBox)).setChecked(load("TwoRoomCheckBox"));
        ((CheckBox) findViewById(R.id.ThreeRoomCheckBox)).setChecked(load("ThreeRoomCheckBox"));
        ((CheckBox) findViewById(R.id.FourRoomCheckBox)).setChecked(load("FourRoomCheckBox"));
        ((CheckBox) findViewById(R.id.FiveRoomCheckBox)).setChecked(load("FiveRoomCheckBox"));
        ((CheckBox) findViewById(R.id.ExecutiveCheckBox)).setChecked(load("ExecutiveCheckBox"));
    }

    private void save(boolean ch, String name) {
        getSharedPreferences("x",Context.MODE_PRIVATE).edit().putBoolean(name,ch).commit();
    }

    private boolean load(String name) {
        return getSharedPreferences("x",Context.MODE_PRIVATE).getBoolean(name, false);
    }
}
