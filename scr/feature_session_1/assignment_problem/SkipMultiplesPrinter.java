public class SkipMultiplesPrinter {
    public static void printSkippingMultiplesOfThree() {
        for (int i = 1; i <= 20; i++) {
            if (i % 3 == 0) continue;
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        printSkippingMultiplesOfThree();
    }
}