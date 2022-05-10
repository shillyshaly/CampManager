import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class CamperTest {

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


    @org.junit.jupiter.api.Test
    void newCamper() throws SQLException, ParseException {
        Camper.newCamper("testish", "mcnasty", 99, "2007-02-15", "y", "august", "female");
        String query = "select count(1) as test from camper where camper_fname = 'testish' && camper_lname = 'mcnasty';";

        statement = connection.prepareStatement(query);
        statement.execute();
        connection.commit();
        rs = statement.executeQuery(query);

        if (rs.isBeforeFirst()){
            rs.next();
        }

        int i = rs.getInt("test");

        System.out.println("expected: 1");
        System.out.println("recv'd: " + i);

        assertEquals(1, i);
    }


    @org.junit.jupiter.api.Test
    void getCamperDeats() throws SQLException {
        rs = Camper.getCamperDeats("testish", "mcnasty");
        if (rs.isBeforeFirst()){
            rs.next();
        }
        String fname = rs.getString("camper_fname");
        String lname = rs.getString("camper_lname");

        System.out.println("expected: testish mcnasty");
        System.out.println("recv'd: " + fname + " " + lname);
        assertEquals("testish mcnasty", fname + " " + lname);
    }


//    @org.junit.jupiter.api.Test
//    void getMaleCount() throws SQLException{
//
//    }


    @org.junit.jupiter.api.Test
    void delCamper() throws SQLException {
        Camper.delCamper("testish", "mcnasty");
        String query = "select count(1) as test from camper where camper_fname = 'testish' && camper_lname" +
                " = 'mcnasty';";
        statement = connection.prepareStatement(query);
        rs = statement.executeQuery(query);

        if (rs.isBeforeFirst()){
            rs.next();
        }

        int i = rs.getInt("test");
        assertEquals(0, i);
    }
}