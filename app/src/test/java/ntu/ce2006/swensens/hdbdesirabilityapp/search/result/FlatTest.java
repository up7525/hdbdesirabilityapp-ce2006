package ntu.ce2006.swensens.hdbdesirabilityapp.search.result;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

/**
 * Created by trollpc on 21/03/17.
 */

public class FlatTest {
    private Flat flat;

    private double score = 8.7;
    private String address = "My Street Address";
    private int price = 5059244;
    private int area = 70;
    private HashMap<String, Integer> amenities = new HashMap<>();

    @Before
    public void setUp() {
        amenities.put("MRT", 5);
        amenities.put("Mall", 10);
        amenities.put("Clinics", 4);
        flat = new Flat.Builder(score, address, price, area).amenities(amenities).build();
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
}
