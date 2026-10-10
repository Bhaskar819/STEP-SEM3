package Week10.ClassPrac;
import java.util.*;

public class ClassMarksGridAnalysis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> names = new ArrayList<>();
        List<int[]> marksList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            line = line.replaceAll("[\\[\\],]", " ");
            String[] parts = line.trim().split("\\s+");

            if (parts.length < 4) {
                continue;
            }

            String name = parts[0];
            int[] marks = new int[3];

            for (int i = 0; i < 3; i++) {
                marks[i] = Integer.parseInt(parts[i + 1]);
            }

            names.add(name);
            marksList.add(marks);
        }

        int n = names.size();

        if (n == 0) {
            sc.close();
            return;
        }

        int[] totals = new int[n];
        double[] subjectTotals = new double[3];

        int topperIndex = 0;

        for (int i = 0; i < n; i++) {
            int[] marks = marksList.get(i);

            for (int j = 0; j < 3; j++) {
                totals[i] += marks[j];
                subjectTotals[j] += marks[j];
            }

            if (totals[i] > totals[topperIndex]) {
                topperIndex = i;
            }
        }

        System.out.print("Totals ");

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                System.out.print(", ");
            }

            System.out.print(names.get(i) + " " + totals[i]);
        }

        System.out.println();

        System.out.printf(
                Locale.US,
                "averages %.2f, %.2f, %.2f%n",
                subjectTotals[0] / n,
                subjectTotals[1] / n,
                subjectTotals[2] / n
        );

        System.out.println(
                "topper " + names.get(topperIndex)
                + " (" + totals[topperIndex] + ")"
        );

        sc.close();
    }
}