package Week8.ClassPrac;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

abstract class Question {
    protected String text;
    protected int marks;

    public Question(String text, int marks) {
        this.text = text;
        this.marks = marks;
    }

    public String getText() {
        return text;
    }

    public int getMarks() {
        return marks;
    }

    public abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {
    private String correctAnswer;

    public MultipleChoiceQuestion(String text, int marks,
                                  String correctAnswer) {
        super(text, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return answer != null
                && answer.trim().equalsIgnoreCase(correctAnswer);
    }
}

class TrueFalseQuestion extends Question {
    private String correctAnswer;

    public TrueFalseQuestion(String text, int marks,
                             String correctAnswer) {
        super(text, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return answer != null
                && answer.trim().equalsIgnoreCase(correctAnswer);
    }
}

class ShortAnswerQuestion extends Question {
    private String correctAnswer;

    public ShortAnswerQuestion(String text, int marks,
                               String correctAnswer) {
        super(text, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return answer != null
                && answer.trim().equalsIgnoreCase(correctAnswer);
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Examination {
    private String title;
    private List<Question> questions = new ArrayList<>();

    public Examination(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }
}

class Attempt {
    private Student student;
    private Examination examination;
    private Map<Question, String> answers = new LinkedHashMap<>();
    private boolean submitted = false;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
    }

    public void start() {
        System.out.println(examination.getTitle()
                + " started by " + student.getName() + ".");
    }

    public void answer(Question question, String answer) {
        if (submitted) {
            System.out.println(
                    "Cannot change answers for a submitted examination.");
            return;
        }

        if (!examination.getQuestions().contains(question)) {
            System.out.println("Question does not belong to this exam.");
            return;
        }

        answers.put(question, answer);
        System.out.println("Answer recorded for "
                + question.getText() + ".");
    }

    public void submit() {
        if (submitted) {
            System.out.println("Examination already submitted.");
            return;
        }

        submitted = true;
        int score = 0;
        int total = 0;

        for (Question question : examination.getQuestions()) {
            total += question.getMarks();
            String answer = answers.get(question);

            if (question.evaluate(answer)) {
                score += question.getMarks();
                System.out.println(question.getText()
                        + ": Correct (" + question.getMarks()
                        + " points).");
            } else {
                System.out.println(question.getText()
                        + ": Incorrect (0 points).");
            }
        }

        System.out.println(examination.getTitle()
                + " submitted by " + student.getName()
                + ". Total score: " + score + "/" + total + ".");
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Student student = new Student("Student 1");

        Examination exam = new Examination("Exam A");

        Question q1 = new MultipleChoiceQuestion(
                "Question 1", 5, "C");

        Question q2 = new TrueFalseQuestion(
                "Question 2", 5, "True");

        exam.addQuestion(q1);
        exam.addQuestion(q2);

        Attempt attempt = new Attempt(student, exam);

        attempt.start();
        attempt.answer(q1, "C");
        attempt.answer(q2, "True");
        attempt.submit();

        attempt.answer(q1, "A");
    }
}