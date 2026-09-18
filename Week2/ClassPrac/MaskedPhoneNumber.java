package Week2.ClassPrac;

import java.util.Scanner;

public class MaskedPhoneNumber {

    static String maskPhoneNumber(String phone) {

        // Check length
        if (phone.length() != 10) {
            return "Invalid phone number";
        }

        // Check that every character is a digit
        for (int i = 0; i < phone.length(); i++) {

            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        String lastFour = phone.substring(6);

        StringBuilder result = new StringBuilder();

        result.append("XXXXXX");
        result.append(lastFour);

        // Insert '-' between mask and last 4 digits
        result.insert(6, "-");

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        System.out.println(
            maskPhoneNumber(phone)
        );

        sc.close();
    }
}