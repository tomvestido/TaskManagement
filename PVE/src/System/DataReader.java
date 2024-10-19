package System;

import Panels.PanelLocation1;
import Panels.PanelMain;
import Panels.PanelNavi;

import java.sql.*;
import java.util.HashMap;
import java.util.Objects;

public class DataReader {

    HashMap<String, String> logininfor = new HashMap<>();



    protected HashMap getLoginInfo() {
        return logininfor;
    }

    public DataReader() throws SQLException {
        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
    }

    // Pobranie bazy użytkowników z SQL
    public void readUsers() {
        Connection connection = null;
        String zapytanie;
        Statement st = null;
        ResultSet rs = null;

        try {
            connection = DatabaseManager.getConnection();
            zapytanie = "SELECT [ID],[Login],[Password],[FirstName],[LastName],[Company],[Rule] FROM [O&M].[dbo].[Users]";
            st = connection.createStatement();
            rs = st.executeQuery(zapytanie);

            while (rs.next()) {
                String usersLogin = rs.getString("Login");
                String usersPassword = rs.getString("Password");

                // Usuwanie spacji
                String usersLoginWithoutSpaces = usersLogin.replaceAll("\\s+$", "");
                String usersPasswordWithoutSpaces = usersPassword.replaceAll("\\s+$", "");

                logininfor.put(usersLoginWithoutSpaces, usersPasswordWithoutSpaces);
            }
            rs.close();
        } catch(SQLException ex) {

        }


    }

    // Pobieranie danych z lokalizacji 1
    public static void readDataLocation1() {
        Connection connection = null;
        String zapytanie = "SELECT [ID], [STATUS], [TIME], [TIMEMOD], [MODBY], [COMMENT] FROM [O&M].[dbo].[Tasks_Object_1]";
        Statement st2 = null;
        ResultSet rs2 = null;
        int counter = 0;
        int countRows = 0;

        try {
            connection = DatabaseManager.getConnection();
            st2 = connection.createStatement();
            rs2 = st2.executeQuery(zapytanie);

            System.out.println("Wykonuję zadanie");

            while (rs2.next()) {
                countRows++;

                String ID_0 = rs2.getString("ID");
                String STATUS_0 = rs2.getString("STATUS");
                String TIME_0 = rs2.getString("TIME");
                String TIMEGET_0 = rs2.getString("TIMEMOD");
                String MODBY = rs2.getString("MODBY");
                String COMMENT_0 = rs2.getString("COMMENT");

                // Usuwanie spacji
                String commentWithoutSpaces = COMMENT_0.replaceAll("\\s+$", "");

                PanelMain.panelLocation1.modelApplications.addRow(new Object[]{ID_0, STATUS_0, TIME_0, TIMEGET_0, MODBY, commentWithoutSpaces});


                if (!Objects.equals(STATUS_0, "ZREALIZOWANE")) {
                    counter++;
                }

            }

            PanelMain.panelServices.btnLocation_1.labelNewServices.setText(String.valueOf(counter));

        } catch (Exception g){
            System.out.println("Niepoprawny odczyt zadan z lokalizacji 1");
        }

    }

    // Pobieranie danych o aktualnym użytkowniku
    public static void readActualDataUser(String actualUserID) {
        Connection connection = null;
        String zapytanie;
        Statement st = null;
        ResultSet rs = null;

        try {
            connection = DatabaseManager.getConnection();
            zapytanie = "SELECT [FirstName],[LastName],[Company],[Rule] FROM [O&M].[dbo].[Users] WHERE [Login] = '" + actualUserID + "'";
            st = connection.createStatement();
            rs = st.executeQuery(zapytanie);

            while (rs.next()) {
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String company = rs.getString("Company");
                String rule = rs.getString("Rule");

                // Usuwanie spacji
                String firstNameWithoutSpaces = firstName.replaceAll("\\s+$", "");
                String lastNameWithoutSpaces = lastName.replaceAll("\\s+$", "");
                String companyWithoutSpaces = company.replaceAll("\\s+$", "");
                String ruleWithoutSpaces = rule.replaceAll("\\s+$", "");
                String loginWithoutSpaces = actualUserID.replaceAll("\\s+$", "");

                PanelNavi.labelLoginDynamic.setText(firstNameWithoutSpaces + " " + lastNameWithoutSpaces);
                PanelNavi.labelUserDynamic.setText(firstNameWithoutSpaces + " " + lastNameWithoutSpaces);
                PanelNavi.labelCompanyDynamic.setText(companyWithoutSpaces);

                switch (ruleWithoutSpaces) {
                    case "0" -> PanelNavi.labelRuleDynamic.setText("Developer");
                    case "1" -> PanelNavi.labelRuleDynamic.setText("Administrator");
                    case "2" -> PanelNavi.labelRuleDynamic.setText("Właściciel");
                    case "3" -> PanelNavi.labelRuleDynamic.setText("Serwisant");
                }

                switch (ruleWithoutSpaces) {
                    case "0","1","2" -> PanelLocation1.btnDelete.setEnabled(true);
                    case "3" -> PanelLocation1.btnDelete.setEnabled(false);
                }

            }

        } catch (Exception g){

        }

    }




    // Archiwa
    public static void sendDataToSQL() {
        Connection connection = null;
        String zapytanie;
        Statement st = null;
        ResultSet rs = null;

        try {
            connection = DatabaseManager.getConnection();
            zapytanie = "INSERT INTO [ArchiveMeasurements] ([TIME],[VARIABLE],[VALUE]) VALUES ('2024-03-08 22:55:00', 'var_INV1_Q', '244.5')";
            st = connection.createStatement();
            rs = st.executeQuery(zapytanie);

        } catch(SQLException ex) {
        }


    }

}





