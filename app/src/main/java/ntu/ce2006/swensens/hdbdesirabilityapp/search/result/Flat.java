package ntu.ce2006.swensens.hdbdesirabilityapp.search.result;

import java.io.*;
import java.util.*;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.result.sort.SortOrder;

/** Flat in builder pattern
 * @author Wang Chun-Yin
 * Created by Swensens on 20/03/17.
 */

public class Flat implements Serializable, Comparable<Flat> {

    /**
     * Desirability Score of the Flat
     */
    private double score;

    /**
     * String representation of the full address
     */
    private String address;

    /**
     * String representation of the street name
     */
    private String streetName;

    /**
     * The block of the Flat
     */
    private String block;

    /**
     * Town area where the Flat is located
     * @see ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Location
     */
    private String town;

    /**
     * Size of the Flat
     * @see ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Size
     */
    private String size;

    /**
     * Price for which the Flat was sold for
     */
    private double price;

    /**
     * Size of the flat in square metres
     */
    private double area;

    /**
     * Amenities of the result in format {name, distance}
     */
    private HashMap<String, Integer> amenities;

    /**
     * Defines for the Builder class for Flat Object
     */
    public static class Builder {
        // Required Parameters
        private double score = 0;
        private String streetName;
        private String block;
        private String town;
        private String address;
        private String size;
        private double price;
        private double area;

        /**
         * Default Amenities is null.
         */
        private HashMap<String, Integer> amenities = new HashMap<>();

        /**
         * Constructor for the Builder class of the Flat object
         * @param block Block of Flat sold
         * @param streetName Street Name of Flat sold
         * @param town Town area of Flat sold
         * @param address String representation of address
         * @param size Size of Flat (in terms of rooms)
         * @param price Price of flat sold
         * @param area Area of flat in square meters
         */
        public Builder(String block, String streetName, String town, String address, String size, double price, double area) {
            this.block = block;
            this.streetName = streetName;
            this.town = town;
            this.address = address;
            this.size = size;
            this.price = price;
            this.area = area;
        }

        /**
         * Set Amenities parameter in soon-to-be generated Flat object
         * @param amenities A hashmap of Amenities as a filter
         */
        public Builder amenities(HashMap<String, Integer> amenities) {
            this.amenities = amenities;
            return this;
        }

        /**
         * Set Score parameter in soon-to-be generated Flat object
         * @param score
         */
        public Builder score(double score) {
            this.score = score;
            return this;
        }

        /**
         * Function to construct the Flat object
         * @return The constructed Flat object
         */
        public Flat build() {
            return new Flat(this);
        }
    }

    /**
     * Constructor for the Flat object
     * @param builder Builder class
     */
    private Flat(Builder builder) {
        score = builder.score;
        block = builder.block;
        streetName = builder.streetName;
        town = builder.town;
        address = builder.address;
        size = builder.size;
        price = builder.price;
        area = builder.area;
        amenities = builder.amenities;
    }

    /**
     * Getter for block value
     * @return Block of Flat
     */
    public String getBlock() {
        return block;
    }

    /**
     * Getter for Streetname
     * @return Street Name of where Flat is situated
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Getter for Town where Flat is located in
     * @return Town
     */
    public String getTown() {
        return town;
    }

    /**
     * Setter for amenities
     * @param amenities
     */
    public void setAmenities(HashMap<String, Integer> amenities) {
        this.amenities = amenities;
    }

    /**
     * Setter for desirability score
     * @param score
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * Returns the score
     * @return score of the result
     */
    public double getScore() {
        return score;
    }

    /**
     * Returns the address
     * @return address of the result
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the size of the flat
     * @return size of the flat
     */
    public String getSize() {
        return size;
    }

    /**
     * Returns the price
     * @return price of the result
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the area
     * @return area of the result
     */
    public double getArea() {
        return area;
    }

    /**
     * Returns the amenities in format {name, distance}
     * @return amenities of result in hashmap
     */
    public HashMap<String, Integer> getAmenities() {
        return amenities;
    }

