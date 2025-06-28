import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private int id;
    private String description;
    private String status;
    private String createdAt;
    private String updatedAt;

    public Task(int id, String description, String status) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = now();
        this.updatedAt = this.createdAt;
    }

    private String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getCreatedAt() { return createdAt; }
    public String getUpdatedAt() { return updatedAt; }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = now();
    }

    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = now();
    }

    public String toJSON() {
        return String.format(
            "{\"id\":%d,\"description\":\"%s\",\"status\":\"%s\",\"createdAt\":\"%s\",\"updatedAt\":\"%s\"}",
            id, description.replace("\"", "\\\""), status, createdAt, updatedAt
        );
    }

    public static Task fromJSON(String json) {
        int id = Integer.parseInt(json.replaceAll(".*\"id\":(\\d+).*", "$1"));
        String description = json.replaceAll(".*\"description\":\"(.*?)\".*", "$1");
        String status = json.replaceAll(".*\"status\":\"(.*?)\".*", "$1");
        String createdAt = json.replaceAll(".*\"createdAt\":\"(.*?)\".*", "$1");
        String updatedAt = json.replaceAll(".*\"updatedAt\":\"(.*?)\".*", "$1");

        Task task = new Task(id, description, status);
        task.createdAt = createdAt;
        task.updatedAt = updatedAt;
        return task;
    }

    public String toString() {
        return String.format("ID: %d | %s | %s | Created: %s | Updated: %s",
                id, status.toUpperCase(), description, createdAt, updatedAt);
    }
}
