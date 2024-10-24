package UI;

import System.Style;

import javax.swing.*;

public class LabelHeadline extends JLabel {

    public LabelHeadline(int x, int y, String text){
        this.setBounds(x,y,180,22);
        this.setFont(Style.fontRegular18);
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setText(text);
    }
}
