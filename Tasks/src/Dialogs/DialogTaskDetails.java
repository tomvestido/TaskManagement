package Dialogs;
import Panels.PanelMain;
import System.*;
import UI.LabelLight;
import UI.LabelSemibold;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DialogTaskDetails extends JDialog implements ActionListener {

    LabelLight labelID = new LabelLight(70,129,"ID");
    LabelLight labelStatus = new LabelLight(70,192,"Status");
    LabelLight labelDescription = new LabelLight(70,255,"Description");
    LabelLight labelLocalization = new LabelLight(70,318,"Localization");
    LabelLight labelType = new LabelLight(70,381,"Type");
    LabelLight labelEntranceDate = new LabelLight(70,444,"Entrance date");

    LabelSemibold labelIDDynamic = new LabelSemibold(70,151, "Unknown");
    LabelSemibold labelStatusDynamic = new LabelSemibold(70,214, "Unknown");
    LabelSemibold labelDescriptionDynamic = new LabelSemibold(70,277,"Unknown");
    LabelSemibold labelLocalizationDynamic = new LabelSemibold(70,340,"Unknown");
    LabelSemibold labelTypeDynamic = new LabelSemibold(70,403,"Unknown");
    LabelSemibold labelEntranceDateDynamic = new LabelSemibold(70,466,"Unknown");



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
        add(labelID);
        add(labelStatus);
        add(labelDescription);
        add(labelLocalization);
        add(labelType);
        add(labelEntranceDate);

        add(labelIDDynamic);
        add(labelStatusDynamic);
        add(labelDescriptionDynamic);
        add(labelLocalizationDynamic);
        add(labelTypeDynamic);
        add(labelEntranceDateDynamic);
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
