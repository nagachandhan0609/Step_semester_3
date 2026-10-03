import java.util.*;

public class CampusParkingChargeCalculator {

    interface Vehicle {
        double calculateCharge(int hours);
    }

    static class Bike implements Vehicle {
        public double calculateCharge(int hours) {
            return 10.0 * hours;
        }
    }

    static class Car implements Vehicle {
        public double calculateCharge(int hours) {
            return 30.0 + (20.0 * (hours - 1));
        }
    }

    static class Truck implements Vehicle {
        public double calculateCharge(int hours) {
            return Math.max(100.0, 50.0 * hours);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int hours = sc.nextInt();

            Vehicle vehicle;

            switch (type) {
                case "BIKE":
                    vehicle = new Bike();
                    break;
                case "CAR":
                    vehicle = new Car();
                    break;
                default:
                    vehicle = new Truck();
            }

            double charge = vehicle.calculateCharge(hours);
            total += charge;

            System.out.printf("%s: %.2f%n", type, charge);
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
