package Panels;

import Buttons.ButtonCategory;
import javax.swing.*;
import System.Style;

public class PanelServices extends JPanel {

    // menu wyboru farmy
    JPanel panelMenus = new JPanel();
    public ButtonCategory btnLocation_1 = new ButtonCategory(0,0,"Komorów I/II", "Localization: Polska", "Plant power: 0,986 MW", "Amount of panels: 3080 sztuk");
    public ButtonCategory btnLocation_2 = new ButtonCategory(240,0,"Dębica", "Localization: Polska", "Plant power: 0,962 MW", "Amount of panels: 3435 sztuk");
    public ButtonCategory btnLocation_3 = new ButtonCategory(480,0,"Goszcz", "Localization: Polska", "Plant power: 0,986 MW", "Amount of panels: 3520 sztuk");
    public ButtonCategory btnLocation_4 = new ButtonCategory(720,0,"Szczuczyn II", "Localization: Polska", "Plant power: 0,986 MW", "Amount of panels: 3520 sztuk");
    public ButtonCategory btnLocation_5 = new ButtonCategory(0,240,"Zacisze I", "Localization: Polska", "Plant power: 0,845 MW", "Amount of panels: 3017 sztuk");
    public ButtonCategory btnLocation_6 = new ButtonCategory(240,240,"Zacisze II", "Localization: Polska", "Plant power: 0,728 MW", "Amount of panels: 2600 sztuk");
    public ButtonCategory btnLocation_7 = new ButtonCategory(480,240,"Peritz", "Localization: Niemcy", "Plant power: 1,2 MW", "Amount of panels: 10000 sztuk");
    public ButtonCategory btnLocation_8 = new ButtonCategory(720,240,"Gummersbach", "Localization: Niemcy", "Plant power: 0,35 MW", "Amount of panels: 1458 sztuk");

    // konstruktor
    public PanelServices(){
        setBounds(0, 0, 1920, 1040);
        setBackground(Style.white);
        setOpaque(true);
        setLayout(null);
        setVisible(false);

        // menu wyboru farmy
        panelMenus.setBounds(485,285,950,470);
        panelMenus.setOpaque(false);
        panelMenus.setLayout(null);
        panelMenus.add(btnLocation_1);
        panelMenus.add(btnLocation_2);
        panelMenus.add(btnLocation_3);
        panelMenus.add(btnLocation_4);
        panelMenus.add(btnLocation_5);
        panelMenus.add(btnLocation_6);
        panelMenus.add(btnLocation_7);
        panelMenus.add(btnLocation_8);

        add(panelMenus);
    }
}
