package withoutpattern;

public class TradingTerminalWithoutCommand {
    static void main() {
        TradingEngine tradingEngine = new TradingEngine();
        TradingTerminal tradingTerminal = new TradingTerminal(tradingEngine);

        tradingTerminal.placeOrderButtonClick("RELIANCE", 100, 1500);
        tradingTerminal.cancelOrderButtonClick("ORD123");
        tradingTerminal.modifyOrderButtonClick("ORD124", 1600);
    }
}

class TradingEngine
{
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

class TradingTerminal
{
    private final TradingEngine tradingEngine;

    public TradingTerminal(TradingEngine tradingEngine) {
        this.tradingEngine = tradingEngine;
    }

    void placeOrderButtonClick(String symbol,int qty,long price)
    {
        tradingEngine.placeOrder(symbol,qty,price);
    }

    public void cancelOrderButtonClick(String orderId) {
        tradingEngine.cancelOrder(orderId);
    }

    public void modifyOrderButtonClick(String orderId, long newPrice) {
        tradingEngine.modifyOrder(orderId, newPrice);
    }
}
