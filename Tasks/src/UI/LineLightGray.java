package UI;

import System.Style;
import javax.swing.*;

public class LineLightGray extends JLabel {

    public LineLightGray(int x, int y, int width){
        this.setBounds(x,y,width,1);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Style.lightGray));
    }
}
