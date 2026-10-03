import java.util.*;

public class PaymentSystemFeeCalculation {

    interface PaymentMethod {
        double calculate(double amount);
    }

    static class Card implements PaymentMethod {
        public double calculate(double amount) {
            return amount * 1.02;
        }
    }

    static class Wallet implements PaymentMethod {
        public double calculate(double amount) {
            return amount * 1.01;
        }
    }

    static class BankTransfer implements PaymentMethod {
        public double calculate(double amount) {
            return amount;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double amount = sc.nextDouble();

            PaymentMethod paymentMethod;

            switch (type) {
                case "CARD":
                    paymentMethod = new Card();
                    break;
                case "WALLET":
                    paymentMethod = new Wallet();
                    break;
                default:
                    paymentMethod = new BankTransfer();
            }

            double adjustedAmount = paymentMethod.calculate(amount);
            total += adjustedAmount;

            System.out.printf("%s: %.2f%n", type, adjustedAmount);
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
