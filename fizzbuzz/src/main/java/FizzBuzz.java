public class FizzBuzz {
    public static String of(int input) {
        if (isFizz(input) && isBuzz(input)) {
            return "FizzBuzz";
        } else if (isFizz(input)) {
            return "Fizz";
        } else if (isBuzz(input)) {
            return "Buzz";
        }
        return String.valueOf(input);
    }

    private static boolean isBuzz(int input) {
        return input % 5 == 0 || String.valueOf(input).contains("5");
    }

    private static boolean isFizz(int input) {
        return input % 3 == 0 || String.valueOf(input).contains("3");
    }
}
