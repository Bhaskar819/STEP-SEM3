package Week4.Practice;
class Participant {

    String name;
    String teamName;
    boolean registered;

    // Constructor for participants with a team
    public Participant(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.registered = true;
    }

    // Constructor for solo participants
    public Participant(String name) {
        this(name, "Unassigned");
    }

    void printStatus() {
        System.out.println(
            name + " | " +
            teamName + " | Registered: " +
            registered
        );
    }
}

public class HackathonRegistration {

    public static void main(String[] args) {

        String[] names = {
            "Ravi",
            "Meera",
            "Karthik",
            "Divya"
        };

        String[] teamNames = {
            "ByteBusters",
            "",
            "CodeCrafters",
            ""
        };

        Participant[] participants =
            new Participant[names.length];

        for (int i = 0; i < names.length; i++) {

            if (teamNames[i].equals("")) {
                participants[i] =
                    new Participant(names[i]);
            }
            else {
                participants[i] =
                    new Participant(
                        names[i],
                        teamNames[i]
                    );
            }

            participants[i].printStatus();
        }
    }
}