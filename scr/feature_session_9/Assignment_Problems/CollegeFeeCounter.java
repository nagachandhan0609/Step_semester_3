import java.util.*;

public class CollegeFeeCounter {
    static abstract class Student {
        static final double TUITION = 40000, BUS_FEE = 12000;
        final String name;
        Student(String name) { this.name = name; }
        abstract double tuition();
        abstract boolean usesBus();
        double totalFee() { return tuition() + (usesBus() ? BUS_FEE : 0); }
    }
    static class DayScholar extends Student {
        DayScholar(String n) { super(n); }
        double tuition() { return TUITION; }
        boolean usesBus() { return true; }
    }
    static class Hosteller extends Student {
        Hosteller(String n) { super(n); }
        double tuition() { return TUITION + 60000; }
        boolean usesBus() { return false; }
    }
    static class Scholar extends Student {
        Scholar(String n) { super(n); }
        double tuition() { return TUITION / 2; }
        boolean usesBus() { return true; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;
        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase(Locale.ROOT);
            String name = sc.next();
            Student s;
            switch (type) {
                case "DAY_SCHOLAR": s = new DayScholar(name); break;
                case "HOSTELLER": s = new Hosteller(name); break;
                case "SCHOLAR": s = new Scholar(name); break;
                default: throw new IllegalArgumentException("Unknown student type: " + type);
            }
            double fee = s.totalFee();
            System.out.printf(Locale.US, "%s: %.2f%n", s.name, fee);
            total += fee;
        }
        System.out.printf(Locale.US, "Total Collected: %.2f%n", total);
        sc.close();
    }
}
