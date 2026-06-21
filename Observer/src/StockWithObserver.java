import java.util.ArrayList;
import java.util.List;

class Stock
{
    private final String name;
    private long price;
    private List<StockObserver> observerList = new ArrayList<>();

    public Stock(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
        notifyObservers();

    }

    public void notifyObservers()
    {
        for(StockObserver observer: observerList)
        {
            observer.update(this);
        }
    }

    public boolean detachObserver(StockObserver stockObserver)
    {
        return observerList.remove(stockObserver);
    }

    public boolean attachObserver(StockObserver stockObserver)
    {
        return observerList.add(stockObserver);
    }

    public boolean attachObserver(List<StockObserver> stockObservers)
    {
        return observerList.addAll(stockObservers);
    }

}

public class StockWithObserver {
    static void main() {
        Stock stock = new Stock("MRF",2000);
        ComplianceAuditService complianceAuditService = new ComplianceAuditService();
        stock.attachObserver(complianceAuditService);
        PushNotificationService pushNotificationService = new PushNotificationService();
        EmailAlertService emailAlertService = new EmailAlertService();
        stock.attachObserver(new ArrayList<>(List.of(emailAlertService,pushNotificationService)));
        stock.setPrice(20_000);
        System.out.println("======================== ADDED TRADING BOT ===================================");
        TradingBot tradingBot = new TradingBot();
        stock.attachObserver(tradingBot);
        stock.setPrice(18_000);
        System.out.println("======================== DETACHED PUSH NOTIFICATION ==========================");
        stock.detachObserver(pushNotificationService);
        stock.setPrice(19_000);

        // ADDING NEW SMS SERVICE OBSERVER TO STOCK
        SMSService smsService = new SMSService();
        stock.attachObserver(smsService);

        System.out.println("======================== SMS NOTIFICATION SUBSCRIBED ===================================");
        stock.setPrice(50_000);


    }

}

interface StockObserver
{
    void update(Stock stock);
}

class ComplianceAuditService implements StockObserver
{
    @Override
    public void update(Stock stock) {
        System.out.println("Logged the Audit Price for the product with price "+ stock.getPrice());
    }
}

class EmailAlertService implements StockObserver
{
    @Override
    public void update(Stock stock) {
        System.out.println("Email sent successfully with updated price " + stock.getPrice());
    }
}

class PushNotificationService implements StockObserver
{
    @Override
    public void update(Stock stock) {
        System.out.println("The Push Notification is send "+ stock.getPrice());
    }
}

class TradingBot implements StockObserver
{
    @Override
    public void update(Stock stock) {
        System.out.println("Evaluated Price by the bot is "+ stock.getPrice());
    }
}

class SMSService implements StockObserver
{
    @Override
    public void update(Stock stock) {
        System.out.println("The SMS sent is "+ stock.getPrice());
    }
}