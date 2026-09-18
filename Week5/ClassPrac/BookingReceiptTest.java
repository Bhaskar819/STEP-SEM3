package Week5.ClassPrac;
class BookingReceipt {

    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(
            String bookingId,
            String[] seatNumbers) {

        this.bookingId = bookingId;

        // Defensive copy IN
        this.seatNumbers =
            seatNumbers.clone();
    }

    public String[] getSeatNumbers() {

        // Defensive copy OUT
        return seatNumbers.clone();
    }

    public BookingReceipt withUpdatedSeat(
            int index,
            String newSeat) {

        String[] newSeats =
            seatNumbers.clone();

        newSeats[index] = newSeat;

        return new BookingReceipt(
            bookingId,
            newSeats
        );
    }

    public String getBookingId() {

        return bookingId;
    }
}

final class GroupBookingReceipt
        extends BookingReceipt {

    private final int groupSize;

    public GroupBookingReceipt(
            String bookingId,
            String[] seatNumbers,
            int groupSize) {

        super(bookingId, seatNumbers);

        this.groupSize = groupSize;
    }

    public int getGroupSize() {

        return groupSize;
    }
}

class SettlementProcessor {

    static String processNightlySettlement(
            BookingReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (int i = 0; i < receipts.length; i++) {

            if (receipts[i] == null) {

                nullSkipped++;

                continue;
            }

            processed++;

            if (receipts[i]
                    instanceof GroupBookingReceipt) {

                groupCount++;
            }
            else {

                individualCount++;
            }
        }

        return processed +
               " processed | " +
               nullSkipped +
               " null skipped | " +
               groupCount +
               " group | " +
               individualCount +
               " individual";
    }
}

public class BookingReceiptTest {

    public static void main(String[] args) {

        // --------------------------------
        // Test defensive copying
        // --------------------------------

        BookingReceipt b =
            new BookingReceipt(
                "CH-1001",
                new String[]{"A1", "A2"}
            );

        String[] seats =
            b.getSeatNumbers();

        seats[0] = "X";

        System.out.println(
            b.getSeatNumbers()[0]
        );

        // --------------------------------
        // Test withUpdatedSeat()
        // --------------------------------

        BookingReceipt updated =
            b.withUpdatedSeat(
                1,
                "A3"
            );

        System.out.println(
            java.util.Arrays.toString(
                b.getSeatNumbers()
            )
        );

        System.out.println(
            java.util.Arrays.toString(
                updated.getSeatNumbers()
            )
        );

        // --------------------------------
        // Test nightly settlement
        // --------------------------------

        BookingReceipt[] receipts = {

            new GroupBookingReceipt(
                "CH-2002",
                new String[]{"B1", "B2"},
                2
            ),

            null,

            new BookingReceipt(
                "CH-3003",
                new String[]{"C1"}
            )
        };

        System.out.println(
            SettlementProcessor
                .processNightlySettlement(
                    receipts
                )
        );
    }
}