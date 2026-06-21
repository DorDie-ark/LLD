public class TradingTerminalWithCommand {
    static void main() {
        TradingEngine tradingEngine = new TradingEngine();
        TradingTerminal tradingTerminal = new TradingTerminal(tradingEngine);

        tradingTerminal.placeOrderButtonClick("ORD1","TATA",100,1000);
        tradingTerminal.placeOrderButtonClick("ORD2","TATA",100,500);
        tradingTerminal.undoLastAction();
        tradingTerminal.modifyOrderButtonClick("ORD1",20000,1000);
        tradingTerminal.undoLastAction();

    }
}


// INVOKER
class TradingTerminal
{
    private final TradingEngine tradingEngine;
    private Command lastCommand;

    public TradingTerminal(TradingEngine tradingEngine) {
        this.tradingEngine = tradingEngine;

    }

    public void placeOrderButtonClick(String orderId, String symbol, int qty, long price)
    {
        PlaceOrderCommand placeOrderCommand = new PlaceOrderCommand(orderId,tradingEngine,symbol,qty,price);
        placeOrderCommand.execute();
        lastCommand=placeOrderCommand;
    }

    public void modifyOrderButtonClick(String orderId,int newPrice,int oldPrice)
    {
        ModifyOrderCommand modifyOrderCommand = new ModifyOrderCommand(tradingEngine,orderId,newPrice,oldPrice);
        modifyOrderCommand.execute();
        lastCommand=modifyOrderCommand;
    }

    public void cancelOrderButtonClick(String symbol,int qty,long price,String orderId)
    {
        CancelOrderCommand cancelOrderCommand = new CancelOrderCommand(tradingEngine,symbol,qty,price,orderId);
        cancelOrderCommand.execute();
        lastCommand=cancelOrderCommand;
    }

    public void undoLastAction()
    {
        if(lastCommand!=null)
        {
            lastCommand.undo();
        }
    }



}

interface Command {
    void execute();
    void undo();
}

class PlaceOrderCommand implements Command {
    private final String orderId;
    private final String symbol;
    private final int qty;
    private final long price;
    private final TradingEngine tradingEngine;

    public PlaceOrderCommand(String orderId, TradingEngine tradingEngine, String symbol, int qty, long price) {
        this.orderId = orderId;
        this.tradingEngine = tradingEngine;
        this.symbol = symbol;
        this.qty = qty;
        this.price = price;
    }
    @Override
    public void execute() {
        tradingEngine.placeOrder(symbol,qty,price);
    }

    @Override
    public void undo() {
        tradingEngine.cancelOrder(orderId);

    }
}

class ModifyOrderCommand implements Command {
    private final String orderId;
    private final long newPrice;
    private final long oldPrice;
    private final TradingEngine tradingEngine;

    public ModifyOrderCommand(TradingEngine tradingEngine, String orderId, long newPrice, long oldPrice) {
        this.tradingEngine = tradingEngine;
        this.orderId = orderId;
        this.newPrice = newPrice;
        this.oldPrice = oldPrice;
    }

    @Override
    public void execute() {
        tradingEngine.modifyOrder(orderId, newPrice);
    }

    @Override
    public void undo() {
        tradingEngine.modifyOrder(orderId,oldPrice);

    }
}

class CancelOrderCommand implements Command {
    private final String symbol;
    private final int qty;
    private final long price;
    private final String orderId;
    private final TradingEngine tradingEngine;

    public CancelOrderCommand(TradingEngine tradingEngine,String symbol, int qty, long price, String orderId) {
        this.symbol = symbol;
        this.qty = qty;
        this.price = price;
        this.orderId = orderId;
        this.tradingEngine = tradingEngine;
    }

    @Override
    public void execute() {
        tradingEngine.cancelOrder(orderId);
    }

    @Override
    public void undo() {
        // what order is getting canceled we need to recreate the new order with same details
        tradingEngine.placeOrder(symbol,qty,price);

    }
}

//RECEIVER
class TradingEngine {
    void placeOrder(String symbol,int qty,long price)
    {
        System.out.println("Order placed: " + symbol + " " + qty + " shares @ " + price);

    }
    void cancelOrder(String orderId)
    {
        System.out.println("Order cancelled: " + orderId);

    }
    void modifyOrder(String orderId,long newPrice)
    {
        System.out.println("Order modified: " + orderId + " new price: " + newPrice);
    }
}



