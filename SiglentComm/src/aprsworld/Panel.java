package aprsworld;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Panel extends JPanel {

    private static final long serialVersionUID = 1L;

    protected TitledBorder title;

    protected DecimalFormat df0, df1, df2, df3;

    public void update() {

    }

    public Panel(String label, PanelDataCell[] p) {

	super( new BorderLayout() );

	df0 = new DecimalFormat( "0" );
	df1 = new DecimalFormat( "0.0" );
	df2 = new DecimalFormat( "0.00" );
	df3 = new DecimalFormat( "0.000" );

	/*
	 * overall border layout container that has another container north and
	 * a button south
	 */

	// setBackground(Color.white);
	title = BorderFactory.createTitledBorder( label );
	setBorder( title );

	Container content = new Container();
	// content.setBackground(Color.white);
	content.setLayout( new GridLayout( 0, 1 ) );

	/* add panels */
	for ( int i = 0; i < p.length; i++ ) {

	    content.add( p[i] );
	}

	/* north */

	add( content, BorderLayout.PAGE_START );

    }
}
