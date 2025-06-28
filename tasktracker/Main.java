import java.util.Arrays;

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
                    String desc = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                    manager.add(desc);
                    break;
                case "update":
                    if (args.length < 4) throw new IllegalArgumentException("Usage: update <id> <new description>");

                    try {
                        int idToUpdate = Integer.parseInt(args[1]);
                        String newDesc = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                        manager.update(idToUpdate, newDesc);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: ID must be a number.");
                    }
                    break;
                case "delete":
                    try {
                        int idToUpdate = Integer.parseInt(args[1]);
                        manager.delete(idToUpdate);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: ID must be a number.");
                    }
                    break;
                case "mark-done":
                    if (args.length < 2) throw new IllegalArgumentException("Usage: mark-done <id>");

                    try {
                        int idToDo = Integer.parseInt(args[1]);
                        manager.markDone(idToDo);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Id must be a number.");
                    }
                    break;
                case "mark-in-progress":
                    if (args.length < 2) throw new IllegalArgumentException("Usage: mark-in-progress <id>");

                    try {
                        int idToInit = Integer.parseInt(args[1]);
                        manager.markInProgress(idToInit);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Id must be a number.");
                    }
                    break;
                case "list":
                    if (args.length == 1) manager.listAll();
                    else manager.listByStatus(args[1]);
                    break;
                default:
                    System.out.println("Unknown command.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
