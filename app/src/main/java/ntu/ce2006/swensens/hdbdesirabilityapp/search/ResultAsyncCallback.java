package ntu.ce2006.swensens.hdbdesirabilityapp.search;

/**
 * Created by trollpc on 6/04/17.
 */

public interface ResultAsyncCallback<T> {
    /**
     * Callback function to retrieve AsyncTask.execute() results
     *
     * @param result result to be returned from AsyncTask executions
     */
    void onTaskComplete(T result);
}
