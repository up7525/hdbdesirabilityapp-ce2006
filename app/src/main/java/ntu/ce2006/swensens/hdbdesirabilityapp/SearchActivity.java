package ntu.ce2006.swensens.hdbdesirabilityapp;

import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.content.*;
import android.widget.*;
import android.app.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

import ntu.ce2006.swensens.hdbdesirabilityapp.exceptions.APIErrorException;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Amenities;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Location;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Size;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.FlatManager ;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.result.Flat;

public class SearchActivity extends AppCompatActivity {

    // FOr logger
    private static final String TAG = "SearchActivity";

    public Button locationButton;
    public Button priceButton;
    public Button sizeButton;
    public Button amenitiesButton;
    public ImageButton SearchButtonSmall;
    public ImageButton InfoButtonSmall;
    public ImageButton ClearButtonSmall;
    public Query userQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("");
        init();
    }

    public void init(){
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

                if(checkIfUserInput())
                    userQuery = createUserQuery();
                else
                    userQuery = createDefaultQuery();

                FlatManager flatManager = new FlatManager(userQuery);
                List<Flat> listOfFlats;
                ArrayList<String> listOfFlatsString = new ArrayList<String>();

                try {

                    listOfFlats = flatManager.getFlats();

                    // convert listOfFlats to String BEFORE sending to ResultsActivity
                    for(int i = 0; i < listOfFlats.size(); i++){
                        listOfFlatsString.add(listOfFlats.get(i).toString());
                    }
                    if(listOfFlats.size() > 0) {
                        Intent intentFlat = new Intent(SearchActivity.this, ResultsActivity.class);
                        intentFlat.putStringArrayListExtra("java.util.List<java.lang.String>", listOfFlatsString);
                        startActivity(intentFlat);
                    }
                    else
                        noResultsFound(v);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    Log.d(TAG, "Exception", e);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        InfoButtonSmall = (ImageButton) findViewById(R.id.InfoButtonSmall);
        InfoButtonSmall.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                showAlert(v);
            }
        });
        ClearButtonSmall = (ImageButton) findViewById(R.id.ClearButtonSmall);
        ClearButtonSmall.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                String s = "Cleared all filters.";
                Toast.makeText(SearchActivity.this,s,Toast.LENGTH_LONG).show();
                SharedPreferences sharedPreferences = getSharedPreferences("x",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
            }
        });
    }

    public void noResultsFound(View v){
        AlertDialog.Builder info = new AlertDialog.Builder(this);
        info.setMessage("No results were found with your filters!").create();
        info.show();
    }

    public void showAlert(View v) {
        AlertDialog.Builder info = new AlertDialog.Builder(this);
        String alert1 = "Set the following filters:";
        String alert2 = "Location: Set town(s).";
        String alert3 = "Size: Select the room type.";
        String alert4 = "Price: Set the minimum and maximum prices";
        String alert5 = "Amenities: Choose nearby places of interest";
        info.setMessage(alert1+"\n"+"\n"+"\n"+
                alert2+"\n"+"\n"+
                alert3+"\n"+"\n"+
                alert4+"\n"+"\n"+
                alert5
        ).create();
        info.show();
    }

    private Query createDefaultQuery(){
        // load filter values
        ArrayList<Location> locationFilters = new ArrayList<>();
        ArrayList<Size> sizeFilters = new ArrayList<>();
        int[] priceFilters = new int[2];
        ArrayList<Amenities> amenitiesFilters = new ArrayList<>();

        // enable locations - ang mo kio only
        locationFilters.add(Location.ANG_MO_KIO);
//        locationFilters.add(Location.BEDOK);
//        locationFilters.add(Location.BISHAN);
//        locationFilters.add(Location.BUKIT_BATOK);
//        locationFilters.add(Location.BUKIT_MERAH);
//        locationFilters.add(Location.CHUA_CHU_KANG);
//        locationFilters.add(Location.CENTRAL_AREA);
//        locationFilters.add(Location.BUKIT_TIMAH);
//        locationFilters.add(Location.BUKIT_PANJANG);
//        locationFilters.add(Location.CLEMENTI);
//        locationFilters.add(Location.GEYLANG);
//        locationFilters.add(Location.HOUGANG);
//        locationFilters.add(Location.JURONG_EAST);
//        locationFilters.add(Location.JURONG_WEST);
//        locationFilters.add(Location.KALLANG_WHAMPOA);
//        locationFilters.add(Location.MARINE_PARADE);
//        locationFilters.add(Location.PASIR_RIS);
//        locationFilters.add(Location.PUNGGOL);
//        locationFilters.add(Location.QUEENSTOWN);
//        locationFilters.add(Location.SEMBAWANG);
//        locationFilters.add(Location.SENGKANG);
//        locationFilters.add(Location.SERANGOON);
//        locationFilters.add(Location.TAMPINES);
//        locationFilters.add(Location.TOA_PAYOH);
//        locationFilters.add(Location.WOODLANDS);
//        locationFilters.add(Location.YISHUN);

        // enable rooms - 2 room only
          sizeFilters.add(Size.ROOM_2);
//        sizeFilters.add(Size.ROOM_3);
//        sizeFilters.add(Size.ROOM_4);
//        sizeFilters.add(Size.ROOM_5);
//        sizeFilters.add(Size.EXECUTIVE);

        // default prices - 300k to 400k
        priceFilters[0] = 300000;
        priceFilters[1] = 400000;

        // enable ALL amenities
        amenitiesFilters.add(Amenities.CLINIC);
        amenitiesFilters.add(Amenities.MRT);
        amenitiesFilters.add(Amenities.MALL);

        Query query = new Query.Builder().locations(locationFilters).size(sizeFilters).price(priceFilters).amenities(amenitiesFilters).build();
        return query;
    }

    private Query createUserQuery(){

        // load filter values
        ArrayList<Location> locationFilters = convertLocs();
        ArrayList<Size> sizeFilters = convertSize();
        int[] priceFilters = convertPrice();
        ArrayList<Amenities> amenitiesFilters = convertAmenities();

        Query query = new Query.Builder().locations(locationFilters).size(sizeFilters).price(priceFilters).amenities(amenitiesFilters).build();
        return query;
    }

    private void loadPins(){
        // TODO Pass pins to database, not through user query
        // load pins
        String Pin1Input = loadString("Pin1Input");
        String Pin2Input = loadString("Pin2Input");
        String Pin3Input = loadString("Pin3Input");
        return;
    }

    private ArrayList<Location> convertLocs(){

        // load locations
        boolean AngMoKio = load("AngMoKio");
        boolean Bedok = load("Bedok");
        boolean Bishan = load("Bishan");
        boolean BukitBatok = load("BukitBatok");
        boolean BukitMerah = load("BukitMerah");
        boolean BukitPanjang = load("BukitPanjang");
        boolean BukitTimah = load("BukitTimah");
        boolean CentralArea = load("CentralArea");
        boolean ChoaChuKang = load("ChoaChuKang");
        boolean Clementi = load("Clementi");
        boolean Geylang = load("Geylang");
        boolean Hougang = load("Hougang");
        boolean JurongEast = load("JurongEast");
        boolean JurongWest = load("JurongWest");
        boolean KallangWhampoa = load("KallangWhampoa");
        boolean MarineParade = load("MarineParade");
        boolean PasirRis = load("PasirRis");
        boolean Punggol = load("Punggol");
        boolean Queenstown = load("Queenstown");
        boolean Sembawang = load("Sembawang");
        boolean Sengkang = load("Sengkang");
        boolean Serangoon = load("Serangoon");
        boolean Tampines = load("Tampines");
        boolean ToaPayoh = load("ToaPayoh");
        boolean Woodlands = load("Woodlands");
        boolean Yishun = load("Yishun");
        boolean[] locationsBoolean = {AngMoKio, Bedok, Bishan, BukitBatok, BukitMerah, BukitPanjang, BukitTimah, CentralArea, ChoaChuKang, Clementi, Geylang, Hougang, JurongEast, JurongWest, KallangWhampoa, MarineParade, PasirRis, Punggol, Queenstown, Sembawang, Sengkang, Serangoon, Tampines, ToaPayoh, Woodlands, Yishun};

        ArrayList<Location> locationsList = new ArrayList<>();

        if(locationsBoolean[0])	//	AngMoKio
            locationsList.add(Location.ANG_MO_KIO);
        if(locationsBoolean[1])	//	Bedok
            locationsList.add(Location.BEDOK);
        if(locationsBoolean[2])	//	Bishan
            locationsList.add(Location.BISHAN);
        if(locationsBoolean[3])	//	BukitBatok
            locationsList.add(Location.BUKIT_BATOK);
        if(locationsBoolean[4])	//	BukitMerah
            locationsList.add(Location.BUKIT_MERAH);
        if(locationsBoolean[5])	//	BukitPanjang
            locationsList.add(Location.BUKIT_PANJANG);
        if(locationsBoolean[6])	//	BukitTimah
            locationsList.add(Location.BUKIT_TIMAH);
        if(locationsBoolean[7])	//	CentralArea
            locationsList.add(Location.CENTRAL_AREA);
        if(locationsBoolean[8])	//	ChoaChuKang
            locationsList.add(Location.CHUA_CHU_KANG);
        if(locationsBoolean[9])	//	Clementi
            locationsList.add(Location.CLEMENTI);
        if(locationsBoolean[10])	//	Geylang
            locationsList.add(Location.GEYLANG);
        if(locationsBoolean[11])	//	Hougang
            locationsList.add(Location.HOUGANG);
        if(locationsBoolean[12])	//	JurongEast
            locationsList.add(Location.JURONG_EAST);
        if(locationsBoolean[13])	//	JurongWest
            locationsList.add(Location.JURONG_WEST);
        if(locationsBoolean[14])	//	KallangWhampoa
            locationsList.add(Location.KALLANG_WHAMPOA);
        if(locationsBoolean[15])	//	MarineParade
            locationsList.add(Location.MARINE_PARADE);
        if(locationsBoolean[16])	//	PasirRis
            locationsList.add(Location.PASIR_RIS);
        if(locationsBoolean[17])	//	Punggol
            locationsList.add(Location.PUNGGOL);
        if(locationsBoolean[18])	//	Queenstown
            locationsList.add(Location.QUEENSTOWN);
        if(locationsBoolean[19])	//	Sembawang
            locationsList.add(Location.SEMBAWANG);
        if(locationsBoolean[20])	//	Sengkang
            locationsList.add(Location.SENGKANG);
        if(locationsBoolean[21])	//	Serangoon
            locationsList.add(Location.SERANGOON);
        if(locationsBoolean[22])	//	Tampines
            locationsList.add(Location.TAMPINES);
        if(locationsBoolean[23])	//	ToaPayoh
            locationsList.add(Location.TOA_PAYOH);
        if(locationsBoolean[24])	//	Woodlands
            locationsList.add(Location.WOODLANDS);
        if(locationsBoolean[25])	//	Yishun
            locationsList.add(Location.YISHUN);

        return locationsList;
    }

    private ArrayList<Size> convertSize(){

        // load size
        boolean bool2 = load("TwoRoomCheckBox");
        boolean bool3 = load("ThreeRoomCheckBox");
        boolean bool4 = load("FourRoomCheckBox");
        boolean bool5 = load("FiveRoomCheckBox");
        boolean boolE = load("ExecutiveCheckBox");
        boolean[] sizesBoolean = {bool2, bool3, bool4, bool5, boolE};

        ArrayList<Size> sizeList = new ArrayList<>();
        if(sizesBoolean[0])
            sizeList.add(Size.ROOM_2);
        if(sizesBoolean[1])
            sizeList.add(Size.ROOM_3);
        if(sizesBoolean[2])
            sizeList.add(Size.ROOM_4);
        if(sizesBoolean[3])
            sizeList.add(Size.ROOM_5);
        if(sizesBoolean[4])
            sizeList.add(Size.EXECUTIVE);
        return sizeList;
    }

    private int[] convertPrice(){
        // load price
        String minPrice = loadString("MinPriceInput");
        String maxPrice = loadString("MaxPriceInput");

        int[] priceArray = new int[2];

        boolean minIsNull = minPrice.equalsIgnoreCase("NULLSTRING") | minPrice.equalsIgnoreCase("0") | minPrice.equalsIgnoreCase("");
        boolean maxIsNull = maxPrice.equalsIgnoreCase("NULLSTRING") | maxPrice.equalsIgnoreCase("0") | maxPrice.equalsIgnoreCase("");

        // Check: if no values, default = 0 to 2000000
        if(minIsNull | maxIsNull){
            if(minIsNull & maxIsNull){
                priceArray[0] = 0;
                priceArray[1] = 2000000;
            }
            else if (minIsNull){
                priceArray[0] = 0;
                priceArray[1] = Integer.parseInt(maxPrice);
            }
            else if (maxIsNull){
                priceArray[0] = Integer.parseInt(minPrice);
                priceArray[1] = 2000000;
            }
        }
        else if (!minIsNull & !maxIsNull){
            priceArray[0] = Integer.parseInt(minPrice);
            priceArray[1] = Integer.parseInt(maxPrice);
        }

        return priceArray;
    }

    private ArrayList<Amenities> convertAmenities(){
        // load amenities
        boolean Mall = load("checkBoxMall");
        boolean MRT = load("checkBoxClinic");
        boolean Clinic = load("checkBoxClinic");
        boolean[] amenitiesBoolean = {Mall, MRT, Clinic};

        ArrayList<Amenities> amenitiesList = new ArrayList<>();
        if(amenitiesBoolean[0])
            amenitiesList.add(Amenities.MALL);
        if(amenitiesBoolean[1])
            amenitiesList.add(Amenities.MRT);
        if(amenitiesBoolean[2])
            amenitiesList.add(Amenities.CLINIC);
        return amenitiesList;
    }

    private boolean checkIfUserInput(){
        boolean AngMoKio = load("AngMoKio");
        boolean Bedok = load("Bedok");
        boolean Bishan = load("Bishan");
        boolean BukitBatok = load("BukitBatok");
        boolean BukitMerah = load("BukitMerah");
        boolean BukitPanjang = load("BukitPanjang");
        boolean BukitTimah = load("BukitTimah");
        boolean CentralArea = load("CentralArea");
        boolean ChoaChuKang = load("ChoaChuKang");
        boolean Clementi = load("Clementi");
        boolean Geylang = load("Geylang");
        boolean Hougang = load("Hougang");
        boolean JurongEast = load("JurongEast");
        boolean JurongWest = load("JurongWest");
        boolean KallangWhampoa = load("KallangWhampoa");
        boolean MarineParade = load("MarineParade");
        boolean PasirRis = load("PasirRis");
        boolean Punggol = load("Punggol");
        boolean Queenstown = load("Queenstown");
        boolean Sembawang = load("Sembawang");
        boolean Sengkang = load("Sengkang");
        boolean Serangoon = load("Serangoon");
        boolean Tampines = load("Tampines");
        boolean ToaPayoh = load("ToaPayoh");
        boolean Woodlands = load("Woodlands");
        boolean Yishun = load("Yishun");        boolean bool2 = load("TwoRoomCheckBox");
        boolean bool3 = load("ThreeRoomCheckBox");
        boolean bool4 = load("FourRoomCheckBox");
        boolean bool5 = load("FiveRoomCheckBox");
        boolean boolE = load("ExecutiveCheckBox");
        boolean Mall = load("checkBoxMall");
        boolean MRT = load("checkBoxClinic");
        boolean Clinic = load("checkBoxClinic");
        String minPrice = loadString("MinPriceInput");
        String maxPrice = loadString("MaxPriceInput");
        boolean minIsNull = minPrice.equalsIgnoreCase("NULLSTRING") | minPrice.equalsIgnoreCase("0") | minPrice.equalsIgnoreCase("");
        boolean maxIsNull = maxPrice.equalsIgnoreCase("NULLSTRING") | maxPrice.equalsIgnoreCase("0") | maxPrice.equalsIgnoreCase("");

        if(!(AngMoKio | Bedok | Bishan | BukitBatok | BukitMerah | BukitPanjang | BukitTimah | CentralArea | ChoaChuKang | Clementi | Geylang | Hougang | JurongEast | JurongWest | KallangWhampoa | MarineParade | PasirRis | Punggol | Queenstown | Sembawang | Sengkang | Serangoon | Tampines | ToaPayoh | Woodlands | Yishun | bool3 | bool4 | bool5 | boolE | Mall | MRT | Clinic) )
            if(minIsNull && maxIsNull)
                return true;

        return false;
    }

    private boolean load(String name) {
        SharedPreferences sharedPreferences = getSharedPreferences("x",Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(name, false);
    }

    private String loadString(String name) {
        SharedPreferences sharedPreferences = getSharedPreferences("x",Context.MODE_PRIVATE);
        return sharedPreferences.getString(name, "");
    }


    private void saveBoolean(boolean boolValue, String name) {
        SharedPreferences sharedPreferences = getSharedPreferences("x",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(name, boolValue);
        editor.commit();
    }

    private void saveString(String string, String name){
        SharedPreferences sharedPreferences = getSharedPreferences("x",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, string);
        editor.commit();
    }


    public void onDisplay(View v) {
        String s = "Cleared";
        Toast.makeText(SearchActivity.this,s,Toast.LENGTH_LONG).show();
    }
}
