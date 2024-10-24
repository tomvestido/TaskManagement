package UI;

import javax.swing.*;
import System.Style;

public class LabelLight extends JLabel {

    public LabelLight(int x, int y, String text){
        this.setBounds(x,y,180,22);
        this.setFont(Style.fontRegular14);
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setText(text);
    }
}
