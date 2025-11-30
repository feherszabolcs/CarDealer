package components.button;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

/**
 *
 * Custom buttons used in navigation bar.
 *
 */
public class NavButton extends JButton {

    private boolean isActive = false;

    /*
     * Setting basic properties, including a property based fg color
     */
    private void initComponent() {
        setFont(new Font("Tahoma", Font.BOLD, 16));
        setFocusPainted(false);
        setBackground(Color.GRAY);
        setBorder(javax.swing.BorderFactory.createEmptyBorder());
        setBorderPainted(false);
        setContentAreaFilled(false);

        if (isActive)
            setForeground(Color.white);
        else
            setForeground(Color.BLACK);

    }

    public void setActive(boolean active) {
        this.isActive = active;
        this.setForeground(active ? Color.WHITE : Color.BLACK);
    }

    public boolean isActive() {
        return isActive;
    }

    public NavButton() {
        super();
        initComponent();
    }

    public NavButton(String title, boolean active) {
        super();
        this.setText(title);
        this.isActive = active;
        initComponent();
    }

    public NavButton(String title) {
        super();
        this.setText(title);
        initComponent();
    }

}
