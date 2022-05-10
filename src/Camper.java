//import com.mysql.cj.util.StringUtils;

import java.sql.*;
import java.util.Scanner;

public class Camper {
    static PreparedStatement statement = null;
    static Connection connection;
    static ResultSet rs = null;


    static {
        try {
            connection = MySQLConnect.mySQLConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newCamper() throws SQLException {

        String camper = "INSERT INTO camper (camper_fname, camper_lname, camper_age, camper_dob, camper_docs, session_month, camper_gender)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter first name: ");
        String fname = scanner.next();
        System.out.println("enter last name: ");
        String lname = scanner.next();
        System.out.println("enter age: ");
        int age = scanner.nextInt();
        System.out.println("enter date of birth (ex. dd/mm/yyyy): ");
        String dob = scanner.next();
        System.out.println("enter completed docs(y/n): ");
        String complete = scanner.next();
        System.out.println("enter session month (ex. august");
        String session = scanner.next();
        System.out.println("enter gender (ex. male/female): ");
        String gender = scanner.next();

        //sql insert statement for camper table (test)
        statement = connection.prepareStatement(camper);
        statement.setString(1, fname);
        statement.setString(2, lname);
        statement.setInt(3, age);
        statement.setString(4, dob);
        statement.setString(5, complete);
        statement.setString(6, session);
        statement.setString(7, gender);

//        System.out.println(statement);

        //add, execute, commit to db
        statement.addBatch();
        statement.executeBatch();
        connection.commit();
    }

    public static void delCamper() throws SQLException {
        System.out.println("Enter first name of camper to be deleted: ");
        Scanner scanner = new Scanner(System.in);
        String fname = scanner.next();
        System.out.println("Enter last name: ");
        String lname = scanner.next();

        String query = "DELETE FROM camper WHERE camper_fname='" + fname + "' and " +
                "camper_lname='" + lname + "';";

        statement = connection.prepareStatement(query);
        statement.execute();
        connection.commit();
        System.out.println("Record of " + fname + " " + lname + " has been deleted.");
        statement = connection.prepareStatement("alter table camper auto_increment=1;");
        statement.execute();
        connection.commit();

        getCampers();
    }

    public static void getCampers() throws SQLException {
        String query = "SELECT * FROM camper;";

        statement = connection.prepareStatement(query);
        rs = statement.executeQuery(query);

        displayRS(rs);

        statement.close();
        System.out.println("\n\n");
    }

    public static ResultSet getCamperDeats(String fname, String lname) throws SQLException {
        String query = "SELECT * FROM camper WHERE camper_fname = \"" + fname + "\" && " + "camper_lname = \"" +
                lname +"\";";

        statement = connection.prepareStatement(query);
        rs = statement.executeQuery(query);
        if (!rs.next()){
            rs = null;
        }

        return rs;
    }

    public static void displayRS(ResultSet rs) throws SQLException {
        System.out.println("+-----------+-------------------+--------------------+-----------+-----------------" +
                "-----+---------------------+---------------------+--------------------+");

        while (rs.next()) {
            //save and parse the query response
            int id = rs.getInt("camper_id");
            String fname = rs.getString("camper_fname");
            String lname = rs.getString("camper_lname");
            int age = rs.getInt("camper_age");
            String dob = rs.getString("camper_dob");
            String complete = rs.getString("camper_docs");
            String session = rs.getString("session_month");
            String gender = rs.getString("camper_gender");

            //print the results of query
            System.out.format("| ID: %-5s | First: %-10s | Last: %-12s | Age: %-4s | D.O.B.: %-12s | " +
                    "Completed docs: %-3s | Session: %-10s | Gender: %-10s |\n" +
                    "", id, fname, lname, age, dob, complete, session, gender);
        }
        System.out.println("+-----------+-------------------+--------------------+-----------+-----------------" +
                "-----+---------------------+---------------------+--------------------+");
    }
}
