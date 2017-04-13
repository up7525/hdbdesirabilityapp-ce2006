package ntu.ce2006.swensens.hdbdesirabilityapp.search.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Amenities;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Location;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Size;


/**
 * Query in builder pattern
 *
 * Example Construction
 * Query query = new Query.Builder().locations(locationList).sizes(sizesList).price(0, 100)
 *          .areas(0,4).amenities(amenitiesList).build();
 *
 * Created by Swensens on 20/03/17.
 */
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**Query in builder pattern
 *
 * Example Construction
 * Query query = new Query.Builder().locations(locationList).sizes(sizesList).price(0, 100)
 *          .areas(0,4).amenities(amenitiesList).build();
 * @author Jonathan
 * Created by Jonathan on 29-Mar-17.
 */


public class Query implements Serializable{
    /**
     * Unique id to identify the query object
     */
    @SerializedName("id")
    private int id_key;

    /**
     * String identifier for the query
     */
    @SerializedName("desc")
    private String desc;

    /**
     * Location lists as chosen by user
     */
    @SerializedName("locations")
    private List<Location> locationFilters;

    /**
     * Size lists as chosen by user
     */
    @SerializedName("size")
    private List<Size> sizeFilters;

    /**
     * Price range as entered in by user
     */
    @SerializedName("price")
    private int[] priceFilters;

    /**
     * Amenities list as chosen by user
     */
    @SerializedName("amen")
    private List<Amenities> amenitiesFilters;

    /**
     * Builder Class for Query object
     */
    public static class Builder {
        /**
         * Default value of id
         */
        private int id_key = 0;

        /**
         * Default value of string descriptor
         */
        private String desc = "Default";

        /**
         * Default location list
         */
        private List<Location> locationFilters = new ArrayList<>();

        /**
         * Default size list
         */
        private List<Size> sizeFilters = new ArrayList<>();

        /**
         * Default price range
         */
        private int[] priceFilters = new int[2];

        /**
         * Default amenity list
         */
        private List<Amenities> amenitiesFilters = new ArrayList<>();

        /**
         * Setting id parameter for builder
         * @param id id of query object to be built
         */
        public Builder idDB(int id){
            id_key = id;
            return this;
        }

        /**
         * Setting string descriptor parameter for builder
         * @param desc desc of query object to be built
         */
        public Builder desc(String desc){
            desc = desc;
            return this;
        }

        /**
         * Setting location list parameter for builder
         * @param locations location list of query object to be built
         */
        public Builder locations(ArrayList<Location> locations) {
            locationFilters = locations;
            return this;
        }

        /**
         * Setting size list parameter for builder
         * @param sizes size list of query object to be built
         */
        public Builder size(ArrayList<Size> sizes) {
            sizeFilters = sizes;
            return this;
        }

        /**
         * Setting price range parameter for builder
         * @param arr price range of query object to be built
         */
        public Builder price(int[] arr) {
            priceFilters[0] = arr[0];
            priceFilters[1] = arr[1];
            return this;
        }

        /**
         * Setting amenities list parameter for builder
         * @param amenities amenities of query object to be built
         */
        public Builder amenities(ArrayList<Amenities> amenities) {
            amenitiesFilters = amenities;
            return this;
        }

        /**
         * Method to build Query object
         * @return Query object built
         */
        public Query build() {
            return new Query(this);
        }
    }

    /**
     * Constructor for the Query object (private)
     */
    private Query(Builder builder) {
        id_key = builder.id_key;
        desc = builder.desc;
        locationFilters = builder.locationFilters;
        sizeFilters = builder.sizeFilters;
        priceFilters = builder.priceFilters;
        amenitiesFilters = builder.amenitiesFilters;
    }

    /**
     * Returns the id
     * @return id of query object
     */
    public int getId_key() {
        return id_key;
    }

    /**
     * Returns the string descriptor
     * @return string descriptor
     */
    public String getDesc(){
        desc = "";
        if(getLocationFilters().size() > 0)
            desc = desc + getLocationFilters().get(0) + "; ";
        if(getSizeFilters().size() > 0)
            desc = desc + getSizeFilters().get(0) + "; ";
        desc = desc + "$" + (getPriceFilters()[0] / 1000) + "k to $" + (getPriceFilters()[1] / 1000) + "k; ";
        for(int u = 0; u < getAmenitiesFilters().size(); u++)
            desc = desc + getAmenitiesFilters().get(u) + " ";
        return desc;
    }

    /**
     * Returns the location filter list
     * @return location filter list
     */
    public List<Location> getLocationFilters() {
        return locationFilters;
    }

    /**
     * Returns the size filter list
     * @return size filter list
     */
    public List<Size> getSizeFilters() {
        return sizeFilters;
    }

    /**
     * Returns the price filter
     * @return price filter
     */
    public int[] getPriceFilters() {
        return priceFilters;
    }

    /**
     * Returns the amenities filter
     * @return amenities filter
     */
    public List<Amenities> getAmenitiesFilters() {
        return amenitiesFilters;
    }

    /**
     * Returns a value if the location filter is empty
     * @return 0 if location filter is empty
     */
    public boolean isLocationEmpty() {
        return locationFilters.size() == 0;
    }

    /**
     * Returns a value if size filter is empty
     * @return 0 if size filter is empty
     */
    public boolean isSizeEmpty() {
        return locationFilters.size() == 0;
    }

    /**
     * Returns a value if price filter is empty
     * @return 0 if price filter is empty
     */
    public boolean isPriceEmpty() {
        return priceFilters[0] == 0 && priceFilters[1] == 0;
    }

    /**
     * Returns a value if amenities filter is empty
     * @return 0 if amenities filter is empty
     */
    public boolean isAmenitiesEmpty() {
        return amenitiesFilters.size() == 0;
    }


}
