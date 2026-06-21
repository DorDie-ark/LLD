# Observer Pattern — Worked Example (Stock Price Alerts)

This documents the actual derivation exercise: build it wrong first, feel the
pain, then fix it — rather than starting from the textbook solution.

## The Scenario

A stock trading platform needs to notify several independent parts of the
system whenever a stock's price changes: an email alert service, a push
notification service, a trading bot (auto stop-loss evaluation), and a
compliance/audit log. Each reacts independently to the same event.

## Step 1 — The Naive Version (`without-pattern/StockWithoutObserver.java`)

`Stock` held a direct field for each service and called each one by name
inside `setPrice()`:

```java
class Stock {
    private final EmailAlertService emailAlertService;
    private final TradingBot tradingBot;
    private final ComplianceAuditService auditService;
    private final PushNotificationService pushNotificationService;

    public void setPrice(long price) {
        this.price = price;
        emailAlertService.sendEmail(this);
        tradingBot.evaluateTrade(this);
        auditService.logPriceChange(this);
        pushNotificationService.send(this);
    }
}
```

It worked. It compiled, ran, all four fired correctly.

## Step 2 — The Test: Add a 5th Channel

Added `SMSService` as a new requirement, without rethinking the design.
**What had to change just to plug it in:**

1. New field `smsService` on `Stock`
2. `Stock`'s constructor signature — new parameter added
3. `setPrice()` — new line added
4. Every call site building a `Stock` — updated to pass the new instance
5. Plus writing the new `SMSService` class itself

**Four edits to code that was already working and already tested**, just to
add one listener. That's the Open/Closed Principle violation, concretely —
`Stock` was not closed for modification.

## Step 3 — The Fix (`src/StockWithObserver.java`)

Extracted an abstraction between `Stock` and its listeners:

```java
interface StockObserver {
    void update(Stock stock);
}

class Stock {
    private List<StockObserver> observerList = new ArrayList<>();

    public Stock(String name, long price) {
        this.name = name;
        this.price = price;
        // no observers known at construction time — that's intentional
    }

    public void setPrice(long price) {
        this.price = price;
        notifyObservers();
    }

    public void notifyObservers() {
        for (StockObserver observer : observerList) {
            observer.update(this);
        }
    }

    public boolean attachObserver(StockObserver o) { return observerList.add(o); }
    public boolean detachObserver(StockObserver o) { return observerList.remove(o); }
}
```

Each service (`EmailAlertService`, `TradingBot`, `ComplianceAuditService`,
`PushNotificationService`) implements `StockObserver` and reacts in its own
`update()`.

## Step 4 — Re-running the Same Test

Added `SMSService implements StockObserver`, then:
```java
SMSService smsService = new SMSService();
stock.attachObserver(smsService);
```
**Zero changes to `Stock.java`.** Only additions: one new class, two lines
at the call site.

## The Comparison

| | Without Observer | With Observer |
|---|---|---|
| Adding a 5th channel required editing | `Stock`'s field list, constructor, `setPrice()`, every call site (4 changes) | Nothing in `Stock.java` |
| New class needed | Yes | Yes (same either way) |
| `Stock` closed for modification? | No | Yes |

## Design Decisions Worth Remembering

- **Why `update()` and not `notify()`:** `Object.notify()` is a `final`,
  no-arg, native method (thread signaling). A no-arg `void notify()` on your
  own class would fail to compile (can't override `final`). A parameterized
  version like `notify(Stock)` would technically compile (it's an overload,
  not an override) — but it's confusing to read, so the GoF convention
  (`update()` on the observer, `notifyObservers()` on the subject) avoids
  the ambiguity entirely.

- **Empty list in the constructor, not an injected one.** `Stock(String,
  long)` only needs a name and price to exist — who's watching it is a
  separate concern, decided later via `attachObserver()`. This also avoids
  a subtle trap: if the constructor took a `List<StockObserver>` and a
  caller passed `List.of(...)`, that list is **immutable** — any later
  `attachObserver()` call (which does `.add()`) would throw
  `UnsupportedOperationException` at runtime, not compile time.

- **Batch attach** (`attachObserver(List<StockObserver>)`) was a useful
  addition but isn't part of the classic GoF `Subject` contract — worth
  knowing the line between "textbook interface" and "reasonable real-world
  extension" if asked in an interview.

- **Dynamic subscription proven, not just claimed** — the demo in `main()`
  attached observers individually and in bulk, changed price, attached one
  more mid-flight, detached one, then changed price again. All of it went
  through `attachObserver`/`detachObserver`; `Stock`'s core logic never
  moved.

## Folder Structure

```
Observer/
├── src/                          → StockWithObserver.java (the actual pattern)
├── without-pattern/              → StockWithoutObserver.java (the naive version)
└── notes/                        → this file + observer-pattern.md (theory)
```