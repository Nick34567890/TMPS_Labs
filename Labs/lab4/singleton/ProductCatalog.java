package singleton;

import domain.products.SamsungProduct;
import domain.observers.Observer;
import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
    private static ProductCatalog instance;
    private List<SamsungProduct> products;
    private List<Observer> observers;

    private ProductCatalog() {
        products = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static ProductCatalog getInstance() {
        if (instance == null) {
            instance = new ProductCatalog();
        }
        return instance;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(SamsungProduct product) {
        for (Observer observer : observers) {
            observer.update(product);
        }
    }

    public void addProduct(SamsungProduct product) {
        products.add(product);
        notifyObservers(product);
    }

    public List<SamsungProduct> getProducts() {
        return products;
    }
}
