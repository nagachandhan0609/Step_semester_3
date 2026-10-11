import java.util.*;

public class ParcelShippingDesk {
    static abstract class Parcel {
        final double weight, declaredValue;
        Parcel(double weight, double declaredValue) { this.weight = weight; this.declaredValue = declaredValue; }
        abstract double shippingCharge();
        double insurance() { return 0; }
        double total() { return shippingCharge() + insurance(); }
    }
    interface Insurable {
        double insuranceAmount();
    }
    static class Standard extends Parcel {
        Standard(double w, double v) { super(w, v); }
        double shippingCharge() { return 40 + 10 * weight; }
    }
    static class Express extends Parcel implements Insurable {
        Express(double w, double v) { super(w, v); }
        double shippingCharge() { return 80 + 15 * weight; }
        public double insuranceAmount() { return declaredValue * 0.02; }
        double insurance() { return insuranceAmount(); }
    }
    static class Fragile extends Parcel implements Insurable {
        Fragile(double w, double v) { super(w, v); }
        double shippingCharge() { return 40 + 10 * weight + 50; }
        public double insuranceAmount() { return declaredValue * 0.02; }
        double insurance() { return insuranceAmount(); }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double grandTotal = 0;
        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase(Locale.ROOT);
            double weight = sc.nextDouble(), value = sc.nextDouble();
            Parcel p;
            switch (type) {
                case "STANDARD": p = new Standard(weight, value); break;
                case "EXPRESS": p = new Express(weight, value); break;
                case "FRAGILE": p = new Fragile(weight, value); break;
                default: throw new IllegalArgumentException("Unknown parcel type: " + type);
            }
            double charge = p.shippingCharge(), insurance = p.insurance(), total = p.total();
            System.out.printf(Locale.US, "%s: Charge=%.2f Insurance=%.2f Total=%.2f%n",
                    type, charge, insurance, total);
            grandTotal += total;
        }
        System.out.printf(Locale.US, "Grand Total: %.2f%n", grandTotal);
        sc.close();
    }
}
