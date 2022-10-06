import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimeZone;

import static java.util.TimeZone.getTimeZone;


public class Prj_581_swingGUI extends JFrame implements ActionListener{
    private final static String lineBreak = "\n";

    private JFrame frame = new JFrame();
    GridBagConstraints gbcMain = new GridBagConstraints();
    GridBagLayout gbLayout = new GridBagLayout();
    private Prj_581_encoderRing encoderRing = new Prj_581_encoderRing();
    FileWriter fw;// = new FileWriter(fileName,append);
    BufferedWriter bw;// = new BufferedWriter(fw);


    private JButton encode = new JButton("Encode");
    private JButton clearFields = new JButton("Clear All Fields");
    private JButton saveToLog = new JButton("Append to File (DNE)");

    private JButton mainMenu = new JButton("Main Menu");
//      NOT IN USE
//    private JButton button1 = new JButton("<-");
//    private JButton button2 = new JButton("<-");
//    private JButton button3 = new JButton("<-");
//    private JButton button4 = new JButton("<-");

    private JRadioButton noVariants = new JRadioButton("None",true);
    private JRadioButton descendingVars = new JRadioButton("Descd");
    private JRadioButton ascendingVariants = new JRadioButton("Ascnd");
    private JRadioButton neighboringVariants = new JRadioButton("near-by");

    /////------------Project Designation (not connected to encoder)-----------------
    private JTextField designationField = new JTextField();
    private JLabel designationLabel = new JLabel("Project 1: (ud) ");
    private JTextField designationDigitLenField = new JTextField();
    private JLabel designationDigitLenLabel = new JLabel("No of Digits: ");
    /////------------Project Name (working)-----------------
    private JTextField projectNameField = new JTextField();
    private JLabel projectNameLabel = new JLabel("Project 2 Name: ");
    private JTextField projectDigitLenField = new JTextField();
    private JLabel projectDigitLenLabel = new JLabel("No 2 of Digits: ");
    ////////////////////////////////////////////////////////
    private JTextField varsDigitsField = new JTextField();
    private JLabel varsLabel = new JLabel("No variations: (ud)");

    private JCheckBox addRecordChckBox = new JCheckBox("Add to log",false);

    private JTextArea resultsTextArea = new JTextArea(30,10);

    private ArrayList<String> resultsForDisplay = new ArrayList<>();

    //to generate an array that contains all of the
    int designationIDX = 0;
    int projectIDX = 1;

    int desigLenIDX = 0; //inputNums [0] = project 1 digit len
    int prjLenIDX = 1; //inputNums [1] = project 2 digit len
    int varsIDX = 2; //inputNums [2] = vars // only applies to project 2?
    int modeIDX = 3; //inputNums [3] = mode

    public Prj_581_swingGUI() {
        super("Encoder Ring");
//        JFrame mainFrame = new JFrame(); //this.(whatever) covers the frame assignment... right?
        setSize(400,400);//creates a frame of 400x600 idk what x:100 y:100 is
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setVisible(true);
//        gbcMain.weightx = 5;
//        gbcMain.weighty = 5;
        setLayout(gbLayout);
        setResizable(false);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(4,1));
//        panel1.add(title);

        panel1.add(designationLabel);
        panel1.add(designationField);
        panel1.add(projectNameLabel);
        panel1.add(projectNameField);
        panel1.setPreferredSize(new Dimension(300,50));
        projectNameField.setPreferredSize(new Dimension(200,25));
        projectNameField.setHorizontalAlignment(SwingConstants.LEFT);
//        panel1.setLayout(gbLayout); //does nothign when no layout manager chosen for frame; not displaying anything
//        panel1.setBackground(Color.RED);
//        panel1.setPreferredSize(new Dimension(100,100));
//        gbcMain.anchor = GridBagConstraints.NORTHWEST;
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.gridwidth = 4; //from 0 - 3
        gbcMain.gridheight = 1;
//        gbcMain.weightx = 5;
//        gbcMain.weighty = 0;
        add(panel1,gbcMain);


        JPanel resultAreaPanel = new JPanel();
        resultsTextArea.setEditable(false);

