import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;
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

    static int maleCount = 0;
    static int femaleCount = 0;
    static int totalCount = 0;

    public static void newCamper(String fname, String lname, int age, String birthday, String complete, String session, String gender) throws SQLException, ParseException {
        //insert query for camper
        String camper = "INSERT INTO camper (camper_fname, camper_lname, camper_age, camper_dob, camper_docs, session_month, camper_gender)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";

        //get difference between local date and dob
        LocalDate dob = LocalDate.parse(birthday);
        LocalDate today = LocalDate.now();
        Period period = Period.between(dob, today);

        //check if age is appropriate
        if (period.getYears() < 9 || period.getYears() > 18) {
            System.out.println("Application Rejected. Either too young or too old to join camp");
            return;
        }

        //get counts of males and females from a
        //specific session
        maleCount = getMaleCount(session);
        femaleCount = getFemaleCount(session);
        totalCount = getTotalCount(session);

        //test male count
//        System.out.println("male count: " + maleCount);
//        System.out.println("female count: " + femaleCount);
//        System.out.println("total count: "+ totalCount);
        //end test count

        //check total count
        if (totalCount > 72){
            System.out.println("Too many Campers in this session. Try filing out an application for another session.");
            return;
        }

        //check male count
        if (gender.equals("male")) {
            if (maleCount >= 32){
                System.out.println("Too many males in this session. Try filing out an application" +
                        " for another session.");
                return;
            }
        }

        //check female count
        if (gender.equals("female")) {
            if (femaleCount >= 32){
                System.out.println("Too many females in this session. Try filing out an application" +
                        " for another session.");
                return;
            }
        }

        //check for payment
        checkPayment(complete);

//        if (session.toLowerCase().equals("august")) {
//            int totalCamp = MAugustCount + FAugustCount;
//            if (totalCamp < 72) {
//                System.out.println("Too many Campers in this session. Try filing out an application for another session.");
//                return;
//            }
//
//            if (gender.toLowerCase().equals("male")) {
//                if (MAugustCount < 32) {
//                    return;
//                } else {
//                    MAugustCount++;
//                }
//
//            }
//            if (gender.toLowerCase().equals("female")) {
//                if (FAugustCount < 32) {
//                    return;
//                } else {
//                    FAugustCount++;
//                }
//            }
//
//        }
//
//        if (session.toLowerCase().equals("july")) {
//            int totalCamp = MJulyCount + FJulyCount;
//            if (totalCamp < 72) {
//                System.out.println("Too many Campers in this session. Try filing out an application for another session.");
//                return;
//            }
//
//            if (gender.toLowerCase().equals("male")) {
//                if (MJulyCount < 32) {
//                    return;
//                } else {
//                    MJulyCount++;
//                }
//
//            }
//            if (gender.toLowerCase().equals("female")) {
//                if (FAugustCount < 32) {
//                    return;
//                } else {
//                    FJulyCount++;
//                }
//            }
//
//        }
//
//        if (session.toLowerCase().equals("june")) {
//            int totalCamp = MJuneCount + FJuneCount;
//            if (totalCamp >= 72) {
//                System.out.println("Too many Campers in this session. Try filing out an application for another session.");
//                return;
//            }
//
//            if (gender.toLowerCase().equals("male")) {
//                if (MJuneCount >= 32) {
//                    return;
//                } else {
//                    MJuneCount++;
//                    //test
//                    System.out.println("male june count: " + MJuneCount);
//                }
//
//            }
//            if (gender.toLowerCase().equals("female")) {
//                if (FJuneCount < 32) {
//                    return;
//                } else {
//                    FJuneCount++;
//                }
//            }
//
//
//        }


//        Scanner scanner = new Scanner(System.in);
//        System.out.println("enter first name: ");
//        String fname = scanner.next().toLowerCase();
//        System.out.println("enter last name: ");
//        String lname = scanner.next().toLowerCase();
//        System.out.println("enter age: ");
//        int age = scanner.nextInt();
//        System.out.println("enter date of birth (ex. dd/mm/yyyy): ");
//        String dob = scanner.next().toLowerCase();
//        System.out.println("enter completed docs(y/n): ");
//        String complete = scanner.next().toLowerCase();
//        System.out.println("enter session month (ex. august");
//        String session = scanner.next().toLowerCase();
//        System.out.println("enter gender (ex. male/female): ");
//        String gender = scanner.next().toLowerCase();

        //sql insert statement for camper table (test)
        statement = connection.prepareStatement(camper);
        statement.setString(1, fname);
        statement.setString(2, lname);
        statement.setInt(3, age);
        statement.setString(4, birthday);
        statement.setString(5, complete);
        statement.setString(6, session);
        statement.setString(7, gender);

//        System.out.println(statement);

        //add, execute, commit to db
        statement.addBatch();
        statement.executeBatch();
        connection.commit();
    }

    public static void delCamper(String fname, String lname) throws SQLException {
//        System.out.println("Enter first name of camper to be deleted: ");
//        Scanner scanner = new Scanner(System.in);
//        String fname = scanner.next();
//        System.out.println("Enter last name: ");
//        String lname = scanner.next();

        String query = "DELETE FROM camper WHERE camper_fname='" + fname + "' and " + "camper_lname='" + lname + "';";

        statement = connection.prepareStatement(query);
        statement.execute();
        connection.commit();
        System.out.println("Record of " + fname + " " + lname + " has been deleted.");

//        getCampers();
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
        String query = "SELECT * FROM camper WHERE camper_fname = \"" + fname + "\" && " + "camper_lname = \"" + lname + "\";";

        statement = connection.prepareStatement(query);
        rs = statement.executeQuery(query);

        return rs;
    }

    public static void displayRS(ResultSet rs) throws SQLException {
        System.out.println("+-----------+-------------------+--------------------+-----------+-----------------" + "-----+---------------------+---------------------+--------------------+");
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
            System.out.format("| ID: %-5s | First: %-10s | Last: %-12s | Age: %-4s | D.O.B.: %-12s | " + "Completed docs: %-3s | Session: %-10s | Gender: %-10s |\n" + "", id, fname, lname, age, dob, complete, session, gender);
        }
        System.out.println("+-----------+-------------------+--------------------+-----------+-----------------" + "-----+---------------------+---------------------+--------------------+");
    }

    public static int getMaleCount(String month) throws SQLException {
        String query = "SELECT COUNT(*) as camper FROM camper WHERE camper_gender = 'male' " +
                "and session_month = \"" + month + "\";";

        statement = connection.prepareStatement(query);
        rs = statement.executeQuery(query);

        if (rs.isBeforeFirst()){
            rs.next();
        }

        return rs.getInt("camper");
    }

    public static int getFemaleCount(String month) throws SQLException {
        String query = "SELECT COUNT(*) as camper FROM camper WHERE camper_gender = 'female' " +
                "and session_month = \"" + month + "\";";

        statement = connection.prepareStatement(query);
        rs = statement.executeQuery(query);

        if (rs.isBeforeFirst()){
            rs.next();
        }

        return rs.getInt("camper");
    }

    public static int getTotalCount(String month) throws SQLException{
        String query = "SELECT COUNT(*) as camper FROM camper WHERE session_month = \"" + month + "\";";

        statement = connection.prepareStatement(query);
        rs = statement.executeQuery(query);

        if (rs.isBeforeFirst()){
            rs.next();
        }

        return rs.getInt("camper");
    }

    //function to ask if payment has made and cleared
    public static void checkPayment(String ans) throws ParseException {
        //did the payment go through
        if(ans.equals("n")){
            System.out.println("Application can not be accepted until payment has been made.");
        }else if(ans.equals("y")){
            System.out.println("Congratulations on acceptance.");
        }else{
            System.out.println("something went wrong...");
        }
    }
}
