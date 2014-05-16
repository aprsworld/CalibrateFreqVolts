package aprsworld;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Siglent {

    String device;
    PrintWriter writer;
    BufferedReader br;

    public Siglent(String dev) {

	device = dev;

    }

    public void write(String query) throws FileNotFoundException, UnsupportedEncodingException {

	writer = new PrintWriter( device, "ASCII" );

	writer.println( query );
	writer.close();
    }

    public String read() throws IOException {

	br = new BufferedReader( new FileReader( device ) );

	String line = br.readLine();

	br.close();
	return line;
    }

    public String clear() throws IOException {

	
	
	while (br.readLine() != null ) {
	   
	}

	return "ok";
    }

    public String writeRead(String query) throws IOException {

	writer = new PrintWriter( device, "UTF-8" );

	writer.println( query );
	writer.close();

	br = new BufferedReader( new FileReader( device ) );

	String line = br.readLine();

	br.close();
	return line;
    }

}
