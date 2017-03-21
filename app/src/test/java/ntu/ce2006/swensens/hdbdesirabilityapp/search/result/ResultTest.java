package ntu.ce2006.swensens.hdbdesirabilityapp.search.result;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

/**
 * Created by trollpc on 21/03/17.
 */

public class ResultTest {
    private Result result;

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
        result = new Result.Builder(score, address, price, area).amenities(amenities).build();
    }

    /**
     * Test if builder has properly created the values
     */
    @Test
    public void testBuilder() {
        assertEquals(result.getScore(), score);
        assertEquals(result.getAddress(), address);
        assertEquals(result.getPrice(), price);
        assertEquals(result.getArea(), area);
        assertEquals(result.getAmenities().get("MRT"), amenities.get("MRT"));
        assertEquals(result.getAmenities().get("Mall"), amenities.get("Mall"));
        assertEquals(result.getAmenities().get("Clinics"), amenities.get("Clinics"));
    }
}
