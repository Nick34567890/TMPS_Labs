package factories;

import domain.products.*;

public class ProductFactory {
    public SamsungProduct createProduct(String productType) {
        switch (productType) {
            case "Smartphone": return new Smartphone();
            case "TV": return new TV();
            case "Appliance": return new Appliance();
            default: throw new IllegalArgumentException("Unknown Product Type");
        }
    }
}
