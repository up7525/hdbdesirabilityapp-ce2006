package ntu.ce2006.swensens.hdbdesirabilityapp.data.api;

import android.util.Log;

import com.google.gson.JsonObject;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;

/**
 * Created by trollpc on 24/03/17.
 */

public class GovDataAPIImpl extends JsonRequest {

    // Filename & Location for HDB CSV
    private final String HDBDATA = "app/src/main/assets/resale-flat-prices-based-on-registration-date-from-march-2012-onwards.csv";

    private final String GOVDATA = "https://data.gov.sg/api/action/datastore_search?resource_id=83b2fc37-ce8c-4df4-968b-370fd818138b&limit=100000";

    /**
     * Retrieves the data from API based on the filters defined in the Query
     *
     * @return JsonObject containing the information retrieved
     */
    @Override
    public JsonObject getData() throws IOException {
        return requestAPI(GOVDATA);
    }

    public List<HashMap<String, String>> getCSVData() throws IOException {
        ArrayList<HashMap<String, String>> hdbList = new ArrayList<>();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File(HDBDATA));
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(fileReader);
            for (CSVRecord record : records) {
                HashMap<String, String> flatDataMap = new HashMap<>();
                flatDataMap.put("town", record.get("town"));
                flatDataMap.put("flat_type", record.get("flat_type"));
                flatDataMap.put("block", record.get("block"));
                flatDataMap.put("street_name", record.get("street_name"));
                flatDataMap.put("floor_area_sqm", record.get("floor_area_sqm"));
                flatDataMap.put("resale_price", record.get("resale_price"));
                hdbList.add(flatDataMap);
            }
            fileReader.close();
        } catch (IOException e) {
            throw new IOException("Cannot read from CSV file");
        }
        return hdbList;
    }
}
