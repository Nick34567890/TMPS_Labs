package lab2.domain.models;
import lab2.utils.ID;
import lab2.prototype.Prototype;

public class Weapon implements Prototype<Weapon> {
    private final long id;
    private String name;
    private String caliber;
    private int magazineSize;
    private double weight;

    public Weapon(String name, String caliber, int magazineSize, double weight) {
        this.id = lab2.utils.ID.generate();
        this.name = name;
        this.caliber = caliber;
        this.magazineSize = magazineSize;
        this.weight = weight;
    }

    // Copy constructor for Prototype
    private Weapon(Weapon source) {
        this.id = source.id;
        this.name = source.name;
        this.caliber = source.caliber;
        this.magazineSize = source.magazineSize;
        this.weight = source.weight;
    }

    @Override
    public Weapon clone() {
        return new Weapon(this);
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", caliber='" + caliber + '\'' +
                ", mag=" + magazineSize +
                ", weight=" + weight + "kg" +
                '}';
    }

    // Getters
    public String getName() { return name; }
    public String getCaliber() { return caliber; }
}