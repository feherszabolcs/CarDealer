package views;

import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.plaf.DimensionUIResource;

import controllers.VehicleData;

public class MainFrame extends JFrame {

    private VehicleData data;

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
        setMinimumSize(new DimensionUIResource(800, 600));
        setLocationRelativeTo(null);
        setTitle("CD - CarDealer");

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
