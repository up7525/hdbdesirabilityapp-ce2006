package ntu.ce2006.swensens.hdbdesirabilityapp.data.api;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ntu.ce2006.swensens.hdbdesirabilityapp.data.api.daointerface.APIDAO;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Amenities;

/**
 * Created by Swensens on 20/03/17.
 */

public class APIImpl implements APIDAO {

    // Tag for logger
    private static final String TAG = "APIImpl";

    // Filename & Location for HDB CSV
    private final String HDBDATA = "app/src/main/assets/resale-flat-prices-based-on-registration-date-from-march-2012-onwards.csv";

    //Filename for Gov Data API
    private final String GOVDATA = "https://data.gov.sg/api/action/datastore_search?resource_id=83b2fc37-ce8c-4df4-968b-370fd818138b";

    // Available Google API Keys
    private final String[] GOOGLEKEY = {"AIzaSyAMSDKBMf-Bwvhe_8vkmp5KgKr8ivQocw4"};

    // TODO THIS IMPLEMENTATION NEEDS SOME UPDATING ON THE METHODS. (High Priority)
    // no idea how to add more than 1 parameter for query boo.
    @Override
    public JsonObject getData() throws IOException {
        JsonObject govApiData;
        try {
            govApiData = requestAPI(GOVDATA);
        } catch (IOException e) {
            throw new IOException("Error connecting to API");
        }
        return govApiData;
    }

    // TODO Retrieving HDB Price Data (Might need to rethink DAO/Implementation structure) (High Priority)
    public List<HashMap<String, String>> getHDBData() {
        // TODO NEED SOOOOOO MUCH OPTIMIZATION (Low Priority)
        ArrayList<HashMap<String, String>> hdbList = new ArrayList<>();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File(HDBDATA));
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(fileReader);
            for (CSVRecord record : records) {
                HashMap<String, String> flatDataMap = new HashMap<>();
                flatDataMap.put("month", record.get("month"));
                flatDataMap.put("town", record.get("town"));
                flatDataMap.put("flat_type", record.get("flat_type"));
                flatDataMap.put("block", record.get("block"));
                flatDataMap.put("street_name", record.get("street_name"));
                flatDataMap.put("storey_range", record.get("storey_range"));
                flatDataMap.put("floor_area_sqm", record.get("floor_area_sqm"));
                flatDataMap.put("resale_price", record.get("resale_price"));
                hdbList.add(flatDataMap);
            }
            fileReader.close();
        } catch (IOException e) {
            // TODO SOMETHING TO HANDLE\
            Log.d(TAG, "FileReader cannot be opened. Possibly file not found.", e);
        }
        return hdbList;
    }


    // TODO ALSO CONSIDER JACKSON/GSON/ALTERNATIVES TO MAP API TO OBJECT DIRECTLY FOR HDB (Low Priority)


    // For retrieving nearby amenities in quantity TODO DON'T FORGET JAVADOC (Low Priority) NEED TO EXPAND ON UNIT TEST
    public HashMap<String, Integer> getNearbyAmenities(double latitude, double longitude, int radius) throws IOException {
        HashMap<String, Integer> amenitiesQuantity = new HashMap<>();
        // MRT
        amenitiesQuantity.put("MRT", getAmenitiesQuantity(latitude, longitude, radius, Amenities.MRT));
        // Clinic
        amenitiesQuantity.put("Clinics", getAmenitiesQuantity(latitude, longitude, radius, Amenities.CLINIC));
        // Mall
        amenitiesQuantity.put("Shopping Mall", getAmenitiesQuantity(latitude, longitude, radius, Amenities.MALL));
        return amenitiesQuantity;
    }

    private int getAmenitiesQuantity(double latitude, double longitude, int radius, Amenities amenities) throws IOException {
        JsonObject jsonObject;
        JsonArray jsonArray;
        while (true) {
            int googleKeyIndex = 0;
            try {
                String urlOriginal = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" +
                        latitude + "," + longitude + "&radius=" + Integer.toString(radius)
                        + "&type=" + amenities.getGoogleType()
                        + "&key=";
                String urlFinal = urlOriginal + GOOGLEKEY[googleKeyIndex];

                // For Debugging
                Log.d(TAG, urlFinal);

                jsonObject = requestAPI(urlFinal);
                jsonArray = jsonObject.getAsJsonArray("results");
                break;
            } catch (IOException e) {
                if (googleKeyIndex < GOOGLEKEY.length - 1) {
                    googleKeyIndex++;
                } else {
                    throw new IOException("Error connecting to API");
                }
            }
        }
        return jsonArray.size();
    }

    private JsonObject requestAPI(String urlString) throws IOException {
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.connect();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        int input;
        StringBuilder jsonString = new StringBuilder();
        while ((input = bufferedReader.read()) != -1) {
            char inputChar = (char) input;
            if (inputChar != '\n' && inputChar != ' ') {
                jsonString.append(inputChar);
            }
        }
        JsonElement jsonElement = new JsonParser().parse(jsonString.toString());
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        bufferedReader.close();
        return jsonObject;
    }
}
