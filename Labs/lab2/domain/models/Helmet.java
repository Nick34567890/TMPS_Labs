package lab2.domain.models;

public class Helmet {
    private final String type;
    private final int protectionLevel;

    public Helmet(String type, int protectionLevel) {
        this.type = type;
        this.protectionLevel = protectionLevel;
    }

    @Override
    public String toString() {
        return "Helmet{" +
                "type='" + type + '\'' +
                ", protection=" + protectionLevel +
                '}';
    }
}