package a8;


import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Map<String, String> passwordManager = new PasswordManager<>();
        scanner.useDelimiter(Pattern.compile("(\\n)|;"));

        // your code below
        while (true) {
            System.out.println("Enter Master Password:");
            String password = scanner.next();
            while (!passwordManager.checkMasterPassword(password)) {
                System.out.println("Enter Master Password:");
                password = scanner.next();
            }

            String command = scanner.next();
            while (command != "Exit") {
                switch (command) {
                    case ("New password"):
                        String _website = scanner.next();
                        String _password = scanner.next();
                        passwordManager.put(_website, _password);
                        System.out.println("New password added");
                        break;
                    case ("Get password"):
                        _website = scanner.next();
                        _password = passwordManager.get(_website);
                        if (_password != null) {
                            System.out.println(_password);
                        }
                        else {
                            System.out.println("Password does not exist");
                        }
                        break;
                    case ("Delete account"):
                        _website = scanner.next();
                        if (passwordManager.remove(_website) != null) {
                            System.out.println("Account deleted");
                        }
                        else {
                            System.out.println("Account does not exist");
                        }
                        break;
                    case ("Check duplicate password"):
                        _password = scanner.next();
                        List<String> duplicates = passwordManager.checkDuplicate(_password);
                        if (duplicates != null) {
                            System.out.println("Websites using that password:");
                            System.out.println(duplicates);
                        }
                        else {
                            System.out.println("No accounts uses that password");
                        }
                        break;
                    case ("Get accounts"):
                        System.out.println("Your accounts: ");
                        break;
                    case ("Generate random password"):
                        int length = scanner.nextInt();
                        System.out.println(passwordManager.generateRandomPassword(length));
                        break;
                    default: System.out.println("Command not found");
                }

                command = scanner.next();
            }
        }
    }
}
