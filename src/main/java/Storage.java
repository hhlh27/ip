import java.io.*;
import java.util.ArrayList;

public class Storage {

    private static final String FILE_PATH = "./data/dazAI.txt"; // relative path

    public static ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] taskData = line.split("\\|");

                if (taskData.length < 3) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String taskType = taskData[0].trim();
                boolean isDone = taskData[1].trim().equals("1");
                String description = taskData[2].trim();
                Task task = null;

                switch (taskType) {
                    case "T":
                        task = new ToDo(description);
                        break;
                    case "D":
                        if (taskData.length < 4) {
                            System.out.println("Skipping invalid deadline task: " + line);
                            continue;
                        }
                        task = new Deadline(description, taskData[3].trim());
                        break;
                    case "E":
                        if (taskData.length < 5) {
                            System.out.println("Skipping invalid event task: " + line);
                            continue;
                        }
                        task = new Event(description, taskData[3].trim(), taskData[4].trim());
                        break;
                    default:
                        System.out.println("Skipping unknown task type: " + line);
                        continue;
                }

                if (task != null && isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new IOException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }



    public static void saveTasks(ArrayList<Task> tasks) throws IOException {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs(); // Ensure directories exist

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                String taskType = task instanceof ToDo ? "T" :
                        task instanceof Deadline ? "D" :
                                task instanceof Event ? "E" : "?";

                String status = task.isDone() ? "1" : "0";
                String description = task.getDescription();
                String extra = "";

                if (task instanceof Deadline) {
                    extra = ((Deadline) task).getBy();
                } else if (task instanceof Event) {
                    extra = ((Event) task).getFrom() + " | " + ((Event) task).getTo();
                }

                bw.write(taskType + " | " + status + " | " + description + (extra.isEmpty() ? "" : " | " + extra));
                bw.newLine();
            }
        }
    }
}