import java.util.Scanner;

public class MenuList {
    public static int mainMenu(){
        System.out.println("******************************\n" +
                "* 1. Add a new camper\n" +
                "* 2. See campers\n" +
                "* 3. Delete by name\n" +
                "* 4. Exit\n" +
                "******************************\n");
        int menuChoice = 0;

        Scanner scanner = new Scanner(System.in);
        menuChoice = scanner.nextInt();
        return menuChoice;
    }
}
