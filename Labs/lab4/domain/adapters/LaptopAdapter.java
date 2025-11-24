package domain.adapters;

import domain.products.SamsungProduct;

public class LaptopAdapter extends SamsungProduct {
    private ThirdPartyLaptop laptop;

    public LaptopAdapter(ThirdPartyLaptop laptop) {
        this.laptop = laptop;
    }

    @Override
    public String details() {
        return laptop.getSpecifications();
    }
}
