package Week6.ClassPrac;

class LibraryMember {

    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(
            String memberId,
            int borrowLimit) {

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {

        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void displayInfo() {

        System.out.print(
            "General | Books: "
            + booksBorrowed
        );
    }
}


class StudentMember extends LibraryMember {

    private String course;

    public StudentMember(
            String memberId,
            int borrowLimit,
            String course) {

        super(memberId, borrowLimit);

        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public void displayInfo() {

        System.out.print(
            "Student | Course: "
            + course
            + " | Books: "
            + booksBorrowed
        );
    }
}


public class Problem4 {

    static String batchPrint(
            LibraryMember[] members) {

        StringBuilder report =
            new StringBuilder();

        for (int i = 0;
             i < members.length;
             i++) {

            // Polymorphism
            members[i].displayInfo();

            // Build report separately
            if (members[i]
                    instanceof StudentMember) {

                StudentMember student =
                    (StudentMember) members[i];

                report.append(
                    "Student | Course: "
                    + student.getCourse()
                    + " | Books: "
                    + student.getBooksBorrowed()
                    + " [Course via downcast: "
                    + student.getCourse()
                    + "] | "
                );

            } else {

                report.append(
                    "General | Books: "
                    + members[i].getBooksBorrowed()
                    + " | "
                );
            }
        }

        return report.toString();
    }


    public static void main(String[] args) {

        LibraryMember[] members = {

            new LibraryMember(
                "LB5",
                3
            ),

            new StudentMember(
                "STU6",
                3,
                "ECE"
            )
        };

        System.out.println();

        System.out.println(
            batchPrint(members)
        );
    }
}