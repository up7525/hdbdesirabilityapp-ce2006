package ntu.ce2006.swensens.hdbdesirabilityapp.data.db.daointerface;

import ntu.ce2006.swensens.hdbdesirabilityapp.pin.Pin;

/**
 * Created by Swensens on 20/03/17.
 */

public interface PinListDAO {
    public void addPin(Pin pin);
    public void removePin(Pin pin);
}
