## Builder Design Pattern

### Problem
- Telescoping constructor — too many parameters, unreadable
- Optional fields force multiple constructor overloads or null passing

### Approach
- Create abstract builder with build methods
- Concrete builders implement each step differently
- Each build method returns `this` — enables method chaining
- Director controls the order — client just picks the builder
- Director returns the final product via `build()`

### When to use
- Object has many optional/complex fields
- Object construction has a fixed order
- Same construction process creates different representations (Gaming/Office)

### Director
- Controls build order — single source of truth
- Without Director — client controls order (error prone)
- Optional — Lombok `@Builder` has no Director

### Chaining
- Each method returns `this` (builder itself)
- Enables fluent API: `builder.buildOs().buildRAM().build()`
- Lombok `@Builder` uses same approach internally

### Weakness
- Adding new field → update all concrete builders
- ISP violation if optional features forced on all builders
- Fix: split optional features into separate interfaces