# Structural Design Patterns


## Author: Nichita Gancear, Group FAF-232

----

## Objectives:

1. Study and understand Structural Design Patterns.
2. Implement a military mission deployment system as a continuation of previous lab work.
3. Apply structural design patterns (Facade, Bridge, Decorator) to manage units, protocols, and upgrades flexibly.

## File Structure
````
lab3/
├─ client/
│   ├─ MilitaryUnit.java
│   ├─ ReconDrone.java
│   ├─ ArmoredVehicle.java
│   ├─ HeavyTank.java
│   ├─ UnitDecorator.java
│   ├─ StealthDecorator.java
│   ├─ ArmorDecorator.java
│   ├─ ThermalDecorator.java
│   └─ MissionFacade.java
└─ domain/
└─ MilitaryUI.java
````



## Used Design Patterns:
Structural Design Patterns are concerned with how classes and objects are composed to form larger structures. Structural class patterns use inheritance, whereas structural object patterns use **composition**, which is more flexible.

* **Facade** – Provides a simplified interface to a complex subsystem. `MilitaryUI` uses the `MissionFacade` to deploy units, choose protocols, and apply upgrades without knowing the internal implementation.
* **Bridge** – Separates abstractions (units) from implementations (deployment protocols), so both can vary independently. This allows adding new units or protocols without changing existing code.
* **Decorator** – Dynamically adds responsibilities (upgrades) to objects without modifying the base class, allowing flexible extension of unit capabilities.


## Implementation

This lab work implements a **military mission deployment system**. Users can deploy units, choose deployment protocols, and add optional upgrades. The system uses **Facade, Bridge, and Decorator** patterns for flexibility and maintainability.

### Facade
The `MissionFacade` class implements the **Facade** pattern by providing a simplified, unified interface to the complex subsystem of unit creation, deployment protocols, upgrade application, and cost calculation. It hides the complexity from the client code (`MilitaryUI`).

The client simply calls methods like `facade.createUnit(...)`, `facade.applyUpgrade(...)`, and `facade.calculateFinalCost(...)` without worrying about the underlying implementation details.

```java
MilitaryUnit unit = facade.createUnit(unitChoice, protocolChoice);
unit = facade.applyUpgrade(unit, upgradeChoice);
double finalPrice = facade.calculateFinalCost(unit, elite);
```

### Bridge

The Bridge pattern separates the unit abstraction (MilitaryUnit) from its deployment protocol implementation (DeploymentProtocol). This allows different unit types and deployment protocols to vary independently without creating a rigid class hierarchy.
```java
public class HeavyTank implements MilitaryUnit {
    private final DeploymentProtocol protocol;

    public HeavyTank(DeploymentProtocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public String getDescription() {
        return "Heavy Tank (" + this.protocol.getProtocolName() + ")";
    }

    @Override
    public double getBaseCost() {
        return 9300.0;
    }

    @Override
    public double getFinalCost() {
        return this.getBaseCost() + this.protocol.getProtocolCost();
    }
}


```
The bridge connection is established through aggregation, where each MilitaryUnit holds a reference to a DeploymentProtocol. This design enables flexible combinations: any unit can work with any protocol, and new units or protocols can be added independently without modifying existing code.

### Decorator
The Decorator pattern dynamically adds responsibilities to units by wrapping them in decorator objects representing mission upgrades. The UnitUpgradeDecorator abstract class implements the MilitaryUnit interface and maintains a reference to a wrapped MilitaryUnit.

Concrete decorators like StealthCoatingDecorator, ReinforcedArmorDecorator, and ThermalImagingDecorator extend UnitUpgradeDecorator to add specific upgrades, modifying the description and cost without changing the base unit classes
```java
public class StealthCoatingDecorator extends UnitUpgradeDecorator {
    public static final double STEALTH_COST = 300;

    public StealthCoatingDecorator(MilitaryUnit unit) {
        super(unit);
    }

    @Override
    public String getDescription() {
        return wrappedUnit.getDescription() + " + Stealth Coating";
    }

    @Override
    public double getCost() {
        return wrappedUnit.getCost() + STEALTH_COST;
    }
}

```
## Results
![results1.png](Results%2Fresults1.png)

![results2.png](Results%2Fresults2.png)

## Conclusions
This laboratory work successfully added military mission deployment capabilities to the command system by implementing three structural design patterns. The Facade pattern simplified complex operations by providing a single MissionFacade interface that handles unit creation, deployment protocols, upgrades, and cost calculations. The Bridge pattern allowed military units and their deployment protocols to vary independently, and the Decorator pattern enabled flexible mission upgrades without creating numerous subclasses. These patterns work together with the underlying system to create a maintainable and extensible solution that can easily grow with new units, protocols, or upgrades.