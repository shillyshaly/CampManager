import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class MenuList {
    public static void mainMenu() throws SQLException {
        System.out.println("******************************\n" +
                "* 1. Applications\n" +
                "* 2. Bunkhouse\n" +
                "* 3. Tribe\n" +
                "* 4. Arrival Day\n" +
                "* 5. Exit\n" +
                "******************************\n");
        int menuChoice = 0;

        Scanner scanner = new Scanner(System.in);
        menuChoice = scanner.nextInt();

        switch (menuChoice) {
            case 1:
                camperMenu();
                break;
            case 2:
                //bunkHouseMenu();
                System.out.println("Bunkhouse Menu");
                break;
            case 3:
                //tribesMenu();
                MenuList.tribesMenu();
                System.out.println("Tribes Menu");
                break;
            case 4:
                //checkInMenu();
                System.out.println("Check-in Menu");
                break;
            case 5:
                System.out.println("Exiting program...");
                System.exit(0);
            default:
                System.out.println("You've entered an invalid input. Please try again.");
        }
        mainMenu();
        System.out.println("\n\n");
    }

    public static void camperMenu() throws SQLException {
        System.out.println("******************************\n" +
                "**********Camp Menu*********\n" +
                "* 1. Add a new Application\n" +
                "* 2. See Applicants\n" +
                "* 3. Delete Applicant\n" +
                "* 4. Camper details by name\n" +
                "* 5. Return to Main Menu\n" +
                "******************************\n");

        int menuChoice = 0;
        Scanner scanner = new Scanner(System.in);
        menuChoice = scanner.nextInt();

        switch (menuChoice) {
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
                System.out.println("Which camper's information would you like to see? (ex \"billy jones\"): ");
                String fname = scanner.next();
                String lname = scanner.next();
                ResultSet rs = Camper.getCamperDeats(fname, lname);
                Tribes.displayRS(rs);
                break;
            case 5:
                mainMenu();
                break;
            default:
                System.out.println("You've entered an invalid input. Please try again.");
        }
        camperMenu();
        System.out.println("\n\n");
    }

    public static void bunkHouseMenu() throws SQLException {
        System.out.println("******************************\n" +
                "**********Bunkhouse Menu*********\n" +
                "* 1. Add a new Application\n" +
                "* 2. See Applicants\n" +
                "* 3. Delete Applicant\n" +
                "* 4. Camper details by name\n" +
                "* 5. Return to Main Menu\n" +
                "******************************\n");

        int menuChoice = 0;
        Scanner scanner = new Scanner(System.in);
        menuChoice = scanner.nextInt();

        switch (menuChoice) {
            case 1:
                camperMenu();
                break;
            case 2:
                //bunkHouseMenu();
                System.out.println("Bunkhouse Menu");
                break;
            case 3:
                //tribesMenu();
                System.out.println("Tribes Menu");
                break;
            case 4:
                //checkInMenu();
                System.out.println("Check-in Menu");
                break;
            case 5:
                MenuList.mainMenu();
            default:
                System.out.println("You've entered an invalid input. Please try again.");
        }
        bunkHouseMenu();
        System.out.println("\n\n");
    }

    public static void tribesMenu() throws SQLException {
        System.out.println("******************************\n" +
                "**********Tribes Menu*********\n" +
                "* 1. Add camper to tribe\n" +
                "* 2. Move camper to new tribe\n" +
                "* 3. Return tribe count\n" +
                "* 4. Display tribe members\n" +
                "* 5. Return to Main Menu\n" +
                "******************************\n");

        int menuChoice = 0;
        Scanner scanner = new Scanner(System.in);
        menuChoice = scanner.nextInt();

        String fname;
        String lname;
        char tribe;

        switch (menuChoice) {
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
                System.out.println("Enter tribe to display (a-d or z to view all): ");

                tribe = scanner.next().toLowerCase(Locale.ROOT).charAt(0);

                if (tribe == 'z') {
                    Tribes.displayAll();
                } else {
                    System.out.println("For which session (June, July, August): ");
                    String session = scanner.next();

                    Tribes.displayTribeMembers(tribe, session);
                }
                break;
            case 5:
                System.out.println("Leaving Tribes Menu...");
                mainMenu();
                break;
        }
        tribesMenu();
        System.out.println("\n\n");
    }

    public static void checkInMenu() throws SQLException {
        System.out.println("******************************\n" +
                "**********Check-in Day Menu*********\n" +
                "* 1. Add a new Application\n" +
                "* 2. See Applicants\n" +
                "* 3. Delete Applicant\n" +
                "* 4. Camper details by name\n" +
                "* 5. Return to Main Menu\n" +
                "******************************\n");

        int menuChoice = 0;
        Scanner scanner = new Scanner(System.in);
        menuChoice = scanner.nextInt();

        switch (menuChoice) {
            case 1:
                camperMenu();
                break;
            case 2:
                //bunkHouseMenu();
                System.out.println("Bunkhouse Menu");
                break;
            case 3:
                //tribesMenu();
                System.out.println("Tribes Menu");
                break;
            case 4:
                //checkInMenu();
                System.out.println("Check-in Menu");
                break;
            case 5:
                mainMenu();
            default:
                System.out.println("You've entered an invalid input. Please try again.");
        }
    }
}