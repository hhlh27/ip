public class DazAI {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String logo = "  ____                    ___    _____ \n"
                + " |  _ \\  __ _  _____     / _\\   |__  _|\n"
                + " | | | |/ _` | |_  |    / /  \\    | | \n"
                + " | |_| | (_| |  / /_   /  --- \\   | | \n"
                + " |____/ \\__,_| |__,_| //       \\\\|____|  \n";

        System.out.println("Hello from\n" + logo);
        // Print greeting message
        System.out.println("____________________________________________________________");
        System.out.println(" Hello from\n" + logo);
        System.out.println(" Hello! I'm DazAI");
        System.out.println(" What can I do for you?");
        //Handle user input
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            // Echo the user's command
            System.out.println("____________________________________________________________");
            System.out.println(" " + input);
            System.out.println("____________________________________________________________");
        }
        //Print exit message
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}

