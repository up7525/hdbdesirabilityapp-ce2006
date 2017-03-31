package ntu.ce2006.swensens.hdbdesirabilityapp.DatabaseTest;

/**
 * Created by Jonathan on 31-Mar-17.
 */
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import org.junit.Test;

import java.util.ArrayList;

import ntu.ce2006.swensens.hdbdesirabilityapp.data.db.dbconfig.DbHandler;
import ntu.ce2006.swensens.hdbdesirabilityapp.pin.Pin;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Amenities;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Location;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.filters.Size;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Jonathan on 28-Mar-17.
 */

public class DatabaseTest {

    Context mMockContext=InstrumentationRegistry.getTargetContext();

    private DbHandler Dbtest = new DbHandler(mMockContext);

    @Test
    public void PinTest(){
        Pin pin1 = new Pin(0, "060770", "House");
        Dbtest.addPin(pin1);
        //assertThat(Dbtest.getPinCount(), is(1));
        Pin pinresult = Dbtest.getPin(1);
        assertThat(pinresult.getDesc(), is(pin1.getDesc()));
    }

    @Test
    public void QueryTest(){

        // Creating test Query
        int id = 0;
        String desc = "Testing";
        ArrayList<Location> locs = new ArrayList<>();
        locs.add(Location.BUKIT_PANJANG);
        locs.add(Location.TAMPINES);

        ArrayList<Size> size = new ArrayList<>();
        size.add(Size.EXECUTIVE);

        int[] price = new int[2];
        price[0]=5;
        price[1]=25;

        ArrayList<Amenities> amen = new ArrayList<>();
        amen.add(Amenities.CLINIC);
        amen.add(Amenities.MALL);

        Query.Builder testBuilder = new Query.Builder();
        testBuilder.desc(desc);
        testBuilder.locations(locs);
        testBuilder.size(size);
        testBuilder.price(price);
        testBuilder.amenities(amen);

        Query query = testBuilder.build();

        // Testing db.
        Dbtest.addQuery(query);
        //assertThat(Dbtest.getQueryCount(), is(1));
        Query queryResult = Dbtest.getQuery(1);
        assertThat(queryResult.getDesc(), is(query.getDesc()));
    }
}
