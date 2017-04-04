package ntu.ce2006.swensens.hdbdesirabilityapp.search;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ntu.ce2006.swensens.hdbdesirabilityapp.exceptions.APIErrorException;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Amenities;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Location;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Size;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by trollpc on 27/03/17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class FlatManagerTest {

    @Mock
    private Query query;

    private FlatManager flatManager;

    @Before
    public void setUp() {

        // Logger Mock
        PowerMockito.mockStatic(Log.class);
        // TODO DETERMINE HOW SIZE IS GOING TO FIT IN, ALSO NOT PRESENT IN Flat class
        //when(query.getSizeFilters()).thenReturn()

        // Size
        List<Size> sizes = new ArrayList<>();
        sizes.add(Size.ROOM_2);
        when(query.getSizeFilters()).thenReturn(sizes);

        // Location
        List<Location> locations = new ArrayList<>();
        locations.add(Location.ANG_MO_KIO);
        when(query.getLocationFilters()).thenReturn(locations);

        // Price
        when(query.getPriceFilters()).thenReturn(new int[]{230000, 250000});

        // amenities
        when(query.getAmenitiesFilters()).thenReturn(new ArrayList<Amenities>());

        // Address


        flatManager = new FlatManager(query);
    }

    @Test
    public void testGetFlat() throws IOException, ExecutionException, InterruptedException, APIErrorException {
        flatManager.getFlats();
        assertTrue(true);
    }
}
