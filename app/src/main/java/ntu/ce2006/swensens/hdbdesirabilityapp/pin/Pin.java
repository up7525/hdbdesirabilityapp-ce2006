package com.example.jonathan.local_2006_test;

/**
 * Created by Jonathan on 29-Mar-17.
 */

public class Pin {

    private int id_DB;

    // Postal code indicating address
    private String postalcode;

    // User generated name
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
