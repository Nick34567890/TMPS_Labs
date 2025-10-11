package grocery;

import grocery.models.Item;
import grocery.storage.Storage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class App {
    private final Storage storage;

    public App(Storage storage) {
        this.storage = storage;
    }

    public Item add(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty.");
        }

        List<Item> items = storage.loadItems();
        int newId = items.stream().mapToInt(Item::getId).max().orElse(0) + 1;

        Item item = new Item(newId, name);
        items.add(item);
        storage.saveItems(items);
        return item;
    }

    public List<Item> list() {
        return storage.loadItems();
    }

    public Item check(int id) {
        List<Item> items = storage.loadItems();
        Optional<Item> match = items.stream().filter(i -> i.getId() == id).findFirst();

        if (match.isEmpty()) {
            throw new IllegalArgumentException("No item with ID " + id);
        }

        Item item = match.get();
        item.check();
        storage.saveItems(items);
        return item;
    }

    public void remove(int id) {
        List<Item> items = storage.loadItems();
        List<Item> updated = items.stream().filter(i -> i.getId() != id).collect(Collectors.toList());

        if (updated.size() == items.size()) {
            throw new IllegalArgumentException("No item with ID " + id);
        }

        storage.saveItems(updated);
    }
}

