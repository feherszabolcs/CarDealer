package views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import components.button.NavButton;
import components.label.InputLabel;
import controllers.VehicleData;
import utils.VehicleUtil.Category;

public class MainFrame extends JFrame {

    static VehicleData data;
    static NavButton tableViewButton = new NavButton("Tableview", true);
    static NavButton addButton = new NavButton("Add vehicle");
    static NavButton removeButton = new NavButton("Remove vehicle");

    private void setIcon() {
        try {
            Image icon = ImageIO.read(new File("lib/img/favico.png"));
            setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setTitle("CD - CarDealer");

        JPanel navPanel = new JPanel(new FlowLayout(1, 35, 8));
        NavButton[] navButtons = { tableViewButton, addButton };

        // Setting one active button and "disabling" others
        for (NavButton navButton : navButtons) {
            navButton.addActionListener(e -> {
                for (NavButton btn : navButtons) {
                    btn.setActive(false);
                }
                navButton.setActive(true);
            });
        }

        /**
         * Openening new frame, where the user can add new vehicle
         */
        addButton.addActionListener(e -> {
            AddFrame addFrame = new AddFrame();
            addFrame.setVisible(true);
        });

        navPanel.add(tableViewButton);
        navPanel.add(addButton);
        navPanel.add(removeButton);
        removeButton.setVisible(false);
        navPanel.setBackground(Color.GRAY);

        JTable table = new JTable(data);
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selection = table.getSelectionModel();

        // Handling the row selection in the table with a listener
        selection.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                handleSelectionChange(e, table);
            }
        });

        /**
         * Action to remove the selected vehichle from the table
         * Showing confirmationdialog and only removes when the user clicks "yes"
         */
        removeButton.addActionListener(e -> {
            int answer = JOptionPane.showConfirmDialog(this,
                    "You will delete the vehicle with ID: " + data.vehicles.get(table.getSelectedRow()).getId(),
                    "Remove vehicle",
                    JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.NO_OPTION) {
                return;
            } else if (answer == JOptionPane.YES_OPTION) {
                int modelRow = table.convertRowIndexToModel(table.getSelectedRow()); // converting index of view to
                                                                                     // model
                data.remove(data.vehicles.get(modelRow));
                table.updateUI();
            }
            removeButton.setVisible(false);
            removeButton.setActive(false);
            tableViewButton.setActive(true);
        });

        /**
         * MainFrame's searchpanel (NORTH)
         */
        JPanel searchPanel = new JPanel();
        InputLabel searchLabel = new InputLabel("Search by brand:");
        JTextField searchField = new JTextField(20);
        searchField.setToolTipText("Searching with regex.");
        searchLabel.setLabelFor(searchField);

        // Setting the dropdown menu
        InputLabel dropdownLabel = new InputLabel("Search by category:");
        List<String> categories = new ArrayList<>();
        categories.add("ALL");
        for (Category cat : Category.values()) {
            categories.add(cat.toString());
        }
        JComboBox<String> catDropdown = new JComboBox<>(categories.toArray(String[]::new));
        dropdownLabel.setLabelFor(catDropdown);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> handleSearch(catDropdown, searchField));
        searchField.addActionListener(e -> handleSearch(catDropdown, searchField));

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(dropdownLabel);
        searchPanel.add(catDropdown);
        searchPanel.add(searchButton);

        /**
         * Adding the components to the frame
         */
        add(searchPanel, BorderLayout.NORTH);
        add(scroll);
        add(navPanel, BorderLayout.SOUTH);

        // Listener when closing the frame (=whole application), to write in JSON
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (data.vehicles.isEmpty())
                    return;
                VehicleData.JSONWriter(data.originalVehicles);
            }
        });

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < data.getColumnCount(); ++i) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        setIcon();
    }

    public MainFrame() {

        data = new VehicleData();
        data.JSonReader();

        initFrame();
    }

    private void handleSelectionChange(ListSelectionEvent e, JTable table) {
        if (e.getValueIsAdjusting()) // if user is still selecting (event in progress)
            return;
        int rowIndex = table.getSelectedRow();
        removeButton.setVisible(rowIndex > 0);
    }

    private void handleSearch(JComboBox<String> cbx, JTextField tfield) {
        if (cbx.getSelectedItem().toString().equals("ALL")) {
            data.searchByBrand(tfield.getText().toLowerCase()); // search only by brand, ALL category involved
        } else {
            data.searchByCategory(cbx.getSelectedItem().toString(), tfield.getText().toLowerCase());
        }
    }
}
