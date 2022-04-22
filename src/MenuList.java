import java.util.Scanner;

public class MenuList {
    public static int mainMenu(){
        System.out.println("******************************\n" +
                "* 1. Camper Menu\n" +
                "* 2. See campers\n" +
                "* 3. Delete by name\n" +
                "* 4. Camper details by name\n" +
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
                "* 1. Add a new camper\n" +
                "* 2. See campers\n" +
                "* 3. Delete by name\n" +
                "* 4. Camper details by name\n" +
                "* 5. Exit\n" +
                "******************************\n");

        int menuChoice = 0;
        Scanner scanner = new Scanner(System.in);
        menuChoice = scanner.nextInt();

        return menuChoice;
    }
}
