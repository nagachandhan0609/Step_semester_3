import java.util.*;

public class FestivalBonusCalculator {

    interface Employee {
        double calculateBonus();
        String getName();
    }

    static class FullTimeEmployee implements Employee {
        private final String name;
        private final double salary;

        FullTimeEmployee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        public double calculateBonus() {
            return salary * 0.10;
        }

        public String getName() {
            return name;
        }
    }

    static class PartTimeEmployee implements Employee {
        private final String name;
        private final double salary;

        PartTimeEmployee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        public double calculateBonus() {
            return salary * 0.05;
        }

        public String getName() {
            return name;
        }
    }

    static class Intern implements Employee {
        private final String name;

        Intern(String name, double salary) {
            this.name = name;
        }

        public double calculateBonus() {
            return 2000.0;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            double salary = sc.nextDouble();

            Employee employee;

            switch (type) {
                case "FULLTIME":
                    employee = new FullTimeEmployee(name, salary);
                    break;
                case "PARTTIME":
                    employee = new PartTimeEmployee(name, salary);
                    break;
                default:
                    employee = new Intern(name, salary);
            }

            double bonus = employee.calculateBonus();
            total += bonus;

            System.out.printf("%s: %.2f%n", employee.getName(), bonus);
        }

        System.out.printf("Total Bonus: %.2f%n", total);
        sc.close();
    }
}
