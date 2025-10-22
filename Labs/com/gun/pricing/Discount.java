package gun.pricing;

public class Discount {
    private final String code;
    private final double percentage;

    public Discount(String code, double percentage) {
        this.code = code;
        this.percentage = percentage;
    }

    public double apply(double total) {
        return total * (1 - percentage);
    }

    public String getCode() {
        return code;
    }

    public double getPercentage() {
        return percentage;
    }

    public static Discount fromCode(String code) {
        return switch (code.toUpperCase()) {
            case "VIP" -> new Discount("VIP", 0.10);
            case "STAFF" -> new Discount("STAFF", 0.15);
            case "FAMILY" -> new Discount("FAMILY", 0.12);
            case "SUMMER" -> new Discount("SUMMER", 0.20);
            case "FRIEND" -> new Discount("FRIEND", 0.05);
            default -> null;
        };
    }
}