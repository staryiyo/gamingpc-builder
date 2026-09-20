# Gaming PC Builder

Coursework project for the Software Design Patterns course (Assignment #1).
Demonstrates the **Builder** creational design pattern through the
step-by-step assembly of a gaming PC.

## About the pattern

Builder separates the construction of a complex object into steps,
allowing the same construction process to build different
representations of an object, instead of relying on a bulky constructor
with many parameters.

## Structure

- **`GamingPC`** — Product. An immutable object holding the PC
  configuration (CPU, GPU, RAM, storage, PSU, cooling, motherboard,
  case).
- **`GamingPC.Builder`** — Builder. Configures the build step by step
  through a fluent API (method chaining) and validates component
  compatibility in `build()`.
- **`PCDirector`** — Director. Contains ready-made build presets:
  `buildBudgetPC()`, `buildHighEndPC()`, `buildStreamingPC()`.
- **`Main`** — Client. Demonstrates using the Builder both through the
  Director and directly (manual custom build).

## Fluent API

```java
GamingPC pc = new GamingPC.Builder()
        .setCpu("AMD Ryzen 5 7600")
        .setGpu("RTX 4060")
        .setRam(32)
        .setStorage("SSD", 1000)
        .setPsu(650)
        .setCooling("Air")
        .setMotherboard("B650")
        .setCaseType("Mid Tower")
        .build();
```

## Validation

`build()` checks:
- required components are present (CPU, GPU);
- PSU wattage is specified and positive;
- compatibility: high-end GPUs (RTX) require a PSU of at least 650W —
  otherwise an `IllegalStateException` is thrown.

## Clean Code principles applied

1. **Meaningful names** — classes and methods are named after their
   purpose (`GamingPC`, `setCpu()`, `buildBudgetPC()`).
2. **Small methods, single purpose** — each Builder method sets exactly
   one field.
3. **Validated construction** — `build()` does not create the object
   blindly; it verifies correctness and component compatibility.
4. **No magic numbers/strings** — threshold values are extracted into
   named constants (`MIN_PSU_FOR_HIGH_END_GPU`).
5. **Single Responsibility** — `GamingPC` holds data, `Builder`
   assembles it, `PCDirector` only knows the presets.

## Running

Open the project in IntelliJ IDEA → run `Main.java`.

---

# Assignment #2 — Factory Method & Abstract Factory

Extension of the project: **Factory Method** and **Abstract Factory**
patterns on the same domain (gaming PC assembly).

## Part A — Factory Method (processors)

- **`Processor`** — Abstract Product. An interface defining the common
  contract for all processors.
- **`IntelProcessor`, `AmdProcessor`** — Concrete Products.
- **`ProcessorFactory`** — Creator (abstract class). Declares the
  factory method `createProcessor()`.
- **`IntelProcessorFactory`, `AmdProcessorFactory`** — Concrete
  Creators, each producing its own processor brand.

```java
ProcessorFactory factory = new IntelProcessorFactory();
factory.printProcessorInfo();
```

## Part B — Abstract Factory (platforms)

- **`Processor`, `GraphicsCard`** — Abstract Products (two product
  types in the family).
- **`IntelProcessor`/`AmdProcessor`, `BudgetGraphicsCard`/`HighEndGraphicsCard`**
  — Concrete Products.
- **`PCPartsFactory`** — Abstract Factory. An interface with one
  creation method per product type in the family.
- **`BudgetPartsFactory`, `HighEndPartsFactory`** — Concrete Factories.
  Each guarantees a consistent set of parts for its platform (Budget
  can never produce a high-end GPU).
- **`Main2`** — Client. Works only through the `PCPartsFactory`,
  `Processor`, and `GraphicsCard` interfaces — never instantiates
  `IntelProcessor` or `BudgetGraphicsCard` directly.

```java
assemblePC(new BudgetPartsFactory(), "Budget PC");
assemblePC(new HighEndPartsFactory(), "High-End PC");
```

## Clean Code principles applied

1. **Program to an interface, not an implementation** — `assemblePC()`
   accepts `PCPartsFactory`, `Processor`, `GraphicsCard` — interfaces,
   not concrete classes. A new platform requires no changes to the
   client.
2. **Meaningful, intention-revealing names** — `ProcessorFactory`,
   `createProcessor()`, `BudgetPartsFactory` clearly state their
   purpose.
3. **Small methods, each doing one thing** — each `createX()` creates
   exactly one object, with no side logic.
4. **Single Responsibility** — each class has one job: the product
   holds data, the factory creates, the client uses.
5. **Open/Closed Principle** — a new manufacturer is added via a new
   class (`extends ProcessorFactory` / `implements PCPartsFactory`),
   with no changes to existing code:

```java
// Anti-pattern — would require editing this method for every