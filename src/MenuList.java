import java.util.Scanner;

public class MenuList {
    public static void mainMenu(){
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

        switch (menuChoice){
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
                System.out.println("Exiting program...");
                System.exit(0);
            default:
                System.out.println("You've entered an invalid input. Please try again.");
                mainMenu();
        }
    }

    public static void camperMenu(){
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

        switch (menuChoice){
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
                System.out.println("Exiting program...");
                System.exit(0);
            default:
                System.out.println("You've entered an invalid input. Please try again.");
                mainMenu();
        }

    }

    public static void bunkHouseMenu(){
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

        switch (menuChoice){
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
                System.out.println("Exiting program...");
                System.exit(0);
            default:
                System.out.println("You've entered an invalid input. Please try again.");
                mainMenu();
        }
    }
    public static void tribesMenu(){
        System.out.println("******************************\n" +
                "**********Tribes Menu*********\n" +
                "* 1. Add a new Application\n" +
                "* 2. See Applicants\n" +
                "* 3. Delete Applicant\n" +
                "* 4. Camper details by name\n" +
                "* 5. Return to Main Menu\n" +
                "******************************\n");

        int menuChoice = 0;
        Scanner scanner = new Scanner(System.in);
        menuChoice = scanner.nextInt();

        switch (menuChoice){
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
                System.out.println("Exiting program...");
                System.exit(0);
            default:
                System.out.println("You've entered an invalid input. Please try again.");
                mainMenu();
        }

    }
    public static void checkInMenu(){
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

        switch (menuChoice){
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
                System.out.println("Exiting program...");
                System.exit(0);
            default:
                System.out.println("You've entered an invalid input. Please try again.");
                mainMenu();
        }
    }
}
