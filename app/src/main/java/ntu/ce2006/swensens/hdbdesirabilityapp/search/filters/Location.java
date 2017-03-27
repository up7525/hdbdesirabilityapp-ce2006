package ntu.ce2006.swensens.hdbdesirabilityapp.search.filters;

/**
 * Created by trollpc on 20/03/17.
 */

public enum Location {
    ANG_MO_KIO("ANG MO KIO"), BEDOK("BEDOK"), BISHAN("BISHAN"), BUKIT_BATOK("BUKIT BATOK"),
    BUKIT_MERAH("BUKIT MERAH"), CHUA_CHU_KANG("CHUA CHU KANG"), CENTRAL_AREA("CENTRAL AREA"),
    BUKIT_TIMAH("BUKIT TIMAH"), BUKIT_PANJANG("BUKIT PANJANG"), CLEMENTI("CLEMENTI"),
    GEYLANG("GEYLANG"), HOUGANG("HOUGANG"), JURONG_EAST("JURONG EAST"), JURONG_WEST("JURONG WEST"),
    KALLANG_WHAMPOA("KALLANG WHAMPOA"), MARINE_PARADE("MARINE PARADE"), PASIR_RIS("PASIR RIS"),
    PUNGGOL("PUNGGOL"), QUEENSTOWN("QUEENSTOWN"), SEMBAWANG("SEMBAWANG"), SENGKANG("SENGKANG"),
    SERANGOON("SERANGOON"), TAMPINES("TAMPINES"), TOA_PAYOH("TOA PAYOH"), WOODLANDS("WOODLANDS"),
    YISHUN("YISHUN");

    private String name;

    Location(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
