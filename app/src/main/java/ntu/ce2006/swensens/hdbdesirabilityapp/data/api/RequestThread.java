package ntu.ce2006.swensens.hdbdesirabilityapp.data.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

/**
 * Created by trollpc on 31/03/17.
 */

public class RequestThread implements Callable<JsonObject> {

    private String urlString;

    public RequestThread(String urlString) {
        this.urlString = urlString;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws IOException if unable to compute a result
     */
    @Override
    public JsonObject call() throws IOException {
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.connect();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        int input;
        StringBuilder jsonString = new StringBuilder();
        while ((input = bufferedReader.read()) != -1) {
            char inputChar = (char) input;
            jsonString.append(inputChar);
        }
        JsonElement jsonElement = new JsonParser().parse(jsonString.toString());
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        bufferedReader.close();
        return jsonObject;
    }
}
