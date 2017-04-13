package ntu.ce2006.swensens.hdbdesirabilityapp.search;

/**
 * @author Wang Chun-Yin
 */

public interface ResultAsyncCallback<T> {
    /**
     * Callback function to retrieve AsyncTask.execute() results
     *
     * @param result result to be returned from AsyncTask executions
     */
    void onTaskComplete(T result);
}
