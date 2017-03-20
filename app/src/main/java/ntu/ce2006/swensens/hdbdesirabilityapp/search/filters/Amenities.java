package ntu.ce2006.swensens.hdbdesirabilityapp.search.filters;

/**
 * Created by Swensens on 20/03/17.
 */

public enum Amenities {
    CLINIC {
        @Override
        public String getGoogleType() {
            return "hospital";
        }
    }, MALL {
        @Override
        public String getGoogleType() {
            return "shopping_mall";
        }
    }, MRT {
        @Override
        public String getGoogleType() {
            return "subway_station";
        }
    };

    public abstract String getGoogleType();
}