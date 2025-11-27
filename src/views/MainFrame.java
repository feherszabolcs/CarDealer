package views;

import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import components.button.NavButton;
import controllers.VehicleData;

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
        JTable table = new JTable(data);

        for (NavButton navButton : navButtons) {
            navButton.addActionListener(e -> {
                for (NavButton btn : navButtons) {
                    btn.setActive(false);
                }
                navButton.setActive(true);
            });
        }

        addButton.addActionListener(e -> {
            AddFrame addFrame = new AddFrame();
            addFrame.setVisible(true);
        });

        removeButton.addActionListener(e -> {
            int answer = JOptionPane.showConfirmDialog(this,
                    "You will delete the vehicle with ID: " + data.vehicles.get(table.getSelectedRow()).getId(),
                    "Remove vehicle",
                    JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.NO_OPTION) {
                return;
            } else if (answer == JOptionPane.YES_OPTION) {
                data.remove(data.vehicles.get(table.getSelectedRow()));
                table.updateUI();
            }
            removeButton.setVisible(false);
            removeButton.setActive(false);
            tableViewButton.setActive(true);
        });

        navPanel.add(tableViewButton);
        navPanel.add(addButton);
        navPanel.add(removeButton);
        removeButton.setVisible(false);
        navPanel.setBackground(Color.GRAY);

        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selection = table.getSelectionModel();
        selection.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                handleSelectionChange(e, table);
            }
        });

        add(scroll);
        add(navPanel, BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (data.vehicles.isEmpty())
                    return;
                VehicleData.JSONWriter(data.vehicles);
            }
        });

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
}
