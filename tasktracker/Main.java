public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Use: add|update|delete|mark-done|mark-in-progress|list");
            return;
        }

        TaskManager manager = new TaskManager();
        String cmd = args[0];

        try {
            switch (cmd) {
                case "add":
                    if(args.length < 2) throw new IllegalArgumentException("Description required.");
                    manager.add(args[1]);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
