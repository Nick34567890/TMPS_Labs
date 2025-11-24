package domain.products;

public class TV extends SamsungProduct {
    private int size;

    public TV() {}
    public TV(int size) { this.size = size; }

    @Override
    public String details() {
        return "Samsung TV [Size: " + size + "\"]";
    }
}
