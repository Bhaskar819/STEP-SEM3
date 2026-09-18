package Week4.ClassPrac;
class SrmStudent {

    String name;

    static String collegeName;
    static String academicYear;

    // Static block
    static {

        collegeName =
            "SRM Institute of Science and Technology";

        academicYear = "2026-27";

        System.out.println("College info loaded");
    }

    SrmStudent(String name) {

        this.name = name;

        System.out.println(
            "Student record created: " +
            this.name
        );
    }
}

public class CollegeSetup {

    public static void main(String[] args) {

        String[] names = {
            "Ravi",
            "Meera",
            "Karthik",
            "Divya",
            "Anitha"
        };

        SrmStudent[] students =
            new SrmStudent[names.length];

        for (int i = 0; i < names.length; i++) {

            students[i] =
                new SrmStudent(names[i]);
        }
    }
}