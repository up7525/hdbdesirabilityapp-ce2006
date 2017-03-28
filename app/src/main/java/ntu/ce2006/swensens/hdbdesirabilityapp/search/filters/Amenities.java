package ntu.ce2006.swensens.hdbdesirabilityapp.search.filters;

/**
 * Created by Swensens on 20/03/17.
 */

public enum Amenities {
    CLINIC("CLINIC") {
        @Override
        public String getGoogleType() {
            return "hospital";
        }
    }, MALL("MALL") {
        @Override
        public String getGoogleType() {
            return "shopping_mall";
        }
    }, MRT("MRT") {
        @Override
        public String getGoogleType() {
            return "subway_station";
        }
    };

    public abstract String getGoogleType();

    private String name;

    Amenities(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}