import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class actualGUI extends JPanel {
    JFrame frame = new JFrame("actual GUI");

    private JButton encode = new JButton("Encode");
    private JButton clearFields = new JButton("Clear All Fields");

    private JRadioButton noVariants = new JRadioButton("None",true);
    private JRadioButton descendingVars = new JRadioButton("Descd");
    private JRadioButton ascendingVariants = new JRadioButton("Ascnd");
    private JRadioButton neighboringVariants = new JRadioButton("near-by");

    private JTextField projectNameField = new JTextField();
    private JLabel projectName = new JLabel("Project Name: ");

    private JTextField digitLenField = new JTextField();
    private JLabel digitLen = new JLabel("No of Digits: ");

    private JTextField variantsField = new JTextField("Variants: ");
    private JLabel variants = new JLabel("Var to display: "); //not yet working
    private String result = "";
    private JTextArea encodedProjectName = new JTextArea(result,10,30);

    private Prj_581_encoderRing encoderRing = new Prj_581_encoderRing();
    private JPanel primaryPanel = new JPanel();

    public actualGUI(){
        this.setLocation(50,50);
        this.setSize(300, 400);//creates a frame of 400x600
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout pLayout = new GridBagLayout();
        GridBagConstraints pConstraints = new GridBagConstraints();
        setLayout(pLayout);

        pConstraints.gridx = 0;
        pConstraints.gridy = 0;
        pConstraints.weightx = 1;
        pConstraints.weighty = 1;
        add(projectName,pConstraints);
        setVisible(true);


    }
}
