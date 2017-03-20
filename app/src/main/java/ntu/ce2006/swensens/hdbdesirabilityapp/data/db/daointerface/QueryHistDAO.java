package ntu.ce2006.swensens.hdbdesirabilityapp.data.db.daointerface;

import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;

/**
 * Created by Swensens on 20/03/17.
 */

public interface QueryHistDAO {
    public void loadQuery(int userChoice);
    public void saveQueryToHistory(Query query);
}
