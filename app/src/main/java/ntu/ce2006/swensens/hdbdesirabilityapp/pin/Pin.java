package ntu.ce2006.swensens.hdbdesirabilityapp.pin;

/**
 * Created by Jonathan on 29-Mar-17.
 */

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jonathan on 29-Mar-17.
 */

public class Pin {

    @SerializedName("id")
    private int id_DB;

    // Postal code indicating address
    @SerializedName("postalcode")
    private String postalcode;

    // User generated name
    @SerializedName("desc")
    private String desc;

    public Pin(){
        this.id_DB = 0;
        this.desc = "N/A";
        this.postalcode = "000000";
    }

    public Pin(int id_DB, String postalcode, String desc){
        this.id_DB = id_DB;
        this.desc = desc;
        this.postalcode = postalcode;
    }

    public int getId_DB() {
        return id_DB;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }
}