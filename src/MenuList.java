import java.util.Scanner;

public class MenuList {
    public static int mainMenu(){
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
        return menuChoice;
    }

    public static int camperMenu(){
        System.out.println("******************************\n" +
                "**********Camp Menu*********\n" +
                "* 1. Add a new Application\n" +
                "* 2. See Applicants\n" +
                "* 3. Delete Applicant\n" +
                "* 4. Camper details by name\n" +
                "* 5. Exit\n" +
                "******************************\n");

        int menuChoice = 0;
        Scanner scanner = new Scanner(System.in);
        menuChoice = scanner.nextInt();

        return menuChoice;
    }

    public static int bunkHouseMenu(){
        System.out.println("******************************\n" +
                "**********Bunkhouse Menu*********\n" +
                "* 1. Add a new Application\n" +
                "* 2. See Applicants\n" +
                "* 3. Delete Applicant\n" +
                "* 4. Camper details by name\n" +
                "* 5. Exit\n" +
                "******************************\n");

        int menuChoice = 0;
        Scanner scanner = new Scanner(System.in);
        menuChoice = scanner.nextInt();

        return menuChoice;
    }
}
