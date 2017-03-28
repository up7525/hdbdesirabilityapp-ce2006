package ntu.ce2006.swensens.hdbdesirabilityapp.search.query;

import java.util.Arrays;
import java.util.List;

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

    private int id_key;

    // A list of location filters
    private List<String> locationFilters;

    // A list of size filters
    private List<String> sizeFilters;

    // Price range: [minimum, maximum]
    private int[] priceFilters;

    // Area range: [minimum, maximum]
    // private int[] areaFilters;

    // A list of amenity filters
    private List<String> amenitiesFilters;

    /**
     * Builder Class
     */
    public static class Builder {
        // Optional Parameters
        private int id_key = 0;
        // A list of location filters
        private List<String> locationFilters;

        // A list of size filters
        private List<String> sizeFilters;

        // Price range: [minimum, maximum]
        private int[] priceFilters = new int[2];

        // Area range: [minimum, maximum]
        // private int[] areaFilters = new int[2];

        // A list of amenity filters
        private List<String> amenitiesFilters;

        public Builder idDB(int id){
            id_key = id;
            return this;
        }

        public Builder locations(List<String> locations) {
            locationFilters = locations;
            return this;
        }

        public Builder size(List<String> sizes) {
            sizeFilters = sizes;
            return this;
        }

        public Builder price(int[] prices) {
            priceFilters[0] = prices[0];
            priceFilters[1] = prices[1];
            return this;
        }

        //public Builder area(int minimum, int maximum) {
        //    areaFilters[0] = minimum;
        //    areaFilters[1] = maximum;
        //    return this;
        //}

        public Builder amenities(List<String> amenities) {
            amenitiesFilters = amenities;
            return this;
        }

        public Query build() {
            return new Query(this);
        }
    }

    private Query(Builder builder) {
        id_key = builder.id_key;
        locationFilters = builder.locationFilters;
        sizeFilters = builder.sizeFilters;
        priceFilters = builder.priceFilters;
        //areaFilters = builder.areaFilters;
        amenitiesFilters = builder.amenitiesFilters;
    }

    public int getId_key() {
        return id_key;
    }

    public List<String> getLocationFilters() {
        return locationFilters;
    }

    public List<String> getSizeFilters() {
        return sizeFilters;
    }

    public int[] getPriceFilters() {
        return priceFilters;
    }

    //public int[] getAreaFilters() {
    //    return areaFilters;
    //}

    public List<String> getAmenitiesFilters() {
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

    //public boolean isAreaEmpty() {
    //    return areaFilters[0] == 0 && areaFilters[1] == 0;
    //}

    public boolean isAmenitiesEmpty() {
        return amenitiesFilters.size() == 0;
    }

    public static String strSeparator = "__,__";

    public static String convertArrayToString(List<String> array){
        String str = "";
        for (int i = 0;i<array.size(); i++) {
            str = str+array.get(i);
            // Do not append comma at the end of last element
            if(i<array.size()-1){
                str = str+strSeparator;
            }
        }
        return str;
    }

    public static String convertIntArrayToString(int[] array){
        String str = "";
        for (int i = 0;i<array.length; i++) {
            str = str+Integer.toString(array[i]);
            // Do not append comma at the end of last element
            if(i<array.length-1){
                str = str+strSeparator;
            }
        }
        return str;
    }

    public static List<String> convertStringToArray(String str){
        String[] arr = str.split(strSeparator);
        List<String> propArr = Arrays.asList(arr);
        return propArr;
    }
    public static int[] convertStringToIntArray(String str){
        String[] arr = str.split(strSeparator);
        int[] intArr = new int[2];
        intArr[0] = Integer.parseInt(arr[0]);
        intArr[1] = Integer.parseInt(arr[1]);
        return intArr;
    }



}
