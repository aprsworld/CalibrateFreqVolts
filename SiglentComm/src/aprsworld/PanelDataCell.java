package aprsworld;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelDataCell extends JPanel {

    private static final long serialVersionUID = 1L;

    protected JLabel v;

    public void setText(String s) {

	v.setText( s );
    }

    public void setLabel(String s) {

	TitledBorder tb = BorderFactory.createTitledBorder( s );
	tb.setTitleFont( new Font( "Serif", Font.BOLD, 24 ) );
	setBorder( tb );

    }

    public PanelDataCell(String label) {

	super( new BorderLayout() );
	setOpaque( false );
	TitledBorder tb = BorderFactory.createTitledBorder( label );
	tb.setTitleFont( new Font( "Serif", Font.BOLD, 24 ) );
	setBorder( tb );

	v = new JLabel( "---" );
	v.setFont( new Font( "Serif", Font.BOLD, 24 ) );
	v.setForeground( Color.blue );

	add( v, BorderLayout.LINE_END );

    }
}
