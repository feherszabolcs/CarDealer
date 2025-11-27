package components.label;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * Custom component for input labels used in forms. Always connect to a textfield
 */ 
public class InputLabel extends JLabel {
    private void initComponent() {
        setFont(new Font("Tahoma", Font.BOLD, 14));
        setForeground(Color.black);
    }

    public InputLabel(String text) {
        super();
        setText(text);
        initComponent();
    }

    public InputLabel(String text, int horizontalAlignment) {
        super();
        setText(text);
        setHorizontalAlignment(horizontalAlignment);
        initComponent();
    }
}
