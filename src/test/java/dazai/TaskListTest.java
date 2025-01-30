package dazai;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TaskListTest {
    @Test
    public void addTask_validTask_success() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Read a book");
        taskList.addTask(task);

        assertEquals(1, taskList.size());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void deleteTask_validIndex_success() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Read a book");
        taskList.addTask(task);
        Task deletedTask = taskList.deleteTask(0);

        assertEquals(0, taskList.size());
        assertEquals(task, deletedTask);
    }

    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        TaskList taskList = new TaskList();

        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.deleteTask(0);
        });

        assertTrue(exception.getMessage().contains("Index 0 out of bounds"));
    }
}