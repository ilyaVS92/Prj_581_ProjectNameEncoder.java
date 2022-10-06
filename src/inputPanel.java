import javax.swing.*;
import java.awt.*;

public class inputPanel extends JPanel {
    JPanel inputFields;
    private JTextField projectNameField = new JTextField();
    private JLabel projectNameLabel = new JLabel("Project Name: ");

    private JTextField prefixField = new JTextField();
    private JLabel prefixNameLabel = new JLabel("Project Prefix: ");

    private JTextField digitLenField = new JTextField();
    private JLabel digitLen = new JLabel("No of Digits: ");

    private JTextField variantsField = new JTextField("Variants: ");
    private JLabel variants = new JLabel("Var to display: "); //not yet working

    private JLabel test1 = new JLabel("test 1");
    private JLabel test2 = new JLabel("test 2");
    private JLabel test3 = new JLabel("test 3");
    private JLabel test4 = new JLabel("test 4");
    private JLabel test5 = new JLabel("test 5");
    private JLabel test6 = new JLabel("test 6");
    private JLabel test7 = new JLabel("test 7");


    public inputPanel(){
//        testFrame.setLayout(new GridBagLayout());
        this.inputFields = new JPanel();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbcInputFields = new GridBagConstraints();
        gbcInputFields.gridwidth = 3;
//        gbcInputFields.gridheight = 7;


        gbcInputFields.gridx = 0;
        gbcInputFields.gridy = 0;
        gbcInputFields.gridwidth = 1; //y+1;
        inputFields.add(test1, gbcInputFields);
//        inputFields.add(projectNameLabel,gbcInputFields);

        gbcInputFields.gridx = 1;
        gbcInputFields.gridy = 0; //I want the field under the name
        gbcInputFields.gridwidth = 3; //y+1;
        inputFields.add(test2, gbcInputFields);

//        inputFields.add(prefixField, gbcInputFields);

        gbcInputFields.gridx = 2;
        gbcInputFields.gridy = 3; //I want the field under the name
        gbcInputFields.gridwidth = 3; //y+1;
        inputFields.add(test3, gbcInputFields);

//        inputFields.add(projectNameField, gbcInputFields);

        gbcInputFields.gridx = 3;
        gbcInputFields.gridy = 3;
        gbcInputFields.gridwidth = 1; //y+1;
        inputFields.add(test4, gbcInputFields);

//        inputFields.add(digitLen,gbcInputFields);

        gbcInputFields.gridx = 4;
        gbcInputFields.gridy = 3;
        gbcInputFields.gridwidth = 1; //y+1;
        inputFields.add(test5, gbcInputFields);

//        inputFields.add(digitLenField,gbcInputFields);

        gbcInputFields.gridx = 5;
        gbcInputFields.gridy = 3;
        gbcInputFields.gridwidth = 1; //y+1;
        inputFields.add(test6, gbcInputFields);

//        inputFields.add(variants, gbcInputFields);

        gbcInputFields.gridx = 6;
        gbcInputFields.gridy = 3;
        gbcInputFields.gridwidth = 1; //y+1;
        inputFields.add(test7, gbcInputFields);

//        inputFields.add(variantsField, gbcInputFields);
//
        inputFields.setVisible(true);
    }
}
