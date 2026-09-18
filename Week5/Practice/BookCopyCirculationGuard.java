package Week5.Practice;
class BookInventory {

    private int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {

        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    public void checkIn() {

        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {

        return copiesAvailable;
    }
}

public class BookCopyCirculationGuard {

    public static void main(String[] args) {

        BookInventory b =
                new BookInventory(3);

        // Checkout
        b.checkOut();
        b.checkOut();
        b.checkOut();

        // 4th checkout - rejected
        b.checkOut();

        System.out.println(
                b.getCopiesAvailable()
        );

        // Check-in
        b.checkIn();
        b.checkIn();
        b.checkIn();

        // 4th check-in - rejected
        b.checkIn();

        System.out.println(
                b.getCopiesAvailable()
        );
    }
}