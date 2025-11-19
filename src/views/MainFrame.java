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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import components.button.NavButton;
import controllers.VehicleData;

public class MainFrame extends JFrame {

    private VehicleData data;
    static NavButton tableViewButton = new NavButton("Tableview", true);
    static NavButton addButton = new NavButton("Add vehicle");

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
        for (NavButton navButton : navButtons) {
            navButton.addActionListener(e -> {
                for (NavButton btn : navButtons)
                    btn.setActive(false);
                navButton.setActive(true);
            });
        }

        addButton.addActionListener(e -> {
            AddFrame addFrame = new AddFrame();
            addFrame.setVisible(true);
        });

        navPanel.add(tableViewButton);
        navPanel.add(addButton);
        navPanel.setBackground(Color.GRAY);

        JTable table = new JTable(data);
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);

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
}
