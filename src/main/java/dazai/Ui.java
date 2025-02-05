package dazai;

import java.util.ArrayList;
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

    /**
     * Displays a given message.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskList The task list to be displayed.
     */
    public void showTaskList(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("Your task list is empty.");
        } else {
            System.out.println("Here are your tasks:");
            ArrayList<Task> tasks = taskList.getAllTasks();
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Displays the goodbye message when the program exits.
     */
    public void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}