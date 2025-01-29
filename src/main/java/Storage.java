import java.io.*;
import java.util.ArrayList;

public class Storage {

    private static final String FILE_PATH = "./data/dazAI.txt"; // relative path

    public static ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks; // Return empty list if file doesn't exist
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] taskData = line.split("\\|");

                if (taskData.length < 3) {
                    System.out.println("Skipping invalid line: " + line);
                    continue; // Skip lines with missing fields
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
                        if (taskData.length < 4) {  // Ensure there is a deadline field
                            System.out.println("Skipping invalid deadline task: " + line);
                            continue;
                        }
                        String deadline = taskData[3].trim();
                        task = new Deadline(description, deadline);
                        break;
                    case "E":
                        if (taskData.length < 5) {  // Ensure both from and to fields exist
                            System.out.println("Skipping invalid event task: " + line);
                            continue;
                        }
                        String from = taskData[3].trim();
                        String to = taskData[4].trim();
                        task = new Event(description, from, to);
                        break;
                    default:
                        System.out.println("Skipping unknown task type: " + line);
                        continue;
                }

                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }


    public static void saveTasks(ArrayList<Task> tasks) throws IOException {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs(); // Ensure the directory exists

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                String taskType = "";
                String taskInfo = "";

                if (task instanceof ToDo) {
                    taskType = "T";
                    taskInfo = task.getDescription();
                } else if (task instanceof Deadline) {
                    taskType = "D";
                    taskInfo = ((Deadline) task).getDescription() + " | " + ((Deadline) task).getBy();
                } else if (task instanceof Event) {
                    taskType = "E";
                    taskInfo = ((Event) task).getDescription() + " | " + ((Event) task).toString();
                }

                String status = task.isDone() ? "1" : "0";
                bw.write(taskType + " | " + status + " | " + taskInfo);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new IOException("Error saving tasks: " + e.getMessage());
        }
    }
}