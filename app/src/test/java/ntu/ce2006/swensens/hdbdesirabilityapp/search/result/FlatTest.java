package ntu.ce2006.swensens.hdbdesirabilityapp.search.result;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Size;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;

/**
 * Created by trollpc on 21/03/17.
 */

public class FlatTest {
    private Flat flat;

    private double score = 8.7;
    private String block = "40";
    private String streetName = "Street";
    private String town = "Town";
    private String address = "My Street Address";
    private String size = Size.ROOM_5.toString();
    private double price = 5059244;
    private double area = 70;
    private HashMap<String, Integer> amenities = new HashMap<>();

    @Before
    public void setUp() {
        amenities.put("MRT", 5);
        amenities.put("Mall", 10);
        amenities.put("Clinics", 4);
        flat = new Flat.Builder(block, streetName, town, address, size, price, area).amenities(amenities).build();
    }

    /**
     * Test if builder has properly created the values
     */
    @Test
    public void testBuilder() {
        assertEquals(flat.getScore(), score);
        assertEquals(flat.getAddress(), address);
        assertEquals(flat.getPrice(), price);
        assertEquals(flat.getArea(), area);
        assertEquals(flat.getAmenities().get("MRT"), amenities.get("MRT"));
        assertEquals(flat.getAmenities().get("Mall"), amenities.get("Mall"));
        assertEquals(flat.getAmenities().get("Clinics"), amenities.get("Clinics"));
    }

    /**
     * Test Overriden equals and hashcode
     */
    @Test
    public void testEquals() {
        Flat otherFlat = new Flat.Builder(block, streetName, town, address, size, price, area).amenities(amenities).build();
        // Test True Case
        assertTrue(flat.equals(otherFlat));
        assertEquals(flat.hashCode(), otherFlat.hashCode());

        // Test False Case
        Flat falseFlat = new Flat.Builder(block, streetName, town, address, size + 1, price, area).amenities(amenities).build();
        assertFalse(flat.equals(falseFlat));
        assertNotSame(flat.hashCode(), falseFlat.hashCode());
    }
}
