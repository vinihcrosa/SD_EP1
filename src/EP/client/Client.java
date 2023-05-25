package EP.client;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Usage: java Client <host:port>");
            return;
        }
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command: ");
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                break;
            }
            System.out.println("Command entered: " + command);
        }
    }
}
