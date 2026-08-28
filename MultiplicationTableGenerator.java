import java.util.Scanner;

public class MultiplicationTableGenerator {

    static void generateFirstValidTable(int[] candidates) {

        for (int number : candidates) {

            if (number < 1) {
                System.out.println("Skipping invalid number: " + number);
                continue;
            }

            for (int i = 1; i <= 10; i++) {
                System.out.println(number + " x " + i + " = " + (number * i));
            }

            break;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of candidates: ");
        int n = sc.nextInt();

        int[] candidates = new int[n];

        System.out.println("Enter the candidates:");
        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }

        generateFirstValidTable(candidates);

        sc.close();
    }
}