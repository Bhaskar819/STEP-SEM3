package Week8.ClassPrac;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

abstract class Room {
    protected int roomNumber;
    protected double pricePerNight;

    public Room(int roomNumber, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return getClass().getSimpleName();
    }

    public abstract double calculatePrice(long nights);
}

class StandardRoom extends Room {
    public StandardRoom(int roomNumber) {
        super(roomNumber, 1000);
    }

    @Override
    public double calculatePrice(long nights) {
        return pricePerNight * nights;
    }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(int roomNumber) {
        super(roomNumber, 2000);
    }

    @Override
    public double calculatePrice(long nights) {
        return pricePerNight * nights;
    }
}

class Suite extends Room {
    public Suite(int roomNumber) {
        super(roomNumber, 4000);
    }

    @Override
    public double calculatePrice(long nights) {
        return pricePerNight * nights;
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

class Reservation {
    private Room room;
    private Customer customer;
    private LocalDate start;
    private LocalDate end;
    private boolean active = true;

    public Reservation(Room room, Customer customer,
                       LocalDate start, LocalDate end) {
        this.room = room;
        this.customer = customer;
        this.start = start;
        this.end = end;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public boolean isActive() {
        return active;
    }

    public void cancel() {
        if (!active) {
            System.out.println("Reservation already cancelled.");
            return;
        }

        if (!LocalDate.now().isBefore(start)) {
            System.out.println("Cancellation deadline has passed.");
            return;
        }

        active = false;

        System.out.println("Reservation for "
                + customer.getName() + ", " + room.getType()
                + " " + room.getRoomNumber()
                + " cancelled successfully.");
    }
}

class Hotel {
    private List<Reservation> reservations = new ArrayList<>();

    public boolean isAvailable(Room room, LocalDate start,
                               LocalDate end) {
        if (start == null || end == null || !end.isAfter(start)) {
            return false;
        }

        for (Reservation reservation : reservations) {
            if (reservation.isActive()
                    && reservation.getRoom().getRoomNumber()
                    == room.getRoomNumber()
                    && start.isBefore(reservation.getEnd())
                    && end.isAfter(reservation.getStart())) {
                return false;
            }
        }

        return true;
    }

    public Reservation reserve(Room room, Customer customer,
                               LocalDate start, LocalDate end) {
        if (!isAvailable(room, start, end)) {
            System.out.println("Room is not available for this period.");
            return null;
        }

        Reservation reservation =
                new Reservation(room, customer, start, end);

        reservations.add(reservation);

        long nights = ChronoUnit.DAYS.between(start, end);

        System.out.println("Reservation confirmed for "
                + customer.getName() + ", " + room.getType()
                + " " + room.getRoomNumber() + " ("
                + start + "-" + end + ").");

        System.out.println("Price: "
                + room.calculatePrice(nights));

        return reservation;
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        Customer a = new Customer("Customer A");
        Customer c = new Customer("Customer C");

        Room standard = new StandardRoom(101);
        Room deluxe = new DeluxeRoom(201);

        LocalDate jan1 = LocalDate.of(2027, 1, 1);
        LocalDate jan5 = LocalDate.of(2027, 1, 5);
        LocalDate jan3 = LocalDate.of(2027, 1, 3);
        LocalDate jan7 = LocalDate.of(2027, 1, 7);

        System.out.println("Available: "
                + hotel.isAvailable(standard, jan1, jan5));

        Reservation r = hotel.reserve(standard, a, jan1, jan5);

        hotel.reserve(standard, a, jan3, jan7);

        if (r != null) {
            r.cancel();
        }

        hotel.reserve(
                deluxe,
                c,
                LocalDate.of(2027, 2, 10),
                LocalDate.of(2027, 2, 12)
        );
    }
}