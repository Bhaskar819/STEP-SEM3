package Week6.Practice;
class GymMember {

    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMember(
            String memberId,
            int monthlyFee) {

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }


    public void displayInfo() {

        System.out.print(
            "Standard | Sessions: "
            + sessionsAttended
        );
    }
}


class PremiumMember extends GymMember {

    private String trainerName;

    public PremiumMember(
            String memberId,
            int monthlyFee,
            String trainerName) {

        super(memberId, monthlyFee);

        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }


    @Override
    public void displayInfo() {

        System.out.print(
            "Premium | Trainer: "
            + trainerName
            + " | Sessions: "
            + sessionsAttended
        );
    }
}


public class Problem4 {

    static String batchPrint(
            GymMember[] members) {

        StringBuilder result =
            new StringBuilder();

        for (GymMember member : members) {

            // Polymorphic method call
            member.displayInfo();

            if (member instanceof PremiumMember) {

                PremiumMember premium =
                    (PremiumMember) member;

                result.append(
                    "Premium | Trainer: "
                    + premium.getTrainerName()
                    + " | Sessions: "
                    + premium.getSessionsAttended()
                    + " [Trainer via downcast: "
                    + premium.getTrainerName()
                    + "] | "
                );

            } else {

                result.append(
                    "Standard | Sessions: "
                    + member.getSessionsAttended()
                    + " | "
                );
            }
        }

        return result.toString();
    }


    public static void main(String[] args) {

        GymMember[] members = {

            new GymMember(
                "MEM6",
                1000
            ),

            new PremiumMember(
                "MEM7",
                2000,
                "Coach Riya"
            )
        };


        System.out.println();

        System.out.println(
            batchPrint(members)
        );
    }
}
