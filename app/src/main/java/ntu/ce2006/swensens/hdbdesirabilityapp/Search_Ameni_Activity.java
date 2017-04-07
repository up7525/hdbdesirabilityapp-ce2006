package ntu.ce2006.swensens.hdbdesirabilityapp;

import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;

import ntu.ce2006.swensens.hdbdesirabilityapp.R;

public class Search_Ameni_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__ameni_);
        setTitle("Set Amenities");
    }

    @Override
    public void onPause() {
        super.onPause();
        save(((CheckBox) findViewById(R.id.checkBoxMall)).isChecked(), "checkBoxMall");
        save(((CheckBox) findViewById(R.id.checkBoxMRT)).isChecked(), "checkBoxMRT");
        save(((CheckBox) findViewById(R.id.checkBoxClinic)).isChecked(), "checkBoxClinic");
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CheckBox) findViewById(R.id.checkBoxMall)).setChecked(load("checkBoxMall"));
        ((CheckBox) findViewById(R.id.checkBoxMRT)).setChecked(load("checkBoxMRT"));
        ((CheckBox) findViewById(R.id.checkBoxClinic)).setChecked(load("checkBoxClinic"));
    }

    private void save(boolean ch, String name) {
        getSharedPreferences("x",Context.MODE_PRIVATE).edit().putBoolean(name, ch).commit();
    }

    private boolean load(String name) {
        return getSharedPreferences("x",Context.MODE_PRIVATE).getBoolean(name,false);
    }
}