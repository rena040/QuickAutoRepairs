import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;

public class UIStyle {
    // Define common colors
    public static final Color BACKGROUND_COLOR = new Color(51, 102, 255);
    public static final Color BUTTON_COLOR = new Color(0, 153, 204);
    public static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    // Apply background color to a panel
    public static void stylePanel(JPanel panel) {
        panel.setBackground(BACKGROUND_COLOR);
    }

    // Style a button
    public static void styleButton(JButton button) {
        button.setBackground(BUTTON_COLOR);
        button.setForeground(BUTTON_TEXT_COLOR);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }
}