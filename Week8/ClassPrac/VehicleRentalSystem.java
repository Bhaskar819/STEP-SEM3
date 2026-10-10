package Week8.ClassPrac;
import java.util.ArrayList;
import java.util.List;

abstract class Vehicle {
    protected String id;
    protected boolean available = true;

    public Vehicle(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract double calculateCharge(int days);
}

class Sedan extends Vehicle {
    public Sedan(String id) {
        super(id);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 1000.0;
    }
}

class SUV extends Vehicle {
    public SUV(String id) {
        super(id);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 2000.0;
    }
}

class Truck extends Vehicle {
    public Truck(String id) {
        super(id);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 3000.0;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int days;
    private boolean active = true;

    public Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean isActive() {
        return active;
    }

    public void returnVehicle() {
        if (active) {
            vehicle.setAvailable(true);
            active = false;
            System.out.println(vehicle.getId()
                    + " returned by " + customer.getName() + ".");
        } else {
            System.out.println("Vehicle already returned.");
        }
    }
}

class RentalService {
    private List<Rental> rentals = new ArrayList<>();

    public Rental rentVehicle(Vehicle vehicle, Customer customer,
                              int days) {
        if (days <= 0) {
            System.out.println("Invalid rental duration.");
            return null;
        }

        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getId()
                    + " is currently unavailable.");
            return null;
        }

        vehicle.setAvailable(false);

        Rental rental = new Rental(vehicle, customer, days);
        rentals.add(rental);

        System.out.println(vehicle.getId()
                + " rented successfully by " + customer.getName() + ".");
        System.out.println("Rental charge: "
                + vehicle.calculateCharge(days));

        return rental;
    }

    public void returnVehicle(Rental rental) {
        if (rental != null) {
            rental.returnVehicle();
        }
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Vehicle sedan = new Sedan("Sedan A");
        Vehicle suv = new SUV("SUV B");

        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        RentalService service = new RentalService();

        Rental r1 = service.rentVehicle(sedan, c1, 3);

        service.rentVehicle(sedan, c2, 2);

        service.returnVehicle(r1);

        service.rentVehicle(suv, c3, 5);

        // Sedan A is available again after being returned.
        service.rentVehicle(sedan, c2, 2);
    }
}