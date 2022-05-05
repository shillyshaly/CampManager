import java.sql.SQLException;
import java.util.Scanner;

public class MenuList {
    //clear the screen
    public static void clear(){
        for (int i = 0; i < 50; i++){
            System.out.println("\n");
        }
    }

    public static int mainMenu(){
        clear();
        System.out.println("******************************\n" + "*        Main Menu           *\n" +
                "+****************************+\n" +
                "* 1. Camper Menu\n" +
                "* 2. Tribes Menu\n" +
                "* 3. Coming Soon\n" +
                "* 4. *test camper_deats function\n" +
                "* 5. Exit\n" +
                "******************************\n\n\n\n\n\n\n\n");
        int menuChoice = 0;

        Scanner scanner = new Scanner(System.in);
        menuChoice = scanner.nextInt();
        return menuChoice;
    }

    public static int camperMenu() throws SQLException {
        System.out.println("******************************\n" + "*      Camper Menu           *\n" +
                "+****************************+\n" +
                "* 1. Add a new camper\n" +
                "* 2. See campers\n" +
                "* 3. Delete by name\n" +
                "* 4. Exit\n" +
                "******************************\n\n\n\n\n\n\n\n");
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
                "******************************\n\n\n\n\n\n\n\n");
        int menuChoice = 0;

        Scanner scanner = new Scanner(System.in);
        menuChoice = scanner.nextInt();

        String fname;
        String lname;
        char tribe;

        switch (menuChoice){
            case 1:
                Tribes.addToTribe();
                break;
            case 2:
                System.out.println("Enter name of camper to move: ");
                fname = scanner.next();
                lname = scanner.next();
                System.out.println("Enter camper's new tribe: ");
                scanner.nextLine();
                tribe = scanner.next().charAt(0);
                Tribes.moveTribes(tribe, fname, lname);
                break;
            case 3:
                Tribes.displayCount();
                break;
            case 4:
                System.out.println("Enter tribe to display (a - d): ");
                tribe = scanner.next().charAt(0);
                System.out.println("For which session (June, July, August):");
                String session = scanner.next();
                Tribes.displayTribeMembers(tribe, session);
                break;
            case 5:
                System.out.println("Leaving Tribes Menu...");
                break;
        }

        return menuChoice;
    }
}