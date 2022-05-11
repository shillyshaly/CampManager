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

        String camper = "INSERT INTO camper (camper_fname, camper_lname, camper_age, camper_house, camper_docs)" +
                " VALUES (?, ?, ?, ?, ?)";
//        String inc = "select max(`camper_id`) as camper_id from `camper`;";

//        statement = connection.prepareStatement(inc);
//        rs = statement.executeQuery(inc);
//        rs.next();
//        int id = rs.getInt("camper_id") + 1;
//        System.out.println("id: " + id);



        LocalDate dob = LocalDate.parse(birthday);
        LocalDate today = LocalDate.now();
        Period period = Period.between(dob, today);

        if (period.getYears() < 9 || period.getYears() > 18)
        {
            System.out.println("Application Rejected. Either too young or too old to join camp");
            return;
        }

        age = period.getYears();



        //get counts of males and females from a
        //specific session
        maleCount = getMaleCount(session);
        femaleCount = getFemaleCount(session);
        totalCount = getTotalCount(session);


        switch (session)
        {
            case "june":

                Period gap = Period.between(today, LocalDate.parse("2022-06-01"));
                if (gap.getMonths() < 2 || gap.getMonths() > 8)
                {
                    System.out.println("Application filled out too late or too early");
                    return;
                }
                break;
            case "july":

                Period gap2 = Period.between(today, LocalDate.parse("2022-07-01"));
                if (gap2.getMonths() < 2 || gap2.getMonths() > 8)
                {
                    System.out.println("Application filled out too late or too early");
                    return;
                }
                break;
            case "august":

                Period gap3 = Period.between(today, LocalDate.parse("2022-08-01"));
                if (gap3.getMonths() < 2 || gap3.getMonths() > 8)
                {
                    System.out.println("Application filled out too late or too early");
                    return;
                }
                break;
            default:
                System.out.println("Something went wrong");
                break;

        }

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


