import com.mysql.cj.util.StringUtils;

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

        String camper = "INSERT INTO camper (camper_fname, camper_lname, camper_age, camper_house, camper_docs) VALUES (?, ?, ?, ?, ?)";
        String inc = "select max(`camper_id`) as camper_id from `camper`;";

        //need to get row id somhow
        statement = connection.prepareStatement(inc);
        rs = statement.executeQuery(inc);
        rs.next();
//        int id = rs.getInt("camper_id") + 1;
//        System.out.println("id: " + id);

        Scanner scanner = new Scanner(System.in);
//        System.out.println("enter id: ");
//        int id = scanner.nextInt();
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
        statement.setString(1, fname);
        statement.setString(2, lname);
        statement.setInt(3, age);
        statement.setString(4, house);
        statement.setString(5, complete);

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

        String query = "DELETE FROM camper WHERE camper_fname='" + fname + "' and " +
                "camper_lname='" + lname + "';";

        statement = connection.prepareStatement(query);
        statement.execute();
        connection.commit();
        System.out.println("Record of " + fname + " " + lname + " has been deleted.");
        statement = connection.prepareStatement("alter table camper auto_increment=1;");
        statement.execute();
        connection.commit();
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
            String fname = rs.getString("camper_fname");
            String lname = rs.getString("camper_lname");
            int age = rs.getInt("camper_age");
            String house = rs.getString("camper_house");
            String complete = rs.getString("camper_docs");

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
