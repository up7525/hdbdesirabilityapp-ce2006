package ntu.ce2006.swensens.hdbdesirabilityapp.data.api;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.HashMap;

import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Amenities;

/**
 * Created by trollpc on 24/03/17.
 */

public class GooglePlacesImpl extends JsonRequest {

    private double latitude;
    private double longitude;
    private int radius;
    private Amenities amenities;

    public GooglePlacesImpl(double latitude, double longitude, int radius, Amenities amenities) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.amenities = amenities;
    }

    // TODO BELOW NEEDS TO BE PLACED SOMEWHERE ELSE NOT IN THIS CLASS
    /***************************************************
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
    // jsonArray = jsonObject.getAsJsonArray("results");
     ***************************************************/

    /**
     * Retrieves the data from API based on the filters defined in the Query
     *
     * @return JsonObject containing the information retrieved
     */
    @Override
    public JsonObject getData() throws IOException {
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
