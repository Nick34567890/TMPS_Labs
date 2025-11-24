package domain.builders;

import domain.products.Smartphone;

public class SmartphoneBuilder {
    private Smartphone smartphone;

    public SmartphoneBuilder() {
        smartphone = new Smartphone();
    }

    public SmartphoneBuilder setColor(String color) {
        smartphone.setColor(color);
        return this;
    }

    public SmartphoneBuilder setRam(int ram) {
        smartphone.setRam(ram);
        return this;
    }

    public SmartphoneBuilder setStorage(int storage) {
        smartphone.setStorage(storage);
        return this;
    }

    public Smartphone build() {
        return smartphone;
    }
}
