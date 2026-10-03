import java.util.*;

public class HostelElectricityBill {

    interface Room {
        double calculateBill();
    }

    static class SingleRoom implements Room {
        private final double units;

        SingleRoom(double units) {
            this.units = units;
        }

        public double calculateBill() {
            return units * 8;
        }
    }

    static class SharedRoom implements Room {
        private final double units;
        private final int occupants;

        SharedRoom(double units, int occupants) {
            this.units = units;
            this.occupants = occupants;
        }

        public double calculateBill() {
            return (units * 6) / occupants;
        }
    }

    static class ACRoom implements Room {
        private final double units;

        ACRoom(double units) {
            this.units = units;
        }

        public double calculateBill() {
            return units * 10 + 200;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double units = sc.nextDouble();

            Room room;

            switch (type) {
                case "SINGLE":
                    room = new SingleRoom(units);
                    break;
                case "SHARED":
                    int occupants = sc.nextInt();
                    room = new SharedRoom(units, occupants);
                    break;
                default:
                    room = new ACRoom(units);
            }

            double bill = room.calculateBill();
            total += bill;

            System.out.printf("%s: %.2f%n", type, bill);
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
