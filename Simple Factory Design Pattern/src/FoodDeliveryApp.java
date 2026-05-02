import java.util.Scanner;

//You have three create types — BIKE, CAR, SCOOTER. User picks one. System delivers the order.
//Write:
//
//DeliveryPartner interface with deliver(String orderId)
//BikeDelivery, CarDelivery, ScooterDelivery — each prints how it delivers
//Main — takes user input for create type and order ID, creates correct object with new, calls deliver()
public class FoodDeliveryApp {
    static Scanner scanner = new Scanner(System.in);
    static void main() {
        System.out.println("Enter a choice of Delivery (CAR,SCOOTY,BIKE)");
        String choice = scanner.nextLine();
        DeliveryPartner delivery = DeliveryFactory.create(choice);
        delivery.deliver("123");
    }


}


class DeliveryFactory
{
    static DeliveryPartner create(String type)
    {

        return switch (type.toUpperCase())
        {
            case "BIKE" -> new BikeDelivery();
            case "CAR" -> new CarDelivery();
            case "SCOOTY" -> new ScooterDelivery();
            default -> throw new IllegalArgumentException("UNKNOWN");
        };

    }
}


interface DeliveryPartner
{
    void deliver(String orderId);
}

class BikeDelivery implements DeliveryPartner
{

    @Override
    public void deliver(String orderId) {
        System.out.println("Small order via Bike create");
    }
}

class ScooterDelivery implements DeliveryPartner
{

    @Override
    public void deliver(String orderId) {
        System.out.println("Quick order via Scooty create");
    }
}

class CarDelivery implements DeliveryPartner
{

    @Override
    public void deliver(String orderId) {
        System.out.println("Premium order via Car create");
    }
}

