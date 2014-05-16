package aprsworld;

import java.text.DecimalFormat;

public class Conversions {

    static DecimalFormat df = new DecimalFormat( "0" );
    static DecimalFormat df1 = new DecimalFormat( "0.0" );
    static DecimalFormat df2 = new DecimalFormat( "0.00" );
    
    /* converts frequency to m/s */
    public static String freqToMS(double freq) {

	return df2.format( ( 0.765 * freq ) + 0.35 ) + "m/s";
    }
    /* amps to direction sector */
    public static String ampToDirSect(double amp) {

	return df.format( amp / .625 ) + "";
    }
    /* direction sector to degrees */
    public static String sectorToDegree(int sector) {

	return sector * 45 + "";
    }

}
