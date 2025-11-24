package domain.decorators;

import domain.products.SamsungProduct;

public abstract class ProductDecorator extends SamsungProduct {
    protected SamsungProduct product;

    public ProductDecorator(SamsungProduct product) {
        this.product = product;
    }
}
