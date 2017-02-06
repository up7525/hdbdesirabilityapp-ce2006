package ntu.ce2006.swensens.hdbdesirabilityapp.searchlist.location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerry on 6/02/17.
 */

public abstract class Location {

    private List<String> northRegion;
    private List<String> southRegion;
    private List<String> eastRegion;
    private List<String> westRegion;

    public Location() {
        northRegion = new ArrayList<>();
        southRegion = new ArrayList<>();
        eastRegion = new ArrayList<>();
        westRegion = new ArrayList<>();
    }

    public void addNorth(String locationName) {
        northRegion.add(locationName);
    }

    public void addSouth(String locationName) {
        southRegion.add(locationName);
    }

    public void addEast(String locationName) {
        eastRegion.add(locationName);
    }

    public void addWest(String locationName) {
        westRegion.add(locationName);
    }

    public List<String> getNorth() {
        return northRegion;
    }

    public List<String> getSouth() {
        return southRegion;
    }

    public List<String> getEast() {
        return eastRegion;
    }

    public List<String> getWest() {
        return westRegion;
    }

}
