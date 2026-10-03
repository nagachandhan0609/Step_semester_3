import java.util.*;

public class CanteenBillingCounter {

    interface Customer {
        double calculateFinalAmount(double amount);
    }

    static class Student implements Customer {
        public double calculateFinalAmount(double amount) {
            return amount * 0.90;
        }
    }

    static class Staff implements Customer {
        public double calculateFinalAmount(double amount) {
            return amount * 0.95;
        }
    }

    static class Guest implements Customer {
        public double calculateFinalAmount(double amount) {
            return amount + 10;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double amount = sc.nextDouble();

            Customer customer;

            switch (type) {
                case "STUDENT":
                    customer = new Student();
                    break;
                case "STAFF":
                    customer = new Staff();
                    break;
                default:
                    customer = new Guest();
            }

            double finalAmount = customer.calculateFinalAmount(amount);
            total += finalAmount;

            System.out.printf("%s: %.2f%n", type, finalAmount);
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
