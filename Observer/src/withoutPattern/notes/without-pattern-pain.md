## The Pain, Concretely

Before building the Observer-based solution, I built the naive version first
to actually feel why the pattern exists.

**Setup:** `Stock` directly held `EmailAlertService`, `TradingBot`,
`ComplianceAuditService`, and `PushNotificationService` as fields, calling
each one explicitly inside `setPrice()`.

**The test:** add a 5th channel — `SMSservice` — without rethinking the design.

**What I had to touch just to add one new channel:**
1. New field `smsService` on `Stock`
2. `Stock`'s constructor signature — new parameter added
3. `setPrice()` — new line added to call the new service
4. Every call site that builds a `Stock` (`main()`) — updated to pass the new instance
5. Plus writing the new `SMSservice` class itself

**Takeaway:** 4 changes to code that was already working and already tested,
just to plug in one new listener. That's the Open/Closed Principle violation
in concrete terms — `Stock` isn't closed for modification; every new
stakeholder forces a change to it.

**Question this leads to:** what needs to sit *between* `Stock` and its
listeners so that adding a 6th channel touches zero existing lines?