        GridLayout p2layout = new GridLayout(1,1);
        resultAreaPanel.setLayout(p2layout); //does nothign when no layout manager chosen for frame; not d

        gbcMain.gridx = 0;
        gbcMain.gridy = 1;
        gbcMain.gridwidth = 1;
        gbcMain.gridheight = 1;
        gbcMain.weightx = 3;
        gbcMain.weighty = 4;
        gbcMain.anchor = GridBagConstraints.SOUTHEAST;
        gbcMain.fill = GridBagConstraints.BOTH;
        resultsTextArea.setSelectedTextColor(Color.BLACK);
        resultsTextArea.setMargin(new Insets(1, 12, 12, 12));

        resultsTextArea.setLineWrap(true);
        resultAreaPanel.add(resultsTextArea,p2layout);
        this.add(resultAreaPanel,gbcMain);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(4,1)); //does nothign when no layout manager chosen for frame; not displaying anything
//        panel3.setBackground(Color.MAGENTA); //for initial setup
        panel3.add(designationDigitLenLabel);
        panel3.add(designationDigitLenField);
        panel3.add(projectDigitLenLabel);
        panel3.add(projectDigitLenField);

//        panel3.setPreferredSize(new Dimension(100,100));
        gbcMain.gridx = 4;
        gbcMain.gridy = 0;
        gbcMain.gridwidth = 1;
        gbcMain.gridheight = 1;
        gbcMain.weightx = 0;
        gbcMain.weighty = 0;
        gbcMain.fill = GridBagConstraints.BOTH;

        add(panel3,gbcMain);

        JPanel panel4 = new JPanel();
//        JLabel title4 = new JLabel("panel 4");
//        title4.setVerticalAlignment(SwingConstants.NORTH);
        GridLayout p4 = new GridLayout(9,1);
        panel4.setLayout(p4);
//        panel4.add(title4);
//        panel4.setLayout(gbLayout); //does nothing when no layout manager chosen for frame; not displaying anything
        panel4.setBackground(Color.BLUE);
//        panel4.setPreferredSize(new Dimension(100,100));
        panel4.add(varsLabel);
        panel4.add(varsDigitsField);
        GridLayout radioButtGrid = new GridLayout(4,1);
//        JPanel radioPanel = new JPanel(radioButtGrid);
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(noVariants);
        radioGroup.add(descendingVars);
        radioGroup.add(ascendingVariants);
        radioGroup.add(neighboringVariants);
        panel4.add(noVariants,radioButtGrid);
        panel4.add(descendingVars,radioButtGrid);
        panel4.add(ascendingVariants,radioButtGrid);
        panel4.add(neighboringVariants,radioButtGrid);
        panel4.add(addRecordChckBox);
        encode.addActionListener(this);
        clearFields.addActionListener(this);
        panel4.add(encode);
        panel4.add(clearFields);

        gbcMain.gridx = 4;
        gbcMain.gridy = 1;
        gbcMain.gridwidth = 1;
        gbcMain.gridheight = 1;
        gbcMain.weightx = 0;
        gbcMain.weighty = 2;
        gbcMain.fill = GridBagConstraints.BOTH;

        add(panel4,gbcMain);

        JPanel panel5 = new JPanel();
//        BorderLayout panel5layout = new BorderLayout(3,3);
        GridLayout panel5layout = new GridLayout(2,1);
        panel5.setLayout(panel5layout);
        panel5.setBackground(Color.BLACK);



        gbcMain.gridx = 4; //so all the next 4 relate to the main layout this??!!
        gbcMain.gridy = 2;
        gbcMain.gridwidth = 1;
        gbcMain.gridheight = 1;
        gbcMain.weightx = 0;
        gbcMain.weighty = 0;
