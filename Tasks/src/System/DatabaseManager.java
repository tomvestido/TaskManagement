package System;
import Panels.PanelMain;

import javax.sql.rowset.serial.SerialArray;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class DatabaseManager {

    final static String dbURL = "jdbc:sqlserver://DESKTOP-8G0Q1BN;Database=O&M;integratedSecurity=false;encrypt=true;trustServerCertificate=true";
    final static String dbUser = "tom";
    final static String dbPassword = "tomakie124";

    Connection connection = null;
    HashMap<String, String> usersData = new HashMap<>();
    public static List<Task> listTasks = new ArrayList<Task>();

    protected HashMap getUsersData() {
        return usersData;
    }
    public List getListTasksObject1(){
        return listTasks;
    }
    public static Connection getConnection() {
        Connection connection;

        try {
            String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(driverName);
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

            try {
                connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Połączenie nieudane", "Komunikat", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(e);
            }

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Nie odnaleziono klasy", "Komunikat", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Operacja nieudana", "Komunikat", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
        return connection;
    }
    public void readUsers() {
        String query = "SELECT [ID],[Login],[Password],[FirstName],[LastName],[Company],[Rule] FROM [O&M].[dbo].[Users]";
        Statement st = null;
        ResultSet rs = null;
        String userLogin;
        String userPassword;

        try {
            connection = DatabaseManager.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                userLogin = rs.getString("Login").replaceAll("\\s+$", "");
                userPassword = rs.getString("Password").replaceAll("\\s+$", "");

                usersData.put(userLogin, userPassword);
            }
            connection.close();

        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Odczyt użytkowników nieudany", "Komunikat", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(ex);

        }
    }
    public static void readObjectData(){
        String query = "SELECT [ID],[STATUS],[DESCRIPTION],[LOCALIZATION],[ENTRANCE_DATE],[STARTED],[FINISHED],[REQUIRED_FINISH_DATE],[RESPONSIBLE_GROUP],[SMS],[MAIL],[TYPE],[COMMENT] FROM [O&M].[dbo].[Tasks_Object_1]";
        Connection connection1 = null;
        Statement st = null;
        ResultSet rs = null;
        int counter = 0;

        try {
            connection1 = DatabaseManager.getConnection();
            st = connection1.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {

                Task task = new Task();
                task.ID = rs.getString("ID");
                task.status = rs.getString("STATUS");
                task.description = rs.getString("DESCRIPTION");
                task.localization = rs.getString("LOCALIZATION");
                task.entranceDate = rs.getDate("ENTRANCE_DATE");
                task.startedDate = rs.getDate("STARTED");
                task.finishedDate = rs.getDate("FINISHED");
                task.requiredDate = rs.getDate("REQUIRED_FINISH_DATE");
                task.responsibleCompany = rs.getString("RESPONSIBLE_GROUP");
                task.smsActive= rs.getBoolean("SMS");
                task.mailActive = rs.getBoolean("MAIL");
                task.type = rs.getString("TYPE");
                task.comment = rs.getString("COMMENT");

                listTasks.add(task);

                PanelMain.panelLocation1.modelApplications.addRow(new Object[]{task.ID, task.status, task.description, task.localization, task.entranceDate, task.startedDate, task.finishedDate, task.requiredDate, task.responsibleCompany, task.smsActive, task.mailActive, task.type, task.comment});


                if (!Objects.equals(task.status, "ZREALIZOWANE")) {
                    counter++;
                }

            }

            PanelMain.panelServices.btnLocation_1.labelNewServices.setText(String.valueOf(counter));

        } catch (Exception g){
            System.out.println("Niepoprawny odczyt zadan z lokalizacji 1");
        }


    }


}
