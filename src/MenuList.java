<<<<<<< Updated upstream
=======
import java.sql.SQLException;
import java.text.ParseException;
>>>>>>> Stashed changes
import java.util.Scanner;

public class MenuList {
    public static int mainMenu(){
        System.out.println("******************************\n" +
                "* 1. Camper Menu\n" +
                "* 2. See campers\n" +
                "* 3. Delete by name\n" +
                "* 4. Camper details by name\n" +
                "* 5. Exit\n" +
<<<<<<< Updated upstream
                "******************************\n");
=======
                "******************************\n\n\n");
>>>>>>> Stashed changes
        int menuChoice = 0;

        Scanner scanner = new Scanner(System.in);
        menuChoice = scanner.nextInt();
        return menuChoice;
    }

<<<<<<< Updated upstream
    public static int camperMenu(){
        System.out.println("******************************\n" +
                "**********Camp Menu*********\n" +
                "* 1. Add a new camper\n" +
                "* 2. See campers\n" +
                "* 3. Delete by name\n" +
                "* 4. Camper details by name\n" +
                "* 5. Exit\n" +
                "******************************\n");
=======
    public static int camperMenu() throws SQLException, ParseException {
        System.out.println("******************************\n" + "*      Camper Menu           *\n" +
                "+****************************+\n" +
                "* 1. Add a new camper\n" +
                "* 2. See campers\n" +
                "* 3. Delete by name\n" +
                "* 4. Exit\n" +
                "******************************\n\n\n");
        int menuChoice = 0;

        Scanner scanner = new Scanner(System.in);
        menuChoice = scanner.nextInt();

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
                System.out.println("Leaving Camper Menu...");
                break;
        }

        return menuChoice;
    }

    public static int tribesMenu() throws SQLException {
        System.out.println("******************************\n" + "*      Tribes Menu           *\n" +
                "+****************************+\n" +
                "* 1. Add camper to tribe\n" +
                "* 2. Move camper to new tribe\n" +
                "* 3. Return tribe count\n" +
                "* 4. Display tribe members\n" +

                "* 5. Exit\n" +
                "******************************\n\n\n");
        int menuChoice = 0;
>>>>>>> Stashed changes

        int menuChoice = 0;
        Scanner scanner = new Scanner(System.in);
        menuChoice = scanner.nextInt();

        return menuChoice;
    }
}