    /**
     * Returns String format of Flat object for display on the application
     * @return String representation of Flat object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Score: " + String.format("%.2f", score) + "/10" + System.getProperty("line.separator")
                + "Address: " + address + System.getProperty("line.separator")
                + "Price: S$" + String.format("%.2f", price) + System.getProperty("line.separator")
                + "Size: " + size + System.getProperty("line.separator")
                + "Amenities: ");
        // Amenities
        int index = 0;
        for (String amenitiesName : amenities.keySet()) {
            if (index == amenities.size() - 1) {
                sb.append(amenities.get(amenitiesName) + " " + amenitiesName);
                break;
            }
            sb.append(amenities.get(amenitiesName) + " " + amenitiesName + ", ");
            index++;
        }
        return sb.toString();
    }

    /**
     * Compare two Flat objects
     * @param o Usually a Flat object so as to compare them
     * @return true if both are equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flat flat = (Flat) o;

        if (Double.compare(flat.score, score) != 0) return false;
        if (Double.compare(flat.price, price) != 0) return false;
        if (Double.compare(flat.area, area) != 0) return false;
        if (!address.equals(flat.address)) return false;
        if (!streetName.equals(flat.streetName)) return false;
        if (!block.equals(flat.block)) return false;
        if (!town.equals(flat.town)) return false;
        return size.equals(flat.size);
    }

    /**
     * Convert Flat object to hashvalue
     * @return hashvalue
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(score);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + address.hashCode();
        result = 31 * result + streetName.hashCode();
        result = 31 * result + block.hashCode();
        result = 31 * result + town.hashCode();
        result = 31 * result + size.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(area);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Default sorting method, by desirability score
     * @param otherFlat Other Flat object for comparison purposes
     * @return 0 if same score, 1, if this object is smaller than the other, -1 if greater
     */
    // DEFAULT (By Score) UNDERNEATH ARE SORTING
    // (TIP): Use Collections.sort(arrayList<Flat>, Flat.DefaultComparator());
    @Override
    public int compareTo(Flat otherFlat) {
        double compareScore = this.score - otherFlat.getScore();
        if (compareScore < 0) {
            return 1;
        } else if (compareScore > 0) {
            return -1;
        }
        return 0;
    }

    /**
     * Method to decide which sorting algorithmn to use
     * @param otherFlat Other Flat object for comparison purposes
     * @param sortOrder
     * @return value depends on the sorting algorithmn.
     */
    public int compareTo(Flat otherFlat, SortOrder sortOrder) {
        switch (sortOrder) {
            case PRICE_ASCENDING:
                return priceAscendingCompare(otherFlat);
            case PRICE_DESCENDING:
                return priceDescendingCompare(otherFlat);
            case DEFAULT:
                return compareTo(otherFlat);
        }
        return 0;
    }

    /**
     * Sorting method by price, ascending
     * @param otherFlat Other Flat object for comparison purposes
     * @return 0 if same price, 1, if this Flat's price is larger than the other, -1 if smaller
     */
    private int priceAscendingCompare(Flat otherFlat) {
        if (this.price > otherFlat.price) {
            return 1;
        } else if (this.price < otherFlat.price) {
            return -1;
        }
        return 0;
    }

    /**
     * Sorting method by price, descending
     * @param otherFlat Other Flat object for comparison purposes
     * @return 0 if same price, -1, if this Flat's price is larger than the other, 1 if smaller
     */
    private int priceDescendingCompare(Flat otherFlat) {
        if (this.price > otherFlat.price) {
            return -1;
        } else if (this.price < otherFlat.price) {
            return 1;
        }
        return 0;
    }

    public static Comparator<Flat> PriceAscendingComparator() {
        return new Comparator<Flat>() {
            @Override
            public int compare(Flat flat1, Flat flat2) {
                return flat1.compareTo(flat2, SortOrder.PRICE_ASCENDING);
            }
        };
    }

    public static Comparator<Flat> PriceDescendingComparator() {
        return new Comparator<Flat>() {
            @Override
            public int compare(Flat flat1, Flat flat2) {
                return flat1.compareTo(flat2, SortOrder.PRICE_DESCENDING);
            }
        };
    }

    public static Comparator<Flat> DefaultComparator() {
        return new Comparator<Flat>() {
            @Override
            public int compare(Flat flat1, Flat flat2) {
                return flat1.compareTo(flat2, SortOrder.DEFAULT);
            }
        };
    }
}