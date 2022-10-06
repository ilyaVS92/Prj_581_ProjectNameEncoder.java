import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class fromTut extends JFrame {
    public void makeButton(String name, GridBagLayout gbLayout, GridBagConstraints c){
        Button button = new Button(name);
        gbLayout.setConstraints(button,c);
        add(button);
    }
    public void init(){
        GridBagLayout gbLayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

//        setFont(new Font("SansSerif"))
        setLayout(gbLayout);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;

//        makeButton("lk");
    }
}
