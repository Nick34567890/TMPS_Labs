package domain.observers;

import domain.products.SamsungProduct;

public class NotificationObserver implements Observer {
    @Override
    public void update(SamsungProduct product) {
        System.out.println("Notification: New product " + product.details() + " is now available");
    }
}
