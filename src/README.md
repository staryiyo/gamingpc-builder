# Gaming PC Builder

Учебный проект по курсу Software Design Patterns (Assignment #1).
Демонстрирует порождающий паттерн проектирования **Builder** на примере
пошаговой сборки игрового ПК.

## О паттерне

Builder разделяет процесс создания сложного объекта на шаги, позволяя
одним и тем же процессом строить разные представления объекта, не
прибегая к громоздкому конструктору с множеством параметров.

## Структура

- **`GamingPC`** — Product. Неизменяемый (immutable) объект, хранящий
  конфигурацию ПК (CPU, GPU, RAM, накопитель, БП, охлаждение,
  материнская плата, корпус).
- **`GamingPC.Builder`** — Builder. Пошагово настраивает конфигурацию
  через fluent API (method chaining) и валидирует совместимость
  компонентов в `build()`.
- **`PCDirector`** — Director. Содержит готовые пресеты сборки:
  `buildBudgetPC()`, `buildHighEndPC()`, `buildStreamingPC()`.
- **`Main`** — Client. Демонстрирует использование Builder-а как через
  Director, так и напрямую (ручная кастомная сборка).

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

## Валидация

`build()` проверяет:
- наличие обязательных компонентов (CPU, GPU);
- что мощность БП указана и положительна;
- совместимость: для топовых видеокарт (RTX) требуется БП не менее
  650W — иначе выбрасывается `IllegalStateException`.

## Применённые принципы Clean Code

1. **Meaningful names** — классы и методы называются в соответствии с
   назначением (`GamingPC`, `setCpu()`, `buildBudgetPC()`).
2. **Small methods, single purpose** — каждый метод Builder-а
   устанавливает ровно одно поле.
3. **Validated construction** — `build()` не создаёт объект вслепую, а
   проверяет корректность и совместимость компонентов.
4. **No magic numbers/strings** — пороговые значения вынесены в
   именованные константы (`MIN_PSU_FOR_HIGH_END_GPU`).
5. **Single Responsibility** — `GamingPC` хранит данные, `Builder`
   собирает, `PCDirector` знает только пресеты.

## Запуск

Открыть проект в IntelliJ IDEA → запустить `Main.java`.