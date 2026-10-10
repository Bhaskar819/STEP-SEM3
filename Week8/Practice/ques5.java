package Week8.Practice;
import java.util.*;

interface NotificationChannel {
    String getName();
    void send(Student student, String message);
}

class EmailChannel implements NotificationChannel {
    public String getName() {
        return "Email";
    }

    public void send(Student student, String message) {
        System.out.println("[Email → " + student.getName()
                + "] " + message);
    }
}

class SmsChannel implements NotificationChannel {
    public String getName() {
        return "SMS";
    }

    public void send(Student student, String message) {
        System.out.println("[SMS → " + student.getName()
                + "] " + message);
    }
}

class AppChannel implements NotificationChannel {
    public String getName() {
        return "App";
    }

    public void send(Student student, String message) {
        System.out.println("[App → " + student.getName()
                + "] " + message);
    }
}

class Student {
    private String name;
    private String department;
    private List<NotificationChannel> channels;

    Student(String name, String department,
            List<NotificationChannel> channels) {
        this.name = name;
        this.department = department;
        this.channels = new ArrayList<>(channels);
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public List<NotificationChannel> getChannels() {
        return Collections.unmodifiableList(channels);
    }
}

class Notice {
    private String title;
    private Set<String> departments;

    Notice(String title, Set<String> departments) {
        this.title = title;
        this.departments = new HashSet<>(departments);
    }

    public String getTitle() {
        return title;
    }

    public boolean targets(String department) {
        return departments.contains(department);
    }

    public boolean isValid() {
        return title != null && !title.trim().isEmpty()
                && !departments.isEmpty();
    }
}

class NoticeBoard {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void postNotice(Notice notice) {
        if (!notice.isValid()) {
            System.out.println("Cannot post notice: "
                    + "title and at least one target department "
                    + "are required.");
            return;
        }

        System.out.println("Notice '" + notice.getTitle()
                + "' posted to "
                + String.join(", ", noticeDepartments(notice)) + ".");

        for (Student student : students) {
            if (notice.targets(student.getDepartment())) {
                for (NotificationChannel channel
                        : student.getChannels()) {
                    channel.send(student, notice.getTitle());
                }
            }
        }
    }

    private List<String> noticeDepartments(Notice notice) {
        List<String> result = new ArrayList<>();

        for (Student student : students) {
            if (notice.targets(student.getDepartment())
                    && !result.contains(student.getDepartment())) {
                result.add(student.getDepartment());
            }
        }

        return result;
    }
}

class CampusNoticeBroadcaster {
    public static void main(String[] args) {
        NotificationChannel email = new EmailChannel();
        NotificationChannel sms = new SmsChannel();
        NotificationChannel app = new AppChannel();

        Student asha = new Student("Asha", "CSE",
                Arrays.asList(email, app));

        Student ravi = new Student("Ravi", "ECE",
                Arrays.asList(sms));

        NoticeBoard board = new NoticeBoard();
        board.addStudent(asha);
        board.addStudent(ravi);

        Notice n1 = new Notice("Lab Closed Tomorrow",
                new HashSet<>(Arrays.asList("CSE")));

        Notice n2 = new Notice("Fee Deadline Extended",
                new HashSet<>(Arrays.asList("CSE", "ECE")));

        Notice n3 = new Notice("Sports Day",
                new HashSet<>());

        board.postNotice(n1);
        board.postNotice(n2);
        board.postNotice(n3);
    }
}
