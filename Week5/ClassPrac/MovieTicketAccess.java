package Week5.ClassPrac;
class MovieTicket {

    private int seatNumber;
    int screenId;              // default
    protected double ticketPrice;
    public String movieTitle;
}

class AccessChecker {

    static String classifyAccess(
            String fieldModifier,
            String accessorContext) {

        // SAME CLASS
        if (accessorContext.equals("SAME_CLASS")) {

            return "ALLOWED";
        }

        // SAME PACKAGE
        if (accessorContext.equals("SAME_PACKAGE")) {

            if (fieldModifier.equals("private")) {
                return "DENIED";
            } else {
                return "ALLOWED";
            }
        }

        // DIFFERENT PACKAGE
        if (accessorContext.equals("DIFFERENT_PACKAGE")) {

            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            } else {
                return "DENIED";
            }
        }

        // SUBCLASS IN DIFFERENT PACKAGE - accessing using own subclass type
        if (accessorContext.equals(
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

            if (fieldModifier.equals("protected")
                    || fieldModifier.equals("public")) {

                return "ALLOWED";
            } else {
                return "DENIED";
            }
        }

        // SUBCLASS IN DIFFERENT PACKAGE - accessing using parent type
        if (accessorContext.equals(
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {

            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            } else {
                return "DENIED";
            }
        }

        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {

        int allowed = 0;
        int denied = 0;

        for (int i = 0; i < attempts.length; i++) {

            String result =
                classifyAccess(attempts[i][0], attempts[i][1]);

            if (result.equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " + allowed +
               " | Denied: " + denied;
    }
}

public class MovieTicketAccess {

    public static void main(String[] args) {

        System.out.println(
            AccessChecker.classifyAccess(
                "private",
                "SAME_CLASS"
            )
        );

        System.out.println(
            AccessChecker.classifyAccess(
                "protected",
                "DIFFERENT_PACKAGE"
            )
        );

        String[][] attempts = {
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
            AccessChecker.summarizeBatch(attempts)
        );

        // Extra Week 5 examples
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
    }
}