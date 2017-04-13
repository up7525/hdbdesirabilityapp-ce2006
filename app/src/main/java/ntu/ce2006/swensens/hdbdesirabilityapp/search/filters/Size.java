package ntu.ce2006.swensens.hdbdesirabilityapp.search.filters;

/**
 * @author Jonathan
 */

public enum Size {
    ROOM_2("2 ROOM"), ROOM_3("3 ROOM"), ROOM_4("4 ROOM"), ROOM_5("5 ROOM"), EXECUTIVE("EXECUTIVE");

    private String size;
    Size(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return size;
    }
}
