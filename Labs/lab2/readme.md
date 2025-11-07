# Laboratory Work #1 — Creational Design Patterns

**Course:** TMPS (Principles of Object-Oriented Programming & Creational Design Patterns)  
**Student:** Gancear Nichita /
**Group:** FAF-232

---

## 📋 Table of Contents

1. [Introduction](#introduction)
2. [Objectives](#objectives)
3. [Theoretical Background](#theoretical-background)
4. [Project Structure](#project-structure)
5. [Implementation Details](#implementation-details)
    - [Factory Method Pattern](#1-factory-method-pattern)
    - [Abstract Factory Pattern](#2-abstract-factory-pattern)
    - [Builder Pattern](#3-builder-pattern)
    - [Prototype Pattern](#4-prototype-pattern)
    - [Singleton Pattern](#5-singleton-pattern)
    - [Object Pool Pattern](#6-object-pool-pattern)
6. [Code Examples](#code-examples)
7. [Results and Testing](#results-and-testing)
8. [Conclusions](#conclusions)
9. [References](#references)

---

## Introduction

This laboratory work focuses on implementing and demonstrating Creational Design Patterns in a weapon inventory management system. The project simulates a gun shop environment where various weapons, helmets, and ammunition clips are created, managed, and tracked using industry-standard design patterns.

Creational design patterns abstract the instantiation process, making systems independent of how objects are created, composed, and represented. This laboratory explores six fundamental creational patterns through a practical TypeScript implementation.

---

## Objectives

The main objectives of this laboratory work are:

1. **Implement all major Creational Design Patterns:**
    - Factory Method
    - Abstract Factory
    - Builder
    - Prototype
    - Singleton
    - Object Pool

2. **Demonstrate practical usage** of each pattern in a cohesive application domain

3. **Create a maintainable and extensible codebase** that showcases pattern interactions

4. **Validate implementations** through comprehensive testing and output verification

---

## Theoretical Background

### Creational Design Patterns

Creational patterns deal with object creation mechanisms, trying to create objects in a manner suitable to the situation. The basic form of object creation could result in design problems or added complexity. Creational patterns solve this by controlling the object creation process.

**Key patterns covered:**

- **Factory Method:** Defines an interface for creating objects but lets subclasses decide which class to instantiate
- **Abstract Factory:** Provides an interface for creating families of related objects without specifying concrete classes
- **Builder:** Separates complex object construction from its representation
- **Prototype:** Creates new objects by copying existing instances
- **Singleton:** Ensures a class has only one instance with global access
- **Object Pool:** Manages reusable object instances to improve performance

---

## Project Structure

```
Labs/
└── lab2/
    ├── builder/
    │   └── WeaponBuilder.java
    ├── client/
    │   └── App.java
    ├── domain/
    │   ├── inventory/
    │   │   ├── AmmoClipPool.java
    │   │   └── Inventory.java
    │   └── models/
    │       ├── Helmet.java
    │       └── Weapon.java
    ├── factory/
    │   ├── abstractfactory/
    │   │   ├── CivilianFactory.java
    │   │   ├── IGunShopFactory.java
    │   │   └── MilitaryFactory.java
    │   └── factorymethod/
    │       ├── ConcreteCreators.java
    │       └── WeaponCreator.java
    ├── prototype/
    │   └── Prototype.java
    └── utils/
        └── ID.java
```

---

## Implementation Details

### 1. Factory Method Pattern

**Purpose:** Define an interface for creating weapons while allowing subclasses to determine the specific weapon type.

**Implementation:**

The pattern consists of an abstract creator class `WeaponCreator` and three concrete creators:

```java
// Abstract Creator
public abstract class WeaponCreator {
    public Weapon orderWeapon(String name, String caliber, int magSize, double weight) {
        Weapon weapon = createWeapon(name, caliber, magSize, weight);
        System.out.println("Created: " + weapon);
        return weapon;
    }

    protected abstract Weapon createWeapon(String name, String caliber, int magSize, double weight);
}
```

**Benefits:**
- Eliminates tight coupling between creator and concrete products
- Single Responsibility Principle: creation logic separated from business logic
- Open/Closed Principle: new weapon types can be added without modifying existing code

**Usage Example:**
```java
Weapon pistol = new PistolCreator().orderWeapon("M17 Modular");
Weapon rifle  = new RifleCreator().orderWeapon("M4A1 SOPMOD");
```

---

### 2. Abstract Factory Pattern

**Purpose:** Provide an interface for creating families of related objects (weapons and helmets) without specifying their concrete classes.

**Implementation:**

```java
// Factory Interface
public interface IGunShopFactory {
    Weapon createPistol();
    Weapon createRifle();
    Helmet createHelmet();
}
```

**Benefits:**
- Ensures product families are compatible (civilian gear vs military gear)
- Isolates concrete classes from client code
- Easy to swap entire product families
- Promotes consistency among products

**Usage Example:**
```typescript
System.out.println("CIVILIAN GEAR:");
System.out.println(civWeapon);
System.out.println(civHelmet);

System.out.println("\nMILITARY GEAR:");
System.out.println(milWeapon);
System.out.println(milHelmet);
```

---

### 3. Builder Pattern

**Purpose:** Construct complex weapon objects step-by-step, allowing the same construction process to create different representations.

**Implementation:**

```java
public class WeaponBuilder {
    private String name = "Unnamed";
    private String caliber = "Unknown";
    private int magazineSize = 30;
    private double weight = 3.0;

    public WeaponBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public WeaponBuilder setCaliber(String caliber) {
        this.caliber = caliber;
        return this;
    }

    public WeaponBuilder setMagazineSize(int magazineSize) {
        this.magazineSize = magazineSize;
        return this;
    }

    public WeaponBuilder setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    public Weapon build() {
        return new Weapon(name, caliber, magazineSize, weight);
    }
}
```

**Benefits:**
- Fluent interface for readable code
- Step-by-step construction of complex objects
- Same construction code can produce different representations
- Isolates complex construction code from business logic

**Usage Example:**
```java
WeaponBuilder builder = new WeaponBuilder();
Weapon custom = builder
    .setName("Freedom Carbine")
    .setCaliber("5.56mm")
    .setMagazineSize(30)
    .addAttachment("Red Dot Scope")
    .addAttachment("Angled Grip")
    .build();
```

---

### 4. Prototype Pattern

**Purpose:** Create new weapon objects by copying existing instances rather than creating from scratch.

**Implementation:**

```java
// Cloneable Interface
public class Weapon implements ICloneable<Weapon> {
    private final String id;
    private final String name;
    private final String type;
    private final int damage;
    private final double weight;
    private final List<String> attachments;

    public Weapon(String name, String type, int damage, double weight, List<String> attachments) {
        this.id = generateId();
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.weight = weight;
        this.attachments = new ArrayList<>(attachments);
    }

    // Copy constructor for clone
    private Weapon(Weapon source) {
        this.id = generateId();
        this.name = source.name + " (copy)";
        this.type = source.type;
        this.damage = source.damage;
        this.weight = source.weight;
        this.attachments = new ArrayList<>(source.attachments);
    }

    @Override
    public Weapon clone() {
        return new Weapon(this);
    }

    private String generateId() {
        return Long.toHexString(new Random().nextLong()).substring(0, 7).toUpperCase();
    }

    public String describe() {
        return String.format("%s (%s) — dmg:%d w:%.1fkg atts:[%s]",
                name, type, damage, weight,
                attachments.isEmpty() ? "none" : String.join(",", attachments));
    }

    @Override
    public String toString() {
        return "Weapon{id='" + id + "', " + describe() + "}";
    }
}

```

**Benefits:**
- Avoids expensive initialization when creating similar objects
- Reduces subclassing needed for object creation
- Dynamic addition and removal of objects at runtime
- Specifying new objects by varying values

**Usage Example:**
```java
Weapon original = new WeaponBuilder()
    .setName("Freedom Carbine")
    .setCaliber("5.56mm")
    .setMagazineSize(30)
    .setWeight(3.2)
    .addAttachment("ACOG Scope")
    .addAttachment("Suppressor")
    .build();

Weapon copy = PrototypeHelper.duplicateWeapon(original); // CLONE!

System.out.println("ORIGINAL: " + original);
System.out.println("COPY:     " + copy);
```

---

### 5. Singleton Pattern

**Purpose:** Ensure the `Inventory` class has only one instance throughout the application lifetime and provide global access to it.

**Implementation:**

```java
public class Inventory {
    private static Inventory instance;
    private final List<Weapon> weapons = new ArrayList<>();

    private Inventory() {
        System.out.println("Inventory system initialized.");
    }

    public static synchronized Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
        System.out.println("Added to inventory: " + weapon.getName());
    }

    public void listAll() {
        System.out.println("\n--- INVENTORY ---");
        weapons.forEach(w -> System.out.println(" • " + w));
        System.out.println("Total weapons: " + weapons.size());
    }
}
```

**Benefits:**
- Controlled access to single instance
- Reduced namespace pollution
- Permits refinement of operations and representation
- Lazy initialization

**Usage Example:**
```java
Inventory inv = Inventory.getInstance();
inv.add(weapon1);
inv.add(weapon2);

// Anywhere else in the code — even in another file!
Inventory sameInv = Inventory.getInstance();  // SAME INSTANCE!

System.out.println("Inventory has " + sameInv.size() + " weapons:");
```

---

### 6. Object Pool Pattern

**Purpose:** Manage a pool of reusable `AmmoClip` objects to avoid expensive creation/destruction cycles.

**Implementation:**

```java
public class AmmoClipPool {
    private static final int POOL_SIZE = 10;
    private final Queue<AmmoClip> pool = new LinkedList<>();

    private static class AmmoClip {
        private final int capacity;
        private int rounds;

        AmmoClip(int capacity) {
            this.capacity = capacity;
            this.rounds = capacity;
        }

        void reload() { this.rounds = capacity; }
        boolean fire() {
            if (rounds > 0) {
                rounds--;
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Clip[" + rounds + "/" + capacity + "]";
        }
    }

    private AmmoClipPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.offer(new AmmoClip(30));
        }
    }

    private static class Holder {
        static final AmmoClipPool INSTANCE = new AmmoClipPool();
    }

    public static AmmoClipPool getInstance() {
        return Holder.INSTANCE;
    }

    public AmmoClip acquire() {
        AmmoClip clip = pool.poll();
        if (clip == null) {
            System.out.println("No clips available, creating new one.");
            clip = new AmmoClip(30);
        }
        return clip;
    }

    public void release(AmmoClip clip) {
        clip.reload();
        pool.offer(clip);
    }
}
```

**Benefits:**
- Improved performance by reusing objects
- Reduced memory allocation overhead
- Predictable memory usage
- Resource management for expensive objects

**Usage Example:**
```java
// EXACTLY LIKE YOUR TYPESCRIPT
AmmoClipPool pool = new AmmoClipPool(3, 30);  // Max 3 clips, 30 rounds each

AmmoClip clip1 = pool.acquire();  // Gets #1
AmmoClip clip2 = pool.acquire();  // Gets #2
AmmoClip clip3 = pool.acquire();  // Gets #3
AmmoClip clip4 = pool.acquire();  // Returns null → pool empty!

pool.release(clip1);              // Returns clip to pool
AmmoClip clip5 = pool.acquire();  // Reuses the returned clip!
```

---

## Code Examples

### Complete Application Flow

```java
public class App {
    public static void main(String[] args) {
        System.out.println("=== TMPS LAB1: ALL 6 CREATIONAL PATTERNS ===\n");

        // 1. FACTORY METHOD
        System.out.println("1. FACTORY METHOD");
        Weapon pistol = new PistolCreator().orderWeapon("Custom 9mm");
        Weapon rifle  = new RifleCreator().orderWeapon("Custom AR");

        // 2. ABSTRACT FACTORY
        System.out.println("\n2. ABSTRACT FACTORY");
        IGunShopFactory civFact = new CivilianFactory();
        Weapon civWeapon = civFact.createPistol();
        Helmet civHelmet = civFact.createHelmet();

        IGunShopFactory milFact = new MilitaryFactory();
        Weapon milWeapon = milFact.createRifle();
        Helmet milHelmet = milFact.createHelmet();

        // 3. BUILDER
        System.out.println("\n3. BUILDER");
        Weapon custom = new WeaponBuilder()
                .setName("Freedom Carbine")
                .setCaliber("5.56mm")
                .setMagazineSize(30)
                .setWeight(3.2)
                .addAttachment("ACOG Scope")
                .addAttachment("Foregrip")
                .build();

        // 4. PROTOTYPE
        System.out.println("\n4. PROTOTYPE");
        Weapon copy = PrototypeHelper.duplicateWeapon(custom);
        System.out.println("Original: " + custom.describe());
        System.out.println("Clone:    " + copy.describe());

        // 5. SINGLETON
        System.out.println("\n5. SINGLETON");
        Inventory inv = Inventory.getInstance();
        inv.add(custom);
        inv.add(copy);
        System.out.println("Inventory has " + inv.size() + " weapons:");
        inv.list();

        // 6. OBJECT POOL
        System.out.println("\n6. OBJECT POOL");
        AmmoClipPool pool = new AmmoClipPool(3, 30);
        AmmoClipPool.AmmoClip clip1 = pool.acquire();
        AmmoClipPool.AmmoClip clip2 = pool.acquire();
        AmmoClipPool.AmmoClip clip3 = pool.acquire();
        AmmoClipPool.AmmoClip clip4 = pool.acquire(); // null
        pool.release(clip1);
        AmmoClipPool.AmmoClip clip5 = pool.acquire(); // reused!

        System.out.println("\nClip 5 (reused): " + clip5);
        System.out.println("\nMission Complete! All patterns demonstrated.");
    }
}
```

---

## Results and Testing

### Program Output(ScreenShorts)

![results1.png](img%2Fresults1.png)

![results2.png](img%2Fresults2.png)

### Verification

**Factory Method:** Different weapon types created through specialized creators  
**Abstract Factory:** Consistent product families (civilian/military) generated  
**Builder:** Complex weapon with multiple attachments constructed fluently  
**Prototype:** Weapon successfully cloned with independent identity  
**Singleton:** Single inventory instance maintains all weapons  
**Object Pool:** Clip reuse working correctly, pool exhaustion handled

---

## Conclusions

This laboratory work successfully demonstrates the implementation and practical application of six fundamental Creational Design Patterns in a weapon inventory management system. Each pattern addresses specific object creation challenges:

**Key Achievements:**

1. **Factory Method** provides extensible weapon creation without coupling to concrete classes
2. **Abstract Factory** ensures consistency across related product families
3. **Builder** enables fluent, step-by-step construction of complex objects
4. **Prototype** allows efficient object duplication without expensive initialization
5. **Singleton** guarantees single inventory instance with global access
6. **Object Pool** optimizes resource management through object reuse

**Learning Outcomes:**

- Deep understanding of when and why to apply each creational pattern
- Practical experience with TypeScript/JavaScript pattern implementation
- Recognition of pattern trade-offs and appropriate use cases
- Improved code maintainability through separation of concerns
- Enhanced ability to design flexible, extensible systems

**Real-World Applications:**

These patterns are widely used in:
- Game development (object pooling for bullets, enemies)
- UI frameworks (singleton services, factory components)
- Database connections (pool pattern)
- Configuration management (singleton pattern)
- Document builders (builder pattern)

The implementation demonstrates that design patterns are not abstract concepts but practical tools that solve real problems in software development. By mastering creational patterns, developers can write more maintainable, testable, and scalable code.

---

## References

1. Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley.

2. Freeman, E., & Freeman, E. (2004). *Head First Design Patterns*. O'Reilly Media.

3. Refactoring Guru. (2024). *Design Patterns*. Retrieved from https://refactoring.guru/design-patterns

4. TypeScript Documentation. (2024). *TypeScript Handbook*. Retrieved from https://www.typescriptlang.org/docs/

5. Martin, R. C. (2017). *Clean Architecture: A Craftsman's Guide to Software Structure and Design*. Prentice Hall.