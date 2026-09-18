package Week4.Practice;
class Payment {

    void pay(double amount) {

        System.out.println(
            "Paid (cash): Rs " + amount
        );
    }
}

class CardPayment extends Payment {

    void payWithProcessingFee(double amount) {

        double total = amount + (amount * 0.02);

        System.out.println(
            "Charged (card, incl. fee): Rs " +
            total
        );
    }
}

public class CanteenPayment {

    static void processTransaction(
        Payment payment,
        double amount
    ) {

        if (payment instanceof CardPayment) {

            CardPayment card =
                (CardPayment) payment;

            card.payWithProcessingFee(amount);
        }
        else {

            payment.pay(amount);
        }
    }

    public static void main(String[] args) {

        // 3 CardPayment + 2 Payment
        Payment[] payments = {
            new CardPayment(),
            new Payment(),
            new CardPayment(),
            new Payment(),
            new CardPayment()
        };

        double[] amounts = {
            100,
            50,
            200,
            75,
            120
        };

        double totalCollected = 0;

        for (int i = 0; i < payments.length; i++) {

            processTransaction(
                payments[i],
                amounts[i]
            );

            // Add actual amount charged
            if (payments[i] instanceof CardPayment) {

                totalCollected +=
                    amounts[i] +
                    (amounts[i] * 0.02);
            }
            else {

                totalCollected += amounts[i];
            }
        }

        System.out.println(
            "Total Collected: Rs " +
            totalCollected
        );
    }
}