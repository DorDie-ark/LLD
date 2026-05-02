import java.util.Scanner;

//Now — Release 2 pain.
//Manager says:
//"We need EXPRESS and STANDARD delivery tiers. Express Bike is faster, costs more.
//Standard Bike is cheaper."

public class FoodDeliveryApp {
    static Scanner scanner = new Scanner(System.in);

    static void main() {
        System.out.println("Enter a choice of Delivery (CAR,SCOTTY,BIKE)");
        String choice = scanner.nextLine();
        System.out.println("Enter Type of delivery (EXPRESS,STANDARD)");
        String typeOfDelivery = scanner.nextLine();
        IDeliveryFactory iDeliveryFactory = RentalVehicleFactory.create(typeOfDelivery);
        switch (choice.toUpperCase()) {
            case "CAR" -> {
                ICar car = iDeliveryFactory.createCar();
                car.deliver();
            }
            case "BIKE" -> {
                IBike bike = iDeliveryFactory.createBike();
                bike.deliver();
            }
            case "SCOOTY" -> {
                IScooty scooty = iDeliveryFactory.createScooty();
                scooty.deliver();
            }
            default -> throw new IllegalArgumentException("Unknown: " + choice);
        }


    }


}

class RentalVehicleFactory
{
    static IDeliveryFactory create(String type)
    {
        return switch (type.toUpperCase())
        {
            case "EXPRESS" -> new ExpressVehicleFactory();
            case "STANDARD" -> new StandardVehicleFactory();
            default -> throw new IllegalArgumentException("UNKNOWN INPUT");
        };

    }
}

interface IDeliveryFactory
{
    IBike createBike();
    ICar createCar();
    IScooty createScooty();
}

class ExpressVehicleFactory implements IDeliveryFactory {

    @Override
    public IBike createBike() {
        return new ExpressBikeDelivery();
    }

    @Override
    public ICar createCar() {
        return new ExpressCarDelivery();
    }

    @Override
    public IScooty createScooty() {
        return new ExpressScootyDelivery();
    }
}
class StandardVehicleFactory implements IDeliveryFactory {

    @Override
    public IBike createBike() {
        return new StandardBikeDelivery();
    }

    @Override
    public ICar createCar() {
        return new StandardCarDelivery();
    }

    @Override
    public IScooty createScooty() {
        return new StandardScootyDelivery();
    }
}

interface IBike
{
    void deliver();
}

interface ICar
{
    void deliver();
}

interface IScooty
{
    void deliver();
}

class ExpressBikeDelivery implements IBike
{

    @Override
    public void deliver() {
        System.out.println("Express Bike Delivery for quick Delivery");
    }
}
class ExpressCarDelivery implements ICar
{

    @Override
    public void deliver() {
        System.out.println("Express Car Delivery for premium Delivery");
    }
}
class ExpressScootyDelivery implements IScooty
{

    @Override
    public void deliver() {
        System.out.println("Express Scooty Delivery for safe Delivery");
    }
}

class StandardBikeDelivery implements IBike
{

    @Override
    public void deliver() {
        System.out.println("Standard Bike Delivery");
    }
}
class StandardCarDelivery implements ICar
{

    @Override
    public void deliver() {
        System.out.println("Standard Car Delivery");
    }
}
class StandardScootyDelivery implements IScooty
{

    @Override
    public void deliver() {
        System.out.println("Standard Scooty Delivery");
    }
}


