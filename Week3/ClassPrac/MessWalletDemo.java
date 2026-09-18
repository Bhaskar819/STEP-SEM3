package Week3.ClassPrac;
class MessWallet {

    private double balance;

    // Constructor
    public MessWallet(double openingBalance) {

        if (openingBalance < 0) {
            System.out.println(
                "Invalid opening balance. Starting with 0."
            );

            balance = 0;
        }
        else {
            balance = openingBalance;
        }
    }

    // Add money
    public void topUp(double amount) {

        if (amount <= 0) {
            System.out.println(
                "Top-up rejected: amount must be greater than 0"
            );
        }
        else {
            balance += amount;
        }
    }

    // Deduct money
    public void deduct(double amount) {

        if (amount > balance) {
            System.out.println(
                "Deduct rejected: insufficient balance"
            );
        }
        else if (amount <= 0) {
            System.out.println(
                "Deduct rejected: invalid amount"
            );
        }
        else {
            balance -= amount;
        }
    }

    // Read-only access
    public double getBalance() {
        return balance;
    }
}

public class MessWalletDemo {

    public static void main(String[] args) {

        MessWallet wallet = new MessWallet(500);

        wallet.topUp(200);

        System.out.println(
            "Balance after top-up: " +
            wallet.getBalance()
        );

        wallet.deduct(1000);

        System.out.println(
            "Final balance: " +
            wallet.getBalance()
        );
    }
}