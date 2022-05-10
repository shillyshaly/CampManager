//import com.mysql.cj.util.StringUtils;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Camper {
    static PreparedStatement statement = null;
    static Connection connection;
    static ResultSet rs = null;

    static int FJuneCount = 0;
    static int MJuneCount = 0;
    static int FAugustCount = 0;
    static int MAugustCount = 0;
    static int FJulyCount = 0;
    static int MJulyCount = 0;


    static {
        try {
            connection = MySQLConnect.mySQLConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newCamper() throws SQLException, ParseException {

        String camper = "INSERT INTO camper (camper_fname, camper_lname, camper_age, camper_house, camper_docs)" +
                " VALUES (?, ?, ?, ?, ?)";
//        String inc = "select max(`camper_id`) as camper_id from `camper`;";

//        statement = connection.prepareStatement(inc);
//        rs = statement.executeQuery(inc);
//        rs.next();
//        int id = rs.getInt("camper_id") + 1;
//        System.out.println("id: " + id);

        Scanner scanner = new Scanner(System.in);
//        System.out.println("enter id: ");
//        int id = scanner.nextInt();
        System.out.println("enter first name: ");
        String fname = scanner.next();
        System.out.println("enter last name: ");
        String lname = scanner.next();
        System.out.println("enter bunk house(a-e): ");
        String house = scanner.next();

        System.out.println("enter date of birth (ex. yyyy-mm-dd): ");
        String birthday = scanner.next();
        LocalDate dob = LocalDate.parse(birthday);
        LocalDate today = LocalDate.now();
        Period period = Period.between(dob, today);

        if (period.getYears() < 9 || period.getYears() > 18)
        {
            System.out.println("Application Rejected. Either too young or too old to join camp");
            return;
        }

        int age = period.getYears();

        System.out.println("enter gender (ex. male/female): ");
        String gender = scanner.next();

        System.out.println("enter session month by typing the month the session takes place. (August, June, or July");
        String session = scanner.next();

        if (session.toLowerCase().equals("august"))
        {
            int totalCamp = MAugustCount + FAugustCount;
            if (totalCamp < 72)
            {
                System.out.println("Too many Campers in this session. Try filing out an application for another session.");
                return;
            }

            if(gender.toLowerCase().equals("male"))
            {
                if(MAugustCount < 32)
                {
                    return;
                }
                else
                {
                    MAugustCount++;
                }

            }
            if (gender.toLowerCase().equals("female"))
            {
                if(FAugustCount < 32)
                {
                    return;
                }
                else
                {
                    FAugustCount++;
                }
            }

        }

        if (session.toLowerCase().equals("july"))
        {
            int totalCamp = MJulyCount + FJulyCount;
            if (totalCamp < 72)
            {
                System.out.println("Too many Campers in this session. Try filing out an application for another session.");
                return;
            }

            if(gender.toLowerCase().equals("male"))
            {
                if(MJulyCount < 32)
                {
                    return;
                }
                else
                {
                    MJulyCount++;
                }

            }
            if (gender.toLowerCase().equals("female"))
            {
                if(FAugustCount < 32)
                {
                    return;
                }
                else
                {
                    FJulyCount++;
                }
            }

        }

        if (session.toLowerCase().equals("june"))
        {
            int totalCamp = MJuneCount + FJuneCount;
            if (totalCamp == 72)
            {
                System.out.println("Too many Campers in this session. Try filing out an application for another session.");
                return;
            }

            if(gender.toLowerCase().equals("male"))
            {
                if(MJuneCount < 32)
                {
                    return;
                }
                else
                {
                    MJuneCount++;
                }

            }
            if (gender.toLowerCase().equals("female"))
            {
                if(FJuneCount < 32)
                {
                    return;
                }
                else
                {
                    FJuneCount++;
                }
            }


        }




        System.out.println("enter if user paid check(y/n): ");
        String complete = scanner.next();

        if (complete.toLowerCase().equals("n"))
        {
            System.out.println("Application rejected, pay next time");
            return;
        }


        //sql insert statement for camper table (test)
        statement = connection.prepareStatement(camper);
        statement.setString(1, fname);
        statement.setString(2, lname);
        statement.setInt(3, age);
        statement.setString(4, house);
        statement.setString(4, birthday);
        statement.setString(5, complete);

//        System.out.println(statement);

        //add, execute, commit to db
        statement.addBatch();
        statement.executeBatch();
        connection.commit();
    }
    /*public static void applicationDate() throws ParseException, SQLException {

<<<<<<< Updated upstream
=======
        //enter application date
        System.out.print("Enter application date (int MM-dd format): ");
        Scanner inputDate = new Scanner(System.in);
        String appDate = inputDate.nextLine();

        //select month option
        System.out.print(" 1. June\n" +
                " 2. July\n" +
                " 3. August\n" +
                "Select month: ");
        Scanner date = new Scanner(System.in);
        int campDate = date.nextInt();
        getDateDiff(appDate, campDate);

        //enter if check cleared
        System.out.print("Did the check clear?: ");
        Scanner clearedCheck = new Scanner(System.in);
        char clrChk = clearedCheck.next().toLowerCase().charAt(0);
        checkPayment(clrChk);

        //check in
        checkIn();
    }

    public static void checkPayment(char ans) throws ParseException, SQLException {
        //did the payment go through
        if(ans == 'n'){
            sendReject();
        }else if(ans == 'y'){
            sendAccept();
        }else{
            System.out.println("something went wrong...");
        }
    }

    public static void checkIn(){
        System.out.print("Camper's forms verified? (y/n): ");
        Scanner scanner = new Scanner(System.in);
        String forms = scanner.next();

        if (forms.toLowerCase().equals("y")){
            System.out.println("Camper is good to go...\n");
        }else if (forms.toLowerCase().equals("n")){
            System.out.println("Parent must be notified of incomplete items.");
        }else{
            System.out.println("Something went wrong.");
        }

    }

    public static void getDateDiff(String date, int campDate) throws ParseException, SQLException {
//        Date today = Calendar.getInstance().getTime();
//        System.out.println("todays date: " + today);

        java.util.Date month = null;

        switch (campDate){
            case 1:
                month = new SimpleDateFormat("MM-dd").parse("06-15");
                break;
            case 2:
                month = new SimpleDateFormat("MM-dd").parse("07-15");
                break;
            case 3:
                month = new SimpleDateFormat("MM-dd").parse("08-15");
                break;
            default:
                System.out.println("incorrect option...");
                System.exit(0);
                break;
        }

        Date applicationDate = new SimpleDateFormat("MM-dd").parse(date);

        long dateDiff = month.getTime() - applicationDate.getTime();
        long days = dateDiff/(1000*60*60*24);

        if(!(days >= 60 && days <= 240)){
            sendReject();
        }
    }


    public static int checkAge(LocalDate bday) throws ParseException, SQLException {
        LocalDate today = LocalDate.now();
        Period period = Period.between(bday, today);

        if (period.getYears() < 18 && period.getYears() > 9) {
            applicationDate();
        }
        else{
            sendReject();
        }
        return period.getYears();
    }

    public static void sendAccept() {
        //send acceptance package
        System.out.println("Congrats on acceptance. Arrival packet sent.");
    }

    //send rejection docs
    public static void sendReject() throws ParseException, SQLException {
        //send rejection
        System.out.println("Does not qualify...\n" + "Rejection letter sent.");
        MenuList.camperMenu();
//        System.exit(0);
    }
*/
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
        rs = statement.executeQuery(query);

        while (rs.next()) {
            //save and parse the query response
            int id = rs.getInt("camper_id");
            String fname = rs.getString("camper_fname");
            String lname = rs.getString("camper_lname");
            int age = rs.getInt("camper_age");
            String house = rs.getString("camper_house");
            String complete = rs.getString("camper_docs");

            //print the results of query
            System.out.format("ID: %-3s First: %-10s Last: %-12s Age: %-3s BunkHouse: %-3s " +
                    "Completed docs: %-3s\n" +
                    "", id, fname, lname, age, house, complete);
        }
        statement.close();
        System.out.println("\n\n");
        System.out.println("There are" + MAugustCount + "males and " + FAugustCount + " females in August.\n There are "+ MJuneCount + " males and " + FJuneCount + " females in June.\n There are " + MJulyCount + " males and " + FJulyCount + "females in July.");

    }

    public static ResultSet getCamperDeats(String fname, String lname) throws SQLException {
        String query = "SELECT * FROM camper WHERE camper_fname = \"" + fname + "\" && " + "camper_lname = \"" +
                lname +"\";";

        statement = connection.prepareStatement(query);
        rs = statement.executeQuery(query);
        rs.next();

        return rs;
    }
}
