package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import utils.SpringUtilities;

import components.label.InputLabel;
import components.textfield.InputField;

public class AddFrame extends JFrame {

    private void init() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Add new vehicle");
        setSize(500, 400);
        setMinimumSize(new Dimension(500, 400));
        setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new SpringLayout());
        mainPanel.setBackground(Color.GRAY);
        String[] labels = { "ID", "Brand", "Category", "Price", "Description", "Manufacture date", "Power" };

        for (int i = 0; i < labels.length; i++) {
            InputLabel il = new InputLabel(labels[i] + ": ", SwingConstants.LEADING);
            mainPanel.add(il);
            InputField iField = new InputField();
            il.setLabelFor(iField);
            mainPanel.add(iField);
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainFrame.tableViewButton.setActive(true);
                MainFrame.addButton.setActive(false);
            }
        });

        add(mainPanel);
        SpringUtilities.makeCompactGrid(mainPanel,
                labels.length, 2,
                6, 6,
                55, 6);
    }

    private void setIcon() {
        try {
            Image icon = ImageIO.read(new File("lib/img/favico.png"));
            setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AddFrame() {
        super();
        setIcon();
        init();
    }
}
