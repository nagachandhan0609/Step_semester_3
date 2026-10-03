import java.time.LocalDate;
import java.util.*;
import java.util.regex.*;

public class LibraryItemDueDateCalculator {

    interface LibraryItem {
        LocalDate calculateDueDate(LocalDate currentDate);
    }

    static class Book implements LibraryItem {
        public LocalDate calculateDueDate(LocalDate currentDate) {
            return currentDate.plusDays(14);
        }
    }

    static class DVD implements LibraryItem {
        public LocalDate calculateDueDate(LocalDate currentDate) {
            return currentDate.plusDays(7);
        }
    }

    static class Magazine implements LibraryItem {
        public LocalDate calculateDueDate(LocalDate currentDate) {
            return currentDate.plusDays(3);
        }
    }

    private static String removeQuotes(String s) {
        if (s.length() >= 2 && s.startsWith("\"") && s.endsWith("\"")) {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        LocalDate currentDate = LocalDate.of(2023, 10, 26);

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();

            String type = line.substring(0, line.indexOf(' '));
            String title = line.substring(line.indexOf(' ') + 1).trim();
            title = removeQuotes(title);

            LibraryItem item;

            switch (type) {
                case "BOOK":
                    item = new Book();
                    break;
                case "DVD":
                    item = new DVD();
                    break;
                default:
                    item = new Magazine();
            }

            LocalDate dueDate = item.calculateDueDate(currentDate);
            System.out.printf("%s: %s%n", title, dueDate);
        }

        sc.close();
    }
}
