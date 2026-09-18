package Week5.Practice;
class LibraryMember {

    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;
}

class AccessChecker {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

        // Same class
        if (accessorContext.equals("SAME_CLASS")) {
            return "ALLOWED";
        }

        // Same package
        if (accessorContext.equals("SAME_PACKAGE")) {

            if (fieldModifier.equals("private")) {
                return "DENIED";
            }

            return "ALLOWED";
        }

        // Different package
        if (accessorContext.equals("DIFFERENT_PACKAGE")) {

            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        // Subclass in different package,
        // accessed using subclass's own type
        if (accessorContext.equals(
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

            if (fieldModifier.equals("protected")
                    || fieldModifier.equals("public")) {

                return "ALLOWED";
            }

            return "DENIED";
        }

        // Subclass in different package,
        // accessed using parent type
        if (accessorContext.equals(
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {

            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        return "DENIED";
    }

    static String firstDeniedAttempt(String[][] attempts) {

        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            String result =
                    classifyAccess(modifier, context);

            if (result.equals("DENIED")) {

                return modifier +
                        " via " +
                        context +
                        " (attempt #" +
                        (i + 1) +
                        ")";
            }
        }

        return "None Denied";
    }
}

public class ReferenceDeskSubclassReach {

    public static void main(String[] args) {

        String[][] attempts = {
                {"public",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},

                {"protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},

                {"protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };

        System.out.println(
                AccessChecker.firstDeniedAttempt(attempts)
        );

        String[][] attempts2 = {
                {"public",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},

                {"protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };

        System.out.println(
                AccessChecker.firstDeniedAttempt(attempts2)
        );
    }
}