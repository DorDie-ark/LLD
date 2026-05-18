## Builder Design Pattern — ISP Compliant

### Problem
Standard Builder forces ALL concrete builders to implement ALL methods
Optional features (e.g. graphics card) result in empty method bodies — ISP violated

### Fix
Extract optional features into separate interfaces

### Structure
```java
// Mandatory — all desktops
abstract class DesktopBuilder {
    public abstract DesktopBuilder buildStorageCard();
    public abstract DesktopBuilder buildRAM();
    public abstract DesktopBuilder buildProcessor();
    public abstract DesktopBuilder buildOs();
    public Desktop build();
}

// Optional — only Gaming
interface IGraphicsBuilder {
    DesktopBuilder buildGraphicCard();
}

// Gaming — needs everything
class GamingDesktopBuilder extends DesktopBuilder 
    implements IGraphicsBuilder { ... }

// Workstation — skips graphics ✅
class WorkstationDesktopBuilder extends DesktopBuilder { ... }
```

### Director handles optional features
```java
builder.buildOs().buildRAM().buildProcessor().buildStorageCard();

if(builder instanceof IGraphicsBuilder g) {
    g.buildGraphicCard(); // only called for Gaming
}

return builder.build();
```

### Key Rule
> **Empty method body = ISP violation — extract to separate interface**

### When to apply
When a new builder type skips one or more features of the abstract builder