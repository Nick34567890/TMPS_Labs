package grocery.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Item {
    private int id;
    private String name;
    private boolean checked;
    private String createdAt;

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
        this.checked = false;
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    // Add a private constructor to support loading an item with all fields (including createdAt)
    private Item(int id, String name, boolean checked, String createdAt) {
        this.id = id;
        this.name = name;
        this.checked = checked;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return name; }
    public boolean isChecked() { return checked; }
    public String getCreatedAt() { return createdAt; }

    public void check() { this.checked = true; }

    @Override
    public String toString() {
        return String.format("[%d] %s %s (%s)", id, name, checked ? "(✓)" : "", createdAt);
    }

    // ------------------ ADD THESE METHODS --------------------

    /**
     * Converts the item to a string for file storage (e.g., CSV format).
     */
    public String toStorageString() {
        // Escape commas in the name (if any) by replacing with \,
        String safeName = name.replace(",", "\\,");
        return String.format("%d,%s,%b,%s", id, safeName, checked, createdAt);
    }

    /**
     * Parses a line from the file and returns an Item instance.
     */
    public static Item fromString(String line) {
        // Split on commas, but handle escaped commas
        // For simplicity, split on comma first and then join back if needed
        String[] parts = line.split(",", 4);
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid item line: " + line);
        }

        int id = Integer.parseInt(parts[0]);

        // Handle escaped commas in name
        String name = parts[1].replace("\\,", ",");

        boolean checked = Boolean.parseBoolean(parts[2]);
        String createdAt = parts[3];

        return new Item(id, name, checked, createdAt);
    }
}
