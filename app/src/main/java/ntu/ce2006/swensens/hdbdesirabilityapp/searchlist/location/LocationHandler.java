package ntu.ce2006.swensens.hdbdesirabilityapp.searchlist.location;

/**
 * Created by Jerry on 6/02/17.
 */

public class LocationHandler extends Location {
    public LocationHandler() {
        super();
        // add north regions
        addNorth("SOME NORTH REGION");

        // add south regions
        addSouth("SOME SOUTH REGION");

        // add east regions
        addEast("SOME EAST REGION");

        // add west regions
        addWest("SOME WEST REGION");

    }

}
