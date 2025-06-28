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
        Task task = new Task(id, desc, "Todo");
        tasks.add(task);
        saveTasks();
        System.out.println("Task added successfully (ID: " + id + ")");
    }

    public void update(int id, String desc) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setDescription(desc);
                saveTasks();
                System.out.println("Task updated.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void delete(int id) {
        boolean removed = tasks.removeIf(t -> t.getId() == id);
        if (removed) {
            saveTasks();
            System.out.println("Task deleted.");
        } else {
            System.out.println("Task not found.");
        }
    }

    public void markDone(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus("Done");
                saveTasks();
                System.out.println("Task marked as done.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void markInProgress(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus("In Progress");
                saveTasks();
                System.out.println("Task marked as in progress.");
                return;
            }
        }
    }

    public void listAll() {
        if (tasks.isEmpty()) System.out.println("No tasks.");
        else tasks.forEach(System.out::println);
    }

    public void listByStatus(String status) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getStatus().equalsIgnoreCase(status)) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) System.out.println("No tasks with status: " + status);
    }
}
