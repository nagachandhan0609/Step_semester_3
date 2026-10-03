import java.util.*;

public class PublicTransportFareCalculator {

    interface Transport {
        double calculateFare();
    }

    static class Bus implements Transport {
        private final double distance;

        Bus(double distance) {
            this.distance = distance;
        }

        public double calculateFare() {
            return Math.min(2 + 0.10 * distance, 10);
        }
    }

    static class Train implements Transport {
        private final double distance;

        Train(double distance) {
            this.distance = distance;
        }

        public double calculateFare() {
            return 3 + 0.15 * distance;
        }
    }

    static class Metro implements Transport {
        private final double distance, peakHourFactor;

        Metro(double distance, double peakHourFactor) {
            this.distance = distance;
            this.peakHourFactor = peakHourFactor;
        }

        public double calculateFare() {
            return (1.50 + 0.20 * distance) * peakHourFactor;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double distance = sc.nextDouble();

            Transport transport;

            switch (type) {
                case "BUS":
                    transport = new Bus(distance);
                    break;
                case "TRAIN":
                    transport = new Train(distance);
                    break;
                default:
                    double factor = sc.nextDouble();
                    transport = new Metro(distance, factor);
            }

            double fare = transport.calculateFare();
            total += fare;

            System.out.printf("%s: %.2f%n", type, fare);
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
