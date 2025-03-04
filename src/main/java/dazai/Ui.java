package dazai;

import java.util.Scanner;

/**
 * Handles the user interface interactions.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initializes the UI and sets up the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the program starts.
     */
    public void showWelcome() {
        String logo = "  ____                    ___    _____ \n"
                + " |  _ \\  __ _  _____     / _\\   |__  _|\n"
                + " | | | |/ _` | |_  |    / /  \\    | | \n"
                + " | |_| | (_| |  / /_   /  --- \\   | | \n"
                + " |____/ \\__,_| |__,_| //       \\\\|____|  \n";

        // Print greeting message
        System.out.println("____________________________________________________________");
        System.out.println(" Hello from\n" + logo);
        System.out.println(" Hello! I'm DazAi");
        System.out.println(" What can I do for you?");
    }

    /**
     * Reads the next line of user input.
     *
     * @return The input command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a separator line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }


    public void showMessage(String... messages) {
        for (String message : messages) {
            System.out.println(message);
        }
    }
}


