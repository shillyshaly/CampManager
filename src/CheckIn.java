import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

public class CheckIn {
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

    public static void refund(String appDate, String fname, String lname){
        LocalDate applyDate = LocalDate.parse(appDate);
        LocalDate today = LocalDate.now();
        Period period = Period.between(applyDate, today);
    }



}