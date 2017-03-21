package ntu.ce2006.swensens.hdbdesirabilityapp.pin;

/**
 * Created by Swensens on 20/03/17.
 */

public class Pin {

    // Postal code indicating address
    private int postalcode;

    // User generated name
    private String desc;

    public Pin(){
        this.desc = "N/A";
        this.postalcode = 0;
    }

    public Pin(int postalcode, String desc){
        this.desc = desc;
        this.postalcode = postalcode;
    }

    public int getPostalcode() {
        return postalcode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPostalcode(int postalcode) {
        this.postalcode = postalcode;
    }
}
