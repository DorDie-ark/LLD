## Factory Method in Spring

### How it works
- Inject all IPaymentFactory beans into Map<String, IPaymentFactory>
- factories.get(type) resolves the right factory — no if/else
- OCP fully satisfied — adding new type = new class + @Component only

### Spring Controller

```java
@RestController
public class PaymentController {

    @Autowired
    Map<String, IPaymentFactory> factories;

    @PostMapping("/pay")
    public double pay(@RequestParam String type, @RequestParam int amount) {
        IPaymentFactory factory = factories.get(type.toLowerCase() + "PaymentFactory");
        if (factory == null) throw new IllegalArgumentException("Unknown payment type: " + type);
        return factory.create().pay(amount);
    }
}

@Component("cashPaymentFactory")
class CashPaymentFactory implements IPaymentFactory {
    @Override
    public IPaymentType create() { return new CashPayment(); }
}

@Component("upiPaymentFactory")
class UPIPaymentFactory implements IPaymentFactory {
    @Override
    public IPaymentType create() { return new UPIPayment(); }
}

@Component("cardPaymentFactory")
class CardPaymentFactory implements IPaymentFactory {
    @Override
    public IPaymentType create() { return new CardPayment(); }
}
```

### Bean resolution
- "cashPaymentFactory" → CashPaymentFactory
- "upiPaymentFactory"  → UPIPaymentFactory
- "cardPaymentFactory" → CardPaymentFactory

### Why explicit @Component name?
- Spring default: `UPIPaymentFactory` → `"uPIPaymentFactory"` (ugly, unreliable)
- Explicit name: `@Component("upiPaymentFactory")` → predictable, clean ✅

### Adding new type (CRYPTO)
- Add `CryptoPayment` implements `IPaymentType`
- Add `CryptoPaymentFactory` implements `IPaymentFactory` with `@Component("cryptoPaymentFactory")`
- Zero edits to existing code ✅

## Simple Factory vs Factory Method

| | Simple Factory | Factory Method |
|---|---|---|
| Context | Plain Java — small apps, scripts | Spring — enterprise apps |
| Resolution | if/else or switch in factory | `Map<String, IFactory>` via DI |
| OCP | ❌ violated in factory | ✅ fully satisfied in Spring |
| Adding new type | Edit existing factory | New class + `@Component` |

### Real world rule of thumb
- Plain Java → Simple Factory is fine and practical
- Spring → Factory Method + DI is the right approach

### Key insight
Spring beans, @Autowired, @Component = Factory Method pattern in action.
You've been using it at work without knowing it! ✅