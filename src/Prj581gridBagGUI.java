import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Prj581gridBagGUI extends JFrame implements WindowListener {
    private JFrame primaryFrame;

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
    private JTextArea resultArea = new JTextArea(result,10,30);

    private Prj_581_encoderRing encoderRing = new Prj_581_encoderRing();
    private JPanel primaryPanel = new JPanel();

    public Prj581gridBagGUI() {
        super("Project 581 - Name Generator");
        this.primaryFrame = new JFrame();
        this.setLocation(50,50);
        this.setSize(300, 400);//creates a frame of 400x600
        addWindowListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout primaryLayout = new GridBagLayout();
        this.setLayout(primaryLayout); //setting this to primaryLayout displays everything in dead center

        GridBagConstraints primaryContraints = new GridBagConstraints();
        //create a control panel for input

        //can we do this without the panels?
        primaryContraints.gridx = 0;
        primaryContraints.gridy = 0;
        primaryContraints.gridwidth = 2;
//        primaryContraints.gridheight = 1;
//        primaryContraints.weightx = 1.0;
//        primaryContraints.weighty = 1.0;
        projectName.setHorizontalAlignment(SwingConstants.LEFT);
        add(projectName,primaryContraints);

        primaryContraints.gridx = 0;
        primaryContraints.gridy = 1;
        primaryContraints.gridwidth = 5;
        primaryContraints.gridheight = 1;
        primaryContraints.fill = GridBagConstraints.HORIZONTAL;
        add(projectNameField,primaryContraints);

//        primaryContraints.weightx = 0.3;
//        primaryContraints.weighty = 0.4;

        primaryContraints.gridx = 2;
        primaryContraints.gridy = 0;
        primaryContraints.gridwidth = 5;
        primaryContraints.gridheight = 1;
//        primaryContraints.weightx = 1.0;
//        primaryContraints.weighty = 1.0;
        add(digitLen,primaryContraints);

        primaryContraints.gridx = 3;
        primaryContraints.gridy = 1;
//        primaryContraints.gridwidth = 5;
//        primaryContraints.gridheight = 1;
        primaryContraints.weightx = 1.0;
        primaryContraints.weighty = 1.0;
        add(digitLenField,primaryContraints);



        primaryContraints.gridx = 0;
        primaryContraints.gridy = 5;
        primaryContraints.gridwidth = 5;
        primaryContraints.gridheight = 1;
//        primaryContraints.weightx = 1.0;
//        primaryContraints.weighty = 1.0;
        primaryContraints.fill = GridBagConstraints.NONE;
        add(encode,primaryContraints);

        primaryContraints.gridx = 0;
        primaryContraints.gridy = 3;
        primaryContraints.gridwidth = 3;
        primaryContraints.gridheight = 4;
        primaryContraints.weightx = 1.0;
        primaryContraints.weighty = 1.0;
//        resultArea.setSize(new Dimension(200,200));
//        resultArea.setBackground(Color.CYAN);
        primaryContraints.fill = GridBagConstraints.HORIZONTAL;
        add(new JPanel(null),primaryContraints);

        primaryContraints.gridx = 1;
        primaryContraints.gridy = 3;
        primaryContraints.gridwidth = 5;
        primaryContraints.gridheight = 4;
        primaryContraints.weightx = 1.0;
        primaryContraints.weighty = 1.0;
        resultArea.setSize(new Dimension(200,200));
        resultArea.setBackground(Color.CYAN);
        primaryContraints.fill = GridBagConstraints.HORIZONTAL;
        add(resultArea,primaryContraints);




//        JPanel controlPanel = new JPanel();
//        primaryContraints.gridx = 0;
//        primaryContraints.gridy = 0;
//        primaryContraints.gridwidth = 2;
//        primaryContraints.gridheight = 1;
//        primaryContraints.weightx = 1.0;
//        primaryContraints.weighty = 1.0;
//        controlPanel.add(projectName,primaryContraints);

//        primaryContraints.gridx = 1;
//        primaryContraints.gridy = 3;
//        primaryContraints.gridwidth = 3;
//        primaryContraints.gridheight = 1;
//        controlPanel.add(projectNameField,primaryContraints);
//        controlPanel.setBackground(Color.RED);
//        primaryLayout.setConstraints(controlPanel,primaryContraints); //irrelevant
//        controlPanel.setLayout(primaryLayout);
//        controlPanel.setSize(300,300);
        primaryContraints.gridx = 0;
        primaryContraints.gridy = 0;
        primaryContraints.weightx = 0.5;
        primaryContraints.weighty = 1.0;
//        primaryContraints.gridwidth = 3;
//        primaryContraints.gridheight = 3;
        primaryContraints.anchor = GridBagConstraints.NORTHWEST;
//        add(controlPanel,primaryContraints);

//        primaryContraints.gridx = 1;
//        primaryContraints.gridy = 1;
//        primaryContraints.gridwidth = 4;
//        primaryContraints.weightx = 0.1;
//        primaryContraints.weighty = 0.7;
//        controlPanel.add(projectNameField,primaryContraints);
//        primaryContraints.gridx = 0;
//        primaryContraints.gridy = 3;
//        primaryContraints.gridwidth = 2;
//        primaryContraints.weightx = 0.1;
//        primaryContraints.weighty = 0.7;
//        controlPanel.add(digitLen,primaryContraints);
//        primaryContraints.gridx = 1;
//        primaryContraints.gridy = 4;
//        primaryContraints.gridwidth = 2;
//        primaryContraints.weightx = 0.1;
//        primaryContraints.weighty = 0.7;
//        controlPanel.add(digitLenField,primaryContraints);
//        controlPanel.add(variants,primaryContraints);
//        controlPanel.add(variantsField,primaryContraints);
//
//        //create a display panel for the output
//        JPanel displayPanel = new JPanel();
//        JTextArea textArea = new JTextArea();
//        displayPanel.add(textArea);
//
//        //create a button panel to take orders from the user
//        JPanel buttonPanel = new JPanel();
//
//        ButtonGroup radioGroup = new ButtonGroup();
//        radioGroup.add(noVariants);
//        radioGroup.add(descendingVars);
//        radioGroup.add(ascendingVariants);
//        radioGroup.add(neighboringVariants);
//        buttonPanel.add(noVariants);
//        buttonPanel.add(descendingVars);
//        buttonPanel.add(ascendingVariants);
//        buttonPanel.add(neighboringVariants);
//
//        buttonPanel.add(encode);
//        buttonPanel.add(clearFields);
//
//        /////////////////tie buttons to actions done by the program;
        encode.addActionListener(new callEncode());
//        clearFields.addActionListener(new clearFields());
//
//        //create a primary panel that will hold the I/O information; spanning the entire frame; with space for the 3 following panels
//        primaryContraints.gridx = 0;
//        primaryContraints.gridy = 0;
//        displayPanel.setBackground(Color.YELLOW);
//        this.add(controlPanel,primaryContraints);
//
//        primaryContraints.ipady = 40;
//        primaryContraints.gridx = 0;
//        primaryContraints.gridy = 1;
//        primaryContraints.gridheight = 3;
//        displayPanel.setBackground(Color.RED);
////        this.add(displayPanel,primaryContraints);
//
//        primaryContraints.ipady = 0;
//        primaryContraints.gridx = 0;
//        primaryContraints.gridy = 2;
//        primaryContraints.gridheight = 1;
//        buttonPanel.setBackground(Color.BLUE);
////        this.add(buttonPanel,primaryContraints);



   }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        this.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    //EVENT LISTENERS FOR BUTTONS
    class callEncode implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String prjName = projectNameField.getText();
            String digitLen = digitLenField.getText();
            int digits;
            if (digitLen.isEmpty()){
                digits = prjName.length();
            } else {
                digits = Integer.parseInt(digitLenField.getText());
            }
            String result = encoderRing.encodeSimple(prjName,digits);
            resultArea.setText("");
            resultArea.setText(result);
        }
    }
    class clearFields implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            projectNameField.setText("");
            digitLenField.setText("");
            resultArea.setText("");
            noVariants.setSelected(true);
        }
    }
}
