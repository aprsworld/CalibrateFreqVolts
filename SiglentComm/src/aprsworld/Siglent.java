package aprsworld;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Siglent {

    String outputAddress;
    PrintWriter writer;
    BufferedReader br;

    public Siglent(String outAdd) {

	outputAddress = outAdd;

    }

    /* This method writes the query to the siglent device */
    public void write(String query) throws FileNotFoundException, UnsupportedEncodingException {

	writer = new PrintWriter( outputAddress, "ASCII" );

	writer.println( query );
	writer.close();
    }

    /* reads the responses from the siglent device. */
    public String read() throws IOException {

	br = new BufferedReader( new FileReader( outputAddress ) );

	String line = br.readLine();

	br.close();
	return line;
    }

    /* a combination of the write and read function. */
    public String writeRead(String query) throws IOException {

	writer = new PrintWriter( outputAddress, "UTF-8" );

	writer.println( query );
	writer.close();

	br = new BufferedReader( new FileReader( outputAddress ) );

	String line = br.readLine();

	br.close();
	return line;
    }

}