//        gbcMain.anchor = GridBagConstraints.EAST;
//        add(panel5,gbcMain);

    }


    private boolean writeToLog(String input){
        try {
            String fileName = "output.txt";
            boolean append = false;
            if (Files.exists(Path.of(fileName))){
                append = true;
            } else {
                File file = new File("output.txt");
            }
            fw = new FileWriter(fileName,append);
            bw = new BufferedWriter(fw);

            bw.append(input+"\n");
            fw.close();
            bw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void displayResults(ArrayList<String> resultsForDisplay){//do we even need this???
        for (String s : resultsForDisplay){
            resultsTextArea.setText(s+ lineBreak);
        }
    }
    private String getDateAndTime(){ //WORKS
        long now = System.currentTimeMillis();
        //get minutes with a leading zero
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        TimeZone tz = TimeZone.getDefault();
//        String zone1 = tz.getID();
//        ZoneId offset = ZoneOffset.of(tz.getID());

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM.dd.yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
//        String tZone2 = TimeZone.getTimeZone(offset).toString();
//        String tZone = offset.getId();
//        offset.getOffset();
        int zone = (tz.getOffset(now))/3600000;
        time = time.truncatedTo(ChronoUnit.SECONDS);
        date.format(dateFormat);
        time.format(timeFormat);

        return date+" "+time+" "+zone;
    }
    private boolean validateInputs (){

        return false;
    }
    private String [] gatherNames (){
        String [] names = new String [2];
        String projectName = projectNameField.getText();
        String projectDesignation = designationField.getText();
        if (!projectName.isEmpty()){
            if (!projectDesignation.isEmpty()) {
                names[designationIDX] = designationField.getText();
            }
            names[projectIDX] = projectName;
        } else {
            resultsTextArea.append(getDateAndTime());
            resultsTextArea.append("ERROR: Project name field is empty... \nhint hint");
            return null;
        }
        return names;
    }

    private int [] gatherNums (){
//        int desigLenIDX = 0; //inputNums [0] = project 1 digit len
//        int prjLenIDX = 1; //inputNums [1] = project 2 digit len
//        int varsIDX = 2; //inputNums [2] = vars // only applies to project 2?
//        int modeIDX = 3; //inputNums [3] = mode
        int [] inputNums = new int[4];
        String varFieldContents = varsDigitsField.getText();
        String digitDesContents = designationDigitLenField.getText();
        String digitLenContents = projectDigitLenField.getText();
        //mode is being set by radio buttons and doesn't need that kind of input

        //get DigitLenDES, 1. consider IFF designation name is !empty;
        // 2. if empty use deisngationField.length();
        if (designationField.getText().isEmpty()){
            inputNums[desigLenIDX] = -1;
        }

        if (digitDesContents.isEmpty()){
            inputNums[desigLenIDX] = -1;
        } else {
            int digitDesInt = Integer.parseInt(designationDigitLenField.getText());
            if (digitDesInt<0){
                inputNums[desigLenIDX] = -1;
            }
            inputNums[desigLenIDX] = digitDesInt;
        }
        //get DigitLen
        if (digitLenContents.isEmpty()){
            inputNums[desigLenIDX] = -1;
        } else {
            int digitLenInt = Integer.parseInt(projectDigitLenField.getText());
            if (digitLenInt<0){
                inputNums[prjLenIDX] = -1;
            }
            inputNums[prjLenIDX] = digitLenInt;
        }
        //get mode and vars used (if any)
        if (noVariants.isSelected()) {
            inputNums[varsIDX] = 1;//not used
            inputNums[modeIDX] = 4;
        } else if (varFieldContents.isEmpty()) {
            //we need to return an error here
            inputNums[varsIDX] = 1;//not used
            inputNums[modeIDX] = -1; // will be used to return an error to the textArea
        } else {
            int varFieldValue = Integer.parseInt(varFieldContents);
            inputNums[varsIDX] = varFieldValue;
            if (ascendingVariants.isSelected()){
                inputNums[modeIDX] = 1;
            } else if (descendingVars.isSelected()){
                inputNums[modeIDX] = 2;
            } else if (neighboringVariants.isSelected()){
                inputNums[modeIDX] = 3;
            } else {
                inputNums[modeIDX] = 4; //probably unreachable
            }
        }
        return inputNums;
    }
//    private boolean [] validateInputs(){
//        boolean validName = false;
//        boolean validNameLen = false;
//        boolean validDes = false;
//        boolean validDesLen = false;
//        boolean validVarsInt = false;
//        boolean validMode = false;
//        boolean [] validation = new boolean [6];
//        int validNameIDX = 0;
//        int validNameLenIDX = 1;
//        int validDesIDX = 2;
//        int validDesLenIDX = 3;
//        int validVarsIntIDX = 4;
//        int validModeIDX = 5;
//
//        if (projectNameField.getText().length()>=2){
//            validation[validNameIDX] = true;
//        }
//        if(Integer.parseInt(projectDigitLenField.getText())>=0){
//            validation[validNameLenIDX] = true;
//        }
//        if(designationField.getText().length()>=2){ // checking if designation len >2
//            validation[validDesIDX] = true;
//        }
//        if(Integer.parseInt(projectDigitLenField.getText())>=0){
//            validation[validDesLenIDX] = true;
//        }
//        if(Integer.parseInt(projectDigitLenField.getText())>=0){
//            validation[validVarsIntIDX] = true;
//        }
//        if(getMode)>=0){
//            validation[validModeIDX] = true;
//        }
//        return validation;
//    }
//    private HashMap<String [],int []> gatherInputs (){ //GATHER INPUTS
//        //names [0] = project designation
//        //names [1] = project name
//
//        //[0] = des digits
//        //[1] = digit len for the main encode
//        //[2] = variations required
//        //[3] = mode selection
//
//        //trying to clean up the code in the ActionListener
//        //GOAL:
//        /* example
//        encoded: 16.08.2022 18:02:34 (add option to incorporate date and time into final result)
//        project designation "fire" = 351
//        project "name" = 443241
//        settings: var = 2; mode = 3
//            1. 3412 (here we can loop through an array)
//            2. 32413
//            3. 443241 *
//            4. 3204811
//            5. 30192831
//         */
//        String projectName = projectNameField.getText();
//        String projectDesignation = designationField.getText();
//        if (!projectName.isEmpty()){
//            HashMap<String [],int []> input = new HashMap<>();
//            String [] names = new String [2];
//            int [] inputNums = new int [4];
//            if (!projectDesignation.isEmpty()) {
//                names[namesDesignationIDX] = designationField.getText();
//            }
//            names[namesProjectIDX] = projectName;
//
//            String varFieldContents = varsDigitsField.getText();
//            String digitDesContents = designationDigitLenField.getText();
//            String digitLenContents = projectDigitLenField.getText();
//            //mode is being set by radio buttons and doesn't need that kind of input
//
//            //get DigitLenDES
//            if (digitDesContents.isEmpty()){
//                inputNums[designationDigitLenIDX] = -1;
//            } else {
//                int digitDesInt = Integer.parseInt(designationDigitLenField.getText());
//                if (digitDesInt<0){
//                    inputNums[designationDigitLenIDX] = -1;
//                }
//                inputNums[designationDigitLenIDX] = digitDesInt;
//            }
//            //get DigitLen
//            if (digitLenContents.isEmpty()){
//                inputNums[designationDigitLenIDX] = -1;
//            } else {
//                int digitLenInt = Integer.parseInt(projectDigitLenField.getText());
//                if (digitLenInt<0){
//                    inputNums[projectDigitLenIDX] = -1;
//                }
//                inputNums[projectDigitLenIDX] = digitLenInt;
//            }
//            //get mode and vars used (if any)
//            if (noVariants.isSelected()) {
//                inputNums[varsIDX] = 1;//not used
//                inputNums[modeIDX] = 4;
//            } else if (varFieldContents.isEmpty()) {
//                //we need to return an error here
//                inputNums[varsIDX] = 1;//not used
//                inputNums[modeIDX] = -1; // will be used to return an error to the textArea
//            } else {
//                int varFieldValue = Integer.parseInt(varFieldContents);
//                inputNums[varsIDX] = varFieldValue;
//                if (ascendingVariants.isSelected()){
//                    inputNums[modeIDX] = 1;
//                } else if (descendingVars.isSelected()){
//                    inputNums[modeIDX] = 2;
//                } else if (neighboringVariants.isSelected()){
//                    inputNums[modeIDX] = 3;
//                } else {
//                    inputNums[modeIDX] = 4; //probably unreachable
//                }
//            }
//            input.put(names,inputNums);
//            return input;
//
//        } else {
//            resultsTextArea.setText("ERROR: Project name field is empty... \nhint hint");
//            return null;
//        }
//        //get
//
////        input.
//    }
    public void computeAndDisplay(){
        String [] names = gatherNames();
        int [] vals = gatherNums();
        ArrayList<String> mainResults = encoderRing.encodeWithVars(names[projectIDX],vals[prjLenIDX],vals[varsIDX],vals[modeIDX]);

//            ArrayList<String> mainResults = encoderRing.encodeWithVars(names[namesProjectIDX],vals[projectDigitLenIDX],vals[])
            /* example
//        encoded: 16.08.2022 18:02:34 (add option to incorporate date and time into final result)
//        project designation "fire" = 351 <<<<<<< 21.06 - still need to add the functionality for this being computed individually
//        project "name" = 443241
//        settings: var = 2; mode = 3
//            1. 3412 (here we can loop through an array)
//            2. 32413
//            3. 443241 *
//            4. 3204811
//            5. 30192831
            */
        //display general information about the process -


        resultsTextArea.setText("RUN-TIME: "+getDateAndTime()+ lineBreak);
        if(!designationField.getText().isEmpty()){
            resultsTextArea.append("DESIGN.: "+names[designationIDX]+"" +
                    " = "+encoderRing.encodeSimple(names[designationIDX],vals[desigLenIDX])+lineBreak);
        } else {
            resultsTextArea.append("DESIGN.: not available"+lineBreak);
        }
        resultsTextArea.append("PROJ.: "+names[projectIDX]+ lineBreak);
        resultsTextArea.append("RESULTS FOLLOW:\n");
        //display results from encoderRing+ vars
        int countResults = 1;
        for (String r : mainResults){
            resultsTextArea.append("   Result "+countResults+". "+r+" ("+r.length()+")"+lineBreak);
            countResults++;
        }
    }
    private void showError(int errorCode){
        //USE ENUMS for this purpose
        /*
        1. invalid value - need an integer, found something else
        2. invalid value - need a string, found something else
        ... don't know what else
         */
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearFields) {
            designationField.setText("");
            projectNameField.setText("");
            projectDigitLenField.setText("");
            designationDigitLenField.setText("");
            varsDigitsField.setText("");
            resultsTextArea.setText("");
            noVariants.setSelected(true);
        }
        if (e.getSource() == encode){
            computeAndDisplay();
            //21.06.2022
            /*
            after I press encode; I want -
            0. get the inputs
            1. to validate the inputs
            2. use the validated inputs in the program
             */
            // MAIN GOAL: return results - 19.06.2022
            //---------------return results for DES
//            int [] localNums = getInputInfo();
//
//            String prjDes = designationField.getText();
//            int digitsDes;
//
//
//            //---------------return results for main projectName
//            String prjName = projectNameField.getText();
//            int digitsPrj;


            //---------------display results for both

            //if write to file is enabled, dump textArea to file

//            if (!prjName.isEmpty()){
////                if (digitLenLabel.isEmpty()){
//                    digitsPrj = prjName.length();
//                } else {
//                    digitsPrj = localNums[inputDigitsIDX];
//                }
//
//                if (!prjDes.isEmpty()){
////                    if(digitDes.isEmpty()){
//                        digitsDes = prjDes.length();
//                    } else {
//                    }
//                    result += encoderRing.encodeSimple(prjName,digitsDes)+"-";
//                }
//                result += encoderRing.encodeSimple(prjName,digitsPrj);
//                displayed += getDateAndTime()+" - "+prjName+" = "+result+"\n";
//
//                resultsTextArea.setText("");
//                resultsTextArea.setText(displayed);
//                encoderRing.encodeWithVars(inputNums[inputDigitsIDX],inputNums[varsIDX],inputNums[modeIDX])
//                if(addRecord.isSelected()){
//                    writeToLog(displayed);
//                    if (writeToLog(displayed)==false){
//                        writeStatus += "unable to write to log"+"\n";
//                    } else {
//                        writeStatus += "I guess everything worked OK"+"\n";
//                    }
//                }
//                resultsField.setText(writeStatus);
//            } else {
//                resultsTextArea.setText("");
//
//            }
        }
    }
}
