import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;

/**
 * In terminal run following command:
 *
 *      docker run -d -p 3306:3306 --name Camp_Manager shillyshaly/test:campManager
 */

public class Main {

    public static void main(String[] args) throws SQLException, IOException, ParseException {
        int exitCode = 0;
        //MySQL connection
        MySQLConnect.mySQLConn();

        MenuList.mainMenu();
    }
}