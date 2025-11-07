package lab2.domain.inventory;

import lab2.domain.models.Weapon;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static Inventory instance = new Inventory();
    private List<Weapon> weapons = new ArrayList<>();

    private Inventory() {}

    public static Inventory getInstance() {
        return instance;
    }

    public void addWeapon(Weapon w) {
        weapons.add(w);
    }

    public void listAll() {
        for (Weapon w : weapons) {
            System.out.println(" • " + w);
        }
    }


    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void listAllWithIndex() {
        for (int i = 0; i < weapons.size(); i++) {
            System.out.println(i + ": " + weapons.get(i));
        }
    }
}
