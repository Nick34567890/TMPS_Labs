package domain.products;

public class Smartphone extends SamsungProduct {
    private String color;
    private int ram;
    private int storage;

    public Smartphone() {}

    public Smartphone(String color, int ram, int storage) {
        this.color = color;
        this.ram = ram;
        this.storage = storage;
    }

    public void setColor(String color) { this.color = color; }
    public void setRam(int ram) { this.ram = ram; }
    public void setStorage(int storage) { this.storage = storage; }

    @Override
    public String details() {
        return "Samsung Smartphone [Color: " + color + ", RAM: " + ram + "GB, Storage: " + storage + "GB]";
    }
}
