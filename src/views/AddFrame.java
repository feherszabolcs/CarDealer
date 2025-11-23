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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import utils.SpringUtilities;
import utils.VehicleUtil;
import utils.VehicleUtil.Category;
import components.button.Button;
import components.dropdown.DropDown;
import components.label.InputLabel;
import components.textfield.InputField;
import model.Vehicle;

public class AddFrame extends JFrame {

    InputField idField = new InputField();
    InputField brandField = new InputField();
    DropDown<VehicleUtil.Category> categoryDropdown = new DropDown<>(VehicleUtil.Category.values());
    InputField priceField = new InputField();
    InputField descriptionField = new InputField();
    DropDown<Integer> dateDropdown;
    InputField powerField = new InputField();
    Component[] fields;

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

        dateDropdown = new DropDown<>(years);
        fields = new Component[] { idField, brandField, categoryDropdown, priceField, descriptionField, dateDropdown,
                powerField };

        for (int i = 0; i < labels.length; i++) {
            InputLabel il = new InputLabel(labels[i] + ": ", SwingConstants.LEADING);
            mainPanel.add(il);
            il.setLabelFor(fields[i]);
            mainPanel.add(fields[i]);
        }

        JPanel submitPanel = new JPanel();
        Button submitButton = new Button("Confirm");

        submitButton.addChangeListener(e -> {
            boolean hover = submitButton.isRolloverEnabled();
            submitButton.setBorderPainted(hover);
        });

        submitPanel.add(submitButton);
        submitPanel.setBackground(Color.GRAY);
        add(submitPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> handleSubmit());
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

    private void handleSubmit() {
        for (Component comp : fields) {
            if (!validateFields(comp)) {
                JOptionPane.showMessageDialog(null, "Egy, vagy több hibás adat", "Hiba!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        Vehicle vehicle = new Vehicle(Integer.parseInt(idField.getText()), brandField.getText(),
                (Category) categoryDropdown.getSelectedItem(), Integer.parseInt(priceField.getText()),
                descriptionField.getText(),
                (Integer) dateDropdown.getSelectedItem(), Double.parseDouble(powerField.getText()));

        MainFrame.data.vehicles.add(vehicle);
        MainFrame.data.fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "Új autó sikeresen felvéve!", "Siker!", JOptionPane.INFORMATION_MESSAGE);
        this.setVisible(false);
        MainFrame.tableViewButton.setActive(true);
        MainFrame.addButton.setActive(false);
    }

    private boolean validateFields(Component comp) {
        if (comp instanceof InputField ifield && ifield.getText().isEmpty()) {
            System.out.println("HIBA!!  " + ifield.getText());
            return false;
        }
        if (comp instanceof DropDown<?> ddown && ddown.getSelectedItem() == null) {
            System.out.println("HIBA!!  " + ddown.getSelectedItem());
            return false; // technically not possible
        }
        return true;
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
