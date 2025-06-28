import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class TaskManager {
    private static final Path FILE_PATH = Paths.get("tasks.json");
    private List<Task> tasks;

    public TaskManager() {
        tasks = loadTasks();
    }

    private List<Task> loadTasks() {
        List<Task> list = new ArrayList<>();

        // Create file if it does not exist
        try {
            if (Files.notExists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
                return list;
            }
        } catch (IOException e) {
            System.out.println("Error when creating file: " + e.getMessage());
            return list;
        }

        try {
            List<String> lines = Files.readAllLines(FILE_PATH);
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    list.add(Task.fromJSON(line));
                }
            }
        } catch (IOException e) {
            System.out.println("Error when loading tasks: " + e.getMessage());
        }

        return list;
    }

    private void saveTasks() {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toJSON());
        }

        try {
            Files.write(FILE_PATH, lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefas: " + e.getMessage());
        }
    }

    public void add(String desc) {
        int id = tasks.stream().mapToInt(Task::getId).max().orElse(0) + 1;
        Task task = new Task(id, desc, "todo");
        tasks.add(task);
        saveTasks();
        System.out.println("Task added successfully (ID: " + id + ")");
    }
}
