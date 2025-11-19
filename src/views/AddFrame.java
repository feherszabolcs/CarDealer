package views;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class AddFrame extends JFrame {

    private void init() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Add new vehicle");
        setSize(500, 400);
        setMinimumSize(new Dimension(500, 400));
        setLocationRelativeTo(this);
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
