package Week5.Practice;
class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(
            String memberId,
            String[] bookIds) {

        this.memberId = memberId;

        // Defensive copy IN
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {

        // Defensive copy OUT
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(
            int index,
            String newId) {

        // Create a copy
        String[] newBookIds =
                bookIds.clone();

        // Change only the new copy
        newBookIds[index] = newId;

        // Return brand-new object
        return new LoanReceipt(
                memberId,
                newBookIds
        );
    }

    public String getMemberId() {

        return memberId;
    }
}


class ReferenceOnlyLoanReceipt
        extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);

        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {

        return roomNumber;
    }
}


class CirculationLedger {

    static String branchCode;

    // Static block runs once
    static {

        branchCode = "PT-001";

        System.out.println(
                "Branch ledger initialized"
        );
    }

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (int i = 0; i < receipts.length; i++) {

            // Null safety
            if (receipts[i] == null) {

                nullSkipped++;

                continue;
            }

            processed++;

            // Check actual object type
            if (receipts[i]
                    instanceof ReferenceOnlyLoanReceipt) {

                referenceOnly++;

            } else {

                regular++;
            }
        }

        return processed +
                " processed | " +
                nullSkipped +
                " null skipped | " +
                referenceOnly +
                " reference-only | " +
                regular +
                " regular";
    }
}


public class LoanReceiptLedger {

    public static void main(String[] args) {

        // --------------------------------
        // Defensive copying test
        // --------------------------------

        LoanReceipt r =
                new LoanReceipt(
                        "LIB-8841",
                        new String[]{
                                "BK-100",
                                "BK-101"
                        }
                );

        String[] ids =
                r.getBookIds();

        ids[0] = "HACKED";

        System.out.println(
                r.getBookIds()[0]
        );


        // --------------------------------
        // With-style correction
        // --------------------------------

        LoanReceipt corrected =
                r.withCorrectedBookId(
                        1,
                        "BK-102"
                );

        System.out.println(
                java.util.Arrays.toString(
                        r.getBookIds()
                )
        );

        System.out.println(
                java.util.Arrays.toString(
                        corrected.getBookIds()
                )
        );


        // --------------------------------
        // Nightly circulation
        // --------------------------------

        LoanReceipt[] receipts = {

                new ReferenceOnlyLoanReceipt(
                        "LIB-001",
                        new String[]{
                                "BK-200"
                        },
                        "Reading Room 3"
                ),

                null,

                new LoanReceipt(
                        "LIB-002",
                        new String[]{
                                "BK-201"
                        }
                )
        };

        System.out.println(
                CirculationLedger
                        .processNightlyCirculation(
                                receipts
                        )
        );
    }
}