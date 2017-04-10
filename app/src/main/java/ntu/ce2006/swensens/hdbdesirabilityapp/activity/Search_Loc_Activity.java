package ntu.ce2006.swensens.hdbdesirabilityapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;

import ntu.ce2006.swensens.hdbdesirabilityapp.R;

public class Search_Loc_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__loc_);
        setTitle("Set Locations");
    }

    @Override
    public void onPause() {
        super.onPause();
        save(((CheckBox) findViewById(R.id.AngMoKio)).isChecked(), "AngMoKio");
        save(((CheckBox) findViewById(R.id.Bedok)).isChecked(), "Bedok");
        save(((CheckBox) findViewById(R.id.Bishan)).isChecked(), "Bishan");
        save(((CheckBox) findViewById(R.id.BukitBatok)).isChecked(), "BukitBatok");
        save(((CheckBox) findViewById(R.id.BukitMerah)).isChecked(), "BukitMerah");
        save(((CheckBox) findViewById(R.id.BukitPanjang)).isChecked(), "BukitPanjang");
        save(((CheckBox) findViewById(R.id.BukitTimah)).isChecked(), "BukitTimah");
        save(((CheckBox) findViewById(R.id.CentralArea)).isChecked(), "CentralArea");
        save(((CheckBox) findViewById(R.id.ChoaChuKang)).isChecked(), "ChoaChuKang");
        save(((CheckBox) findViewById(R.id.Clementi)).isChecked(), "Clementi");
        save(((CheckBox) findViewById(R.id.Geylang)).isChecked(), "Geylang");
        save(((CheckBox) findViewById(R.id.Hougang)).isChecked(), "Hougang");
        save(((CheckBox) findViewById(R.id.JurongEast)).isChecked(), "JurongEast");
        save(((CheckBox) findViewById(R.id.JurongWest)).isChecked(), "JurongWest");
        save(((CheckBox) findViewById(R.id.KallangWhampoa)).isChecked(), "KallangWhampoa");
        save(((CheckBox) findViewById(R.id.MarineParade)).isChecked(), "MarineParade");
        save(((CheckBox) findViewById(R.id.PasirRis)).isChecked(), "PasirRis");
        save(((CheckBox) findViewById(R.id.Punggol)).isChecked(), "Punggol");
        save(((CheckBox) findViewById(R.id.Queenstown)).isChecked(), "Queenstown");
        save(((CheckBox) findViewById(R.id.Sembawang)).isChecked(), "Sembawang");
        save(((CheckBox) findViewById(R.id.Sengkang)).isChecked(), "Sengkang");
        save(((CheckBox) findViewById(R.id.Serangoon)).isChecked(), "Serangoon");
        save(((CheckBox) findViewById(R.id.Tampines)).isChecked(), "Tampines");
        save(((CheckBox) findViewById(R.id.ToaPayoh)).isChecked(), "ToaPayoh");
        save(((CheckBox) findViewById(R.id.Woodlands)).isChecked(), "Woodlands");
        save(((CheckBox) findViewById(R.id.Yishun)).isChecked(), "Yishun");
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CheckBox) findViewById(R.id.AngMoKio)).setChecked(load("AngMoKio"));
        ((CheckBox) findViewById(R.id.Bedok)).setChecked(load("Bedok"));
        ((CheckBox) findViewById(R.id.Bishan)).setChecked(load("Bishan"));
        ((CheckBox) findViewById(R.id.BukitBatok)).setChecked(load("BukitBatok"));
        ((CheckBox) findViewById(R.id.BukitMerah)).setChecked(load("BukitMerah"));
        ((CheckBox) findViewById(R.id.BukitPanjang)).setChecked(load("BukitPanjang"));
        ((CheckBox) findViewById(R.id.BukitTimah)).setChecked(load("BukitTimah"));
        ((CheckBox) findViewById(R.id.CentralArea)).setChecked(load("CentralArea"));
        ((CheckBox) findViewById(R.id.ChoaChuKang)).setChecked(load("ChoaChuKang"));
        ((CheckBox) findViewById(R.id.Clementi)).setChecked(load("Clementi"));
        ((CheckBox) findViewById(R.id.Geylang)).setChecked(load("Geylang"));
        ((CheckBox) findViewById(R.id.Hougang)).setChecked(load("Hougang"));
        ((CheckBox) findViewById(R.id.JurongEast)).setChecked(load("JurongEast"));
        ((CheckBox) findViewById(R.id.JurongWest)).setChecked(load("JurongWest"));
        ((CheckBox) findViewById(R.id.KallangWhampoa)).setChecked(load("KallangWhampoa"));
        ((CheckBox) findViewById(R.id.MarineParade)).setChecked(load("MarineParade"));
        ((CheckBox) findViewById(R.id.PasirRis)).setChecked(load("PasirRis"));
        ((CheckBox) findViewById(R.id.Punggol)).setChecked(load("Punggol"));
        ((CheckBox) findViewById(R.id.Queenstown)).setChecked(load("Queenstown"));
        ((CheckBox) findViewById(R.id.Sembawang)).setChecked(load("Sembawang"));
        ((CheckBox) findViewById(R.id.Sengkang)).setChecked(load("Sengkang"));
        ((CheckBox) findViewById(R.id.Serangoon)).setChecked(load("Serangoon"));
        ((CheckBox) findViewById(R.id.Tampines)).setChecked(load("Tampines"));
        ((CheckBox) findViewById(R.id.ToaPayoh)).setChecked(load("ToaPayoh"));
        ((CheckBox) findViewById(R.id.Woodlands)).setChecked(load("Woodlands"));
        ((CheckBox) findViewById(R.id.Yishun)).setChecked(load("Yishun"));
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