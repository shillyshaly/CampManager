import java.sql.*;
import java.util.Scanner;

public class CheckIn
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

    static int ridingHelmet = 50;
    static int boots = 60;
    static int sleepingBag = 75;
    static int waterBottle = 30;
    static int sunscreen = 12;
    static int bugSpray = 9;
    static int total;

    public static void equipment(){
        boolean screenMenu = true;

        while (screenMenu) {

            System.out.println("******************************\n" +
                    "**********Equipment Menu*********\n" +
                    "Please enter missing equipment, if any, then select done.\n" +
                    "* 1. Riding Helmet\n" +
                    "* 2. Boots\n" +
                    "* 3. Sleeping Bag\n" +
                    "* 4. Water Bottle\n" +
                    "* 5. Sunscreen\n" +
                    "* 6. Bug Spray\n" +
                    "* 7. Done\n" +
                    "******************************\n");

            int menuChoice = 0;
            Scanner scanner = new Scanner(System.in);
            menuChoice = scanner.nextInt();

            switch (menuChoice) {
                case 1:
                    total+= ridingHelmet;
                    System.out.println("Riding Helmet added to bill...");
                    break;
                case 2:
                    total+= boots;
                    System.out.println("Boots added to bill...");
                    break;
                case 3:
                    total+= sleepingBag;
                    System.out.println("Sleeping Bag added to bill...");
                    break;
                case 4:
                    total+= waterBottle;
                    System.out.println("Water Bottle added to bill...");
                    break;
                case 5:
                    total+= sunscreen;
                    System.out.println("Sunscreen added to bill...");
                    break;
                case 6:
                    total+= bugSpray;
                    System.out.println("Bug Spray added to bill...");
                    break;
                case 7:
                    if (total == 0){
                        System.out.println("No missing equipment! Registration complete.");
                        screenMenu = false;
                        break;
                    }
                    else {
                        System.out.println("Please send camper to store with printed bill total of $" + total + ".00.");
                        screenMenu = false;
                        break;
                    }
                default:
                    System.out.println("You've entered an invalid input. Please try again.");
                    break;
            }
        }
    }
    public static void documentation(){
        boolean screenMenu = true;

        while (screenMenu) {

            System.out.println("******************************\n" +
                    "***** Documentation Menu *****\n" +
                    "Please enter missing Documents, if any, then select done.\n" +
                    "* 1. Legal\n" +
                    "* 2. Medical\n" +
                    "* 3. Emergency Contacts\n" +
                    "* 4. Done\n" +
                    "******************************\n");

            int menuChoice = 0;
            Scanner scanner = new Scanner(System.in);
            menuChoice = scanner.nextInt();

            switch (menuChoice) {
                case 1:
                    System.out.println("Printing out Legal documentation for completion...");
                    break;
                case 2:
                    System.out.println("Printing out Medical documentation for completion...");
                    break;
                case 3:
                    System.out.println("Printing out Emergency Contact documentation for completion...");
                    break;
                case 4:
                    System.out.println("All Documentation is complete!");
                    screenMenu = false;
                    break;
                default:
                    System.out.println("You've entered an invalid input. Please try again.");
                    break;
            }
        }
    }

    public static void verify() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter last name: ");
        String lname = scanner.next();
        System.out.println("Please enter session: ");
        String session = scanner.next();

        String query = "SELECT * FROM camper WHERE session_month='" + session + "'AND " + "camper_lname='"+ lname +"';";

        statement = connection.prepareStatement(query);
        rs = statement.executeQuery(query);

        Camper.displayRS(rs);
        statement.close();

        System.out.println("Is camper in correct session? Y/N ");
        char answer = scanner.next().charAt(0);
        if (answer == 'Y'||answer == 'y'){
            System.out.println("Please continue with Registration");
        }
        else {
            System.out.println("Please check camper details otherwise notify camp administrator!");
        }

        System.out.println("\n\n");

    }
}


