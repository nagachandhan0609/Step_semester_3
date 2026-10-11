import java.util.*;

public class LibraryLateFineCounter {
    static abstract class LibraryItem {
        final String title;
        final int daysLate;
        LibraryItem(String title, int daysLate) { this.title = title; this.daysLate = daysLate; }
        abstract double fine();
    }
    static class Book extends LibraryItem {
        Book(String t, int d) { super(t, d); }
        double fine() { return daysLate * 2.0; }
    }
    static class DVD extends LibraryItem {
        DVD(String t, int d) { super(t, d); }
        double fine() { return Math.min(daysLate * 5.0, 50.0); }
    }
    static class Magazine extends LibraryItem {
        Magazine(String t, int d) { super(t, d); }
        double fine() { return daysLate; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;
        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase(Locale.ROOT);
            String title = sc.next();
            int days = sc.nextInt();
            LibraryItem item;
            switch (type) {
                case "BOOK": item = new Book(title, days); break;
                case "DVD": item = new DVD(title, days); break;
                case "MAGAZINE": item = new Magazine(title, days); break;
                default: throw new IllegalArgumentException("Unknown item type: " + type);
            }
            double fine = item.fine();
            System.out.printf(Locale.US, "%s: %.2f%n", item.title, fine);
            total += fine;
        }
        System.out.printf(Locale.US, "Total Fines: %.2f%n", total);
        sc.close();
    }
}
