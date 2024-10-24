package System;

import Frames.MainFrame;
import Panels.PanelLocation1;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginFront extends JFrame implements ActionListener {
    // Linki od zdjec
    java.net.URL imgUrl_photo = getClass().getResource("/Images/polsl_big_pion.png");
    ImageIcon imgPhoto = new ImageIcon(imgUrl_photo);

    java.net.URL urlEV = getClass().getResource("/Images/logo-color-big.png");
    ImageIcon imgEV = new ImageIcon(urlEV);

    JButton loginButton = new JButton("Sign In");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("Username");
    JLabel userPasswordLabel = new JLabel("Password");
    JLabel headline = new JLabel("Sign In");
    JLabel headline_2 = new JLabel();
    JLabel photo = new JLabel();

    HashMap<String,String> logininfo = new HashMap<String,String>();

    LoginFront(HashMap<String,String> loginInfoOriginal){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,800);
        getContentPane().setBackground(Style.white);
        setTitle("O&M App");
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setIconImage(imgEV.getImage());
        setVisible(true);

        logininfo = loginInfoOriginal;

        headline.setBounds(650,185,350,40);
        headline.setFont(Style.fontSemibold18);

        headline_2.setText("O&M App");
        headline_2.setBounds(650,220,350,20);
        headline_2.setFont(Style.fontRegular16);
        headline_2.setForeground(Style.black);

        userIDLabel.setBounds(650,285,200,25);
        userIDLabel.setFont(Style.fontRegular12);

        userPasswordLabel.setBounds(650,360,75,25);
        userPasswordLabel.setFont(Style.fontRegular12);

        userIDField.setBounds(650,310,400,35);
        userIDField.setHorizontalAlignment(SwingConstants.RIGHT);
        userIDField.setFont(Style.fontRegular12);

        userPasswordField.setBounds(650,385,400,35);
        userPasswordField.setHorizontalAlignment(SwingConstants.RIGHT);
        userPasswordField.setFont(Style.fontRegular12);

        loginButton.setBounds(650,480,400,45);
        loginButton.setFont(Style.fontSemibold12);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        photo.setBounds(87,70,466,600);
        photo.setIcon(imgEV);

        add(userIDLabel);
        add(userPasswordLabel);
        add(userIDField);
        add(userPasswordField);
        add(loginButton);
        add(headline);
        add(headline_2);
        add(photo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            // Weryfikacja czy klucz znajduje się na liscie
            if( logininfo.containsKey(userID)){
                // Weryfikacja czy klucz jest zgodny z podanym hasłem
                if(logininfo.get(userID).equals(password)){

                    // Uruchomienie głównej ramki aplikacji
                    MainFrame mainFrame = new MainFrame();

                    // Odczyt danych o aktualnie zalogowanym użytkowniku
                    DataReader.readActualDataUser(userID);

                    // Zamknięcie okna
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Błędne hasło", "Komunikat", JOptionPane.INFORMATION_MESSAGE);
                }

            }
            else {
                JOptionPane.showMessageDialog(null, "Nie znaleziono użytkownika", "Komunikat", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
