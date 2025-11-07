package lab2.factory.factorymethod;

import lab2.domain.models.Weapon;

public class ConcreteCreators {

    public static class PistolCreator extends WeaponCreator {
        @Override
        protected Weapon createWeapon(String name, String caliber, int magSize, double weight) {
            return new Weapon(name + " (Pistol)", caliber, magSize, weight);
        }
    }

    public static class RifleCreator extends WeaponCreator {
        @Override
        protected Weapon createWeapon(String name, String caliber, int magSize, double weight) {
            return new Weapon(name + " (Rifle)", caliber, magSize, weight);
        }
    }
}