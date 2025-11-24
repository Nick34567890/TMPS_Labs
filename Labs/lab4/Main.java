import domain.facades.ProductManager;
import domain.adapters.*;
import domain.decorators.*;
import domain.observers.*;
import domain.products.Appliance;

public class Main {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();

        manager.getCatalog().addObserver(new InventoryObserver());
        manager.getCatalog().addObserver(new NotificationObserver());

        // Add products using builder/factory
        manager.addProduct("Smartphone", "color", "Blue", "ram", 4, "storage", 64);
        manager.addProduct("TV", 55);

        // Adapter pattern
        ThirdPartyLaptop thirdPartyLaptop = new ThirdPartyLaptop();
        LaptopAdapter adaptedLaptop = new LaptopAdapter(thirdPartyLaptop);
        manager.getCatalog().addProduct(adaptedLaptop);

        // Decorator pattern
        Appliance appliance = new Appliance();
        manager.getCatalog().addProduct(
                new ExtendedWarrantyDecorator(
                        new AccessoriesDecorator(appliance)
                )
        );

        // Show catalog
        manager.showCatalog();
    }
}
