package ntu.ce2006.swensens.hdbdesirabilityapp.search;

import java.util.ArrayList;
import java.util.List;

import ntu.ce2006.swensens.hdbdesirabilityapp.data.api.APIImpl;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.result.Result;

/**
 * Created by Swensens on 20/03/17.
 */

public class ResultHandler {
    private List<Result> resultList = new ArrayList<>();

    private Query query;

    public ResultHandler(Query query) {
        this.query = query;
    }

    // Below can be in its separate factory in factory method design
    public void queryAPI() {
        APIImpl apiImpl = new APIImpl();
        // TODO
    }

    public void makeResults() {
        // TODO
    }

}