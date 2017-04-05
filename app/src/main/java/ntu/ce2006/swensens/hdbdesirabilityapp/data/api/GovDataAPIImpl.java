package ntu.ce2006.swensens.hdbdesirabilityapp.data.api;

import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;

/**
 * Created by trollpc on 24/03/17.
 */

public class GovDataAPIImpl extends JsonRequest {

    // Tag for debugging and logger
    private static final String TAG = "GovDataAPIImpl";

    // Filename & Location for HDB CSV
    private final String HDB_FILE = "app/src/main/assets/resale-flat-price.json";

    private final String GOVDATA = "https://data.gov.sg/api/action/datastore_search?resource_id=83b2fc37-ce8c-4df4-968b-370fd818138b&limit=100000";

    // For Debugging to be fast and not waste limits on API access


    /**
     * Retrieves the data from API based on the filters defined in the Query
     *
     * @return JsonObject containing the information retrieved
     */
    @Override
    public JsonObject getData() throws IOException, ExecutionException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(HDB_FILE));
        String line = reader.readLine();
        while(line != null) {
            sb.append(line);
            line = reader.readLine();
        }
        reader.close();
        JsonParser parser = new JsonParser();
        JsonObject results = parser.parse(sb.toString()).getAsJsonObject();
        return results;
    }

    public void updateData() throws IOException, ExecutionException, InterruptedException {
        RequestThread requestThread = new RequestThread(GOVDATA);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<JsonObject> future = executor.submit(requestThread);
        JsonObject result = future.get();
        executor.shutdown();

        PrintWriter writer = new PrintWriter(new FileOutputStream(HDB_FILE, false));
        writer.print(result);
        writer.close();
    }
}
