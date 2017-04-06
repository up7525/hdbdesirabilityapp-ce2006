package ntu.ce2006.swensens.hdbdesirabilityapp.data.api;

import android.app.ProgressDialog;

import com.google.common.io.Files;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ntu.ce2006.swensens.hdbdesirabilityapp.MainActivity;

/**
 * Created by trollpc on 24/03/17.
 */

public class GovDataAPIImpl extends JsonRequest {

    // Tag for debugging and logger
    private static final String TAG = "GovDataAPIImpl";

    // Filename & Location for HDB CSV
    private final String HDB_FILE = "app/src/main/assets/resale-flat-price.json";

    private final String GOVDATA = "https://data.gov.sg/api/action/datastore_search?resource_id=83b2fc37-ce8c-4df4-968b-370fd818138b&limit=100000";

    /**
     * Retrieves the data from API based on the filters defined in the Query
     *
     * @return JsonObject containing the information retrieved
     */
    @Override
    public JsonObject getData() throws IOException, ExecutionException, InterruptedException {
        File path = MainActivity.appContext.getFilesDir();
        File file = new File(path, "resale-flat-price.json");
        String fileContent = Files.toString(file, Charset.forName("UTF-8"));
        JsonParser parser = new JsonParser();
        JsonObject results = parser.parse(fileContent).getAsJsonObject();
        return results;
    }

    public void updateData(File file) throws IOException, ExecutionException, InterruptedException {
        Files.write(requestAPI(GOVDATA).toString(), file, Charset.forName("UTF-8"));
    }
}
