package Week10.ClassPrac;
import java.util.*;

class Student {
    String rollNumber;
    String name;

    Student(String rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }
}

public class StudentClubRegistrationManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Student> members = new LinkedHashMap<>();
        List<String> addOutputs = new ArrayList<>();
        List<String> containsOutputs = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+");

            if (parts[0].equalsIgnoreCase("ADD")) {
                String roll = parts[1];
                String name = parts[2];

                if (members.containsKey(roll)) {
                    addOutputs.add("duplicate rejected");
                } else {
                    members.put(roll, new Student(roll, name));
                    addOutputs.add("Added");
                }
            } else if (parts[0].equalsIgnoreCase("CONTAINS")) {
                String roll = parts[1];

                if (members.containsKey(roll)) {
                    containsOutputs.add("contains: true");
                } else {
                    containsOutputs.add("contains: false");
                }
            }
        }

        for (String output : addOutputs) {
            System.out.println(output);
        }

        System.out.println("member count " + members.size());

        for (String output : containsOutputs) {
            System.out.println(output);
        }

        sc.close();
    }
}