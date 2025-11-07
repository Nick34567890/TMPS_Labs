package lab2.factory.abstractfactory;

import lab2.domain.models.Helmet;
import lab2.domain.models.Weapon;

public interface IGunShopFactory {
    Weapon createPistol();
    Weapon createRifle();
    Helmet createHelmet();
}