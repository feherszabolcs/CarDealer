package components.dropdown;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class DropDown<E> extends JComboBox<E> {
    private void initComponent() {
        setBackground(Color.gray);
        setForeground(Color.BLACK);
        setFont(new Font("Tahoma", Font.BOLD, 14));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                BasicArrowButton btn = new BasicArrowButton(SwingConstants.SOUTH, Color.gray, Color.black,
                        Color.black, Color.white);
                btn.setBorderPainted(false);
                btn.setBorder(BorderFactory.createEmptyBorder());
                btn.setFocusPainted(false);
                btn.setContentAreaFilled(false);
                btn.setRolloverEnabled(false);
                return btn;
            }
        });
        setFocusable(false);
    }

    public DropDown(E[] items) {
        super();
        setModel(new DefaultComboBoxModel<>(items));
        initComponent();
    }
}
