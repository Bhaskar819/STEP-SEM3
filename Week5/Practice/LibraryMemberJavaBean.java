package Week5.Practice;
class LibraryMember {

    private String membershipId;
    private String name;
    private boolean premiumMember;

    // Stored transformed security answer
    private String securityAnswer;

    public LibraryMember() {
    }

    // -----------------------------
    // membershipId
    // -----------------------------

    public String getMembershipId() {

        return membershipId;
    }

    public void setMembershipId(String id) {

        // Write-once
        if (membershipId == null) {
            membershipId = id;
        }
    }

    // -----------------------------
    // name
    // -----------------------------

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    // -----------------------------
    // premiumMember
    // -----------------------------

    public boolean isPremiumMember() {

        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {

        this.premiumMember = premium;
    }

    // -----------------------------
    // securityAnswer
    // -----------------------------

    public void setSecurityAnswer(String answer) {

        // Deterministic one-way transformation
        this.securityAnswer =
                Integer.toHexString(answer.hashCode());
    }

    // NO getSecurityAnswer()
}

public class LibraryMemberJavaBean {

    public static void main(String[] args) {

        LibraryMember m =
                new LibraryMember();

        m.setMembershipId("LIB-8841");

        m.setName("Priya Nair");

        m.setPremiumMember(true);

        System.out.println(
                m.getMembershipId()
        );

        // Second attempt is ignored
        m.setMembershipId("FAKE-0000");

        System.out.println(
                m.getMembershipId()
        );

        System.out.println(
                m.isPremiumMember()
        );

        // Security answer can be set
        // but cannot be retrieved.
        m.setSecurityAnswer("BlueMountain");

        System.out.println(
                "Security answer stored."
        );
    }
}