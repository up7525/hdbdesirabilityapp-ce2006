package ntu.ce2006.swensens.hdbdesirabilityapp.data.api.daointerface;

import com.google.gson.JsonObject;

import java.io.IOException;

/**
 * Created by Swensens on 20/03/17.
 */

public interface APIDAO {
    public JsonObject getData() throws IOException;
}
