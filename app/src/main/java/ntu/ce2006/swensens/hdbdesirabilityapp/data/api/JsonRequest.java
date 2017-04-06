package ntu.ce2006.swensens.hdbdesirabilityapp.data.api;

import android.app.ProgressDialog;
import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ntu.ce2006.swensens.hdbdesirabilityapp.data.api.daointerface.APIDAO;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Location;

/**
 * Created by trollpc on 24/03/17.
 */

public abstract class JsonRequest implements APIDAO {

    // For logger
    private static final String TAG = "JsonRequest";

    // Available Google API Keys
    protected final String[] GOOGLEKEY = {"AIzaSyDb4T0qH-QsLBETqDQUCnuryyQIP2e25Qs", "AIzaSyAMSDKBMf-Bwvhe_8vkmp5KgKr8ivQocw4"};

    protected JsonObject requestAPI(String urlString) throws IOException, ExecutionException, InterruptedException {
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
