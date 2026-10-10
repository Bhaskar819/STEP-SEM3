package Week8.Practice;
abstract class WashType {
    abstract int getDuration();
    abstract double getCharge();
    abstract String getName();
}

class QuickWash extends WashType {
    int getDuration() {
        return 30;
    }

    double getCharge() {
        return 20.0;
    }

    String getName() {
        return "Quick";
    }
}

class NormalWash extends WashType {
    int getDuration() {
        return 45;
    }

    double getCharge() {
        return 30.0;
    }

    String getName() {
        return "Normal";
    }
}

class HeavyWash extends WashType {
    int getDuration() {
        return 60;
    }

    double getCharge() {
        return 45.0;
    }

    String getName() {
        return "Heavy";
    }
}

class WashingMachine {
    private String machineId;
    private boolean busy;

    WashingMachine(String machineId) {
        this.machineId = machineId;
        this.busy = false;
    }

    public String getMachineId() {
        return machineId;
    }

    public boolean isBusy() {
        return busy;
    }

    public void startWash() {
        busy = true;
    }

    public void completeWash() {
        busy = false;
    }
}

class Student {
    private String name;

    Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    WashCycle(Student student, WashingMachine machine,
              WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public void start() {
        if (machine.isBusy()) {
            System.out.println(machine.getMachineId()
                    + " is currently busy.");
            return;
        }

        machine.startWash();

        System.out.println(washType.getName()
                + " wash started on "
                + machine.getMachineId() + " for "
                + student.getName() + " ("
                + washType.getDuration() + " min). Charge: ₹"
                + String.format("%.2f", washType.getCharge()));
    }

    public void complete() {
        if (machine.isBusy()) {
            machine.completeWash();
            System.out.println(machine.getMachineId()
                    + " cycle completed. Machine is now free.");
        }
    }
}

class HostelLaundryQueue {
    public static void main(String[] args) {
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashCycle c1 = new WashCycle(
                asha, m1, new QuickWash());
        c1.start();

        WashCycle c2 = new WashCycle(
                ravi, m1, new HeavyWash());
        c2.start();

        WashCycle c3 = new WashCycle(
                ravi, m2, new HeavyWash());
        c3.start();

        c1.complete();

        WashCycle c4 = new WashCycle(
                neha, m1, new NormalWash());
        c4.start();
    }
}