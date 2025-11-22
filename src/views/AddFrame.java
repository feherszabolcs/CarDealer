package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import utils.SpringUtilities;
import utils.VehicleUtil;
import components.button.Button;
import components.dropdown.DropDown;
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
        String[] labels = { "ID", "Brand", "Category", "Price", "Description", "Manufacture year", "Power" };

        Integer[] years = new Integer[100];
        Arrays.setAll(years, g -> LocalDate.now().getYear() - g);

        InputField idField = new InputField();
        InputField brandField = new InputField();
        DropDown<VehicleUtil.Category> categoryDropdown = new DropDown<>(VehicleUtil.Category.values());
        InputField priceField = new InputField();
        InputField descriptionField = new InputField();
        DropDown<Integer> dateDropdown = new DropDown<>(years);
        InputField powerField = new InputField();
        Component[] fields = { idField, brandField, categoryDropdown, priceField, descriptionField, dateDropdown,
                powerField };

        for (int i = 0; i < labels.length; i++) {
            InputLabel il = new InputLabel(labels[i] + ": ", SwingConstants.LEADING);
            mainPanel.add(il);
            il.setLabelFor(fields[i]);
            mainPanel.add(fields[i]);
        }
        Button submitButton = new Button("Confirm");
        JPanel submitPanel = new JPanel();
        submitPanel.add(submitButton);
        submitPanel.setBackground(Color.GRAY);
        add(submitPanel, BorderLayout.SOUTH);
        submitButton.addChangeListener(e -> {
            boolean hover = submitButton.isRolloverEnabled();
            submitButton.setBorderPainted(hover);
        });

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
