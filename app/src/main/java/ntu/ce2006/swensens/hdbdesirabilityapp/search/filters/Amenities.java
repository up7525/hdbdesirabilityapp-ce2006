package ntu.ce2006.swensens.hdbdesirabilityapp.search.filters;

/**
 * Created by Swensens on 20/03/17.
 */

public enum Amenities {
    CLINIC("CLINIC", 10) {
        @Override
        public String getGoogleType() {
            return "hospital";
        }
    }, MALL("SHOPS", 15) {
        @Override
        public String getGoogleType() {
            return "shopping_mall";
        }
    }, MRT("MRT", 2) {
        @Override
        public String getGoogleType() {
            return "subway_station";
        }
    };

    public abstract String getGoogleType();

    private String name;
    private int maxScoreWeight;

    Amenities(String name, int maxScoreWeight) {
        this.name = name;
        this.maxScoreWeight = maxScoreWeight;
    }

    public int getMaxScoreWeight() {
        return maxScoreWeight;
    }

    @Override
    public String toString() {
        return name;
    }
}