import java.util.*;

class Date {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String date = scn.nextLine();
        String dayRegex = "([0-2][0-9]|3[0-1])";
        String monthRegex = "(0[1-9]|1[0-2])";
        String yearRegex = "(19[0-9]{2}|20[0-9]{2})";
        String separatorRegex = "[-\\/\\.]";
        String dateRegex1 = yearRegex + separatorRegex + monthRegex + separatorRegex + dayRegex;
        String dateRegex2 = dayRegex + separatorRegex + monthRegex + separatorRegex + yearRegex;
        String dateRegex = dateRegex1 + "|" + dateRegex2;
        System.out.println(date.matches(dateRegex) ? "Yes" : "No");
    }
}