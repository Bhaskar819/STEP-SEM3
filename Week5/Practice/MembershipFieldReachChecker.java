package Week5.Practice;
class LibraryMember {

    private String membershipPin;
    String branchCode;              // default
    protected double finesOwed;
    public String displayName;

    public LibraryMember() {
    }
}

class AccessChecker {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

        if (accessorContext.equals("SAME_CLASS")) {
            return "ALLOWED";
        }

        if (accessorContext.equals("SAME_PACKAGE")) {

            if (fieldModifier.equals("private")) {
                return "DENIED";
            } else {
                return "ALLOWED";
            }
        }

        if (accessorContext.equals("DIFFERENT_PACKAGE")) {

            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            } else {
                return "DENIED";
            }
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        int privateAllowed = 0;
        int privateDenied = 0;

        int defaultAllowed = 0;
        int defaultDenied = 0;

        int protectedAllowed = 0;
        int protectedDenied = 0;

        int publicAllowed = 0;
        int publicDenied = 0;

        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            String result =
                    classifyAccess(modifier, context);

            if (modifier.equals("private")) {

                if (result.equals("ALLOWED")) {
                    privateAllowed++;
                } else {
                    privateDenied++;
                }

            } else if (modifier.equals("default")) {

                if (result.equals("ALLOWED")) {
                    defaultAllowed++;
                } else {
                    defaultDenied++;
                }

            } else if (modifier.equals("protected")) {

                if (result.equals("ALLOWED")) {
                    protectedAllowed++;
                } else {
                    protectedDenied++;
                }

            } else if (modifier.equals("public")) {

                if (result.equals("ALLOWED")) {
                    publicAllowed++;
                } else {
                    publicDenied++;
                }
            }
        }

        return "private: " + privateAllowed +
                " allowed / " + privateDenied + " denied | " +

                "default: " + defaultAllowed +
                " allowed / " + defaultDenied + " denied | " +

                "protected: " + protectedAllowed +
                " allowed / " + protectedDenied + " denied | " +

                "public: " + publicAllowed +
                " allowed / " + publicDenied + " denied";
    }
}

public class MembershipFieldReachChecker {

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
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
                AccessChecker.summarizeByModifier(attempts)
        );
    }
}