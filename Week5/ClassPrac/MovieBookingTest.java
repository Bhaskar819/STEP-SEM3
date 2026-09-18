package Week5.ClassPrac;
class MovieBookingProfile {

    private String name;
    private boolean confirmed;
    private String otp;

    // No-argument constructor
    public MovieBookingProfile() {

        this.name = "";
        this.confirmed = false;
    }

    // Convenience constructor
    public MovieBookingProfile(String name) {

        this();
        this.name = name;
    }

    // JavaBean getter
    public String getName() {

        return name;
    }

    // JavaBean setter
    public void setName(String name) {

        this.name = name;
    }

    // JavaBean getter for boolean
    public boolean isConfirmed() {

        return confirmed;
    }

    // JavaBean setter
    public void setConfirmed(boolean confirmed) {

        this.confirmed = confirmed;
    }

    // OTP is WRITE-ONLY
    public void setOtp(String otp) {

        this.otp = otp;
    }
}

public class MovieBookingTest {

    public static void main(String[] args) {

        // Test name
        MovieBookingProfile p =
            new MovieBookingProfile("Rahul Dev");

        System.out.println(
            p.getName()
        );

        // Test confirmed
        p.setConfirmed(true);

        System.out.println(
            p.isConfirmed()
        );

        // OTP can be set
        p.setOtp("4471");

        System.out.println(
            "OTP has been set."
        );

        /*
         * There is intentionally NO:
         *
         * getOtp()
         *
         * because OTP must be write-only.
         */
    }
}