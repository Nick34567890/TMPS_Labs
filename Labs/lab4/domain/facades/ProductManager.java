package domain.facades;

import domain.builders.SmartphoneBuilder;
import domain.products.*;
import domain.adapters.*;
import domain.decorators.*;
import factories.ProductFactory;
import singleton.ProductCatalog;

public class ProductManager {
    private ProductCatalog catalog;
    private ProductFactory factory;

    public ProductManager() {
        catalog = ProductCatalog.getInstance();
        factory = new ProductFactory();
    }

    public void addProduct(String productType, Object... args) {
        SamsungProduct product = null;

        switch (productType) {
            case "Smartphone":
                SmartphoneBuilder builder = new SmartphoneBuilder();
                for (int i = 0; i < args.length; i += 2) {
                    String key = (String) args[i];
                    Object value = args[i + 1];
                    switch (key) {
                        case "color": builder.setColor((String) value); break;
                        case "ram": builder.setRam((int) value); break;
                        case "storage": builder.setStorage((int) value); break;
                    }
                }
                product = builder.build();
                break;
            case "TV":
                product = new TV((int) args[0]);
                break;
            default:
                product = factory.createProduct(productType);
        }

        catalog.addProduct(product);
    }

    public void showCatalog() {
        System.out.println("\nCatalog Products:");
        for (SamsungProduct product : catalog.getProducts()) {
            System.out.println(product.details());
        }
    }

    public ProductCatalog getCatalog() {
        return catalog;
    }
}
