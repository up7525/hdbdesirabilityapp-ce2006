package ntu.ce2006.swensens.hdbdesirabilityapp.search;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
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
 * @author Wang Chun-Yin
 * Created by trollpc on 27/03/17.
 */

public class FlatManager extends AsyncTask<Void, Void, List<Flat>> {

    // For Logger
    private static final String TAG = "FlatManager";

    private Query query;

    private ProgressDialog progressDialog;

    private Activity activity;

    private Exception asyncTaskException;

    private List<Flat> flats = new ArrayList<>();

    /**
     * Constructor to construct the FlatMaanger class 
     * 
     * @param activity the parent activity of this object
     * @param query the query specified by the user to search for a flat
     */
    public FlatManager(Activity activity, Query query) {
        this.query = query;
        this.activity = activity;
    }

    /**
     * Set the query for FlatManager to compute. Used when query has changed but FlatManager class had already been constructed
     *
     * @param query the query specified by the user to search for a flat
     */
    public void setQuery(Query query) {
        this.query = query;
    }

    /**
     * Compute the flat information, the desirability score based on the query specified by the user. The retrieves a list
     * of computed flats. 
     *
     * @return flats a list of computed flats
     * @exception IOException throws IOException when the IO connections cannot be made
     * @exception ExecutionException thrown when error on executor thread
     * @exception InterruptedException thrown when error when thread is interrupted for any reason
     */
    public List<Flat> getFlats() throws IOException, ExecutionException, InterruptedException {
        if (flats.size() < 1) {
            requestAPI();
        }
        Collections.sort(flats, Flat.DefaultComparator());
        return flats;
    }

    /**
     * Private helper method to handle request from the API for the HDB flats
     *
     * @exception IOException throws IOException when the IO connections cannot be made
     * @exception ExecutionException thrown when error on executor thread
     * @exception InterruptedException thrown when error when thread is interrupted for any reason
     */
    private void requestAPI() throws IOException, ExecutionException, InterruptedException {
        // Request of General Flat Data and make it into list of Flats object
        GovDataAPIImpl govDataAPI = new GovDataAPIImpl();
        flats = makeFlat(govDataAPI.getData());
        Log.d(TAG, flats.toString());
    }

    /**
     * Take a JsonObject of flat to convert them into Flat objects
     *
     * @return list of flats 
     * @exception IOException throws IOException when the IO connections cannot be made
     * @exception ExecutionException thrown when error on executor thread
     * @exception InterruptedException thrown when error when thread is interrupted for any reason
     */
    private List<Flat> makeFlat(JsonObject jsonObject) throws IOException, ExecutionException, InterruptedException {
        List<Flat> flatList = new ArrayList<>();
        JsonArray jsonArray = jsonObject.getAsJsonObject("result").getAsJsonArray("records");
        for (int i = 0; i < jsonArray.size(); i++) {
            flatList.add(flat(jsonArray.get(i)));
        }
        return filterFlat(flatList);
    }

    /**
     * Filter the flats with location, price, size and amenities conditions of the query using a list of flats
     *
     * @return filteredList List of flats that have been filtered by the query
     * @exception IOException throws IOException when the IO connections cannot be made
     * @exception ExecutionException thrown when error on executor thread
     * @exception InterruptedException thrown when error when thread is interrupted for any reason
     */
    private List<Flat> filterFlat(List<Flat> flatList) throws IOException, ExecutionException, InterruptedException {
        List<Flat> filteredList = new ArrayList<>();
        int limit = 10;
        for (int i = 0; i < flatList.size(); i++) {
            // Filters
            if (filteredList.size() >= limit) {
                break;
            }
            try {
                if (containsLocation(flatList.get(i), filteredList) && isWithinPrice(flatList.get(i))
                        && hasSize(flatList.get(i)) && hasAmenities(flatList.get(i))) {
                    filteredList.add(flatList.get(i));
                    computeScore(flatList.get(i));
                }
            } catch (APIErrorException e) {
                limit += 1;
            }
        }
        return filteredList;
    }

    /**
     * Helper private class to check if the given Flat includes some amenities defined by the query.
     * If exist, pass the result of the API request into the Flat class
     *
     * @return true when amenities exist, false otherwise
     * @exception IOException throws IOException when the IO connections cannot be made
     * @exception ExecutionException thrown when error on executor thread
     * @exception InterruptedException thrown when error when thread is interrupted for any reason
     */
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

    /**
     * Helper private class to check if the flat is already within the filteredList,
     * or contains in the query filters defined by the user
     *
     * @param flat that is to be checked
     * @param filteredList a list of flats that has already been filtered
     * @return true when location contains in query and not exist within the filteredList already, false otherwise
     */
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

    /**
     * Helper private class to check if the flat contains the size defined in the query
     *
     * @param flat that is to be checked
     * @return true if flat size is queried by the query object, defined by the user, false otherwise
     */
    private boolean hasSize(Flat flat) {
        for (Size size : query.getSizeFilters()) {
            if (size.toString().equals(flat.getSize())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper private class to check if price is within the defined query price
     *
     * @param flat that is to be checked
     * @return true if flat is within the price defined in query, false otherwise
     */
    private boolean isWithinPrice(Flat flat) {
        if(flat.getPrice() >= query.getPriceFilters()[0])
            if(flat.getPrice() <= query.getPriceFilters()[1])
                return true;
        return false;
    }

    /**
     * Helper class to create the flat object from the result returned by the API
     *
     * @param jsonElement the result retrieved from the API in JSON
     * @return flat that is built and returned
     */
    private Flat flat(JsonElement jsonElement) {
        JsonParser parser = new JsonParser();
        JsonObject flatJson = parser.parse(jsonElement.toString()).getAsJsonObject();
        return new Flat.Builder(flatJson.get("block").getAsString(), flatJson.get("street_name").getAsString(),
                flatJson.get("town").getAsString(), makeAddress(flatJson), flatJson.get("flat_type").getAsString(), flatJson.get("resale_price").getAsDouble(),
                flatJson.get("floor_area_sqm").getAsDouble()).build();
    }

    /**
     * Helper class to compute the score of the flat, then store into the flat object
     *
     * @param flat to be computed
     */
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

    /**
     * Make the address, then build and return the full address from the API data
     *
     * @param flatJson json of the flat to create the full address of the flat
     * @return address in string created
     */
    private String makeAddress(JsonObject flatJson) {
        return flatJson.get("block").getAsString() + " " + flatJson.get("street_name").getAsString() + " "
                + flatJson.get("town").getAsString();
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param voids The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected List<Flat> doInBackground(Void...voids) {
        try {
            getFlats();
        } catch (Exception e) {
            asyncTaskException = e;
        }
        return flats;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("Searching");
        progressDialog.setMessage("Please wait...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(List<Flat> result) {
        if (asyncTaskException != null) {
            Log.e(TAG, "SOME ANNOYING EXCEPTIONS SIGH TRY HANDLING SOMEHOW", asyncTaskException);
        }
        ((ResultAsyncCallback) activity).onTaskComplete(result);
        progressDialog.dismiss();
    }
}
