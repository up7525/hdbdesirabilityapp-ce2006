package ntu.ce2006.swensens.hdbdesirabilityapp.search.query;

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

public class Query {

    // A list of location filters
    private List<Location> locationFilters;

    // A list of size filters
    private List<Size> sizeFilters;

    // Price range: [minimum, maximum]
    private int[] priceFilters;

    // Area range: [minimum, maximum]
    private int[] areaFilters;

    // A list of amenity filters
    private List<Amenities> amenitiesFilters;

    /**
     * Builder Class
     */
    public static class Builder {
        // Optional Parameters
        // A list of location filters
        private List<Location> locationFilters = new ArrayList<>();

        // A list of size filters
        private List<Size> sizeFilters = new ArrayList<>();

        // Price range: [minimum, maximum]
        private int[] priceFilters = new int[2];

        // Area range: [minimum, maximum]
        private int[] areaFilters = new int[2];

        // A list of amenity filters
        private List<Amenities> amenitiesFilters = new ArrayList<>();

        public Builder locations(ArrayList<Location> locations) {
            locationFilters = locations;
            return this;
        }

        public Builder size(ArrayList<Size> sizes) {
            sizeFilters = sizes;
            return this;
        }

        public Builder price(int minimum, int maximum) {
            priceFilters[0] = minimum;
            priceFilters[1] = maximum;
            return this;
        }

        public Builder area(int minimum, int maximum) {
            areaFilters[0] = minimum;
            areaFilters[1] = maximum;
            return this;
        }

        public Builder amenities(ArrayList<Amenities> amenities) {
            amenitiesFilters = amenities;
            return this;
        }

        public Query build() {
            return new Query(this);
        }
    }

    private Query(Builder builder) {
        locationFilters = builder.locationFilters;
        sizeFilters = builder.sizeFilters;
        priceFilters = builder.priceFilters;
        areaFilters = builder.areaFilters;
        amenitiesFilters = builder.amenitiesFilters;
    }

    public List<Location> getLocationFilters() {
        return locationFilters;
    }

    public List<Size> getSizeFilters() {
        return sizeFilters;
    }

    public int[] getPriceFilters() {
        return priceFilters;
    }

    public int[] getAreaFilters() {
        return areaFilters;
    }

    public List<Amenities> getAmenitiesFilters() {
        return amenitiesFilters;
    }

    public boolean isLocationEmpty() {
        return locationFilters.size() == 0;
    }

    public boolean isSizeEmpty() {
        return locationFilters.size() == 0;
    }

    public boolean isPriceEmpty() {
        return priceFilters[0] == 0 && priceFilters[1] == 0;
    }

    public boolean isAreaEmpty() {
        return areaFilters[0] == 0 && areaFilters[1] == 0;
    }

    public boolean isAmenitiesEmpty() {
        return amenitiesFilters.size() == 0;
    }
}
