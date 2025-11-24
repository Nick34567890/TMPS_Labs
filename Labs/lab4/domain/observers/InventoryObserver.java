package domain.observers;

import domain.products.SamsungProduct;

public class InventoryObserver implements Observer {
    @Override
    public void update(SamsungProduct product) {
        System.out.println("Inventory updated: " + product.details() + " added to stock");
    }
}
