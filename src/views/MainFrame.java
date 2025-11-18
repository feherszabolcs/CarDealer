package views;

import java.io.File;
import java.io.IOException;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.plaf.DimensionUIResource;

public class MainFrame extends JFrame {

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

        setIcon();
    }

    public MainFrame() {
        initFrame();
    }
}
