package grocery.storage;

import grocery.models.Item;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage implements Storage {
    private static final Path FILE_PATH = Paths.get("grocery_items.txt");

    @Override
    public List<Item> loadItems() {
        List<Item> items = new ArrayList<>();
        if (!Files.exists(FILE_PATH)) {
            return items; // empty list if file not found
        }
        try {
            List<String> lines = Files.readAllLines(FILE_PATH);
            for (String line : lines) {
                try {
                    items.add(Item.fromString(line));
                } catch (Exception e) {
                    System.err.println("Skipping invalid item line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public void saveItems(List<Item> items) {
        List<String> lines = new ArrayList<>();
        for (Item item : items) {
            lines.add(item.toStorageString());
        }
        try {
            Files.write(FILE_PATH, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
