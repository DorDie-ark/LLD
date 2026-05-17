// 1. Problem  -> Adding a new provider requires modifying all existing concrete classes
//                (CashPayment, CardPayment, UPIPayment) with if/else — becomes hectic

// 2. Approach -> Create IPaymentFactory with createCard/Cash/UPI methods
//                Each provider (Paytm, PhonePe) MUST implement ALL payment types
//                Enforced by interface — no provider can skip a payment type

// 3. Structure -> ICardPayment, ICashPayment, IUPIPayment — separate product interfaces
//                 PaytmCardPayment, PhonePayCardPayment etc — provider concrete classes
//                 IPaymentFactory — abstract factory with all create methods
//                 PaytmFactory, PhonePayFactory — implement all three create methods

// 4. Weakness -> Adding a NEW payment type (e.g. WALLET) breaks OCP
//                Must add createWalletPayment() to IPaymentFactory
//                Every existing factory (Paytm, PhonePe) must implement it
//                Product types must be stable — providers can vary freely
import java.util.Scanner;

enum Provider
{
    PAYTM,PHONEPAY
}
enum PaymentType
{
    CARD,CASH,UPI
}

public class PaymentDemo {
    static Scanner scanner = new Scanner(System.in);
    void main()
    {
        System.out.println("Choose the Provider First (PAYTM,PHONEPAY)");
        String choice = scanner.nextLine().toUpperCase();
        System.out.println("Choose the PaymentType (CARD,CASH,UPI)");
        PaymentType paymentType = PaymentType.valueOf(scanner.nextLine().toUpperCase());
        System.out.println("Choose Amount");
        int amount = scanner.nextInt();
        scanner.nextLine();
        Provider provider = Provider.valueOf(choice);
        IPaymentFactory paymentFactory = resolve(provider);
        switch(paymentType) {
            case CASH -> {
                ICashPayment cash = paymentFactory.createCashPayment();
                System.out.println("Amount paid is : " + cash.payByCash(amount));
            }
            case CARD -> {
                ICardPayment card = paymentFactory.createCardPayment();
                System.out.println("Amount paid is : " + card.payByCard(amount));
            }
            case UPI -> {
                IUPIPayment upi = paymentFactory.createUpiPayment();
                System.out.println("Amount paid is : " + upi.payByUPI(amount));
            }
        }

    }

    static IPaymentFactory resolve(Provider provider)
    {
        return switch (provider)
        {
            case PHONEPAY -> new PhonePayFactory();
            case PAYTM -> new PaytmFactory();
        };

    }

}

interface IPaymentFactory
{
    ICashPayment createCashPayment();
    IUPIPayment createUpiPayment();
    ICardPayment createCardPayment();
}

class PhonePayFactory implements IPaymentFactory
{

    @Override
    public ICashPayment createCashPayment() {
        return new PhonePayCashPayment();
    }

    @Override
    public IUPIPayment createUpiPayment() {
        return new PhonePayUPIPayment();
    }

    @Override
    public ICardPayment createCardPayment() {
        return new PhonePayCardPayment();
    }
}

class PaytmFactory implements IPaymentFactory
{

    @Override
    public ICashPayment createCashPayment() {
        return new PaytmCashPayment();
    }

    @Override
    public IUPIPayment createUpiPayment() {
        return new PaytmUPIPayment();
    }

    @Override
    public ICardPayment createCardPayment() {
        return new PaytmCardPayment();
    }
}


interface ICardPayment
{
    double payByCard(int amount);
}
interface ICashPayment
{
    double payByCash(int amount);
}
interface IUPIPayment
{
    double payByUPI(int amount);
}

class PhonePayCardPayment implements ICardPayment
{
    @Override
    public double payByCard(int amount) {
        return amount * 0.02 + amount;
    }
}
class PhonePayCashPayment implements ICashPayment
{
    @Override
    public double payByCash(int amount) {
        return amount * 0.1 + amount;
    }
}
class PhonePayUPIPayment implements IUPIPayment
{
    @Override
    public double payByUPI(int amount) {
        return amount;
    }
}

class PaytmCardPayment implements ICardPayment
{
    @Override
    public double payByCard(int amount) {
        return amount * 0.03 + amount;
    }
}
class PaytmCashPayment implements ICashPayment
{
    @Override
    public double payByCash(int amount) {
        return amount * 0.1 + amount + 50;
    }
}
class PaytmUPIPayment implements IUPIPayment
{
    @Override
    public double payByUPI(int amount) {
        return amount+(0.05*amount);
    }
}
