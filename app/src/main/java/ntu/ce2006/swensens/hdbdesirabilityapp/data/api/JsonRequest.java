package ntu.ce2006.swensens.hdbdesirabilityapp.data.api;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ntu.ce2006.swensens.hdbdesirabilityapp.data.api.daointerface.APIDAO;

/**
 * Created by trollpc on 24/03/17.
 */

public abstract class JsonRequest implements APIDAO {

    // Available Google API Keys
    protected final String[] GOOGLEKEY = {"AIzaSyDb4T0qH-QsLBETqDQUCnuryyQIP2e25Qs", "AIzaSyAMSDKBMf-Bwvhe_8vkmp5KgKr8ivQocw4"};

    protected JsonObject requestAPI(String urlString) throws IOException, ExecutionException, InterruptedException {
        RequestThread requestThread = new RequestThread(urlString);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<JsonObject> future = executor.submit(requestThread);
        JsonObject result = future.get();
        executor.shutdown();
        return result;
    }
}
