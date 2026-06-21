package withoutPattern;

class Stock
{
    private String name;
    private long price;

    private final EmailAlertService emailAlertService;
    private final TradingBot tradingBot;
    private final ComplianceAuditService auditService;
    private final PushNotificationService pushNotificationService;
    // Adding SMS service
    private final SMSService smSservice;

    public Stock(String name, long price, EmailAlertService emailAlertService, TradingBot tradingBot, ComplianceAuditService auditService, PushNotificationService pushNotificationService, SMSService smSservice) {
        this.name=name;
        this.price = price;
        this.emailAlertService = emailAlertService;
        this.tradingBot = tradingBot;
        this.auditService = auditService;
        this.pushNotificationService = pushNotificationService;
        this.smSservice = smSservice;
    }

    public long getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }


    public void setPrice(long price) {
        this.price = price;
        emailAlertService.sendEmail(this);
        tradingBot.evaluateTrade(this);
        auditService.logPriceChange(this);
        pushNotificationService.send(this);
        // Adding SMS service -> violates OCP
        smSservice.sendMessage(this);

    }
}

public class StockWithoutObserver {
    static void main() {
        Stock stock = new Stock("Tata",100,new EmailAlertService(),new TradingBot(),new ComplianceAuditService(),new PushNotificationService(), new SMSService());
        stock.setPrice(1000);


    }
}

class EmailAlertService
{
    public void sendEmail(Stock stock)
    {
        System.out.println("Email sent successfully with updated price " + stock.getPrice());
    }
}

class TradingBot
{
    public void evaluateTrade(Stock stock)
    {
        System.out.println("Evaluated Price from the bot is "+ stock.getPrice());
    }
}

class ComplianceAuditService
{
    public void  logPriceChange(Stock stock)
    {
        System.out.println("Logged the Audit Price for the product with price"+ stock.getPrice());
    }
}

class PushNotificationService
{
    public void send(Stock stock)
    {
        System.out.println("The Updated stock price is send "+ stock.getPrice());

    }
}

// Adding SMS service
class SMSService
{
    public void sendMessage(Stock stock)
    {
        System.out.println("The SMS sent is "+ stock.getPrice());
    }
}





