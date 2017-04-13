package ntu.ce2006.swensens.hdbdesirabilityapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ntu.ce2006.swensens.hdbdesirabilityapp.R;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.FlatManager;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.ResultAsyncCallback;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Amenities;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Location;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Size;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.result.Flat;

/**
 * @author Faith, Nicholas, Chester
 *
 */

public class SearchActivity extends AppCompatActivity implements ResultAsyncCallback<List<Flat>> {

    // FOr logger
    private static final String TAG = "SearchActivity";

    public static SearchActivity searchActivity;

    public Button locationButton, priceButton, sizeButton, amenitiesButton;
    public ImageButton SearchButtonSmall, InfoButtonSmall, ClearButtonSmall;
    public Query userQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchActivity.searchActivity = SearchActivity.this;
        setContentView(R.layout.activity_search);
        setTitle("");
        init();
    }

    public void init() {
        locationButton = (Button) findViewById(R.id.locationButton);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent location = new Intent(SearchActivity.this, Search_Loc_Activity.class);
                startActivity(location);
            }
        });
        priceButton = (Button) findViewById(R.id.priceButton);
        priceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent price = new Intent(SearchActivity.this, Search_Price_Activity.class);
                startActivity(price);
            }
        });
        sizeButton = (Button) findViewById(R.id.sizeButton);
        sizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent size = new Intent(SearchActivity.this, Search_Size_Activity.class);
                startActivity(size);
            }
        });
        amenitiesButton = (Button) findViewById(R.id.amenitiesButton);
        amenitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ameni = new Intent(SearchActivity.this, Search_Ameni_Activity.class);
                startActivity(ameni);
            }
        });
        SearchButtonSmall = (ImageButton) findViewById(R.id.SearchButtonSmall);
        SearchButtonSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userQuery = createUserQuery();
                new FlatManager(SearchActivity.searchActivity, userQuery).execute();
            }
        });
        InfoButtonSmall = (ImageButton) findViewById(R.id.InfoButtonSmall);
        InfoButtonSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayString;
                String alert1 = "Set the following filters:";
                String alert2 = "Location: Set town(s).";
                String alert3 = "Size: Select the room type.";
                String alert4 = "Price: Set the minimum and maximum prices";
                String alert5 = "Amenities: Choose nearby places of interest";
                displayString = alert1 + "\n" + "\n" + "\n" +
                        alert2 + "\n" + "\n" +
                        alert3 + "\n" + "\n" +
                        alert4 + "\n" + "\n" +
                        alert5;
                showAlert(v, displayString);
            }
        });
        ClearButtonSmall = (ImageButton) findViewById(R.id.ClearButtonSmall);
        ClearButtonSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchActivity.this, "Cleared all filters.", Toast.LENGTH_LONG).show();
                getSharedPreferences("x", Context.MODE_PRIVATE).edit().clear().commit();
            }
        });
    }

    /**
     * Displays an alert on the screen.
     */

    public void showAlert(View v, String displayString) {
        AlertDialog.Builder info = new AlertDialog.Builder(this);
        info.setMessage(displayString).create().show();
    }

    /**
     * Creates a query object based on the User's inputted filters.
     * If no filters inputted, default Query created.
     * @return Query object.
     */
    private Query createUserQuery() {
        Query query;
        if (checkIfUserInput())
            return new Query.Builder().locations(convertLocs()).size(convertSize()).price(convertPrice()).amenities(convertAmenities()).build();
        else
            return createDefaultQuery();
    }

    /**
     * Checks if the User has inputted a location.
     * @return true if User has inputted a location.
     */

    private boolean checkIfUserInput() {
        // return TRUE if user has inputted something / at least one location chosen.
        return (load("AngMoKio") | load("Bedok") | load("Bishan") | load("BukitBatok") | load("BukitMerah") | load("BukitPanjang") |
                load("BukitTimah") | load("CentralArea") | load("ChoaChuKang") | load("Clementi") | load("Geylang") | load("Hougang") |
                load("JurongEast") | load("JurongWest") | load("KallangWhampoa") | load("MarineParade") | load("PasirRis") | load("Punggol") |
                load("Queenstown") | load("Sembawang") | load("Sengkang") | load("Serangoon") | load("Tampines") | load("ToaPayoh") |
                load("Woodlands") | load("Yishun"));
    }

    /**
     * Creates the default Query object, if no filters are inputted.
     * @return Query object with default filters (Ang Mo Kio, 2 Room, 230k-250k, Clinic, MRT, Shops)
     */

    public Query createDefaultQuery() {
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
        priceFilters[0] = 230000;
        priceFilters[1] = 250000;

        // enable ALL amenities
        amenitiesFilters.add(Amenities.CLINIC);
        amenitiesFilters.add(Amenities.MRT);
        amenitiesFilters.add(Amenities.MALL);

        Query query = new Query.Builder().locations(locationFilters).size(sizeFilters).price(priceFilters).amenities(amenitiesFilters).build();
        return query;
    }

    /**
     * Converts the user's selected locations into an arraylist of locations.
     * @return arraylist of locations. Used as an input to create a Query object.
     */

    private ArrayList<Location> convertLocs() {

        ArrayList<Location> locationsList = new ArrayList<>();

        if(load("AngMoKio")) // AngMoKio
            locationsList.add(Location.ANG_MO_KIO);
        if(load("Bedok")) // Bedok
            locationsList.add(Location.BEDOK);
        if(load("Bishan")) // Bishan
            locationsList.add(Location.BISHAN);
        if(load("BukitBatok")) // BukitBatok
            locationsList.add(Location.BUKIT_BATOK);
        if(load("BukitMerah")) // BukitMerah
            locationsList.add(Location.BUKIT_MERAH);
        if(load("BukitPanjang")) // BukitPanjang
            locationsList.add(Location.BUKIT_PANJANG);
        if(load("BukitTimah")) // BukitTimah
            locationsList.add(Location.BUKIT_TIMAH);
        if(load("CentralArea")) // CentralArea
            locationsList.add(Location.CENTRAL_AREA);
        if(load("ChoaChuKang")) // ChoaChuKang
            locationsList.add(Location.CHUA_CHU_KANG);
        if(load("Clementi")) // Clementi
            locationsList.add(Location.CLEMENTI);
        if(load("Geylang")) // Geylang
            locationsList.add(Location.GEYLANG);
        if(load("Hougang")) // Hougang
            locationsList.add(Location.HOUGANG);
        if(load("JurongEast")) // JurongEast
            locationsList.add(Location.JURONG_EAST);
        if(load("JurongWest")) // JurongWest
            locationsList.add(Location.JURONG_WEST);
        if(load("KallangWhampoa")) // KallangWhampoa
            locationsList.add(Location.KALLANG_WHAMPOA);
        if(load("MarineParade")) // MarineParade
            locationsList.add(Location.MARINE_PARADE);
        if(load("PasirRis")) // PasirRis
            locationsList.add(Location.PASIR_RIS);
        if(load("Punggol")) // Punggol
            locationsList.add(Location.PUNGGOL);
        if(load("Queenstown")) // Queenstown
            locationsList.add(Location.QUEENSTOWN);
        if(load("Sembawang")) // Sembawang
            locationsList.add(Location.SEMBAWANG);
        if(load("Sengkang")) // Sengkang
            locationsList.add(Location.SENGKANG);
        if(load("Serangoon")) // Serangoon
            locationsList.add(Location.SERANGOON);
        if(load("Tampines")) // Tampines
            locationsList.add(Location.TAMPINES);
        if(load("ToaPayoh")) // ToaPayoh
            locationsList.add(Location.TOA_PAYOH);
        if(load("Woodlands")) // Woodlands
            locationsList.add(Location.WOODLANDS);
        if(load("Yishun")) // Yishun
            locationsList.add(Location.YISHUN);

        return locationsList;
    }

    /**
     * Converts the user's selected sizes into an arraylist of sizes.
     * @return arraylist of prices. Used as an input to create a Query object.
     */

    private ArrayList<Size> convertSize() {
        ArrayList<Size> sizeList = new ArrayList<>();
        if (load("TwoRoomCheckBox"))
            sizeList.add(Size.ROOM_2);
        if (load("ThreeRoomCheckBox"))
            sizeList.add(Size.ROOM_3);
        if (load("FourRoomCheckBox"))
            sizeList.add(Size.ROOM_4);
        if (load("FiveRoomCheckBox"))
            sizeList.add(Size.ROOM_5);
        if (load("ExecutiveCheckBox"))
            sizeList.add(Size.EXECUTIVE);
        return sizeList;
    }

    /**
     * Converts the user's selected min/max prices into an array of prices.
     * @return array of prices. Used as an input to create a Query object.
     */

    private int[] convertPrice() {
        // load price
        String minPrice = loadString("MinPriceInput");
        String maxPrice = loadString("MaxPriceInput");

        int[] priceArray = new int[2];

        boolean minIsNull = minPrice.equalsIgnoreCase("NULLSTRING") | minPrice.equalsIgnoreCase("0") | minPrice.equalsIgnoreCase("");
        boolean maxIsNull = maxPrice.equalsIgnoreCase("NULLSTRING") | maxPrice.equalsIgnoreCase("0") | maxPrice.equalsIgnoreCase("");

        // Check: if no values, default = 0 to 2000000
        if (minIsNull | maxIsNull) {
            if (minIsNull & maxIsNull) {
                priceArray[0] = 0;
                priceArray[1] = 2000000;
            } else if (minIsNull) {
                priceArray[0] = 0;
                priceArray[1] = Integer.parseInt(maxPrice);
            } else if (maxIsNull) {
                priceArray[0] = Integer.parseInt(minPrice);
                priceArray[1] = 2000000;
            }
        } else if (!minIsNull & !maxIsNull) {
            priceArray[0] = Integer.parseInt(minPrice);
            priceArray[1] = Integer.parseInt(maxPrice);
        }
        return priceArray;
    }

    /**
     * Converts the user's selected amenities into an array of amenities.
     * @return arraylist of amenities. Used as an input to create a Query object.
     */

    private ArrayList<Amenities> convertAmenities() {
        ArrayList<Amenities> amenitiesList = new ArrayList<>();
        if (load("checkBoxMall"))
            amenitiesList.add(Amenities.MALL);
        if (load("checkBoxClinic"))
            amenitiesList.add(Amenities.MRT);
        if (load("checkBoxClinic"))
            amenitiesList.add(Amenities.CLINIC);
        return amenitiesList;
    }

    /**
     * Loads the User's selected boolean filters from SharedPreferences.
     * @return boolean of checbox.
     */

    private boolean load(String name) {
        return getSharedPreferences("x", Context.MODE_PRIVATE).getBoolean(name, false);
    }


    /**
     * Loads the User's selected string filters from SharedPreferences.
     * @return Saved string.
     */
    private String loadString(String name) {
        return getSharedPreferences("x", Context.MODE_PRIVATE).getString(name, "");
    }


    /**
     * Allows for display of progress animation.
     */
    @Override
    public void onTaskComplete(List<Flat> listOfFlats) {
        ArrayList<String> listOfFlatsString = new ArrayList<String>();
        if ((listOfFlats != null) & (listOfFlats.size() > 0)) {
            // convert listOfFlats to String BEFORE sending to ResultsActivity
            for (int i = 0; i < listOfFlats.size(); i++)
                listOfFlatsString.add(listOfFlats.get(i).toString());
            listOfFlatsString.add("");
            Intent intentFlat = new Intent(SearchActivity.this, ResultsActivity.class);
            intentFlat.putStringArrayListExtra("java.util.List<java.lang.String>", listOfFlatsString);
            intentFlat.putExtra("ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query", userQuery);
            startActivity(intentFlat);
        } else {
            showAlert(findViewById(android.R.id.content), "No results found with your filters!");
        }
    }
}
