package ntu.ce2006.swensens.hdbdesirabilityapp.search.result;

import java.util.HashMap;
import java.util.StringTokenizer;

import ntu.ce2006.swensens.hdbdesirabilityapp.exceptions.UnavailableDataException;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Amenities;

/**
 * Created by Swensens on 20/03/17.
 */

public class Result {

    // Score of the result
    private double score;

    // Address of the result
    private String address;

    // Price of the result
    private int price;

    // Area of the result
    private int area;

    // Amenities of the result in format {name, distance}
    private HashMap<String, Integer> amenities;

    public static class Builder {
        // Required Parameters
        private double score;
        private String address;
        private int price;
        private int area;

        // Optional Parameters
        private HashMap<String, Integer> amenities = new HashMap<>();

        public Builder(double score, String address, int price, int area) {
            this.score = score;
            this.address = address;
            this.price = price;
            this.area = area;
        }

        // TODO depends on when you want to sort the amenities from the API
        /*
        public Builder amenities(String Name)
        */
        public Builder amenities(HashMap<String, Integer> amenities) {
            this.amenities = amenities;
            return this;
        }

        public Result build() {
            return new Result(this);
        }
    }

    private Result(Builder builder) {
        score = builder.score;
        address = builder.address;
        price = builder.price;
        area = builder.area;
        amenities = builder.amenities;
    }

    /**
     * Returns the score
     *
     * @return score of the result
     */
    public double getScore() {
        return score;
    }

    /**
     * Returns the address
     *
     * @return address of the result
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the price
     *
     * @return price of the result
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the area
     *
     * @return area of the result
     */
    public int getArea() {
        return area;
    }

    /**
     * Returns the amenities in format {name, distance}
     *
     * @return amenities of result in hashmap
     */
    public HashMap<String, Integer> getAmenities() {
        return amenities;
    }

    @Override
    public String toString() {
        return "Score: " + Double.toString(score) + "/10" + System.getProperty("line.separator")
                + "Address: " + address + System.getProperty("line.separator")
                + "Price: S$" + Integer.toString(price) + System.getProperty("line.separator")
                + "Area: " + Integer.toString(area) + "SQM" + System.getProperty("line.separator");
        // TODO String Representations of amenities
                /*
                + "Amenities " + + System.getProperty("line.separator")
                + "Distance: " +  + System.getProperty("line.separator");
                */
    }
}
