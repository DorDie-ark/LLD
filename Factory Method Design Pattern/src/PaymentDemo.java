// 1. Problem  -> Simple Factory violates OCP — editing factory for every new type
// 2. Approach -> Created IPaymentFactory interface with create() method
//                Each payment type has its own factory — no if/else in factories
//                Client gets IPaymentType via factory — depends on abstraction (SOLID D)
// 3. Weakness -> OCP still violated in resolve() — adding new type requires
//                editing the switch in client code
//                In Spring, @Autowired eliminates resolve() entirely

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
        IPaymentFactory paymentFactory = resolve(Payment.valueOf(input));
        IPaymentType paymentType = paymentFactory.create();
        System.out.println("Amount Paid is : " + paymentType.pay(amount));

    }

    static IPaymentFactory resolve(Payment payment) {
        return switch (payment) {
            case CARD -> new CardPaymentFactory();
            case UPI -> new UPIPaymentFactory();
            case CASH -> new CashPaymentFactory();

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

class CardPayment implements IPaymentType {
    @Override
    public double pay(int amount) {
        return amount * 0.02 + amount;
    }
}

class UPIPayment implements IPaymentType {

    @Override
    public double pay(int amount) {
        return amount;
    }
}

interface IPaymentFactory {
    IPaymentType create();
}

class CardPaymentFactory implements IPaymentFactory {
    @Override
    public IPaymentType create() {
        return new CardPayment();
    }
}

class UPIPaymentFactory implements IPaymentFactory {
    @Override
    public IPaymentType create() {
        return new UPIPayment();
    }
}

class CashPaymentFactory implements IPaymentFactory {
    @Override
    public IPaymentType create() {
        return new CashPayment();
    }
}



