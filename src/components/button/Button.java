package components.button;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class Button extends JButton {

    private void initComponent() {
        setFocusPainted(false);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setBorderPainted(false);
        setFont(new Font("Tahoma", Font.BOLD, 14));

    }

    public Button(String text) {
        super();
        setText(text);
        initComponent();
    }
}
