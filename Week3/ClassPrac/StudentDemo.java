package Week3.ClassPrac;

class Student {

    // Instance variables
    String name;
    int attendance;

    // Static variables
    static String collegeName =
        "SRM Institute of Science and Technology";

    static int studentCount = 0;

    // Constructor
    Student(String name, int attendance) {

        this.name = name;
        this.attendance = attendance;

        studentCount++;
    }

    // Static method
    static void printCollegeInfo() {

        System.out.println(
            "College: " + collegeName
        );

        System.out.println(
            "Student Count: " + studentCount
        );
    }
}

public class StudentDemo {

    public static void main(String[] args) {

        Student s1 =
            new Student("Ravi", 90);

        Student s2 =
            new Student("Anitha", 95);

        // Call static method using class name
        Student.printCollegeInfo();
    }
}