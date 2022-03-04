import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        String password = scanner.nextLine();

        String regex = "\\s*(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{12,}\\s*";

        String result = password.matches(regex) ? "YES" : "NO";

        System.out.println(result);

    }
}