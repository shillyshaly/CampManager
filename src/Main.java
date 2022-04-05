import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        int exitCode = 0;
        MySQLConnect.mySQLConn();

        while (exitCode == 0) {

            int menuChoice = MenuList.mainMenu();

            switch (menuChoice){
                case 1:
                    Camper.newCamper();
                    break;
                case 2:
                    Camper.getCampers();
                    break;
                case 3:
                    Camper.delCamper();
                    break;
                case 4:
                    System.out.println("Exiting Camp Manager...");
                    System.out.println("Good Bye");
                    exitCode = 1;
                    break;
                default:
            }
        }
    }
}
