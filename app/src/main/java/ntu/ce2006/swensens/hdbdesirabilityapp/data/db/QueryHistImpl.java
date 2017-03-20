package ntu.ce2006.swensens.hdbdesirabilityapp.data.db;

import ntu.ce2006.swensens.hdbdesirabilityapp.data.db.daointerface.QueryHistDAO;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;

/**
 * Created by Swensens on 20/03/17.
 */

public class QueryHistImpl extends DatabaseAccess implements QueryHistDAO {

    public QueryHistImpl() {
        super();
    }

    @Override
    public void loadQuery(int userChoice) {

    }

    @Override
    public void saveQueryToHistory(Query query) {

    }
}
