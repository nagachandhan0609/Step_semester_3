import java.util.Scanner;
public class ArmstrongNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");
        int number = sc.nextInt();
        int origNumber = number, sum = 0;
        while (number != 0) {
            int digit = number % 10;
            sum = sum + digit * digit * digit;
            number = number / 10;
        }
        System.out.println("Is the number " + origNumber + " an Armstrong number? "
                + (sum == origNumber));
        sc.close();
    }
}