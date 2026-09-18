package Week2.Practice;
import java.util.*;

public class WordFrequency {

    static void printFilteredWordFrequency(String feedback) {

        // Convert to lowercase
        feedback = feedback.toLowerCase();

        // Remove periods and commas
        feedback = feedback.replace(",", "");
        feedback = feedback.replace(".", "");

        // Split using whitespace
        String[] words = feedback.split("\\s+");

        // Stop words
        String[] stopWords = {
            "the", "was", "and", "a", "is", "of", "in"
        };

        HashMap<String, Integer> frequency =
            new HashMap<>();

        for (int i = 0; i < words.length; i++) {

            String word = words[i];

            boolean isStopWord = false;

            // Check stop-word list
            for (int j = 0; j < stopWords.length; j++) {

                if (word.equals(stopWords[j])) {
                    isStopWord = true;
                    break;
                }
            }

            // Skip stop words
            if (isStopWord) {
                continue;
            }

            // Count frequency
            if (frequency.containsKey(word)) {

                frequency.put(
                    word,
                    frequency.get(word) + 1
                );

            }
            else {

                frequency.put(word, 1);
            }
        }

        // Convert HashMap to list
        ArrayList<Map.Entry<String, Integer>> list =
            new ArrayList<>(frequency.entrySet());

        // Sort by frequency descending
        Collections.sort(
            list,
            new Comparator<Map.Entry<String, Integer>>() {

                public int compare(
                    Map.Entry<String, Integer> a,
                    Map.Entry<String, Integer> b) {

                    return b.getValue() - a.getValue();
                }
            }
        );

        // Print result
        for (Map.Entry<String, Integer> entry : list) {

            System.out.println(
                entry.getKey() + ": " +
                entry.getValue()
            );
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter feedback:");

        String feedback = sc.nextLine();

        printFilteredWordFrequency(feedback);

        sc.close();
    }
}