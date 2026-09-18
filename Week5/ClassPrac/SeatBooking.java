package Week5.ClassPrac;
class CineScreen {

    private int seatsTotal;
    private int seatsAvailable;

    public CineScreen(int seatsTotal) {

        if (seatsTotal <= 0) {

            throw new IllegalArgumentException(
                "construction rejected"
            );
        }

        this.seatsTotal = seatsTotal;
        this.seatsAvailable = seatsTotal;
    }

    public void bookSeat() {

        if (seatsAvailable > 0) {
            seatsAvailable--;
        }
    }

    public void cancelBooking() {

        if (seatsAvailable < seatsTotal) {
            seatsAvailable++;
        }
    }

    public int getSeatsAvailable() {

        return seatsAvailable;
    }
}

public class SeatBooking {

    public static void main(String[] args) {

        // Test invalid construction
        try {

            CineScreen invalid =
                new CineScreen(0);

        } catch (IllegalArgumentException e) {

            System.out.println(
                e.getMessage()
            );
        }

        // Test booking
        CineScreen c =
            new CineScreen(2);

        c.bookSeat();
        c.bookSeat();
        c.bookSeat();

        System.out.println(
            c.getSeatsAvailable()
        );

        // Test cancellation
        c.cancelBooking();
        c.cancelBooking();
        c.cancelBooking();

        System.out.println(
            c.getSeatsAvailable()
        );
    }
}