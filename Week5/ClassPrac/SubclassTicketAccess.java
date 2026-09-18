package Week5.ClassPrac;
class MovieTicket {

    protected double ticketPrice;

    public MovieTicket(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}

class PremiumMovieTicket extends MovieTicket {

    public PremiumMovieTicket(double ticketPrice) {
        super(ticketPrice);
    }

    void showPrice() {
        System.out.println(
            "Ticket Price: Rs " + ticketPrice
        );
    }
}

class AccessChecker {

    static String classifyAccess(
            String fieldModifier,
            String accessorContext) {

        if (accessorContext.equals("SAME_CLASS")) {

            return "ALLOWED";
        }

        if (accessorContext.equals("SAME_PACKAGE")) {

            if (fieldModifier.equals("private")) {
                return "DENIED";
            }

            return "ALLOWED";
        }

        if (accessorContext.equals("DIFFERENT_PACKAGE")) {

            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (accessorContext.equals(
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

            if (fieldModifier.equals("protected")
                    || fieldModifier.equals("public")) {

                return "ALLOWED";
            }

            return "DENIED";
        }

        if (accessorContext.equals(
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {

            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        return "DENIED";
    }
}

public class SubclassTicketAccess {

    public static void main(String[] args) {

        System.out.println(
            AccessChecker.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
            )
        );

        System.out.println(
            AccessChecker.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
            )
        );

        PremiumMovieTicket ticket =
            new PremiumMovieTicket(250);

        ticket.showPrice();
    }
}