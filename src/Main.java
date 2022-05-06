import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

/**
 * In terminal run following command:
 *
 *      docker run -d -p 3306:3306 --name Camp_Manager shillyshaly/test:campManager
 */

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        int exitCode = 0;
        //MySQL connection
        MySQLConnect.mySQLConn();

        MenuList.mainMenu();

        /*while (exitCode == 0) {
            //get the main menu
            int menuChoice = MenuList.mainMenu();

            switch (menuChoice){
                case 1:
                    MenuList.camperMenu();
//                    Camper.newCamper();
                    break;
                case 2:
                    Camper.getCampers();
                    break;
                case 3:
                    Camper.delCamper();
                    break;
                case 4:
                    System.out.print("Enter camper name(first last): ");
                    Scanner scanner = new Scanner(System.in);

                    String fname = scanner.next();
                    String lname = scanner.next();
                    ResultSet rs = Camper.getCamperDeats(fname, lname);
                    int id = rs.getInt("camper_id");
                    System.out.println("CAMPER ID:: " + id);
                    break;
                case 5:
                    System.out.println("Exiting Camp Manager...");
                    System.out.println("Good Bye");
                    exitCode = 1;
                    break;
                default:
            }
        }*/
    }
}
