package Week8.Practice;
interface MembershipPlan {
    double calculateFee();
    String getPlanName();
}

class MonthlyPlan implements MembershipPlan {
    public double calculateFee() {
        return 1000.0;
    }

    public String getPlanName() {
        return "Monthly";
    }
}

class QuarterlyPlan implements MembershipPlan {
    public double calculateFee() {
        return 3 * 1000.0 * 0.90;
    }

    public String getPlanName() {
        return "Quarterly";
    }
}

class AnnualPlan implements MembershipPlan {
    public double calculateFee() {
        return 12 * 1000.0 * 0.85;
    }

    public String getPlanName() {
        return "Annual";
    }
}

class Member {
    private String name;

    Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Membership {
    enum Status {
        ACTIVE, FROZEN, EXPIRED
    }

    private Member member;
    private MembershipPlan plan;
    private Status status;

    Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.status = Status.ACTIVE;
    }

    public double getFee() {
        return plan.calculateFee();
    }

    public Status getStatus() {
        return status;
    }

    public void checkIn() {
        if (status == Status.ACTIVE) {
            System.out.println(member.getName()
                    + " checked in successfully.");
        } else {
            System.out.println("Check-in denied: "
                    + member.getName() + "'s membership is "
                    + status + ".");
        }
    }

    public void freeze() {
        if (status != Status.ACTIVE) {
            System.out.println("Cannot freeze a "
                    + status + " membership.");
            return;
        }

        status = Status.FROZEN;
        System.out.println(member.getName()
                + "'s membership frozen. Status: " + status + ".");
    }

    public void unfreeze() {
        if (status != Status.FROZEN) {
            System.out.println("Cannot unfreeze a "
                    + status + " membership.");
            return;
        }

        status = Status.ACTIVE;
        System.out.println(member.getName()
                + "'s membership unfrozen. Status: " + status + ".");
    }

    public void expire() {
        status = Status.EXPIRED;
        System.out.println(member.getName()
                + "'s membership expired. Status: " + status + ".");
    }
}

class FitZoneMembershipDesk {
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership m1 = new Membership(asha, new QuarterlyPlan());
        Membership m2 = new Membership(ravi, new MonthlyPlan());

        System.out.printf("Quarterly membership created for Asha. "
                + "Fee: ₹%.2f. Status: %s.%n",
                m1.getFee(), m1.getStatus());

        System.out.printf("Monthly membership created for Ravi. "
                + "Fee: ₹%.2f. Status: %s.%n",
                m2.getFee(), m2.getStatus());

        m1.checkIn();
        m1.freeze();
        m1.checkIn();
        m1.unfreeze();

        m2.expire();
        m2.freeze();
    }
}