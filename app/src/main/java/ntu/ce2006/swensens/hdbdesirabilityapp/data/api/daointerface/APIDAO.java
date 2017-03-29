package ntu.ce2006.swensens.hdbdesirabilityapp.data.api.daointerface;

import com.google.gson.JsonObject;

import java.io.IOException;

/**
 * Created by Swensens on 20/03/17.
 */

public interface APIDAO {
    /**
     * Retrieves the data from API based on the filters defined in the Query
     *
     * @return JsonObject containing the information retrieved
     */
    public JsonObject getData() throws IOException;
}
