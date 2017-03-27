package ntu.ce2006.swensens.hdbdesirabilityapp.search;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ntu.ce2006.swensens.hdbdesirabilityapp.data.api.GovDataAPIImpl;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Location;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.result.Flat;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.result.sort.SortOrder;

/**
 * Created by trollpc on 27/03/17.
 */

public class FlatManager {

    private Query query;
    private List<Flat> flats = new ArrayList<>();

    public FlatManager(Query query) {
        this.query = query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<Flat> getFlats() throws IOException {
        if (flats.size() < 1) {
            requestAPI();
        }
        Collections.sort(flats, Flat.DefaultComparator());
        return flats;
    }

    private void computerScore() {

    }

    private void requestAPI() throws IOException {
        List<Flat> unfilteredFlatList = new ArrayList<>();
        // Request of General Flat Data and make it into list of Flats object
        GovDataAPIImpl govDataAPI = new GovDataAPIImpl();
        unfilteredFlatList = makeFlat(govDataAPI.getData());
        System.out.println(unfilteredFlatList);
        //
    }

    private List<Flat> makeFlat(JsonObject jsonObject) {
        List<Flat> flatList = new ArrayList<>();
        JsonArray jsonArray = jsonObject.getAsJsonObject("result").getAsJsonArray("records");
        for (int i = 0; i < jsonArray.size(); i++) {
            flatList.add(flat(jsonArray.get(i)));
        }
        return filterFlat(flatList);
    }

    // TODO not completed
    private List<Flat> filterFlat(List<Flat> flatList) {
        List<Flat> filteredList = new ArrayList<>();
        // TODO FIND MORE EFFICIENT THAN O(flatList.size()*locationFilter.size())
        for (int i = 0; i < flatList.size(); i++) {
            // Filter by location
            if (containsLocation(flatList.get(i))) {
                filteredList.add(flatList.get(i));
            }

            // Filter by price
            if (isWithinPrice(flatList.get(i))) {
                filteredList.add(flatList.get(i));
            }

            // Filter by area
            if (isWithinArea(flatList.get(i))) {
                filteredList.add(flatList.get(i));
            }

        }

        // TODO PLease confirm if still using Filter by size


        // Filter by Amenities

        return filteredList;
    }

    private boolean containsLocation(Flat flat) {
        for (Location loc: query.getLocationFilters()) {
            if (loc.toString().equals(flat.getTown())) {
                return true;
            }
        }
        return false;
    }

    private boolean isWithinArea(Flat flat) {
        return flat.getArea() >= query.getAreaFilters()[0] && flat.getArea() <= query.getAreaFilters()[1];
    }

    private boolean isWithinPrice(Flat flat) {
        return flat.getPrice() >= query.getPriceFilters()[0] && flat.getPrice() <= query.getPriceFilters()[1];
    }

    private Flat flat(JsonElement jsonElement) {
        JsonParser parser = new JsonParser();
        JsonObject flatJson = parser.parse(jsonElement.toString()).getAsJsonObject();
        return new Flat.Builder(computeScore(), flatJson.get("block").toString(), flatJson.get("street_name").toString(),
                flatJson.get("town").toString(), makeAddress(flatJson), flatJson.get("resale_price").getAsDouble(),
                flatJson.get("floor_area_sqm").getAsDouble()).build();
    }

    private double computeScore() {
        // TODO RETURN PLACEHOLDER FOR NOW
        return 6.6;
    }

    private String makeAddress(JsonObject flatJson) {
        return flatJson.get("block").toString() + " " + flatJson.get("street_name").toString() + " "
                + flatJson.get("town").toString();
    }

}
