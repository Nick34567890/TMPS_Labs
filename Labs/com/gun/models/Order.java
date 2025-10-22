package gun.models;

import java.util.ArrayList;
import java.util.List;
import gun.pricing.Discount;

public class Order {
    private final List<Product> items = new ArrayList<>();
    private Discount discount = new Discount("NONE", 0.0);  // default: no discount

    public void addProduct(Product product) {
        items.add(product);
    }

    public List<Product> getItems() {
        return items;
    }

    public double getTotal() {
        double total = items.stream().mapToDouble(Product::getPrice).sum();
        return discount.apply(total);
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void printSummary() {
        System.out.println("=== Order Summary ===");
        for (Product p : items) {
            System.out.println(p);
        }
        System.out.printf("Total after discount: $%.2f\n", getTotal());
    }
}
