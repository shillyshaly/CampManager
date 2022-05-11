//package src;

import java.sql.*;
import java.util.Scanner;

public class Bunkhouses
{

    static PreparedStatement statement = null;
    static Connection connection;
    static ResultSet rs = null;

    static
    {
        try
        {
            connection = MySQLConnect.mySQLConn();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    static Scanner scanner = new Scanner(System.in);

    public static void addToBunk() throws SQLException
    {
        String query = "INSERT INTO bunkhouse VALUES (?, ?, ?, ?);";

        String first, last, gender;
        int bunkhouse;

        System.out.println("Please enter in the camper you want to add (first, last): ");
        first = scanner.next();
        last = scanner.next();

        System.out.println("Enter in " + first + " " + last + "'s last name:");
        gender = scanner.next();

        System.out.println("Enter in which bunkhouse you want to put the camper in (Girls: 1-3; Boys: 4-6): ");
        bunkhouse = scanner.nextInt();


        statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(bunkhouse));
        statement.setString(2, first);
        statement.setString(3, last);
        statement.setString(4, gender);

        statement.addBatch();
        statement.executeBatch();
        connection.commit();

    }

    public static void displayTable(ResultSet rs) throws SQLException
    {
        while (rs.next())
        {
            int bunk_id = rs.getInt("bunk_id");
            String first = rs.getString("first");
            String last = rs.getString("last");
            String gender = rs.getString("gender");

            System.out.format("Bunkhouse: %-10s     First: %-10s     Last: %-12s      Gender: %-10s\n", bunk_id, first, last, gender);
        }
    }

    public static void displayBunkhouses(int bunk_id, String first, String last, String gender) throws SQLException
    {
        String query = "SELECT * FROM bunkhouse WHERE bunk_id = \"" + bunk_id + "\" and " + "first = \"" + first + "last = \"" + last + "gender = \"" + gender + "\";";

        statement = connection.prepareStatement(query);
        rs = statement.executeQuery(query);
        displayTable(rs);
    }

    public static void deleteFromBunk() throws SQLException
    {
        Scanner scanner = new Scanner(System.in);

        String first = null;
        String last = null;

        String query = "DELETE FROM bunkhouse WHERE bunk_first='" + first + "' and " + "bunk_last='" + last + "';";



        System.out.println("Enter in the first name of the camper you want to delete: ");
        first = scanner.next();
        System.out.println("For verification, enter the last name of " + first + ": ");
        last = scanner.next();


        statement = connection.prepareStatement(query);
        statement.execute();
        connection.commit();

        System.out.println(first + last + " has been deleted from the bunkhouse.");
        statement = connection.prepareStatement("alter table bunkhouse auto_increment=1;");
        statement.execute();
        connection.commit();

    }

    public static void numberOfCampers() throws SQLException
    {
        int bunk1 = Integer.parseInt("select count(*) as total from bunkhouse where bunk_id = \"1\";");
        int bunk2 = Integer.parseInt("select count(*) as total from bunkhouse where bunk_id = \"2\";");
        int bunk3 = Integer.parseInt("select count(*) as total from bunkhouse where bunk_id = \"3\";");
        int bunk4 = Integer.parseInt("select count(*) as total from bunkhouse where bunk_id = \"4\";");
        int bunk5 = Integer.parseInt("select count(*) as total from bunkhouse where bunk_id = \"5\";");
        int bunk6 = Integer.parseInt("select count(*) as total from bunkhouse where bunk_id = \"6\";");

        int one = counter(String.valueOf(bunk1));
        int two = counter(String.valueOf(bunk2));
        int three = counter(String.valueOf(bunk3));
        int four = counter(String.valueOf(bunk4));
        int five = counter(String.valueOf(bunk5));
        int six = counter(String.valueOf(bunk6));

        System.out.print("Bunkhouse 1: " + one + "\n");
        System.out.print("Bunkhouse 2: " + two + "\n");
        System.out.print("Bunkhouse 3: " + three + "\n");
        System.out.print("Bunkhouse 4: " + four + "\n");
        System.out.print("Bunkhouse 5: " + five + "\n");
        System.out.print("Bunkhouse 6: " + six + "\n");
    }

    public static int counter(String bunkhouse) throws SQLException
    {
        statement = connection.prepareStatement(bunkhouse);
        rs = statement.executeQuery(bunkhouse);
        rs.next();
        return rs.getInt("Counter");
    }
}

// ******MOVE TO MENU LIST CLASS******
//public void bunkMenu() throws SQLException
//{
//        String first, last;
//
//        System.out.print("BUNKHOUSE ASSIGNMENTS:\n");
//        System.out.print("1: ADD CAMPER TO BUNKHOUSE\n");
//        System.out.print("2: DISPLAY BUNKHOUSES\n");
//        System.out.print("3: DELETE FROM BUNKHOUSE\n");
//        System.out.print("4: DISPLAY NUMBER OF CAMPERS IN BUNKHOUSE\n");
//
//        int userInput = userInput.nextInt();
//        Scanner scanner = new Scanner(System.in);
//
//        switch (userInput)
//       {
//            case 1:
//              Bunkhouses.addToBunk();
//              break;
//            case 2:
//              Bunkhouses.displayBunkhouses();
//              break;
//            case 3:
//              Bunkhouses.deleteFromBunk();
//              break;
//        }
//}