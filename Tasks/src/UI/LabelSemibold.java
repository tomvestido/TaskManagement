package UI;

import System.Style;

import javax.swing.*;

public class LabelSemibold extends JLabel {

    public LabelSemibold(int x, int y, String text){
        this.setBounds(x,y,180,22);
        this.setFont(Style.fontSemibold14);
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setText(text);
    }
}
