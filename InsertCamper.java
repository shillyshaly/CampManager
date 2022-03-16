public class InsertCamper {

    static Connection connection = null;
    static PreparedStatement statement = null;

    static String camper = "INSERT INTO camper VALUES (?, ?, ?, ?, ?, ?)";

    public static newCamper(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter id: ");
        String id = scanner.next();
        System.out.println("enter first name: ");
        String fname = scanner.next();
        System.out.println("enter last name: ");
        String lname = scanner.next();
        System.out.println("enter age: ");
        int age = scanner.nextInt();
        System.out.println("enter bunk house(a-e): ");
        String house = scanner.next();
        System.out.println("enter completed docs(y/n): ");
        String complete = scanner.next();

        //sql insert statement for camper table (test)
        statement = connection.prepareStatement(camper);
        statement.setString(1, id);
        statement.setString(2, fname);
        statement.setString(3, lname);
        statement.setInt(4, age);
        statement.setString(5, house);
        statement.setString(6, complete);

        System.out.println(statement);

        //add, execute, commit to db
        statement.addBatch();
        statement.executeBatch();
        connection.commit();
    }
}
