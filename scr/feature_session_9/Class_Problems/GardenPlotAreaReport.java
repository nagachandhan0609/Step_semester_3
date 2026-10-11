import java.util.*;

public class GardenPlotAreaReport {
    static abstract class Plot {
        final String owner;
        Plot(String owner) { this.owner = owner; }
        abstract double area();
        abstract String shape();
    }
    static class Circle extends Plot {
        final double radius;
        Circle(String owner, double radius) { super(owner); this.radius = radius; }
        double area() { return Math.PI * radius * radius; }
        String shape() { return "CIRCLE"; }
    }
    static class Rectangle extends Plot {
        final double length, width;
        Rectangle(String owner, double length, double width) {
            super(owner); this.length = length; this.width = width;
        }
        double area() { return length * width; }
        String shape() { return "RECTANGLE"; }
    }
    static class Triangle extends Plot {
        final double base, height;
        Triangle(String owner, double base, double height) { super(owner); this.base = base; this.height = height; }
        double area() { return 0.5 * base * height; }
        String shape() { return "TRIANGLE"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;
        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase(Locale.ROOT);
            String owner = sc.next();
            Plot plot;
            switch (type) {
                case "CIRCLE": plot = new Circle(owner, sc.nextDouble()); break;
                case "RECTANGLE": plot = new Rectangle(owner, sc.nextDouble(), sc.nextDouble()); break;
                case "TRIANGLE": plot = new Triangle(owner, sc.nextDouble(), sc.nextDouble()); break;
                default: throw new IllegalArgumentException("Unknown plot shape: " + type);
            }
            double area = plot.area();
            System.out.printf(Locale.US, "%s (%s): %.2f%n", plot.owner, plot.shape(), area);
            total += area;
        }
        System.out.printf(Locale.US, "Total Area: %.2f%n", total);
        sc.close();
    }
}
