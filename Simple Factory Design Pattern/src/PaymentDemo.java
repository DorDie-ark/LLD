// 1. Problem  -> Client handling if/else for object creation — violates SRP
// 2. Approach -> Externalize object creation to a separate Factory class
//                Client depends on IPaymentType interface (SOLID D) ✅
//                All implementations substitutable (SOLID L) ✅
// 3. Weakness -> Violates OCP — adding new payment type requires
//                editing PaymentFactory switch — modifying existing code

import java.util.Scanner;

enum Payment {
    CASH, UPI, CARD
}

public class PaymentDemo {
    static Scanner sc = new Scanner(System.in);

    void main() {
        System.out.println("Enter Payment Choice (CASH/UPI/CARD):");
        String input = sc.nextLine().toUpperCase();
        System.out.println("Enter Payable Amount");
        int amount = sc.nextInt();
        sc.nextLine();
        Payment payment = Payment.valueOf(input);
        IPaymentType paymentType = PaymentFactory.create(payment);
        System.out.println("Amount Paid is : " + paymentType.pay(amount));
    }
}

class PaymentFactory {
    static IPaymentType create(Payment choice) {
        return switch (choice) {
            case CASH -> new CashPayment();
            case UPI -> new UPIPayment();
            case CARD -> new CardPayment();
        };
    }
}

interface IPaymentType {
    double pay(int amount);
}

class CashPayment implements IPaymentType {
    @Override
    public double pay(int amount) {
        return amount * 0.1 + amount;
    }
}

class UPIPayment implements IPaymentType {
    @Override
    public double pay(int amount) {
        return amount;
    }
}

class CardPayment implements IPaymentType {

    @Override
    public double pay(int amount) {
        return amount * 0.02 + amount;
    }
}

