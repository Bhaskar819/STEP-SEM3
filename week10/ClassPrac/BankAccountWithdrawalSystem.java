package Week10.ClassPrac;
import java.util.*;

abstract class BankAccount {
    protected String id;
    protected double balance;

    BankAccount(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    abstract boolean withdraw(double amount);

    double getBalance() {
        return balance;
    }
}

class SavingsAccount extends BankAccount {
    SavingsAccount(String id, double balance) {
        super(id, balance);
    }

    boolean withdraw(double amount) {
        if (balance - amount < 1000) {
            System.out.println(id + " rejected: minimum balance 1000");
            return false;
        }
        balance -= amount;
        System.out.println(id + " balance " + (long) balance);
        return true;
    }
}

class CurrentAccount extends BankAccount {
    CurrentAccount(String id, double balance) {
        super(id, balance);
    }

    boolean withdraw(double amount) {
        if (balance - amount < -5000) {
            System.out.println(id + " rejected: overdraft limit 5000");
            return false;
        }
        balance -= amount;
        System.out.println(id + " balance " + (long) balance);
        return true;
    }
}

public class BankAccountWithdrawalSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, BankAccount> accounts = new LinkedHashMap<>();

        while (sc.hasNext()) {
            String operation = sc.next();

            if (operation.equalsIgnoreCase("Savings")) {
                String id = sc.next();
                double balance = sc.nextDouble();
                accounts.put(id, new SavingsAccount(id, balance));
            } else if (operation.equalsIgnoreCase("Current")) {
                String id = sc.next();
                double balance = sc.nextDouble();
                accounts.put(id, new CurrentAccount(id, balance));
            } else if (operation.equalsIgnoreCase("WITHDRAW")) {
                String id = sc.next();
                double amount = sc.nextDouble();

                BankAccount account = accounts.get(id);

                if (account == null) {
                    System.out.println("Account not found");
                } else {
                    account.withdraw(amount);
                }
            }
        }

        sc.close();
    }
}