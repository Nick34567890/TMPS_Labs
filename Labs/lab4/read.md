# Behavioral Design Patterns

**Author:** Nichita Gancear
**Group:** FAF-232

---

## Objectives:
- Implement behavioral design patterns to manage communication between objects
- Extend the previous laboratory work with new functionalities
- Implement at least one behavioral design pattern

## Used Design Pattern:
- Observer Pattern

## File Structure:
![File_Structure.png](results%2FFile_Structure.png)

---

## Implementation Details

### 1. Observer Pattern Core Components

#### Base Observer Interface
```java
import domain.products.SamsungProduct;

public interface Observer {
    void update(SamsungProduct product);
}

```
This abstract base class defines the contract that all concrete observers must follow. The `update` method is intentionally left empty as it serves as an interface that concrete observers will implement according to their specific notification needs.

#### Concrete Observers
```java
public class InventoryObserver implements Observer {
@Override
public void update(SamsungProduct product) {
System.out.println("Inventory updated: " + product.details() + " added to stock");
}
}

```
Each concrete observer implements the `update` method differently:
- `InventoryObserver`: Simulates updating inventory records
- `NotificationObserver`: Simulates sending notifications to interested parties

The observers use the `product.details()` method, which was already implemented in our product classes from the previous laboratory work, maintaining consistency across the system.

### 2. Subject Implementation (ProductCatalog)

```java

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
```
The `ProductCatalog` maintains its singleton pattern from the previous implementation while adding observer functionality. The `_observers` list is initialized in `__new__` to ensure it's created only once, maintaining the singleton's integrity.

Key methods:
- `add_observer`: Registers new observers to the notification list
- `notify_observers`: Iterates through all observers and calls their `update` method
- `add_product`: Enhanced to trigger notifications after adding a product

### 3. Integration with Existing Architecture

```java

public class Main {
public static void main(String[] args) {
// Create the ProductManager (Facade)
ProductManager manager = new ProductManager();

// Initialize observers
manager.getCatalog().addObserver(new InventoryObserver());
manager.getCatalog().addObserver(new NotificationObserver());

// Product additions with different creation patterns
// Builder pattern for Smartphone
manager.addProduct("Smartphone", "color", "Blue", "ram", 4, "storage", 64);

// Direct instantiation for TV
manager.addProduct("TV", 55);

// Adapter pattern integration with observer
ThirdPartyLaptop thirdPartyLaptop = new ThirdPartyLaptop();
LaptopAdapter adaptedLaptop = new LaptopAdapter(thirdPartyLaptop);
manager.getCatalog().addProduct(adaptedLaptop); // observers triggered

// Decorator pattern with observer
Appliance appliance = new Appliance();
SamsungProduct decoratedAppliance = new ExtendedWarrantyDecorator(
new AccessoriesDecorator(appliance)
);
manager.getCatalog().addProduct(decoratedAppliance); // observers triggered

// Display the catalog
manager.showCatalog();
}
}
```

The integration showcases how the Observer pattern works seamlessly with:
- Builder Pattern (Smartphone creation)
- Direct instantiation (TV creation)
- Adapter Pattern (Third-party laptop)
- Decorator Pattern (Appliance with extensions)

## Design Decisions and Their Implications

1. **Singleton Maintenance**
    - The observer list is part of the singleton instance
    - Ensures all parts of the system work with the same observers
    - Prevents multiple notification systems from existing

2. **Observer Implementation**
    - Kept observers simple with print statements for demonstration
    - In a real system, these would connect to actual inventory systems or notification services
    - Easy to extend with new observer types without modifying existing code

3. **Integration with Existing Patterns**
    - Observer pattern complements existing structural patterns
    - Notifications work regardless of how products are created or modified
    - Maintains single responsibility principle while adding new functionality

## Results
![Terminal.png](results%2FTerminal.png)

## Conclusions
The implementation of the Observer pattern in this project demonstrates how behavioral design patterns can enhance the flexibility and responsiveness of a software system. By integrating observers into the ProductCatalog singleton, the system can automatically notify relevant components whenever products are added, without creating tight coupling between classes.

This approach ensures that:

- Real-time notifications are seamlessly delivered to different parts of the system, such as inventory management and user alerts.

- Extensibility is maintained, allowing new observers to be added without modifying existing code, adhering to the Open/Closed Principle.

- Compatibility with other design patterns is preserved. Builder, Adapter, and Decorator patterns work alongside Observer without interference, demonstrating how behavioral patterns can complement structural and creational patterns.

- Clean architecture is maintained, separating responsibilities between product creation, enhancement, and notification.

Overall, this implementation shows a practical, maintainable, and scalable application of the Observer pattern. It highlights how behavioral patterns can improve system communication, provide real-time updates, and enhance the overall design quality, all while integrating smoothly with other design patterns in a cohesive system.