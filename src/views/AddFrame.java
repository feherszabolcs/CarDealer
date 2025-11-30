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

import com.formdev.flatlaf.FlatClientProperties;

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
    String[] labels;

    private void init() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Add new vehicle");
        setSize(500, 400);
        setMinimumSize(new Dimension(500, 400));
        setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new SpringLayout());
        mainPanel.setBackground(Color.GRAY);

        Integer[] years = new Integer[100];
        Arrays.setAll(years, g -> LocalDate.now().getYear() - g);

        dateDropdown = new DropDown<>(years);
        dateDropdown.putClientProperty(FlatClientProperties.STYLE_CLASS, "main");

        labels = new String[] { "ID", "Brand", "Category", "Price", "Description", "Manufacture year", "Power" };
        fields = new Component[] { idField, brandField, categoryDropdown, priceField, descriptionField, dateDropdown,
                powerField };

        // rendering the labales and textfields in the panel
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

        // setting the active button status when the frame closed
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainFrame.tableViewButton.setActive(true);
                MainFrame.addButton.setActive(false);
            }
        });

        // setting the compactGrid parameters (row, cols, startpos, padding)
        add(mainPanel);
        SpringUtilities.makeCompactGrid(mainPanel,
                labels.length, 2,
                6, 6,
                55, 6);
    }

    /**
     * Handling the add button action with front-end validations
     */
    private void handleSubmit() {
        for (int i = 0; i < fields.length; i++) {
            if (!validateEmptyFields(fields[i])) {
                JOptionPane.showMessageDialog(null, "One, or more values are missing!", "Error!",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!(fields[i] instanceof InputField)) {
                continue;
            }
            InputField tf = (InputField) fields[i];

            // checks if the textfields are filled correctly with specified values

            if ((i == 0 || i == 3) && !validateFieldValue(tf, "num")) {
                JOptionPane.showMessageDialog(null,
                        tf.getText() + " at " + labels[i] + " - The given values is not an integer.",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (i == 0 && VehicleUtil.isIdUsed(Integer.parseInt(idField.getText()), MainFrame.data.vehicles)) {
                JOptionPane.showMessageDialog(null,
                        tf.getText() + " at " + labels[i] + " - The given ID is not unique!", "Error!",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (i == 6 && !validateFieldValue(tf, "double")) {
                JOptionPane.showMessageDialog(null,
                        tf.getText() + " at " + labels[i] + " - The given value is not a double.", "Error!",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
        Vehicle vehicle = new Vehicle(Integer.parseInt(idField.getText()), brandField.getText(),
                (Category) categoryDropdown.getSelectedItem(), Integer.parseInt(priceField.getText()),
                descriptionField.getText(),
                (Integer) dateDropdown.getSelectedItem(), Double.parseDouble(powerField.getText()));

        MainFrame.data.vehicles.add(vehicle);
        MainFrame.data.originalVehicles.add(vehicle);
        MainFrame.data.fireTableDataChanged();
        JOptionPane.showMessageDialog(null, "The new vehicle has been successfuly added.", "Success!",
                JOptionPane.INFORMATION_MESSAGE);
        this.setVisible(false);
        MainFrame.tableViewButton.setActive(true);
        MainFrame.addButton.setActive(false);
    }

    /**
     * Checking empty value of the component
     * 
     * @param comp
     * @return false if the field is empty
     */
    private boolean validateEmptyFields(Component comp) {

        // first checks if the component textvalue is empty or null
        if (comp instanceof InputField ifield && ifield.getText().isEmpty()) {
            System.out.println("[ERR!! ] " + ifield.getText());
            return false;
        }
        if (comp instanceof DropDown<?> ddown && ddown.getSelectedItem() == null) {
            System.out.println("[ERR!! ] " + ddown.getSelectedItem());
            return false; // technically not possible
        }

        return true;
    }

    /**
     * Regex validation of the textfields
     *
     * @param field - to checked
     * @param type  - expected format (num, string, double)
     * @return true if the input is valid
     */
    private boolean validateFieldValue(InputField field, String type) {
        if (type.equals("num"))
            return ((field.getText()).matches("\\d+"));
        else if (type.equals("double"))
            return ((field.getText()).matches("\\d+(\\.\\d+)?"));

        return true;
    }

    // Setting the frame's icon
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
