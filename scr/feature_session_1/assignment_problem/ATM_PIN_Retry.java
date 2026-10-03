public class ATM_PIN_Retry {
    public static void atmPinRetry(String correctPin, String[] attempts) {
        int attemptCount = 0;
        boolean success = false;
        while (attemptCount < 3 && attemptCount < attempts.length && !success) {
            if (attempts[attemptCount].equals(correctPin)) {
                success = true;
                System.out.println("PIN accepted");
                break;
            }
            attemptCount++;
        }
        if (!success) System.out.println("Card blocked — too many incorrect attempts");
    }
    public static void main(String[] args) {
        String correctPin = "1234";
        String[] attempts = {"1111", "2222", "1234"};
        atmPinRetry(correctPin, attempts);
    }
}