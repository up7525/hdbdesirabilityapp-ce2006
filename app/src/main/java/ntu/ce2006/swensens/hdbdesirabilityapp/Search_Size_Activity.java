package ntu.ce2006.swensens.hdbdesirabilityapp;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;

import ntu.ce2006.swensens.hdbdesirabilityapp.R;

public class Search_Size_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__size_);
        String s = "Set Size";
        setTitle(s);
    }

    @Override
    public void onPause() {
        super.onPause();
        boolean TwoRoomCheckBox = ((CheckBox) findViewById(R.id.TwoRoomCheckBox)).isChecked();
        save(TwoRoomCheckBox, "TwoRoomCheckBox");
        boolean ThreeRoomCheckBox = ((CheckBox) findViewById(R.id.ThreeRoomCheckBox)).isChecked();
        save(ThreeRoomCheckBox, "ThreeRoomCheckBox");
        boolean FourRoomCheckBox = ((CheckBox) findViewById(R.id.FourRoomCheckBox)).isChecked();
        save(FourRoomCheckBox, "FourRoomCheckBox");
        boolean FiveRoomCheckBox = ((CheckBox) findViewById(R.id.FiveRoomCheckBox)).isChecked();
        save(FiveRoomCheckBox, "FiveRoomCheckBox");
        boolean ExecutiveCheckBox = ((CheckBox) findViewById(R.id.ExecutiveCheckBox)).isChecked();
        save(ExecutiveCheckBox, "ExecutiveCheckBox");
    }

    @Override
    public void onResume() {
        super.onResume();

        CheckBox checkboxTwoRoomCheckBox = (CheckBox) findViewById(R.id.TwoRoomCheckBox);
        boolean booleanTwoRoomCheckBox = checkboxTwoRoomCheckBox.isChecked();
        checkboxTwoRoomCheckBox.setChecked(load("TwoRoomCheckBox"));
        CheckBox checkboxThreeRoomCheckBox = (CheckBox) findViewById(R.id.ThreeRoomCheckBox);
        boolean booleanThreeRoomCheckBox = checkboxThreeRoomCheckBox.isChecked();
        checkboxThreeRoomCheckBox.setChecked(load("ThreeRoomCheckBox"));
        CheckBox checkboxFourRoomCheckBox = (CheckBox) findViewById(R.id.FourRoomCheckBox);
        boolean booleanFourRoomCheckBox = checkboxFourRoomCheckBox.isChecked();
        checkboxFourRoomCheckBox.setChecked(load("FourRoomCheckBox"));
        CheckBox checkboxFiveRoomCheckBox = (CheckBox) findViewById(R.id.FiveRoomCheckBox);
        boolean booleanFiveRoomCheckBox = checkboxFiveRoomCheckBox.isChecked();
        checkboxFiveRoomCheckBox.setChecked(load("FiveRoomCheckBox"));
        CheckBox checkboxExecutiveCheckBox = (CheckBox) findViewById(R.id.ExecutiveCheckBox);
        boolean booleanExecutiveCheckBox = checkboxExecutiveCheckBox.isChecked();
        checkboxExecutiveCheckBox.setChecked(load("ExecutiveCheckBox"));
    }

    private void save(boolean ch, String name) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(name, ch);
        editor.commit();
    }

    private boolean load(String name) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(name, false);
    }
}
