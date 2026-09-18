package Week4.Practice;
class MembershipCard {

    static String libraryName;
    static String validUntil;

    String studentName;

    // Static block
    static {

        libraryName = "SRM Central Library";
        validUntil = "May 2027";

        System.out.println(
            "Library info loaded"
        );
    }

    // Constructor
    MembershipCard(String studentName) {

        this.studentName = studentName;
    }
}

public class LibraryMembership {

    public static void main(String[] args) {

        String[] names = {
            "Ananya",
            "Rohan",
            "Priya",
            "Arjun",
            "Sneha"
        };

        MembershipCard[] cards =
            new MembershipCard[names.length];

        for (int i = 0; i < names.length; i++) {

            cards[i] =
                new MembershipCard(names[i]);

            System.out.println(
                "Membership card issued: " +
                cards[i].studentName
            );
        }
    }
}