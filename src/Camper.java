import com.mysql.cj.util.StringUtils;

import java.sql.*;
import java.util.Scanner;

public class Camper {
    static PreparedStatement statement = null;
    static Connection connection;

    static {
        try {
            connection = MySQLConnect.mySQLConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newCamper() throws SQLException {


        String camper = "INSERT INTO camper VALUES (?, ?, ?, ?, ?, ?)";

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter id: ");
        String id = scanner.next();
        System.out.println("enter first name: ");
        String fname = scanner.next();
        System.out.println("enter last name: ");
        String lname = scanner.next();
        System.out.println("enter age: ");
        int age = scanner.nextInt();
        System.out.println("enter bunk house(a-e): ");
        String house = scanner.next();
        System.out.println("enter completed docs(y/n): ");
        String complete = scanner.next();

        //sql insert statement for camper table (test)
        statement = connection.prepareStatement(camper);
        statement.setString(1, id);
        statement.setString(2, fname);
        statement.setString(3, lname);
        statement.setInt(4, age);
        statement.setString(5, house);
        statement.setString(6, complete);

        System.out.println(statement);

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

        String query = "DELETE FROM camper WHERE camper_first_name='" + fname + "' and " +
                "camper_last_name='" + lname + "';";

        statement = connection.prepareStatement(query);
        statement.execute();
        connection.commit();
        System.out.println("Record of " + fname + " " + lname + " has been deleted.");
    }

    public static void getCampers() throws SQLException {
        String query = "SELECT * FROM camper;";

        statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery(query);

        int longLength = 18;
        int shortLength = 4;

        System.out.format(StringUtils.padString("Id", shortLength) + "|  " +
                StringUtils.padString("First Name", longLength) + "|  " +
                StringUtils.padString("Last Name", longLength) + "|  " +
                StringUtils.padString("Age", longLength) + "|  " +
                StringUtils.padString("Bunk House", longLength) + "|  " +
                StringUtils.padString("Docs Complete", longLength) + "|\n"
        );

        while (rs.next())
        {
            int id = rs.getInt("camper_id");
            String fname = rs.getString("camper_first_name");
            String lname = rs.getString("camper_last_name");
            int age = rs.getInt("camper_age");
            String house = rs.getString("bunk_house");
            String complete = rs.getString("completed_docs");

            // print the results
            System.out.format(StringUtils.padString(String.valueOf(id), shortLength) + "|  " +
                    StringUtils.padString(fname, longLength) + "|  " +
                    StringUtils.padString(lname, longLength) + "|  " +
                    StringUtils.padString(String.valueOf(age), longLength) + "|  " +
                    StringUtils.padString(house, longLength) + "|  " +
                    StringUtils.padString(complete, longLength) + "|\n"
            );
        }
        statement.close();
    }
}
