import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TribesTest {
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

    @Test
    void addToTribe() {

    }

    @Test
    void moveTribes() {
    }

    @Test
    void getCount() {
    }
}