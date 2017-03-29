package ntu.ce2006.swensens.hdbdesirabilityapp.activity;

import android.icu.text.IDNA;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;

import ntu.ce2006.swensens.hdbdesirabilityapp.R;

public class SearchActivity extends AppCompatActivity {


    public Button locationButton;
    public Button priceButton;
    public Button sizeButton;
    public Button amenitiesButton;
    public ImageButton SearchButtonSmall;
    public ImageButton InfoButtonSmall;

    public void init(){

        // Code to be placed in RECEIVING class's init()
        Intent i = getIntent();

        // Create a new object, cast the intent's stored object
        // getIntExtra / getSerializable based on object type.
        // getExtra("<package name>.<object name>", <valueifobject is not found>);
        // setContentView / TextView is just formatting.
        int a = (int) i.getIntExtra("java.lang.Integer.integerObject",0);
        setContentView(R.layout.activity_search);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(Integer.toString(a));


        locationButton = (Button)findViewById(R.id.locationButton);
        locationButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                Intent location = new Intent(SearchActivity.this,Search_Loc_Activity.class);

                startActivity(location);
            }
        });

        priceButton = (Button)findViewById(R.id.priceButton);
        priceButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                Intent price = new Intent(SearchActivity.this,Search_Price_Activity.class);

                startActivity(price);
            }
        });

        sizeButton = (Button)findViewById(R.id.sizeButton);
        sizeButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                Intent size = new Intent(SearchActivity.this,Search_Size_Activity.class);

                startActivity(size);
            }
        });

        amenitiesButton = (Button)findViewById(R.id.amenitiesButton);
        amenitiesButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                Intent ameni = new Intent(SearchActivity.this,Search_Ameni_Activity.class);

                startActivity(ameni);
            }
        });

        SearchButtonSmall = (ImageButton) findViewById(R.id.SearchButtonSmall);
        SearchButtonSmall.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                Intent query = new Intent(SearchActivity.this,ResultsActivity.class);

                startActivity(query);
            }
        });
        InfoButtonSmall = (ImageButton) findViewById(R.id.InfoButtonSmall);
        InfoButtonSmall.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                Intent info = new Intent(SearchActivity.this,Info.class);

                startActivity(info);
            }
        });

    }

//    private Query createUserQuery(){
//        Query userQuery = new Query();
//
//        // load filter values
//
//        // load amenities
//        boolean Clinic = load("checkBoxClinic");
//        boolean MRT = load("checkBoxClinic");
//        boolean Mall = load("checkBoxMall");
//
//        // load locations
//        boolean AngMoKio = load("AngMoKio");
//        boolean Bedok = load("Bedok");
//        boolean Bishan = load("Bishan");
//        boolean BukitBatok = load("BukitBatok");
//        boolean BukitMerah = load("BukitMerah");
//        boolean BukitPanjang = load("BukitPanjang");
//        boolean BukitTimah = load("BukitTimah");
//        boolean CentralArea = load("CentralArea");
//        boolean ChoaChuKang = load("ChoaChuKang");
//        boolean Clementi = load("Clementi");
//        boolean Geylang = load("Geylang");
//        boolean Hougang = load("Hougang");
//        boolean JurongEast = load("JurongEast");
//        boolean JurongWest = load("JurongWest");
//        boolean KallangWhampoa = load("KallangWhampoa");
//        boolean MarineParade = load("MarineParade");
//        boolean PasirRis = load("PasirRis");
//        boolean Punggol = load("Punggol");
//        boolean Queenstown = load("Queenstown");
//        boolean Sembawang = load("Sembawang");
//        boolean Sengkang = load("Sengkang");
//        boolean Serangoon = load("Serangoon");
//        boolean Tampines = load("Tampines");
//        boolean ToaPayoh = load("ToaPayoh");
//        boolean Woodlands = load("Woodlands");
//        boolean Yishun = load("Yishun");
//
//        // load price
//        String MinPriceInput = loadString("MinPriceInput");
//        String MaxPriceInput = loadString("MaxPriceInput");
//
//        // load size
//        boolean booleanTwoRoomCheckBox = load("TwoRoomCheckBox");
//        boolean booleanThreeRoomCheckBox = load("ThreeRoomCheckBox");
//        boolean booleanFourRoomCheckBox = load("FourRoomCheckBox");
//        boolean booleanFiveRoomCheckBox = load("FiveRoomCheckBox");
//        boolean booleanExecutiveCheckBox = load("ExecutiveCheckBox");
//
//        // load pins
//        String Pin1Input = loadString("Pin1Input");
//        String Pin2Input = loadString("Pin2Input");
//        String Pin3Input = loadString("Pin3Input");
//
//        return userQuery;
//    }

    private boolean load(String name) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(name, false);
    }

    private String loadString(String name) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getString(name, "");
    }

    private void clearBoolean(String name){
        saveBoolean(false, name);
    }
    private void clearString(String name) {
        saveString(name,"");
    }

    private void saveBoolean(boolean boolValue, String name) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(name, boolValue);
        editor.commit();
    }

    private void saveString(String string, String name){
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, string);
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        String s = "Search";
        setTitle(s);
        init();

    }

    public void onDisplay(View v) {
        String s = "Cleared";
        Toast.makeText(SearchActivity.this,s,Toast.LENGTH_LONG).show();
    }
}
