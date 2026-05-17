## When to use which Factory Pattern

---

### Simple Factory
- Plain Java apps, small scripts
- When adding new types is rare
- OCP violation in factory is **acceptable**

---

### Factory Method
- When OCP on factory must be satisfied
- Each type gets its own factory class
- In Spring: `Map<String, IFactory>` via DI eliminates if/else completely
- OCP still violated in `resolve()` — but that's the client's responsibility

---

### Abstract Factory
- When **providers** exist that implement the **same family of products**
- Each provider must implement ALL product types — enforced by interface

> **Core rule: Product types must be stable — providers can vary freely**

- Adding new provider = new factory class, zero edits to existing code ✅
- Adding new product type = edit all factories ❌ — use only when types are stable

---

### Quick Decision Guide

| Situation | Pattern |
|---|---|
| Plain Java, simple object creation | Simple Factory |
| Spring Boot, OCP on factory | Factory Method |
| Multiple providers, same product family | Abstract Factory |