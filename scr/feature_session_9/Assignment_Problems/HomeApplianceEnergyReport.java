import java.util.*;

public class HomeApplianceEnergyReport {
    interface SaverMode {
        double saverMultiplier();
    }
    static abstract class Appliance {
        final double hours;
        Appliance(double hours) { this.hours = hours; }
        abstract double powerWatts();
        boolean supportsSaver() { return this instanceof SaverMode; }
        double units(boolean saver) {
            double units = powerWatts() * hours / 1000.0;
            return saver ? units * ((SaverMode) this).saverMultiplier() : units;
        }
        double cost(boolean saver) { return units(saver) * 8.0; }
    }
    static class Fridge extends Appliance {
        Fridge(double h) { super(h); }
        double powerWatts() { return 150; }
    }
    static class AC extends Appliance implements SaverMode {
        AC(double h) { super(h); }
        double powerWatts() { return 1500; }
        public double saverMultiplier() { return 0.75; }
    }
    static class TV extends Appliance {
        TV(double h) { super(h); }
        double powerWatts() { return 100; }
    }
    static class Washer extends Appliance implements SaverMode {
        Washer(double h) { super(h); }
        double powerWatts() { return 500; }
        public double saverMultiplier() { return 0.75; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double totalCost = 0;
        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase(Locale.ROOT);
            double hours = sc.nextDouble();
            String optional = "";
            if (sc.hasNextLine()) {
                // Read any remaining token on this line, if present.
                optional = sc.nextLine().trim();
            }
            boolean saver = optional.equalsIgnoreCase("SAVER");
            Appliance a;
            switch (type) {
                case "FRIDGE": a = new Fridge(hours); break;
                case "AC": a = new AC(hours); break;
                case "TV": a = new TV(hours); break;
                case "WASHER": a = new Washer(hours); break;
                default: throw new IllegalArgumentException("Unknown appliance: " + type);
            }
            if (saver && !a.supportsSaver()) {
                System.out.printf("%s: saver mode not supported%n", type);
                continue;
            }
            double units = a.units(saver), cost = a.cost(saver);
            System.out.printf(Locale.US, "%s: Units=%.2f Cost=%.2f%n", type, units, cost);
            totalCost += cost;
        }
        System.out.printf(Locale.US, "Total Cost: %.2f%n", totalCost);
        sc.close();
    }
}
