package aprsworld;

import java.util.Arrays;

public class ChannelData {

    String[] splitData;
    String channel, waveType, frequency, amplitude, offset, phase, duty;

    public ChannelData(String data) {

	splitData = data.split( "," );
	System.out.println( Arrays.toString( splitData ) );

	channel = splitData[0].substring( 0, 2 );

	for ( int i = 0; i < splitData.length; i++ ) {
	    if ( splitData[i].contains( "WVTP" ) ) {
		waveType = splitData[i + 1];
	    }
	    if ( splitData[i].contains( "FRQ" ) ) {
		frequency = splitData[i + 1];
	    }
	    if ( splitData[i].contains( "AMP" ) ) {
		amplitude = splitData[i + 1];
	    }
	    if ( splitData[i].contains( "OFST" ) ) {
		offset = splitData[i + 1];
	    }
	    if ( splitData[i].contains( "PHSE" ) ) {
		phase = splitData[i + 1];
	    }
	    if ( splitData[i].contains( "DUTY" ) ) {
		duty = splitData[i + 1];
	    }
	}

    }

}
