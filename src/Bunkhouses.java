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

    //ArrayList<String> BUNK1 = new ArrayList<String>();
    //ArrayList<String> BUNK2 = new ArrayList<String>();
    //ArrayList<String> BUNK3 = new ArrayList<String>();
    //ArrayList<String> BUNK4 = new ArrayList<String>();
    //ArrayList<String> BUNK5 = new ArrayList<String>();
    //ArrayList<String> BUNK6 = new ArrayList<String>();

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

    public static void displayBunkhouses() throws SQLException
    {

    }

    public static void deleteFromBunk() throws SQLException
    {
        Scanner scanner = new Scanner(System.in)

        String query = "DELETE FROM bunkhouse WHERE bunk_first='" + first + "' and " + "bunk_last='" + last + "';";

        String first, last;

        System.out.println("Enter in the first name of the camper you want to delete: ");
        first = scanner.next();
        System.out.println("For verification, enter the last name of " + first + ": ");
        last = scanner.next();


        statement = connection.prepareStatement(query);
        statement.execute();
        connection.commit();

        System.out.println(first + last + " has been deleted from the bunkhouse.")
        statement = connection.prepareStatement("alter table bunkhouse auto_increment=1;");
        statement.execute();
        connection.commit();

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


