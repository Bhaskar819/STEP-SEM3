package Week2.ClassPrac;
import java.util.Scanner;

public class BankTransactionReference {

    static String normalizeReference(String raw) {

        // Remove leading and trailing spaces
        raw = raw.trim();

        // If less than 3 characters, return as it is
        if (raw.length() < 3) {
            return raw;
        }

        // Uppercase only first 3 characters
        String bankCode = raw.substring(0, 3).toUpperCase();

        // Keep remaining characters unchanged
        String remaining = raw.substring(3);

        return bankCode + remaining;
    }

    static String validateAndFormat(String reference) {

        // Check length
        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }

        // Check first 3 characters are letters
        for (int i = 0; i < 3; i++) {

            if (!Character.isLetter(reference.charAt(i))) {

                return "Invalid: bank code must be 3 letters";
            }
        }

        // Check remaining 11 characters are digits
        for (int i = 3; i < 14; i++) {

            if (!Character.isDigit(reference.charAt(i))) {

                return "Invalid: body must contain only digits";
            }
        }

        // Extract parts
        String bankCode = reference.substring(0, 3);

        String date = reference.substring(3, 9);

        String sequence = reference.substring(9, 14);

        // Convert DDMMYY → DD/MM/YY
        String formattedDate =
            date.substring(0, 2) + "/" +
            date.substring(2, 4) + "/" +
            date.substring(4, 6);

        // Build final output
        StringBuilder result = new StringBuilder();

        result.append("[");
        result.append(bankCode);
        result.append("] DATE: ");
        result.append(formattedDate);
        result.append(" | SEQ: ");
        result.append(sequence);

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter transaction reference: ");

        String raw = sc.nextLine();

        // Step 1: Normalize
        String normalized = normalizeReference(raw);

        // Step 2: Validate and format
        String result = validateAndFormat(normalized);

        System.out.println(result);

        sc.close();
    }
}