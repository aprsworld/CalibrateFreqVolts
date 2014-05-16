package aprsworld;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends Thread {

    /**
     * @param args
     * @throws IOException
     */

    JFrame f;
    Container content;
    Container cont;
    PanelDataCell[] panel0DataCells, panel1DataCells;
    Panel[] panels;
    Button bContinue;
    JEditorPane jlInstructions;

    boolean frOpen;
    Scanner fileReader;

    ChannelData chData1, chData2;
    Siglent s;

    String instructions;

    static String address;
    static String filename = "inst";

    public static void main(String[] args) throws IOException {

	
	if ( args.length > 0 ) {
	    address = args[0];
	}
	if ( args.length > 1 ) {
	    filename = args[1];
	}

	( new Main() ).start();

    }

    public void run() {

	try {
	    s = new Siglent( address );
	    connectionTest( address );

	    fileReader = new Scanner( new File( filename ) );
	    frOpen = true;
	} catch ( IOException e1 ) {
	    
	    e1.printStackTrace();
	}

	f = new JFrame( "Siglent" );

	f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	f.setBackground( Color.white );
	f.setSize( 800, 500 );

	/* Overall BorderLayout */
	cont = f.getContentPane();
	cont.setLayout( new BorderLayout() );

	/* Our body section */
	content = new Container();

	content.setBackground( Color.white );
	content.setLayout( new GridLayout( 1, 0 ) );

	/* initialize panels */

	panel0DataCells = new PanelDataCell[2];
	panel1DataCells = new PanelDataCell[3];
	panels = new Panel[2];

	double frequency = Double.parseDouble( chData1.frequency.substring( 0, chData1.frequency.length() - 2 ) );
	panel0DataCells[0] = new PanelDataCell( "Frequency" );
	panel0DataCells[1] = new PanelDataCell( "Speed" );

	panel0DataCells[0].setText( chData1.frequency );
	panel0DataCells[1].setText( Conversions.freqToMS( frequency ) );

	double amp = Double.parseDouble( chData2.offset.substring( 0, chData2.offset.length() - 1 ) );
	panel1DataCells[0] = new PanelDataCell( "Volts" );
	panel1DataCells[1] = new PanelDataCell( "Direction" );
	panel1DataCells[2] = new PanelDataCell( "Sector" );

	panel1DataCells[0].setText( chData2.offset );
	panel1DataCells[1].setText( Conversions.sectorToDegree( Integer.parseInt( Conversions.ampToDirSect( amp ) ) ) );
	panel1DataCells[2].setText( Conversions.ampToDirSect( amp ) );

	panels[0] = new Panel( "panel 1", panel0DataCells );
	panels[1] = new Panel( "panel 2", panel1DataCells );

	for ( int i = 0; i < panels.length; i++ ) {
	    content.add( panels[i] );
	}

	/* title text */
	Container titleContainer = new Container();
	titleContainer.setLayout( new FlowLayout() );

	JLabel titleLabel = new JLabel( "Siglent" );
	jlInstructions = new JEditorPane();
	jlInstructions.setContentType( "text/html" );

	instructions = "Please wait while the program initializes";
	jlInstructions.setText( "<div align=center><h1>Siglent</h1>" + instructions + "</div>" );

	jlInstructions.setEditable( false );
	jlInstructions.setBackground( titleLabel.getBackground() );
	titleLabel.setFont( new Font( "Serif", Font.BOLD, 24 ) );
	titleLabel.setForeground( Color.blue );
	titleContainer.add( titleLabel );

	/* add the title */
	cont.add( titleContainer, BorderLayout.BEFORE_FIRST_LINE );

	/* add the instructions */
	cont.add( jlInstructions, BorderLayout.PAGE_START );

	/* Add the body */
	cont.add( content, BorderLayout.CENTER );

	/* Add the button */

	bContinue = new Button( "Start" ) {

	    /**
	     * 
	     */
	    private static final long serialVersionUID = -1749965528134606026L;

	    public Dimension getPreferredSize() {

		return new Dimension( 100, 80 );
	    };
	};
	bContinue.addActionListener( new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub
		bContinue.setVisible( false );
		bContinue.setLabel( "Confirm your readings and go on to next step" );
		System.out.println( "button" );
		try {
		    readInstructionsFromFile();
		} catch ( UnsupportedEncodingException e1 ) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		} catch ( IOException e1 ) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
		bContinue.setVisible( true );
	    }
	} );

	bContinue.setFont( new Font( "Serif", Font.BOLD, 24 ) );
	bContinue.setSize( f.getWidth(), 300 );

	bContinue.setVisible( false );
	f.add( bContinue, BorderLayout.PAGE_END );

	f.setLocationRelativeTo( null );
	f.setVisible( true );

	bContinue.setFocusable( true );
	bContinue.requestFocus();

	try {
	    readInstructionsFromFile();
	} catch ( Exception e ) {
	    System.err.println( e + "" );
	}

    }

    public void connectionTest(String addrs) throws IOException, UnsupportedEncodingException {

	chData1 = new ChannelData( s.writeRead( "C2:BSWV?" ) );

	chData2 = new ChannelData( s.writeRead( "C1:BSWV?" ) );

    }

    public void clearSiglent() throws IOException {

    }

    public void update() throws IOException, UnsupportedEncodingException {

	connectionTest( address );
	panel0DataCells[0].setText( chData1.frequency );
	double frequency = Double.parseDouble( chData1.frequency.substring( 0, chData1.frequency.length() - 2 ) );
	panel0DataCells[1].setText( Conversions.freqToMS( frequency ) );
	double amp = Double.parseDouble( chData2.offset.substring( 0, chData2.offset.length() - 1 ) );
	panel1DataCells[0].setText( chData2.offset );
	panel1DataCells[1].setText( Conversions.sectorToDegree( Integer.parseInt( Conversions.ampToDirSect( amp ) ) ) );
	panel1DataCells[2].setText( Conversions.ampToDirSect( amp ) );

	instructions = "Place holder instructions are neat-o";
	jlInstructions.setText( "<div align=center><h1>Siglent</h1>" + instructions + "</div>" );

	bContinue.setVisible( true );
    }

    public void readInstructionsFromFile() throws IOException {

	String command = "";

	while ( frOpen && fileReader.hasNext() && !command.equals( "STEP" ) ) {
	    if ( frOpen && fileReader.hasNext() ) {
		command = fileReader.nextLine();
		System.out.println( command );
		if ( command.equals( "step" ) )
		    break;
		s.write( command );

	    } else {
		fileReader.close();
		frOpen = false;
	    }
	}
	update();

    }

}
