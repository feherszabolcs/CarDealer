package components.dropdown;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class DropDown<E> extends JComboBox<E> {
    private void initComponent() {
        setBackground(Color.gray);
        setForeground(Color.BLACK);
        setFont(new Font("Tahoma", Font.BOLD, 14));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        setFocusable(false);
    }

    public DropDown(E[] items) {
        super();
        setModel(new DefaultComboBoxModel<>(items));
        initComponent();
    }
}
