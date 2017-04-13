package ntu.ce2006.swensens.hdbdesirabilityapp.exceptions;

/** Generic Error Message when communication to the API has failed
 * @author Swensens
 * Created by Swensens on 20/03/17.
 */

public class APIErrorException extends Exception {
    public APIErrorException(String message) {
        super(message);
    }
}
