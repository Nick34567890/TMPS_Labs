package grocery.storage;

import grocery.models.Item;
import java.util.ArrayList;
import java.util.List;

public class MemoryStorage implements Storage {
    private List<Item> items = new ArrayList<>();

    @Override
    public List<Item> loadItems() {
        return new ArrayList<>(items); // return a copy
    }

    @Override
    public void saveItems(List<Item> items) {
        this.items = new ArrayList<>(items); // replace with new copy
    }
}
