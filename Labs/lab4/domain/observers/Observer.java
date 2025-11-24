package domain.observers;

import domain.products.SamsungProduct;

public interface Observer {
    void update(SamsungProduct product);
}
