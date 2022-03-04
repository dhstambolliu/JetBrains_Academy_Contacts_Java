import java.util.*;

class BankCard {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String numbers = scn.nextLine();
        String card = numbers.replaceAll("\\s", "");
        String visaRegex = "(?<visa>4[0-9]{12}(?:[0-9]{3})?)";
        String masterCardRegex = "(?<mastercard>5[1-5][0-9]{14}|(2[27]2[10][0-9]{12}))";
        String americanExpressRegex = "(?<amex>3[47][0-9]{13})";

        if (card.matches(visaRegex)) {
            System.out.println("Visa");
        } else if (card.matches(masterCardRegex)) {
            System.out.println("MasterCard");
        } else if (card.matches(americanExpressRegex)) {
            System.out.println("AmericanExpress");
        } else {
            System.out.println("Card number does not exist");
        }
    }
}