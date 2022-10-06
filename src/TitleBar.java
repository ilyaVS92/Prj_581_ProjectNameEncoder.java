import javax.swing.*;
import java.awt.*;
import static java.awt.Font.BOLD;

public class TitleBar extends JPanel {
    private JPanel titleBar;

    public TitleBar(){
        this.titleBar = new JPanel();

        titleBar.setSize(200,10);
        JLabel titleText = new JLabel("Project name generator");
        titleText.setFont(new Font("Sans-serif", BOLD, 14));
        titleText.setVerticalAlignment(JLabel.CENTER);
        titleText.setHorizontalAlignment(JLabel.CENTER);

        titleBar.add(titleText);
    }
}
