import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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




}
