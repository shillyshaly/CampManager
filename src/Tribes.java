import java.sql.*;
import java.util.Scanner;

public class Tribes {
    //MySQL connection variables
    static PreparedStatement statement = null;
    static Connection connection;
    static ResultSet rs = null;

    //Get MySQL connection
    static {
        try {
            connection = MySQLConnect.mySQLConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //scanner for user input
    static Scanner scanner = new Scanner(System.in);

    //add to tribe
    public static void addToTribe() throws SQLException {
        //MySQL query to insert into tribe
        String query = "INSERT INTO tribe VALUES (?, ?, ?, ?, ?, ?);";

        //show numbers for each tribe
        displayCount();

        //get user input for name and tribe to add to
        System.out.println("Enter camper name to add (ex. billy thompson): ");
        String fname = scanner.next();
        String lname = scanner.next();
        System.out.println("Enter tribe to add camper to (a - d): ");
        char tribe = scanner.next().charAt(0);

        //test area
        ResultSet camper = Camper.getCamperDeats(fname, lname);
        String session = camper.getString("session_month");
        int age = camper.getInt("camper_age");



        System.out.println("Are there any special requests?: ");
        scanner.nextLine();
        String spReq = scanner.nextLine();

        //prep statment
        statement = connection.prepareStatement(query);
        statement.setString(1, String.valueOf(tribe));
        statement.setString(2, fname);
        statement.setString(3, lname);
        statement.setString(4, session);
        statement.setString(5, spReq);
        statement.setInt(6, age);

        //add, execute, commit to db
        statement.addBatch();
        statement.executeBatch();
        connection.commit();
    }

    //display resultSet
    public static void displayRS(ResultSet rs) throws SQLException {
        System.out.println("+-------------------+-------------------+--------------------+---------------------" +
                "+------------+-----------------------------------------------+");
        while (rs.next()){
            //save and parse query response
            String tr = rs.getString("tribe_name");
            String fname = rs.getString("camper_fname");
            String lname = rs.getString("camper_lname");
            String sess = rs.getString("session_month");
            String spReq = rs.getString("special_req");
            int age = rs.getInt("camper_age");

            //print the result set
            System.out.format("| Tribe: %-10s | First: %-10s | Last: %-12s | Session: %-10s | Age: %-5s | " +
                    "Special Req: %-32s |\n", tr, fname, lname, sess, age, spReq);
        }
        System.out.println("+-------------------+-------------------+--------------------+---------------------" +
                "+------------+-----------------------------------------------+\n\n");
    }

    //move tribe
    public static void moveTribes(char tribe, String fname, String lname) throws SQLException {

        String query = "UPDATE tribe SET tribe_name = \"tribe " + tribe + "\" where camper_fname = \"" + fname + "\" and " +
                "camper_lname = \"" + lname + "\";";

        statement = connection.prepareStatement(query);
        statement.execute();
        connection.commit();
    }

    //get count for tribe
    public static int getCount(String tribe) throws SQLException {
        statement = connection.prepareStatement(tribe);
        rs = statement.executeQuery(tribe);
        rs.next();
        return rs.getInt("total");
    }

    //tribe count
    public static void displayCount () throws SQLException {
        //getting the count of each tribe
        String tribeA = "select count(*) as total from tribe where tribe_name = \"tribe a\";";
        String tribeB = "select count(*) as total from tribe where tribe_name = \"tribe b\";";
        String tribeC = "select count(*) as total from tribe where tribe_name = \"tribe c\";";
        String tribeD = "select count(*) as total from tribe where tribe_name = \"tribe d\";";

        int aCount = getCount(tribeA);
        int bCount = getCount(tribeB);
        int cCount = getCount(tribeC);
        int dCount = getCount(tribeD);

        System.out.println("+----------------------------+\n" +
                "| Tribe counts as follows:\n" +
                "+----------------------------+\n" +
                "| Tribe A: " + aCount + "\n" +
                "| Tribe B: " + bCount + "\n" +
                "| Tribe C: " + cCount + "\n" +
                "| Tribe D: " + dCount + "\n" +
                "+----------------------------+\n\n"
                );
    }

    //display members of a tribe
    public static void displayTribeMembers(char tribe, String session) throws SQLException{
        String query = "SELECT * FROM tribe WHERE tribe_name = \"" + tribe + "\" and " +
                "session_month = \"" + session + "\";";

        statement = connection.prepareStatement(query);
        rs = statement.executeQuery(query);
        displayRS(rs);
    }
}
