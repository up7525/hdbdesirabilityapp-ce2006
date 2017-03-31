package ntu.ce2006.swensens.hdbdesirabilityapp.search;


import java.util.ArrayList;
import java.util.List;

import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.result.Flat;

import com.google.gson.JsonArray;


/**
 * Created by Swensens on 20/03/17.
 */

public class ResultHandler {

    // Tag for logger
    private static final String TAG = "ResultsHandler";

    private JsonArray govData;

    private List<Flat> flatList = new ArrayList<>();

    private Query query;

    public ResultHandler(Query query) {
        this.query = query;
    }

    // Below can be in its separate factory in factory method design
    public void queryAPI() {

        // TODO
    }

    public void makeResults() {
        // TODO
    }

}


