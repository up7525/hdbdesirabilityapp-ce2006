package ntu.ce2006.swensens.hdbdesirabilityapp.data.api;

import android.app.ProgressDialog;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.result.Flat;

/**
 * @author Wang Chun-Yin
 */

public class GoogleGeoLocImpl extends JsonRequest {

    // flat to be requested
    private String address;
    
    // URL of the API to be used
    private String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=";

    /**
     * Constructor for GoogleGeoLocImpl, modifies the address string to be used for API query
     *
     * @param flat to be requested
     */
    public GoogleGeoLocImpl(Flat flat) {
        address = flat.getAddress().replace(' ', '+');
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
        while(true) {
            try {
                String urlFinal = apiUrl + address + "&key=" + GOOGLEKEY[index];
                jsonObject = requestAPI(urlFinal);
                break;
            } catch (IOException e) {
                if (index < GOOGLEKEY.length - 1) {
                    index++;
                } else {
                    throw new IOException("Cannot Access Google Geolocation API");
                }
            }
        }
        return jsonObject;
    }
}
