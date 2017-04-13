package ntu.ce2006.swensens.hdbdesirabilityapp.search.filters;

/**
 * An emuneration class to define Flat sizes
 * @author Jonathan
 */

public enum Size {
    ROOM_2("2 ROOM"), ROOM_3("3 ROOM"), ROOM_4("4 ROOM"), ROOM_5("5 ROOM"), EXECUTIVE("EXECUTIVE");
    /**
     * Size enum string
     */
    private String size;
    Size(String size) {
        this.size = size;
    }

    /**
     * A simple string getter for the enum
     * @return string representation of the enum
     */
    @Override
    public String toString() {
        return size;
    }
}
