import java.time.LocalDate;
import java.util.*;

public class StreamingPlanRenewalReminder {

    interface SubscriptionPlan {
        LocalDate calculateRenewalDate(LocalDate startDate);
    }

    static class BasicPlan implements SubscriptionPlan {
        public LocalDate calculateRenewalDate(LocalDate startDate) {
            return startDate.plusDays(30);
        }
    }

    static class StandardPlan implements SubscriptionPlan {
        public LocalDate calculateRenewalDate(LocalDate startDate) {
            return startDate.plusDays(90);
        }
    }

    static class PremiumPlan implements SubscriptionPlan {
        public LocalDate calculateRenewalDate(LocalDate startDate) {
            return startDate.plusDays(365);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            LocalDate startDate = LocalDate.parse(sc.next());

            SubscriptionPlan plan;

            switch (type) {
                case "BASIC":
                    plan = new BasicPlan();
                    break;
                case "STANDARD":
                    plan = new StandardPlan();
                    break;
                default:
                    plan = new PremiumPlan();
            }

            LocalDate renewalDate = plan.calculateRenewalDate(startDate);

            System.out.printf("%s: %s%n", name, renewalDate);
        }

        sc.close();
    }
}
