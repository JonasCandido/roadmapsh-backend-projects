import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GitHubUserActivity {

    private static final String GITHUB_API = "https://api.github.com/users/";

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java GitHubActivity <username>");
            return;
        }

        String username = args[0];
        String url = GITHUB_API + username + "/events";
        HttpURLConnection conn = null;

        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Java-CLI");

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Error: Unable to fetch activity. HTTP code: " + responseCode);
                return;
            }

            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
            }

            String json = response.toString();
            printActivities(json);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            conn.disconnect();
        }
    }

    public static void printActivities(String json) {
        String[] events = json.split("\\},\\{");

        for (String event : events) {

            String type = extractValue(event, "\"type\":\"", "\"");
            String repo = extractValue(event, "\"name\":\"", "\"");

            if (type == null || repo == null) continue;

            switch (type) {
                case "PushEvent":
                    String commits = extractValue(event, "\"size\":", ",");
                    System.out.println("- Pushed " + commits + " commits to " + repo);
                    break;
                case "IssuesEvent":
                    System.out.println("- Opened a new issue in " + repo);
                    break;
                case "WatchEvent":
                    System.out.println("- Starred " + repo);
                    break;
                case "ForkEvent":
                    System.out.println("- Forked " + repo);
                    break;
                default:
                    System.out.println("- " + type.replace("Event", "") + " in " + repo);
            }
        }
    }

    private static String extractValue(String text, String start, String end) {
        int startIdx = text.indexOf(start);
        if (startIdx == -1) return null;
        startIdx += start.length();
        int endIdx = text.indexOf(end, startIdx);
        if (endIdx == -1) return null;
        return text.substring(startIdx, endIdx);
    }
}
