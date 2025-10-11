package grocery;


import grocery.storage.FileStorage;

public class GroceryApp {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("No command provided.");
            System.exit(1);
        }

        App app = new App(new FileStorage());

        try {
            switch (args[0]) {
                case "add":
                    if (args.length < 2) throw new IllegalArgumentException("Usage: add \"item name\"");
                    var item = app.add(args[1]);
                    System.out.println("Added: " + item);
                    break;
                case "list":
                    app.list().forEach(System.out::println);
                    break;
                case "check":
                    if (args.length < 2) throw new IllegalArgumentException("Usage: check <id>");
                    int checkId = Integer.parseInt(args[1]);
                    var checked = app.check(checkId);
                    System.out.println("Checked: " + checked);
                    break;
                case "remove":
                    if (args.length < 2) throw new IllegalArgumentException("Usage: remove <id>");
                    int removeId = Integer.parseInt(args[1]);
                    app.remove(removeId);
                    System.out.println("Removed item " + removeId);
                    break;
                default:
                    System.err.println("Unknown command: " + args[0]);
                    System.exit(1);
            }
            System.exit(0);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
