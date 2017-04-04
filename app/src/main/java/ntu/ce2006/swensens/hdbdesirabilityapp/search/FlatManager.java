package ntu.ce2006.swensens.hdbdesirabilityapp.search;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ntu.ce2006.swensens.hdbdesirabilityapp.data.api.GoogleGeoLocImpl;
import ntu.ce2006.swensens.hdbdesirabilityapp.data.api.GooglePlacesImpl;
import ntu.ce2006.swensens.hdbdesirabilityapp.data.api.GovDataAPIImpl;
import ntu.ce2006.swensens.hdbdesirabilityapp.exceptions.APIErrorException;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Amenities;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Location;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Size;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.result.Flat;

/**
 * Created by trollpc on 27/03/17.
 */

public class FlatManager {

    // For Logger
    private static final String TAG = "FlatManager";

    private final int RESULT_LIMIT = 10;

    private Query query;
    private List<Flat> flats = new ArrayList<>();

    public FlatManager(Query query) {
        this.query = query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<Flat> getFlats() throws IOException, ExecutionException, InterruptedException, APIErrorException {
        if (flats.size() < 1) {
            requestAPI();
        }
        Collections.sort(flats, Flat.DefaultComparator());
        return flats;
    }

    private void requestAPI() throws IOException, ExecutionException, InterruptedException, APIErrorException {
        List<Flat> filteredFlatList = new ArrayList<>();
        // Request of General Flat Data and make it into list of Flats object
        GovDataAPIImpl govDataAPI = new GovDataAPIImpl();
        filteredFlatList = makeFlat(govDataAPI.getData());
        // TODO remove this when don't need anymore
        System.out.println(filteredFlatList);
        flats = filteredFlatList;
    }

    private List<Flat> makeFlat(JsonObject jsonObject) throws IOException, ExecutionException, InterruptedException, APIErrorException {
        List<Flat> flatList = new ArrayList<>();
        JsonArray jsonArray = jsonObject.getAsJsonObject("result").getAsJsonArray("records");
        for (int i = 0; i < jsonArray.size(); i++) {
            flatList.add(flat(jsonArray.get(i)));
        }
        return filterFlat(flatList);
    }

    private List<Flat> filterFlat(List<Flat> flatList) throws IOException, ExecutionException, InterruptedException, APIErrorException {
        List<Flat> filteredList = new ArrayList<>();
        // TODO FIND MORE EFFICIENT THAN O(flatList.size()*locationFilter.size())
        for (int i = 0; i < flatList.size(); i++) {
            // Filters
            if (i >= RESULT_LIMIT) {
                break;
            }
            if (containsLocation(flatList.get(i), filteredList) && isWithinPrice(flatList.get(i))
                    && hasSize(flatList.get(i)) && hasAmenities(flatList.get(i))) {
                filteredList.add(flatList.get(i));
                computeScore(flatList.get(i));
            }
        }
        return filteredList;
    }

    private boolean hasAmenities(Flat flat) throws IOException, ExecutionException, InterruptedException, APIErrorException {
        // Get Geolocation
        GoogleGeoLocImpl googleGeoLoc = new GoogleGeoLocImpl(flat);
        HashMap<String, Integer> amenitiesQuantity = new HashMap<>();
        JsonParser parser = new JsonParser();
        try {
            JsonElement googleGeoLocData = googleGeoLoc.getData().getAsJsonArray("results").get(0);

            JsonObject locationData = parser.parse(googleGeoLocData.toString()).getAsJsonObject().getAsJsonObject("geometry").getAsJsonObject("location");
            double latitude = locationData.get("lat").getAsDouble();
            double longitude = locationData.get("lng").getAsDouble();
            int radius = 1000;

            GooglePlacesImpl googlePlaces;
            if (query.getAmenitiesFilters().contains(Amenities.MRT) || query.getAmenitiesFilters().size() == 0) {
                googlePlaces = new GooglePlacesImpl(latitude, longitude, radius, Amenities.MRT);
                amenitiesQuantity.put(Amenities.MRT.toString(), googlePlaces.getData().getAsJsonArray("results").size());
                if (googlePlaces.getData().getAsJsonArray("results").size() == 0 && query.getAmenitiesFilters().size() != 0) {
                    return false;
                }
            }

            if (query.getAmenitiesFilters().contains(Amenities.CLINIC) || query.getAmenitiesFilters().size() == 0) {
                googlePlaces = new GooglePlacesImpl(latitude, longitude, radius, Amenities.CLINIC);
                amenitiesQuantity.put(Amenities.CLINIC.toString(), googlePlaces.getData().getAsJsonArray("results").size());
                if (googlePlaces.getData().getAsJsonArray("results").size() == 0 && query.getAmenitiesFilters().size() != 0) {
                    return false;
                }
            }

            if (query.getAmenitiesFilters().contains(Amenities.MALL) || query.getAmenitiesFilters().size() == 0) {
                googlePlaces = new GooglePlacesImpl(latitude, longitude, radius, Amenities.MALL);
                amenitiesQuantity.put(Amenities.MALL.toString(), googlePlaces.getData().getAsJsonArray("results").size());
                if (googlePlaces.getData().getAsJsonArray("results").size() == 0 && query.getAmenitiesFilters().size() != 0) {
                    return false;
                }
            }
            flat.setAmenities(amenitiesQuantity);
        } catch (IndexOutOfBoundsException e) {
            Log.e(TAG, "Error searching Google Geodata or Places API for address: " + flat.getAddress()
                    + ". Caused by Status: " + googleGeoLoc.getData().get("status").getAsString(), e);
            throw new APIErrorException("Error searching Google Geodata or Places API");
        }
        return true;
    }

    private boolean containsLocation(Flat flat, List<Flat> filteredList) {
        for (Flat existingFlat : filteredList) {
            if (existingFlat.getAddress().equals(flat.getAddress())) {
                return false;
            }
        }
        for (Location loc : query.getLocationFilters()) {
            if (loc.toString().equals(flat.getTown())) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSize(Flat flat) {
        for (Size size : query.getSizeFilters()) {
            if (size.toString().equals(flat.getSize())) {
                return true;
            }
        }
        return false;
    }

    private boolean isWithinPrice(Flat flat) {
        return flat.getPrice() >= query.getPriceFilters()[0] && flat.getPrice() <= query.getPriceFilters()[1];
    }

    private Flat flat(JsonElement jsonElement) {
        JsonParser parser = new JsonParser();
        JsonObject flatJson = parser.parse(jsonElement.toString()).getAsJsonObject();
        return new Flat.Builder(flatJson.get("block").getAsString(), flatJson.get("street_name").getAsString(),
                flatJson.get("town").getAsString(), makeAddress(flatJson), flatJson.get("flat_type").getAsString(), flatJson.get("resale_price").getAsDouble(),
                flatJson.get("floor_area_sqm").getAsDouble()).build();
    }

    private void computeScore(Flat flat) {
        double weight = 10.0 / flat.getAmenities().size();
        double score = 0;
        for (Amenities amenities : Amenities.values()) {
            if (flat.getAmenities().get(amenities.toString()) != null) {
                if (flat.getAmenities().get(amenities.toString()) >= amenities.getMaxScoreWeight()) {
                    score += weight;
                } else {
                    score += (flat.getAmenities().get(amenities.toString()) * weight * 1.0)
                            / (amenities.getMaxScoreWeight());
                }
            }
        }
        flat.setScore(score);
    }

    private String makeAddress(JsonObject flatJson) {
        return flatJson.get("block").getAsString() + " " + flatJson.get("street_name").getAsString() + " "
                + flatJson.get("town").getAsString();
    }
}