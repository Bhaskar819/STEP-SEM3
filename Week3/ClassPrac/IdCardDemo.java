package Week3.ClassPrac;
class IdCard {

    String name;
    int booksIssued;

    IdCard(String name, int booksIssued) {

        this.name = name;
        this.booksIssued = booksIssued;
    }
}

public class IdCardDemo {

    public static void main(String[] args) {

        // First object
        IdCard ravi = new IdCard("Ravi", 0);

        // Second variable points to SAME object
        IdCard duplicate = ravi;

        // Change through duplicate
        duplicate.booksIssued = 3;

        // Value is changed in original object too
        System.out.println(
            "Ravi's booksIssued (via first variable): "
            + ravi.booksIssued
        );

        // Both references point to same object
        System.out.println(
            "duplicate == ravi: "
            + (duplicate == ravi)
        );

        // Create a completely separate object
        IdCard separate = new IdCard("Ravi", 3);

        // Same values, but different objects
        System.out.println(
            "separate == ravi: "
            + (separate == ravi)
        );
    }
}