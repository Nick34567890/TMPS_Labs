package gun;

import gun.models.Order;
import gun.pricing.Discount;
import gun.payment.PaymentMethod;
import gun.payment.CreditCardPayment;
import gun.payment.PayPalPayment;

import java.util.Scanner;

public class FUNShop {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Order order = new Order();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the FUN-Shop!");
        shop.showCatalog();

        // Adding products loop (your existing code)
        while (true) {
            System.out.print("Enter product ID to add to cart or |0| to finish: ");
            String line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("0") || line.equalsIgnoreCase("q")) {
                break;
            }

            String[] tokens = line.split("[\\s,]+");
            boolean anyAdded = false;

            for (String token : tokens) {
                if (token.isEmpty()) continue;
                if (token.equalsIgnoreCase("0") || token.equalsIgnoreCase("q")) break;

                try {
                    int id = Integer.parseInt(token);
                    shop.getProductById(id).ifPresentOrElse(product -> {
                        order.addProduct(product);
                        System.out.println("Added: " + product);
                    }, () -> System.out.println("Invalid product ID: " + id));
                    anyAdded = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input (not a number): '" + token + "'");
                }
            }

            if (!anyAdded) {
                System.out.println("No valid product IDs entered.");
            }
        }

        // --- Ask for Discount Code ---
        Discount discount = null;
        while (discount == null) {
            System.out.print("Enter discount code () or X for no discount: ");
            String discountCode = scanner.nextLine().trim();
            if (discountCode.equalsIgnoreCase("X")) {
                break;
            }
            discount = Discount.fromCode(discountCode);
            if (discount == null) {
                System.out.println("Invalid discount code. Try again.");
            }
        }

        // Calculate total with discount
        double total = order.getTotal();
        if (discount != null) {
            total = discount.apply(total);
            System.out.printf("Discount '%s' applied! New total: $%.2f\n", discount.getCode(), total);
        } else {
            System.out.printf("No discount applied. Total: $%.2f\n", total);
        }

        // --- Payment method selection ---
        PaymentMethod paymentMethod = null;
        while (paymentMethod == null) {
            System.out.print("Choose payment method (1: Credit Card, 2: PayPal): ");
            String paymentChoice = scanner.nextLine().trim();
            switch (paymentChoice) {
                case "1" -> paymentMethod = new CreditCardPayment();
                case "2" -> paymentMethod = new PayPalPayment();
                default -> System.out.println("Invalid choice, please select 1 or 2.");
            }
        }

        // Pay and print order summary
        paymentMethod.pay(total);
        System.out.println();
        order.printSummary();
        System.out.println("Thank you for your purchase!");
    }
}
