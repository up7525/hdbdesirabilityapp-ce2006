package ntu.ce2006.swensens.hdbdesirabilityapp.pin;

/**
 * Created by Swensens on 20/03/17.
 */

public class Pin {

    private int id_DB;

    // Postal code indicating address
    private int postalcode;

    // User generated name
    private String desc;

    public Pin(){
        this.id_DB = 0;
        this.desc = "N/A";
        this.postalcode = 0;
    }

    public Pin(int id_DB, int postalcode, String desc){
        this.id_DB = id_DB;
        this.desc = desc;
        this.postalcode = postalcode;
    }

    public int getId_DB() {
        return id_DB;
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
