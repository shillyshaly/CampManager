import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CamperTest {

    @Test
    void newCamper() {
    }

    @org.junit.jupiter.api.Test
    void delCamper() {
    }

    @org.junit.jupiter.api.Test
    void getCampers() {
    }

    @org.junit.jupiter.api.Test
    void getCamperDeats() throws SQLException {
        ResultSet rs = Camper.getCamperDeats("jamie", "hernandez");
        String fname = rs.getString("camper_fname");
        assertEquals("jamie", fname);
    }
}