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
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
               listTasks(tasks);
            } else if (input.startsWith("mark ")) {
               markTask(tasks, input);
            } else if (input.startsWith("unmark ")) {
               unmarkTask(tasks, input);
            } else if (input.startsWith("todo ")) {
                addToDo(tasks,input);
            } else if (input.startsWith("deadline ")) {
                addDeadline(tasks,input);
            } else if (input.startsWith("event ")) {
               addEvent(tasks, input);
            }

            else {
                addTask(tasks, input);
            }
        }




/**
            // Echo the user's command
            System.out.println("____________________________________________________________");
            System.out.println(" " + input);
            System.out.println("____________________________________________________________");
 **/


        //Print exit message
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        scanner.close();
    }

    private static void addEvent(ArrayList<Task> tasks, String input) {
        String[] parts = input.substring(6).split("/from|/to", 3);
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        Task task = new Event(description, from, to);
        tasks.add(task);
        printTaskAdded(task, tasks.size());
    }

    private static void addDeadline(ArrayList<Task> tasks, String input) {
        String[] parts = input.substring(9).split("/by", 2);

        String description = parts[0].trim();
        String by = parts[1].trim();
        Task task = new Deadline(description, by);
        tasks.add(task);
        printTaskAdded(task, tasks.size());
    }

    private static void addToDo(ArrayList<Task> tasks, String input) {
        String description = input.substring(5).trim();
        Task task = new ToDo(description);
        tasks.add(task);
        printTaskAdded(task, tasks.size());
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

