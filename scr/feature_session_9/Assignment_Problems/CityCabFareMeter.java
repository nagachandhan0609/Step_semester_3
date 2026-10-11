import java.util.*;

public class CityCabFareMeter {
    interface NightService {
        double nightFare(double baseFare);
    }
    static abstract class Cab {
        final double km;
        Cab(double km) { this.km = km; }
        abstract double ratePerKm();
        double baseFare() { return Math.max(100, km * ratePerKm()); }
        boolean hasNightService() { return this instanceof NightService; }
        double fare(boolean night) {
            double fare = baseFare();
            if (night) {
                if (!hasNightService()) throw new IllegalStateException("night service not available");
                fare = ((NightService) this).nightFare(fare);
            }
            return fare;
        }
    }
    static class Mini extends Cab {
        Mini(double km) { super(km); }
        double ratePerKm() { return 10; }
    }
    static class Sedan extends Cab implements NightService {
        Sedan(double km) { super(km); }
        double ratePerKm() { return 14; }
        public double nightFare(double baseFare) { return baseFare * 1.20; }
    }
    static class SUV extends Cab implements NightService {
        SUV(double km) { super(km); }
        double ratePerKm() { return 18; }
        public double nightFare(double baseFare) { return baseFare * 1.20; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;
        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase(Locale.ROOT);
            double km = sc.nextDouble();
            String time = sc.next().toUpperCase(Locale.ROOT);
            Cab cab;
            switch (type) {
                case "MINI": cab = new Mini(km); break;
                case "SEDAN": cab = new Sedan(km); break;
                case "SUV": cab = new SUV(km); break;
                default: throw new IllegalArgumentException("Unknown cab type: " + type);
            }
            if (time.equals("NIGHT") && !cab.hasNightService()) {
                System.out.printf("%s: night service not available%n", type);
            } else {
                double fare = cab.fare(time.equals("NIGHT"));
                System.out.printf(Locale.US, "%s: %.2f%n", type, fare);
                total += fare;
            }
        }
        System.out.printf(Locale.US, "Total: %.2f%n", total);
        sc.close();
    }
}
