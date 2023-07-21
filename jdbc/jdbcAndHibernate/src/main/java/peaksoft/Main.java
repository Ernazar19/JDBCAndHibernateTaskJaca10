package peaksoft;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        while (true) {
            System.out.println("|----------------------|\n" +
                    "| 1. creat table       |\n" +
                    "| 2. saveUser          |\n" +
                    "| 3. dropUsersTable    |\n" +
                    "| 4. removeUserById    |\n" +
                    "| 5. getAllUsers       |\n" +
                    "| 6. cleanUsersTable   |\n" +
                    "|______________________|");
            System.out.print("Chose command:");
            int num = new Scanner(System.in).nextInt();
            switch (num) {
                case 1 -> userService.createUsersTable();
                case 2 -> {
                    System.out.print("Enter user first name: ");
                    String firstName = new Scanner(System.in).nextLine();
                    System.out.print("Enter user last name: ");
                    String lastName = new Scanner(System.in).nextLine();
                    System.out.print("Enter user age: ");
                    byte age = new Scanner(System.in).nextByte();
                    userService.saveUser(firstName,lastName, age);
                }
                case 3 -> userService.dropUsersTable();
                case 4 -> {
                    System.out.print("Enter user id");
                    long id = new Scanner(System.in).nextLong();
                    userService.removeUserById(id);
                }
                case 5 -> userService.getAllUsers().forEach(System.out::println);
                case 6 -> userService.cleanUsersTable();
            }
        }
    }
}
