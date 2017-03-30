package com.example.nicholas.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;

public class Search_Loc_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__loc_);
    }

    @Override
    public void onPause() {
        super.onPause();
        boolean AngMoKio = ((CheckBox) findViewById(R.id.AngMoKio)).isChecked();

        save(AngMoKio, "AngMoKio");

        boolean Bedok = ((CheckBox) findViewById(R.id.Bedok)).isChecked();

        save(Bedok, "Bedok");

        boolean Bishan = ((CheckBox) findViewById(R.id.Bishan)).isChecked();

        save(Bishan, "Bishan");

        boolean BukitBatok = ((CheckBox) findViewById(R.id.BukitBatok)).isChecked();

        save(BukitBatok, "BukitBatok");

        boolean BukitMerah = ((CheckBox) findViewById(R.id.BukitMerah)).isChecked();

        save(BukitMerah, "BukitMerah");

        boolean BukitPanjang = ((CheckBox) findViewById(R.id.BukitPanjang)).isChecked();

        save(BukitPanjang, "BukitPanjang");

        boolean BukitTimah = ((CheckBox) findViewById(R.id.BukitTimah)).isChecked();

        save(BukitTimah, "BukitTimah");

        boolean CentralArea = ((CheckBox) findViewById(R.id.CentralArea)).isChecked();

        save(CentralArea, "CentralArea");

        boolean ChoaChuKang = ((CheckBox) findViewById(R.id.ChoaChuKang)).isChecked();

        save(ChoaChuKang, "ChoaChuKang");

        boolean Clementi = ((CheckBox) findViewById(R.id.Clementi)).isChecked();

        save(Clementi, "Clementi");

        boolean Geylang = ((CheckBox) findViewById(R.id.Geylang)).isChecked();

        save(Geylang, "Geylang");

        boolean Hougang = ((CheckBox) findViewById(R.id.Hougang)).isChecked();

        save(Hougang, "Hougang");

        boolean JurongEast = ((CheckBox) findViewById(R.id.JurongEast)).isChecked();

        save(JurongEast, "JurongEast");

        boolean JurongWest = ((CheckBox) findViewById(R.id.JurongWest)).isChecked();

        save(JurongWest, "JurongWest");

        boolean KallangWhampoa = ((CheckBox) findViewById(R.id.KallangWhampoa)).isChecked();

        save(KallangWhampoa, "KallangWhampoa");

        boolean MarineParade = ((CheckBox) findViewById(R.id.MarineParade)).isChecked();

        save(MarineParade, "MarineParade");

        boolean PasirRis = ((CheckBox) findViewById(R.id.PasirRis)).isChecked();

        save(PasirRis, "PasirRis");

        boolean Punggol = ((CheckBox) findViewById(R.id.Punggol)).isChecked();

        save(Punggol, "Punggol");

        boolean Queenstown = ((CheckBox) findViewById(R.id.Queenstown)).isChecked();

        save(Queenstown, "Queenstown");

        boolean Sembawang = ((CheckBox) findViewById(R.id.Sembawang)).isChecked();

        save(Sembawang, "Sembawang");

        boolean Sengkang = ((CheckBox) findViewById(R.id.Sengkang)).isChecked();

        save(Sengkang, "Sengkang");

        boolean Serangoon = ((CheckBox) findViewById(R.id.Serangoon)).isChecked();

        save(Serangoon, "Serangoon");

        boolean Tampines = ((CheckBox) findViewById(R.id.Tampines)).isChecked();

        save(Tampines, "Tampines");

        boolean ToaPayoh = ((CheckBox) findViewById(R.id.ToaPayoh)).isChecked();

        save(ToaPayoh, "ToaPayoh");

        boolean Woodlands = ((CheckBox) findViewById(R.id.Woodlands)).isChecked();

        save(Woodlands, "Woodlands");

        boolean Yishun = ((CheckBox) findViewById(R.id.Yishun)).isChecked();

        save(Yishun, "Yishun");
    }

    @Override
    public void onResume() {
        super.onResume();

        CheckBox checkBoxAngMoKio2 = (CheckBox) findViewById(R.id.AngMoKio);
        boolean AngMoKio = checkBoxAngMoKio2.isChecked();
        checkBoxAngMoKio2.setChecked(load("AngMoKio"));

        CheckBox checkBoxBedok2 = (CheckBox) findViewById(R.id.Bedok);
        boolean Bedok = checkBoxBedok2.isChecked();
        checkBoxBedok2.setChecked(load("Bedok"));

        CheckBox checkBoxBishan2 = (CheckBox) findViewById(R.id.Bishan);
        boolean Bishan = checkBoxBishan2.isChecked();
        checkBoxBishan2.setChecked(load("Bishan"));

        CheckBox checkBoxBukitBatok2 = (CheckBox) findViewById(R.id.BukitBatok);
        boolean BukitBatok = checkBoxBukitBatok2.isChecked();
        checkBoxBukitBatok2.setChecked(load("BukitBatok"));

        CheckBox checkBoxBukitMerah2 = (CheckBox) findViewById(R.id.BukitMerah);
        boolean BukitMerah = checkBoxBukitMerah2.isChecked();
        checkBoxBukitMerah2.setChecked(load("BukitMerah"));

        CheckBox checkBoxBukitPanjang2 = (CheckBox) findViewById(R.id.BukitPanjang);
        boolean BukitPanjang = checkBoxBukitPanjang2.isChecked();
        checkBoxBukitPanjang2.setChecked(load("BukitPanjang"));

        CheckBox checkBoxBukitTimah2 = (CheckBox) findViewById(R.id.BukitTimah);
        boolean BukitTimah = checkBoxBukitTimah2.isChecked();
        checkBoxBukitTimah2.setChecked(load("BukitTimah"));

        CheckBox checkBoxCentralArea2 = (CheckBox) findViewById(R.id.CentralArea);
        boolean CentralArea = checkBoxCentralArea2.isChecked();
        checkBoxCentralArea2.setChecked(load("CentralArea"));

        CheckBox checkBoxChoaChuKang2 = (CheckBox) findViewById(R.id.ChoaChuKang);
        boolean ChoaChuKang = checkBoxChoaChuKang2.isChecked();
        checkBoxChoaChuKang2.setChecked(load("ChoaChuKang"));

        CheckBox checkBoxClementi2 = (CheckBox) findViewById(R.id.Clementi);
        boolean Clementi = checkBoxClementi2.isChecked();
        checkBoxClementi2.setChecked(load("Clementi"));

        CheckBox checkBoxGeylang2 = (CheckBox) findViewById(R.id.Geylang);
        boolean Geylang = checkBoxGeylang2.isChecked();
        checkBoxGeylang2.setChecked(load("Geylang"));

        CheckBox checkBoxHougang2 = (CheckBox) findViewById(R.id.Hougang);
        boolean Hougang = checkBoxHougang2.isChecked();
        checkBoxHougang2.setChecked(load("Hougang"));

        CheckBox checkBoxJurongEast2 = (CheckBox) findViewById(R.id.JurongEast);
        boolean JurongEast = checkBoxJurongEast2.isChecked();
        checkBoxJurongEast2.setChecked(load("JurongEast"));

        CheckBox checkBoxJurongWest2 = (CheckBox) findViewById(R.id.JurongWest);
        boolean JurongWest = checkBoxJurongWest2.isChecked();
        checkBoxJurongWest2.setChecked(load("JurongWest"));

        CheckBox checkBoxKallangWhampoa2 = (CheckBox) findViewById(R.id.KallangWhampoa);
        boolean KallangWhampoa = checkBoxKallangWhampoa2.isChecked();
        checkBoxKallangWhampoa2.setChecked(load("KallangWhampoa"));

        CheckBox checkBoxMarineParade2 = (CheckBox) findViewById(R.id.MarineParade);
        boolean MarineParade = checkBoxMarineParade2.isChecked();
        checkBoxMarineParade2.setChecked(load("MarineParade"));

        CheckBox checkBoxPasirRis2 = (CheckBox) findViewById(R.id.PasirRis);
        boolean PasirRis = checkBoxPasirRis2.isChecked();
        checkBoxPasirRis2.setChecked(load("PasirRis"));

        CheckBox checkBoxPunggol2 = (CheckBox) findViewById(R.id.Punggol);
        boolean Punggol = checkBoxPunggol2.isChecked();
        checkBoxPunggol2.setChecked(load("Punggol"));

        CheckBox checkBoxQueenstown2 = (CheckBox) findViewById(R.id.Queenstown);
        boolean Queenstown = checkBoxQueenstown2.isChecked();
        checkBoxQueenstown2.setChecked(load("Queenstown"));

        CheckBox checkBoxSembawang2 = (CheckBox) findViewById(R.id.Sembawang);
        boolean Sembawang = checkBoxSembawang2.isChecked();
        checkBoxSembawang2.setChecked(load("Sembawang"));

        CheckBox checkBoxSengkang2 = (CheckBox) findViewById(R.id.Sengkang);
        boolean Sengkang = checkBoxSengkang2.isChecked();
        checkBoxSengkang2.setChecked(load("Sengkang"));

        CheckBox checkBoxSerangoon2 = (CheckBox) findViewById(R.id.Serangoon);
        boolean Serangoon = checkBoxSerangoon2.isChecked();
        checkBoxSerangoon2.setChecked(load("Serangoon"));

        CheckBox checkBoxTampines2 = (CheckBox) findViewById(R.id.Tampines);
        boolean Tampines = checkBoxTampines2.isChecked();
        checkBoxTampines2.setChecked(load("Tampines"));

        CheckBox checkBoxToaPayoh2 = (CheckBox) findViewById(R.id.ToaPayoh);
        boolean ToaPayoh = checkBoxToaPayoh2.isChecked();
        checkBoxToaPayoh2.setChecked(load("ToaPayoh"));

        CheckBox checkBoxWoodlands2 = (CheckBox) findViewById(R.id.Woodlands);
        boolean Woodlands = checkBoxWoodlands2.isChecked();
        checkBoxWoodlands2.setChecked(load("Woodlands"));

        CheckBox checkBoxYishun2 = (CheckBox) findViewById(R.id.Yishun);
        boolean Yishun = checkBoxYishun2.isChecked();
        checkBoxYishun2.setChecked(load("Yishun"));
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