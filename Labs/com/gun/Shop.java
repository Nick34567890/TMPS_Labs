package gun;

import gun.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Shop {
    private final List<Product> catalog;

    public Shop() {
        this.catalog = new ArrayList<>();
        populateCatalog();
    }

    private void populateCatalog() {
        // Firearms
        catalog.add(new Product(1, "Glock 19", 499.99, "Weapon"));
        catalog.add(new Product(2, "Colt M1911", 699.00, "Weapon"));
        catalog.add(new Product(3, "Remington 870", 429.99, "Weapon"));
        catalog.add(new Product(4, "AK-47", 899.50, "Weapon"));

        // Fruits
        catalog.add(new Product(5, "Apple", 0.99, "Fruit"));
        catalog.add(new Product(6, "Banana", 0.79, "Fruit"));
        catalog.add(new Product(7, "Orange", 1.10, "Fruit"));
        catalog.add(new Product(8, "Grapes", 2.49, "Fruit"));
    }

    public List<Product> getCatalog() {
        return catalog;
    }

    public Optional<Product> getProductById(int id) {
        return catalog.stream().filter(p -> p.getId() == id).findFirst();
    }

    public void showCatalog() {
        System.out.println("=== Elite Products ===");
        for (Product p : catalog) {
            System.out.println(p);
        }
    }
}
