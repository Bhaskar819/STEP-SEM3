package Week8.Practice;
import java.util.*;

abstract class Seat {
    protected String seatId;

    Seat(String seatId) {
        this.seatId = seatId;
    }

    public String getSeatId() {
        return seatId;
    }

    abstract double getPrice();
}

class RegularSeat extends Seat {
    RegularSeat(String seatId) {
        super(seatId);
    }

    double getPrice() {
        return 150.0;
    }
}

class PremiumSeat extends Seat {
    PremiumSeat(String seatId) {
        super(seatId);
    }

    double getPrice() {
        return 250.0;
    }
}

class ReclinerSeat extends Seat {
    ReclinerSeat(String seatId) {
        super(seatId);
    }

    double getPrice() {
        return 400.0;
    }
}

class Customer {
    private String name;

    Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class MovieShow {
    private String showTime;
    private Map<String, Seat> seats = new LinkedHashMap<>();
    private Set<String> bookedSeats = new HashSet<>();
    private boolean started = false;

    MovieShow(String showTime) {
        this.showTime = showTime;
    }

    public void addSeat(Seat seat) {
        seats.put(seat.getSeatId(), seat);
    }

    public boolean isAvailable(String seatId) {
        return seats.containsKey(seatId)
                && !bookedSeats.contains(seatId);
    }

    public Seat getSeat(String seatId) {
        return seats.get(seatId);
    }

    public boolean bookSeat(String seatId) {
        if (!isAvailable(seatId)) {
            return false;
        }

        bookedSeats.add(seatId);
        return true;
    }

    public void releaseSeats(List<String> seatIds) {
        bookedSeats.removeAll(seatIds);
    }

    public void startShow() {
        started = true;
    }

    public boolean hasStarted() {
        return started;
    }

    public String getShowTime() {
        return showTime;
    }
}

class Booking {
    private Customer customer;
    private MovieShow show;
    private List<String> seatIds = new ArrayList<>();
    private boolean cancelled = false;

    Booking(Customer customer, MovieShow show) {
        this.customer = customer;
        this.show = show;
    }

    public boolean book(List<String> requestedSeats) {
        if (cancelled || show.hasStarted()) {
            System.out.println("Booking cannot be made.");
            return false;
        }

        if (requestedSeats.isEmpty() || requestedSeats.size() > 6) {
            System.out.println("Select between 1 and 6 seats.");
            return false;
        }

        if (new HashSet<>(requestedSeats).size()
                != requestedSeats.size()) {
            System.out.println("Duplicate seats are not allowed.");
            return false;
        }

        for (String id : requestedSeats) {
            if (!show.isAvailable(id)) {
                System.out.println("Seat " + id
                        + " is already booked or unavailable.");
                return false;
            }
        }

        for (String id : requestedSeats) {
            show.bookSeat(id);
            seatIds.add(id);
        }

        System.out.printf("Booking confirmed for %s: %s. Total: ₹%.2f%n",
                customer.getName(), String.join(", ", seatIds),
                getTotal());
        return true;
    }

    public double getTotal() {
        double total = 0;

        for (String id : seatIds) {
            total += show.getSeat(id).getPrice();
        }

        return total;
    }

    public void cancel() {
        if (cancelled) {
            System.out.println("Booking already cancelled.");
            return;
        }

        if (show.hasStarted()) {
            System.out.println("Cancellation not allowed after show starts.");
            return;
        }

        show.releaseSeats(seatIds);
        cancelled = true;

        System.out.println("Booking cancelled for "
                + customer.getName() + ". Seats released.");
    }
}

class CampusPremiereTicketCounter {
    public static void main(String[] args) {
        MovieShow show = new MovieShow("7 PM");

        show.addSeat(new RegularSeat("A1"));
        show.addSeat(new RegularSeat("A2"));
        show.addSeat(new PremiumSeat("F5"));
        show.addSeat(new ReclinerSeat("R1"));

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking b1 = new Booking(asha, show);
        b1.book(Arrays.asList("A1", "A2", "F5"));

        Booking b2 = new Booking(ravi, show);
        b2.book(Arrays.asList("A2"));

        Booking b3 = new Booking(ravi, show);
        b3.book(Arrays.asList("R1"));

        b1.cancel();

        Booking b4 = new Booking(neha, show);
        b4.book(Arrays.asList("A2"));
    }
}