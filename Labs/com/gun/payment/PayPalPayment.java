package gun.payment;

public class PayPalPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.printf("Paid $%.2f using PayPal.\n", amount);
    }
}
