package domain.decorators;

import domain.products.SamsungProduct;

public class ExtendedWarrantyDecorator extends ProductDecorator {
    public ExtendedWarrantyDecorator(SamsungProduct product) {
        super(product);
    }

    @Override
    public String details() {
        return product.details() + " + Extended Warranty";
    }
}
