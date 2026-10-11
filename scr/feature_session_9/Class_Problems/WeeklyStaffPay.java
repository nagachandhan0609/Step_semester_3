import java.util.*;

public class WeeklyStaffPay {
    static abstract class Staff {
        final String name;
        Staff(String name) { this.name = name; }
        abstract double pay();
    }
    static class FullTime extends Staff {
        final double weeklySalary;
        FullTime(String n, double s) { super(n); weeklySalary = s; }
        double pay() { return weeklySalary; }
    }
    static class Hourly extends Staff {
        final double hours, rate;
        Hourly(String n, double h, double r) { super(n); hours = h; rate = r; }
        double pay() { return Math.min(hours, 40) * rate + Math.max(0, hours - 40) * rate * 1.5; }
    }
    static class Intern extends Staff {
        final double stipend;
        Intern(String n, double s) { super(n); stipend = s; }
        double pay() { return stipend; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;
        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase(Locale.ROOT);
            String name = sc.next();
            Staff staff;
            switch (type) {
                case "FULLTIME": staff = new FullTime(name, sc.nextDouble()); break;
                case "HOURLY": staff = new Hourly(name, sc.nextDouble(), sc.nextDouble()); break;
                case "INTERN": staff = new Intern(name, sc.nextDouble()); break;
                default: throw new IllegalArgumentException("Unknown staff type: " + type);
            }
            double pay = staff.pay();
            System.out.printf(Locale.US, "%s: %.2f%n", staff.name, pay);
            total += pay;
        }
        System.out.printf(Locale.US, "Total Payroll: %.2f%n", total);
        sc.close();
    }
}
