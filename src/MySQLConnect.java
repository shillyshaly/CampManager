import java.sql.*;

public class MySQLConnect {
    static Connection connection = null;
    static String url = null;
    static PreparedStatement statement = null;

    public static Connection mySQLConn() throws SQLException {
        url = "jdbc:mysql://0.0.0.0:3306/Camp_Manager?user=root&password=team4";

        connection = DriverManager.getConnection(url);
        connection.setAutoCommit(false);

        //get rid of warnings


        connection.setAutoCommit(false);
        return connection;
    }
}
