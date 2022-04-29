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

        while (exitCode == 0) {
            //get the main menu
            int menuChoice = MenuList.mainMenu();

            switch (menuChoice){
                //camper menu
                case 1:
                    int exitCamper = 0;
                    while (exitCamper != 4){
                        MenuList.camperMenu();
                        if ((exitCamper = MenuList.camperMenu()) == 4){break;}
                    }
                    break;
                //tribe menu
                case 2:
                    int exitTribe = 0;
                    while (exitTribe != 5){
                        MenuList.tribesMenu();
                        if ((exitTribe = MenuList.tribesMenu()) == 5){break;}
                    }
                    break;
                //coming soon
                case 3:
                    System.out.println("coming soon");
                    break;
                //testing getCamperDeats func
                case 4:
                    System.out.print("Enter camper name(first last): ");
                    Scanner scanner = new Scanner(System.in);

                    String fname = scanner.next();
                    String lname = scanner.next();
                    ResultSet rs = Camper.getCamperDeats(fname, lname);
                    int id = rs.getInt("camper_id");
                    System.out.println("CAMPER ID:: " + id);
                    break;
                //EXIT
                case 5:
                    System.out.println("Exiting Camp Manager...");
                    System.out.println("Good Bye");
                    exitCode = 1;
                    break;
                default:
            }
        }
    }
}
