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
    private float score;

    // Address of the result
    private String address;

    // Drive of the result
    private String drive;

    // Price of the result
    private int price;

    // Area of the result
    private int area;

    // Amenities of the result in format {name, distance}
    private HashMap<Amenities, Float> amenities;

    public static class Builder {
        // Required Parameters
        private float score;
        private String address;
        private String drive;
        private int price;
        private int area;

        // Optional Parameters
        private HashMap<Amenities, Float> amenities = new HashMap<>();

        public Builder(float score, String address, String drive, int price, int area) {
            this.score = score;
            this.address = address;
            this.drive = drive;
            this.price = price;
            this.area = area;
        }

        // TODO depends on when you want to sort the amenities from the API
        /*
        public Builder amenities(String Name)
        */

        public Result build(Builder builder) {
            return new Result(this);
        }
    }

    public Result(Builder builder) {
        score = builder.score;
        address = builder.address;
        drive = builder.drive;
        price = builder.price;
        area = builder.area;
        amenities = builder.amenities;
    }

    /**
     * Returns the score
     *
     * @return score of the result
     */
    public float getScore() {
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
     * Returns the drive
     *
     * @return drive of the result
     */
    public String getDrive() {
        return drive;
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
    public HashMap<Amenities, Float> getAmenities() {
        return amenities;
    }

    /**
     * Returns the distance of the given amenities
     *
     * @param name Name of the amenities
     * @return Distance from the given amenities
     * @throws UnavailableDataException when data is not available in this result
     */
    public float getAmenitiesDistance(Amenities name) throws UnavailableDataException {
        if (amenities.get(name) == null) {
            throw new UnavailableDataException("The data for this amenity does not exist");
        }
        return amenities.get(name);
    }

    @Override
    public String toString() {
        return "Score: " + Float.toString(score) + "/10" + System.getProperty("line.separator")
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
