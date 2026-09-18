package Week2.Practice;

import java.util.Scanner;

public class WordReversal {

    static String reverseEachWord(String sentence) {

        String[] words = sentence.split(" ");

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {

            StringBuilder reverse = new StringBuilder();

            for (int j = words[i].length() - 1; j >= 0; j--) {
                reverse.append(words[i].charAt(j));
            }

            result.append(reverse);

            // Add space between words
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter sentence: ");
        String sentence = sc.nextLine();

        System.out.println(
            reverseEachWord(sentence)
        );

        sc.close();
    }
}