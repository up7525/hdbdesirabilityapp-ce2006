package ntu.ce2006.swensens.hdbdesirabilityapp.search.filters;

/**
 * An enumeration class to define amenities
 * @author Swensens
 * Created by Swensens on 20/03/17.
 */

public enum Amenities {
    CLINIC("CLINIC", 10) {
        /**
         * Getter for google string representation for API use
         * @return google string representation of the enum
         */
        @Override
        public String getGoogleType() {
            return "hospital";
        }
    }, MALL("SHOPS", 15) {
        /**
         * Getter for google string representation for API use
         * @return google string representation of the enum
         */
        @Override
        public String getGoogleType() {
            return "shopping_mall";
        }
    }, MRT("MRT", 2) {
        /**
         * Getter for google string representation for API use
         * @return google string representation of the enum
         */
        @Override
        public String getGoogleType() {
            return "subway_station";
        }
    };

    public abstract String getGoogleType();

    private String name;
    /**
     * Weightage of the amenity in the desirability score algorithmn
     */
    private int maxScoreWeight;

    /**
     * Constructor for amenities enum
     * @param name Name of the amenity type
     * @param maxScoreWeight Weightage of the amenity in the desirability score algorithmn
     */
    Amenities(String name, int maxScoreWeight) {
        this.name = name;
        this.maxScoreWeight = maxScoreWeight;
    }

    /**
     * Getter for the weightage in desirability score algorithmn
     * @return weightage
     */
    public int getMaxScoreWeight() {
        return maxScoreWeight;
    }

    /**
     * Getter for string representation
     * @return string representation of the enum
     */
    @Override
    public String toString() {
        return name;
    }
}