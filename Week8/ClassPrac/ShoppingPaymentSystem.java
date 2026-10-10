package Week8.ClassPrac;
import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}

interface PaymentMethod {
    boolean processPayment(double amount);
    String getName();
}

class CreditCardPayment implements PaymentMethod {
    private boolean successful;

    public CreditCardPayment(boolean successful) {
        this.successful = successful;
    }

    @Override
    public boolean processPayment(double amount) {
        return amount > 0 && successful;
    }

    @Override
    public String getName() {
        return "Credit Card";
    }
}

class PayPalPayment implements PaymentMethod {
    private boolean successful;

    public PayPalPayment(boolean successful) {
        this.successful = successful;
    }

    @Override
    public boolean processPayment(double amount) {
        return amount > 0 && successful;
    }

    @Override
    public String getName() {
        return "PayPal";
    }
}

class BankTransferPayment implements PaymentMethod {
    private boolean successful;

    public BankTransferPayment(boolean successful) {
        this.successful = successful;
    }

    @Override
    public boolean processPayment(double amount) {
        return amount > 0 && successful;
    }

    @Override
    public String getName() {
        return "Bank Transfer";
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

class Order {
    private Customer customer;
    private List<OrderItem> items = new ArrayList<>();
    private String status = "Pending";

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void addProduct(Product product, int quantity) {
        if (!status.equals("Pending") || quantity <= 0) {
            System.out.println("Cannot add product.");
            return;
        }

        items.add(new OrderItem(product, quantity));
    }

    public double getTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    public boolean pay(PaymentMethod method) {
        if (!status.equals("Pending")) {
            System.out.println("Order cannot be paid in its current state.");
            return false;
        }

        if (items.isEmpty()) {
            System.out.println(
                    "Cannot process payment for an empty order.");
            return false;
        }

        System.out.println("Payment initiated via "
                + method.getName() + " for Order "
                + customer.getName() + ".");

        boolean success = method.processPayment(getTotal());

        if (success) {
            status = "Paid";
            System.out.println("Payment successful. Order status: "
                    + status + ".");
        } else {
            System.out.println("Payment failed. Order status: "
                    + status + ".");
        }

        return success;
    }

    public String getStatus() {
        return status;
    }
}

public class ShoppingPaymentSystem {
    public static void main(String[] args) {
        Customer x = new Customer("Customer X");
        Customer y = new Customer("Customer Y");
        Customer z = new Customer("Customer Z");

        Product a = new Product("Product A", 100);
        Product b = new Product("Product B", 200);
        Product c = new Product("Product C", 300);

        Order orderX = new Order(x);
        orderX.addProduct(a, 2);
        orderX.addProduct(b, 1);

        System.out.println("Order created for " + x.getName() + ".");
        orderX.pay(new CreditCardPayment(true));

        Order orderY = new Order(y);

        System.out.println("Order created for " + y.getName() + ".");
        orderY.pay(new PayPalPayment(true));

        Order orderZ = new Order(z);
        orderZ.addProduct(c, 1);

        System.out.println("Order created for " + z.getName() + ".");
        orderZ.pay(new PayPalPayment(false));
    }
}