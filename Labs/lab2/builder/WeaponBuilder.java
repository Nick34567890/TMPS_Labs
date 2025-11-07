package lab2.builder;

import lab2.domain.models.Weapon;

public class WeaponBuilder {
    private String name = "Unnamed";
    private String caliber = "Unknown";
    private int magazineSize = 30;
    private double weight = 3.0;

    public WeaponBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public WeaponBuilder setCaliber(String caliber) {
        this.caliber = caliber;
        return this;
    }

    public WeaponBuilder setMagazineSize(int magazineSize) {
        this.magazineSize = magazineSize;
        return this;
    }

    public WeaponBuilder setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    public Weapon build() {
        return new Weapon(name, caliber, magazineSize, weight);
    }
}