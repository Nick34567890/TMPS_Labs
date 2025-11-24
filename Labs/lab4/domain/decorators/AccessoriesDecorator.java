package domain.decorators;

import domain.products.SamsungProduct;

public class AccessoriesDecorator extends ProductDecorator {
    public AccessoriesDecorator(SamsungProduct product) {
        super(product);
    }

    @Override
    public String details() {
        return product.details() + " + Additional Accessories";
    }
}
