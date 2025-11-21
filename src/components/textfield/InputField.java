package components.textfield;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class InputField extends JTextField {

    private void initComponent() {
        setColumns(20);
        setSize(100, HEIGHT);
        setFont(new Font("Tahoma", Font.BOLD, 14));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        setBackground(Color.gray);
        setForeground(Color.black);
    }

    public InputField() {
        super();
        initComponent();
    }
}
