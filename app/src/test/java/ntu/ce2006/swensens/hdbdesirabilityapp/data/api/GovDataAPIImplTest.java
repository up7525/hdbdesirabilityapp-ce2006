package ntu.ce2006.swensens.hdbdesirabilityapp.data.api;

import android.util.Log;

import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.*;

/**
 * Created by trollpc on 21/03/17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class GovDataAPIImplTest {

    private GovDataAPIImpl govDataAPI = new GovDataAPIImpl();

    @Before
    public void setUp() {
        // Logger Mock
        PowerMockito.mockStatic(Log.class);
    }

    @Test
    public void testExample() throws IOException, ExecutionException, InterruptedException {
        JsonParser parser = new JsonParser();
        System.out.println(parser.parse(govDataAPI.getData().getAsJsonObject("result").getAsJsonArray("records").get(0).toString()).getAsJsonObject().get("town"));
    }

    @Test
    public void testUpdateData() throws InterruptedException, ExecutionException, IOException {

//        govDataAPI.updateData();
    }

}
