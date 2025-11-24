import com.formdev.flatlaf.FlatLightLaf;

import rescources.UIProperties;
import views.MainFrame;

public class App {
    public static void main(String[] args) {
        FlatLightLaf.setup();

        UIProperties.loadUI();

        MainFrame mf = new MainFrame();
        mf.setVisible(true);
    }
}
