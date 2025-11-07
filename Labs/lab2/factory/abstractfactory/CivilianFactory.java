package lab2.factory.abstractfactory;

import lab2.domain.models.Helmet;
import lab2.domain.models.Weapon;

public class CivilianFactory implements IGunShopFactory {
    @Override
    public Weapon createPistol() {
        return new Weapon("Glock 19", "9mm", 15, 0.67);
    }

    @Override
    public Weapon createRifle() {
        return new Weapon("AR-15 Civilian", "5.56mm", 30, 3.1);
    }

    @Override
    public Helmet createHelmet() {
        return new Helmet("Civilian Ballistic", 2);
    }
}