package ntu.ce2006.swensens.hdbdesirabilityapp.data.api;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import ntu.ce2006.swensens.hdbdesirabilityapp.data.api.daointerface.APIDAO;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Amenities;

/**
 * Created by Swensens on 20/03/17.
 */

public class APIImpl implements APIDAO {

    // Tag for logger
    private static final String TAG = "APIImpl";

    // Available Google API Keys
    private final String[] GOOGLEKEY = {"AIzaSyAMSDKBMf-Bwvhe_8vkmp5KgKr8ivQocw4"};

    // TODO THIS IMPLEMENTATION NEEDS SOME UPDATING ON THE METHODS. (High Priority)
    @Override
    public void getData() {

    }

    // TODO Retrieving HDB Price Data (Might need to rethink DAO/Implementation structure) (High Priority)
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

                jsonArray = requestAPI(urlFinal);
                break;
            } catch (IOException e) {
                if (googleKeyIndex < GOOGLEKEY.length) {
                    googleKeyIndex++;
                } else {
                    throw new IOException("Error connecting to API");
                }
            }
        }
        return jsonArray.size();
    }

    private JsonArray requestAPI(String urlString) throws IOException {
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
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
        JsonArray jsonArray = jsonObject.getAsJsonArray("results");
        bufferedReader.close();
        return jsonArray;
    }
}
