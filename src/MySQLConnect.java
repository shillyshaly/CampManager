import java.sql.*;

public class MySQLConnect {
    static Connection connection = null;
    static String url = null;
    static PreparedStatement statement = null;

    public static Connection mySQLConn() throws SQLException {
        url = "jdbc:mysql://0.0.0.0/CampDB?user=root&password=team4";

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
