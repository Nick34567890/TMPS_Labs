package grocery.storage;

import grocery.models.Item;
import java.util.List;

public interface Storage {
    List<Item> loadItems();           // method 1
    void saveItems(List<Item> items); // method 2
}
