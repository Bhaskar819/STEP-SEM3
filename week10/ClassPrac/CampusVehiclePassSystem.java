package Week10.ClassPrac;
import java.util.*;

interface Chargeable {
    void bookCharging();
}

abstract class Vehicle {
    protected String passNumber;
    protected String owner;

    Vehicle(String passNumber, String owner) {
        this.passNumber = passNumber;
        this.owner = owner;
    }

    abstract int getPassFee();

    String getType() {
        return getClass().getSimpleName();
    }
}

class Bike extends Vehicle {
    Bike(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 300;
    }
}

class Car extends Vehicle {
    Car(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 1000;
    }
}

class EBike extends Vehicle implements Chargeable {
    EBike(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 300;
    }

    public void bookCharging() {
        System.out.println(passNumber + " charging bay allotted");
    }
}

class ECar extends Vehicle implements Chargeable {
    ECar(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 1000;
    }

    public void bookCharging() {
        System.out.println(passNumber + " charging bay allotted");
    }
}

public class CampusVehiclePassSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new LinkedHashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+");

            if (parts[0].equalsIgnoreCase("PASS")) {
                String type = parts[1];
                String pass = parts[2];
                String owner = parts[3];

                Vehicle vehicle = null;

                switch (type.toUpperCase()) {
                    case "BIKE":
                        vehicle = new Bike(pass, owner);
                        break;
                    case "CAR":
                        vehicle = new Car(pass, owner);
                        break;
                    case "EBIKE":
                    case "E-BIKE":
                        vehicle = new EBike(pass, owner);
                        break;
                    case "ECAR":
                    case "E-CAR":
                        vehicle = new ECar(pass, owner);
                        break;
                }

                if (vehicle != null) {
                    vehicles.put(pass, vehicle);
                    System.out.println(pass + " (" + type + ") pass fee "
                            + vehicle.getPassFee());
                }
            } else if (parts[0].equalsIgnoreCase("CHARGE")) {
                String pass = parts[1];
                Vehicle vehicle = vehicles.get(pass);

                if (vehicle instanceof Chargeable) {
                    ((Chargeable) vehicle).bookCharging();
                } else if (vehicle != null) {
                    System.out.println(pass
                            + " rejected: charging unsupported");
                } else {
                    System.out.println("Vehicle not found");
                }
            }
        }

        sc.close();
    }
}