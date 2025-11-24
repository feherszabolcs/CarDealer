package rescources;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.UIManager;

public class UIProperties {

    private UIProperties() {
    }

    public static void loadUI() {
        UIManager.put("ScrollBar.trackArc", 999);
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.trackInsets", new Insets(2, 4, 2, 4));
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("ScrollBar.track", new Color(0xe0e0e0));

        UIManager.put("ComboBox.buttonBackground", Color.gray);
        UIManager.put("ComboBox.buttonFocusedBackground", Color.gray);
        UIManager.put("ComboBox.buttonArrowColor", Color.black);
        UIManager.put("ComboBox.buttonHoverArrowColor", Color.BLACK);
        UIManager.put("ComboBox.arc", 0);

        UIManager.put("TextField.focusedBorderColor", Color.LIGHT_GRAY);
        UIManager.put("TextField.borderColor", Color.WHITE);
        UIManager.put("TextField.showClearButton", true);
        UIManager.put("TextField.arc", 0);

    }
}
