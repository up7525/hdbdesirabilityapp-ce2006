package ntu.ce2006.swensens.hdbdesirabilityapp.search;

import android.app.Activity;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ntu.ce2006.swensens.hdbdesirabilityapp.data.api.GovDataAPIImpl;
import ntu.ce2006.swensens.hdbdesirabilityapp.exceptions.APIErrorException;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Amenities;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Location;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Size;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by trollpc on 27/03/17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class FlatManagerTest {

    @Mock
    private Query query;

    @Mock
    GovDataAPIImpl govDataAPI;

    private FlatManager flatManager;

    private Activity activity;

    @Before
    public void setUp() throws InterruptedException, ExecutionException, IOException {

        // Logger Mock
        PowerMockito.mockStatic(Log.class);

        // Activity Mock

        Mockito.doNothing().when(((ResultAsyncCallback) activity)).onTaskComplete(new ArrayList<>());

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
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader("app/src/main/assets/resale-flat-price.json"));
        String line = reader.readLine();
        while(line != null) {
            sb.append(line);
            line = reader.readLine();
        }
        reader.close();
        JsonParser parser = new JsonParser();
        JsonObject results = parser.parse(sb.toString()).getAsJsonObject();
        when(govDataAPI.getData()).thenReturn(results);
        flatManager = new FlatManager(activity, query);
    }

    @Test
    public void testGetFlat() throws IOException, ExecutionException, InterruptedException, APIErrorException {
        assertNotNull(flatManager.getFlats());
    }
}
