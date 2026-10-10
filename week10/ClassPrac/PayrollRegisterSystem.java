package Week10.ClassPrac;
import java.util.*;

abstract class Staff {
    protected String name;

    Staff(String name) {
        this.name = name;
    }

    abstract double calculatePay();

    abstract String getType();

    String getPayslip() {
        return "Payslip[name=" + name
                + ", type=" + getType()
                + ", pay=" + formatPay(calculatePay()) + "]";
    }

    static String formatPay(double pay) {
        if (pay == (long) pay) {
            return String.valueOf((long) pay);
        }
        return String.valueOf(pay);
    }
}

class FullTime extends Staff {
    private double salary;

    FullTime(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    double calculatePay() {
        return salary;
    }

    String getType() {
        return "FullTime";
    }
}

class PartTime extends Staff {
    private double hours;
    private double rate;

    PartTime(String name, double hours, double rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    double calculatePay() {
        return hours * rate;
    }

    String getType() {
        return "PartTime";
    }
}

class Intern extends Staff {
    private double stipend;

    Intern(String name, double stipend) {
        super(name);
        this.stipend = stipend;
    }

    double calculatePay() {
        return stipend;
    }

    String getType() {
        return "Intern";
    }
}

public class PayrollRegisterSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Staff> staffList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+");

            switch (parts[0]) {
                case "FullTime":
                    staffList.add(
                            new FullTime(
                                    parts[1],
                                    Double.parseDouble(parts[2])
                            )
                    );
                    break;

                case "PartTime":
                    staffList.add(
                            new PartTime(
                                    parts[1],
                                    Double.parseDouble(parts[2]),
                                    Double.parseDouble(parts[3])
                            )
                    );
                    break;

                case "Intern":
                    staffList.add(
                            new Intern(
                                    parts[1],
                                    Double.parseDouble(parts[2])
                            )
                    );
                    break;
            }
        }

        double totalPay = 0;
        Staff topEarner = null;

        for (Staff staff : staffList) {
            System.out.println(staff.getPayslip());

            totalPay += staff.calculatePay();

            if (topEarner == null
                    || staff.calculatePay() > topEarner.calculatePay()) {
                topEarner = staff;
            }
        }

        System.out.println("Total " + Staff.formatPay(totalPay));

        if (topEarner != null) {
            System.out.println("top earner " + topEarner.name);
        }

        sc.close();
    }
}