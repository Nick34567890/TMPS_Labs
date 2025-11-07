package lab2.factory.factorymethod;

import lab2.domain.models.Weapon;

public abstract class WeaponCreator {
    public Weapon orderWeapon(String name, String caliber, int magSize, double weight) {
        Weapon weapon = createWeapon(name, caliber, magSize, weight);
        System.out.println("Created: " + weapon);
        return weapon;
    }

    protected abstract Weapon createWeapon(String name, String caliber, int magSize, double weight);
}