package ntu.ce2006.swensens.hdbdesirabilityapp.data.api;

import android.app.ProgressDialog;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Amenities;

/**
 * @author Wang Chun-Yin
 * Created by trollpc on 24/03/17.
 */

public class GooglePlacesImpl extends JsonRequest {

    // Latitude of the flat
    private double latitude;
    
    // Longitude of the flat
    private double longitude;
    
    // Radius of nearby amenities
    private int radius;
    
    // Amanity of interest to be queried
    private Amenities amenities;

    /**
     * Constructor to create GoogleplacesImpl
     * 
     * @param latitude of the flat
     * @param longitude of the flat
     * @param radius of the nearby amenities
     * @param types of amenity of interest
     */
    public GooglePlacesImpl(double latitude, double longitude, int radius, Amenities amenities) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.amenities = amenities;
    }

    /**
     * Retrieves the data from API based on the filters defined in the Query
     *
     * @return JsonObject containing the information retrieved
     */
    @Override
    public JsonObject getData() throws IOException, ExecutionException, InterruptedException {
        JsonObject jsonObject;
        int index = 0;
        while (true) {
            try {
                String urlFinal = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" +
                        latitude + "," + longitude + "&radius=" + Integer.toString(radius)
                        + "&type=" + amenities.getGoogleType()
                        + "&key=" + GOOGLEKEY[index];

                jsonObject = requestAPI(urlFinal);
                break;
            } catch (IOException e) {
                if (index < GOOGLEKEY.length - 1) {
                    index++;
                } else {
                    throw new IOException("Cannot Access Google Places API");
                }
            }
        }
    return jsonObject;
    }
}
