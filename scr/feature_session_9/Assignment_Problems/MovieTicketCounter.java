import java.util.*;

public class MovieTicketCounter {
    static abstract class Ticket {
        static final double CONVENIENCE_FEE = 20.0;
        final int count;
        Ticket(int count) { this.count = count; }
        abstract double seatPrice();
        double amount() { return count * (seatPrice() + CONVENIENCE_FEE); }
    }
    static class Regular extends Ticket {
        Regular(int n) { super(n); }
        double seatPrice() { return 150; }
    }
    static class Premium extends Ticket {
        Premium(int n) { super(n); }
        double seatPrice() { return 250; }
    }
    static class Recliner extends Ticket {
        Recliner(int n) { super(n); }
        double seatPrice() { return 400; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;
        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase(Locale.ROOT);
            int count = sc.nextInt();
            Ticket ticket;
            switch (type) {
                case "REGULAR": ticket = new Regular(count); break;
                case "PREMIUM": ticket = new Premium(count); break;
                case "RECLINER": ticket = new Recliner(count); break;
                default: throw new IllegalArgumentException("Unknown seat type: " + type);
            }
            double amount = ticket.amount();
            System.out.printf(Locale.US, "%s: %.2f%n", type, amount);
            total += amount;
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
        sc.close();
    }
}
