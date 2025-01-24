import java.util.ArrayList;
import java.util.Scanner;

public class DazAI {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new java.util.Scanner(System.in);
        printGreeting();
        //Handle user input
        while (true) {
            String input = scanner.nextLine().trim();
            String[] words = input.split(" ", 2);
            String command = words[0];
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                scanner.close();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                listTasks(tasks);
            } else if (input.startsWith("mark ")) {
                markTask(tasks, input);
            } else if (input.startsWith("unmark ")) {
                unmarkTask(tasks, input);
            } else if (input.startsWith("todo")) {
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("____________________________________________________________");
                } else {
                    addToDo(tasks, input);
                }

            } else if (input.startsWith("deadline ")) {
                addDeadline(tasks, input);
            } else if (input.startsWith("event ")) {
                addEvent(tasks, input);
            } else {
                try {
                    throw new DazAIException("I'm sorry, but I don't understand that command.");
                } catch (DazAIException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! " + e.getMessage());
                    System.out.println("____________________________________________________________");
                }
            }
        }
    }


    /**
     * // Echo the user's command
     * System.out.println("____________________________________________________________");
     * System.out.println(" " + input);
     * System.out.println("____________________________________________________________");
     **/


    private static void addEvent(ArrayList<Task> tasks, String input) {

        String[] parts = input.substring(6).split("/from|/to", 3);
        if (parts.length < 3) {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid format. Input: event <description> /from <time> /to <time>");
            System.out.println("____________________________________________________________");

        }
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        Task task = new Event(description, from, to);
        tasks.add(task);
        printTaskAdded(task, tasks.size());
    }

    private static void addDeadline(ArrayList<Task> tasks, String input) {
        String[] parts = input.substring(9).split("/by", 2);
        if (parts.length < 2) {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid format. Input: deadline <description> /by <time>");
            System.out.println("____________________________________________________________");

        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        Task task = new Deadline(description, by);
        tasks.add(task);
        printTaskAdded(task, tasks.size());
    }

    private static void addToDo(ArrayList<Task> tasks, String input) {
        try {
            String description = input.substring(5).trim();
            if (description.isEmpty()) {
                throw new DazAIException("The description of a todo cannot be empty.");
            }
            Task task = new ToDo(description);
            tasks.add(task);
            printTaskAdded(task, tasks.size());
        } catch (DazAIException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" OOPS!!! " + e.getMessage());
            System.out.println("____________________________________________________________");
        } catch (StringIndexOutOfBoundsException e) {

            System.out.println("____________________________________________________________");
            System.out.println(" OOPS!!! The description of a todo cannot be empty.");
            System.out.println("____________________________________________________________");
        }
    }

    private static void printTaskAdded(Task task, int size) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void printGreeting() {
        String logo = "  ____                    ___    _____ \n"
                + " |  _ \\  __ _  _____     / _\\   |__  _|\n"
                + " | | | |/ _` | |_  |    / /  \\    | | \n"
                + " | |_| | (_| |  / /_   /  --- \\   | | \n"
                + " |____/ \\__,_| |__,_| //       \\\\|____|  \n";


        // Print greeting message
        System.out.println("____________________________________________________________");
        System.out.println(" Hello from\n" + logo);
        System.out.println(" Hello! I'm DazAI");
        System.out.println(" What can I do for you?");
    }

    private static void listTasks(ArrayList<Task> tasks) {
        // Display the list of tasks
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println(" Your task list is empty.");
        } else {
            System.out.println(" These are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    private static void addTask(ArrayList<Task> tasks, String input) {
        // Add the input to the list of tasks
        tasks.add(new Task(input));
        System.out.println("____________________________________________________________");
        System.out.println(" added: " + input);
        System.out.println("____________________________________________________________");
    }

    private static void markTask(ArrayList<Task> tasks, String input) {
        // Mark task as done
        try {
            int taskNumber = Integer.parseInt(input.substring(5)) - 1;
            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                tasks.get(taskNumber).markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Great! Marked as done!");
                System.out.println("   " + tasks.get(taskNumber));
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" Invalid task number.");
                System.out.println("____________________________________________________________");
            }
        } catch (NumberFormatException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid input.");
            System.out.println("____________________________________________________________");
        }
    }

    private static void unmarkTask(ArrayList<Task> tasks, String input) {
        // Mark task as not done
        try {
            int taskNumber = Integer.parseInt(input.substring(7)) - 1;
            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                tasks.get(taskNumber).markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Task marked as not done.");
                System.out.println("   " + tasks.get(taskNumber));
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" Invalid task number. ");
                System.out.println("____________________________________________________________");
            }
        } catch (NumberFormatException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" Invalid input.");
            System.out.println("____________________________________________________________");
        }
    }
}



