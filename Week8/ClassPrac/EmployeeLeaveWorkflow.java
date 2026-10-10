package Week8.ClassPrac;
abstract class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(int days) {
        return days > 0 && days <= 30;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(int days) {
        return days > 0 && days <= 10;
    }
}

class Contractor extends Employee {
    public Contractor(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(int days) {
        return days > 0 && days <= 5;
    }
}

class LeaveRequest {
    private Employee employee;
    private String startDate;
    private String endDate;
    private int days;
    private String status = "Pending";

    public LeaveRequest(Employee employee, String startDate,
                        String endDate, int days) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
    }

    public String getStatus() {
        return status;
    }

    public void review(boolean approved) {
        if (!status.equals("Pending")) {
            System.out.println("Cannot change leave request status from "
                    + status + " to Pending.");
            return;
        }

        status = approved ? "Approved" : "Rejected";

        System.out.println(employee.getName() + "'s leave request ("
                + startDate + "-" + endDate + ") "
                + status.toLowerCase() + ". Status: " + status + ".");
    }
}

class LeaveService {
    public LeaveRequest submit(Employee employee, String startDate,
                               String endDate, int days) {
        if (days <= 0 || !employee.canTakeLeave(days)) {
            System.out.println("Leave request rejected by policy.");
            return null;
        }

        LeaveRequest request = new LeaveRequest(
                employee, startDate, endDate, days);

        System.out.println("Leave request submitted for "
                + employee.getName() + " (" + startDate + "-"
                + endDate + "). Status: Pending.");

        return request;
    }
}

public class EmployeeLeaveWorkflow {
    public static void main(String[] args) {
        LeaveService service = new LeaveService();

        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest r1 =
                service.submit(john, "Jan 1", "Jan 5", 5);

        LeaveRequest r2 =
                service.submit(jane, "Feb 10", "Feb 11", 2);

        if (r1 != null) {
            r1.review(true);
            r1.review(false);
        }

        if (r2 != null) {
            r2.review(false);
        }
    }
}