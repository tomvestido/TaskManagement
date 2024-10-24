package Dialogs;
import Panels.PanelMain;
import System.*;
import UI.LabelHeadline;
import UI.LabelLight;
import UI.LabelSemibold;
import UI.LineLightGray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DialogTaskDetails extends JDialog implements ActionListener {

    LabelHeadline labelHeadline = new LabelHeadline(70,75,"Task details");
    LabelLight labelID = new LabelLight(70,119,"ID");
    LabelLight labelStatus = new LabelLight(70,182,"Status");
    LabelLight labelDescription = new LabelLight(70,245,"Description");
    LabelLight labelLocalization = new LabelLight(70,308,"Localization");
    LabelLight labelType = new LabelLight(70,371,"Type");
    LabelLight labelEntranceDate = new LabelLight(70,434,"Entrance date");
    LabelLight labelRequiredDate = new LabelLight(280,119,"Required Date");
    LabelLight labelStarted = new LabelLight(280,182,"Started");
    LabelLight labelFinished = new LabelLight(280,245,"Finished");
    LabelLight labelResponsibleCompany = new LabelLight(280,308,"ResponsibleCompany");
    LabelLight labelSMS = new LabelLight(280,371,"SMS");
    LabelLight labelMail = new LabelLight(280,434,"Mail");
    LabelLight labelComment = new LabelLight(490,119,"Comment");

    LabelSemibold labelIDDynamic = new LabelSemibold(70,141, "Unknown");
    LabelSemibold labelStatusDynamic = new LabelSemibold(70,204, "Unknown");
    LabelSemibold labelDescriptionDynamic = new LabelSemibold(70,267,"Unknown");
    LabelSemibold labelLocalizationDynamic = new LabelSemibold(70,330,"Unknown");
    LabelSemibold labelTypeDynamic = new LabelSemibold(70,393,"Unknown");
    LabelSemibold labelEntranceDateDynamic = new LabelSemibold(70,456,"Unknown");


    LineLightGray line1 = new LineLightGray(70,111,400);
    LineLightGray line2 = new LineLightGray(70,174,400);
    LineLightGray line3 = new LineLightGray(70,237,400);
    LineLightGray line4 = new LineLightGray(70,300,400);
    LineLightGray line5 = new LineLightGray(70,363,400);
    LineLightGray line6 = new LineLightGray(70,426,400);
    LineLightGray line7 = new LineLightGray(70,489,400);

    JLabel labelDetailsEDIT = new JLabel("Edytuj status");
    JLabel labelStatusEDIT = new JLabel("Status zgłoszenia");
    String[] varStatus = {"NOWE", "PRZYJĘTE","W REALIZACJI", "ZREALIZOWANE"};
    JComboBox comboStatusEDIT = new JComboBox(varStatus);
    JLabel labelCommentEDIT = new JLabel("Edytuj opis");
    JTextArea tfCommentEDIT = new JTextArea();

    // Przycisk potwierdź
    public JButton btnConfirm = new JButton("Potwierdź");

    // Obsługa SQL
    String actualID;
    Connection connection = null;
    String zapytanie;
    Statement st = null;
    ResultSet rs = null;

    public DialogTaskDetails(Task task){
        setSize(1000,600);
        setTitle("Details");
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Style.white);
        setVisible(true);

        // przypisanie wartosci do elementow dynamicznych
        labelIDDynamic.setText(task.getID());
        labelStatusDynamic.setText(task.getStatus());
        labelDescriptionDynamic.setText(task.getDescription());
        labelLocalizationDynamic.setText(task.getLocalization());
        labelTypeDynamic.setText(task.getType());

        if (task.getEntranceDate() != null){
            labelEntranceDateDynamic.setText(task.getEntranceDate().toString());
        } else {
            labelEntranceDateDynamic.setText("null");
        }

        // dodanie elementów
        add(labelHeadline);
        add(labelID);
        add(labelStatus);
        add(labelDescription);
        add(labelLocalization);
        add(labelType);
        add(labelEntranceDate);
        add(labelRequiredDate);
        add(labelStarted);
        add(labelFinished);
        add(labelResponsibleCompany);
        add(labelSMS);
        add(labelMail);
        add(labelComment);

        add(labelIDDynamic);
        add(labelStatusDynamic);
        add(labelDescriptionDynamic);
        add(labelLocalizationDynamic);
        add(labelTypeDynamic);
        add(labelEntranceDateDynamic);

        add(line1);
        add(line2);
        add(line3);
        add(line4);
        add(line5);
        add(line6);
        add(line7);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Czynności po wciśnięciu przycisku
        if(e.getSource() == btnConfirm){
            // Wykonanie aktualizacji
            try {
                connection = DatabaseManager.getConnection();
                zapytanie = "UPDATE [O&M].[dbo].[Tasks_Object_1] SET [STATUS] = '" + String.valueOf(comboStatusEDIT.getSelectedItem()) + "', [COMMENT] = '" + tfCommentEDIT.getText() +  "' WHERE [ID] = " + actualID;
                st = connection.createStatement();
                rs = st.executeQuery(zapytanie);

            } catch (Exception g){
                System.out.println("Nieudana aktualizacja");
                System.out.println( g.getMessage());

            }
            
            // Odświeżenie tabeli
            PanelMain.panelLocation1.modelApplications.setRowCount(0);
            DatabaseManager.readObjectData();

            // Zamknięcie okna po wykonaniu czynności
            dispose();
        }
    }
}
