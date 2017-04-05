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
        boolean Mall = ((CheckBox) findViewById(R.id.checkBoxMall)).isChecked();
        save(Mall, "checkBoxMall");

        boolean MRT = ((CheckBox) findViewById(R.id.checkBoxMRT)).isChecked();
        save(MRT, "checkBoxMRT");

        boolean Clinic = ((CheckBox) findViewById(R.id.checkBoxClinic)).isChecked();
        save(Clinic, "checkBoxClinic");
    }

    @Override
    public void onResume() {
        super.onResume();

        CheckBox checkBoxMall2 = (CheckBox) findViewById(R.id.checkBoxMall);
        boolean Mall = checkBoxMall2.isChecked();
        checkBoxMall2.setChecked(load("checkBoxMall"));

        CheckBox checkBoxMRT2 = (CheckBox) findViewById(R.id.checkBoxMRT);
        boolean MRT = checkBoxMRT2.isChecked();
        checkBoxMRT2.setChecked(load("checkBoxMRT"));

        CheckBox checkBoxClinic2 = (CheckBox) findViewById(R.id.checkBoxClinic);
        boolean Clinic = checkBoxClinic2.isChecked();
        checkBoxClinic2.setChecked(load("checkBoxClinic"));
    }

    private void save(boolean ch, String name) {
        SharedPreferences sharedPreferences = getSharedPreferences("x",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(name, ch);
        editor.commit();
    }

    private boolean load(String name) {
        SharedPreferences sharedPreferences = getSharedPreferences("x",Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(name, false);
    }
}