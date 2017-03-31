package ntu.ce2006.swensens.hdbdesirabilityapp.data.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.result.Flat;

/**
 * Created by trollpc on 24/03/17.
 */

public class GoogleGeoLocImpl extends JsonRequest {

    private String address;
    private String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=";

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
