import java.sql.*;

public class MySQLConnect {
    static Connection connection = null;
    static String url = null;
    static PreparedStatement statement = null;

    public static Connection mySQLConn() throws SQLException {
        url = "jdbc:mysql://ec2-44-203-193-250.compute-1.amazonaws.com:3306/Camp_Manager?user=root&password=comp350";

        connection = DriverManager.getConnection(url);
        connection.setAutoCommit(false);

        //get rid of warnings
        statement = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0;");
        statement.addBatch();
        statement.executeBatch();
        connection.commit();
        statement = connection.prepareStatement("SET sql_safe_updates = 0;");
        statement.addBatch();
        statement.executeBatch();
        connection.commit();

        connection.setAutoCommit(false);
        return connection;
    }
}
