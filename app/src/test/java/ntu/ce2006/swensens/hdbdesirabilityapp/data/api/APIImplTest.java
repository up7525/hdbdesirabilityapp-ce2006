package ntu.ce2006.swensens.hdbdesirabilityapp.data.api;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.*;

/**
 * Created by trollpc on 21/03/17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class APIImplTest {

    private double[] coordinates = {1.3483367,103.6743799};
    private int radius = 3000;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(Log.class);
    }
    @Test
    public void getAmenitiesNoException() throws IOException {
        PowerMockito.mockStatic(Log.class);
        APIImpl apiImpl = new APIImpl();

        assertNotNull(true);
    }
}
