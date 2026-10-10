package Week8.Practice;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment {
    protected String title;
    protected double maxMarks;
    protected LocalDate dueDate;

    Assignment(String title, double maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    abstract double getPenaltyPerDay();

    public double calculateFinalMarks(double marks, LocalDate submitDate) {
        long lateDays = Math.max(
                0, ChronoUnit.DAYS.between(dueDate, submitDate));

        double penalty = lateDays * maxMarks * getPenaltyPerDay();

        return Math.max(0, Math.min(maxMarks, marks) - penalty);
    }

    public String getTitle() {
        return title;
    }

    public double getMaxMarks() {
        return maxMarks;
    }
}

class CodingAssignment extends Assignment {
    CodingAssignment(String title, double maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    double getPenaltyPerDay() {
        return 0.10;
    }
}

class WrittenAssignment extends Assignment {
    WrittenAssignment(String title, double maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    double getPenaltyPerDay() {
        return 0.20;
    }
}

class Student {
    private String name;

    Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Submission {
    private Student student;
    private Assignment assignment;
    private LocalDate submitDate;
    private boolean submitted;
    private boolean graded;
    private double finalMarks;

    Submission(Student student, Assignment assignment) {
        this.student = student;
        this.assignment = assignment;
        this.submitted = false;
        this.graded = false;
    }

    public void submit(LocalDate date) {
        if (graded) {
            System.out.println("Cannot resubmit: "
                    + assignment.getTitle() + " has already been graded.");
            return;
        }

        if (submitted) {
            System.out.println("Assignment has already been submitted.");
            return;
        }

        submitDate = date;
        submitted = true;

        System.out.println(student.getName()
                + "'s submission for '"
                + assignment.getTitle() + "' received"
                + (date.isAfter(getDueDate()) ? " (late)." : " (on time).")
                + " Status: Submitted.");
    }

    private LocalDate getDueDate() {
        return assignment.dueDate;
    }

    public void grade(double marks) {
        if (!submitted) {
            System.out.println("Cannot grade before submission.");
            return;
        }

        if (graded) {
            System.out.println("Submission has already been graded.");
            return;
        }

        if (marks < 0 || marks > assignment.getMaxMarks()) {
            System.out.println("Invalid marks.");
            return;
        }

        finalMarks = assignment.calculateFinalMarks(marks, submitDate);
        graded = true;

        System.out.printf("%s graded: %.0f/%.0f marks. Status: Graded.%n",
                student.getName(), finalMarks,
                assignment.getMaxMarks());
    }
}

class AssignmentSubmissionPortal {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding = new CodingAssignment(
                "Linked List Lab", 50,
                LocalDate.of(2026, 3, 10));

        Assignment written = new WrittenAssignment(
                "Design Essay", 50,
                LocalDate.of(2026, 3, 12));

        Submission s1 = new Submission(asha, coding);
        Submission s2 = new Submission(ravi, written);

        s1.submit(LocalDate.of(2026, 3, 10));
        s2.submit(LocalDate.of(2026, 3, 14));

        s1.grade(45);
        s2.grade(50);

        s1.submit(LocalDate.of(2026, 3, 11));
    }
}