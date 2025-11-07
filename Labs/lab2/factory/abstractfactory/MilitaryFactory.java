package lab2.factory.abstractfactory;

import lab2.domain.models.Helmet;
import lab2.domain.models.Weapon;

public class MilitaryFactory implements IGunShopFactory {
    @Override
    public Weapon createPistol() {
        return new Weapon("M17", "9mm", 17, 0.82);
    }

    @Override
    public Weapon createRifle() {
        return new Weapon("M4A1", "5.56mm", 30, 3.4);
    }

    @Override
    public Helmet createHelmet() {
        return new Helmet("ACH Military", 4);
    }
}