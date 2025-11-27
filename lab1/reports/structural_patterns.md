
#  **Structural Design Patterns in Dungeon Duel**

## **Laboratory Work: Structural Design Patterns**

## **Author:** Costin Loredana, FAF-232

---

# # **1.  Theory

Structural Design Patterns describe **how classes and objects combine to form larger structures**.
Unlike creational patterns (Builder, Factory) that create objects, structural patterns focus on:

* reducing coupling
* improving code organization
* enabling extensibility
* integrating external components
* adding behavior without modifying existing classes

For this laboratory, the Dungeon Duel project was extended with **three structural design patterns**:

1. **Facade** – simplifies interactions between multiple subsystems
2. **Decorator** – adds dynamic enhancements to Player and Enemy
3. **Adapter** – integrates external JSON-based enemies into the game

These patterns were chosen because they naturally match the game architecture, enhance maintainability, and demonstrate strong object-oriented principles (Open/Closed, Single Responsibility).

---

# # **2. Structural Pattern #1 — Facade**

Originally, the client (`Main`) had to coordinate:

* user input
* player building (Builder + Director)
* enemy creation (Factory Method)
* buff application
* combat execution

This resulted in **tight coupling** and duplicated logic.

A **Facade** gives the system **one clean entry point**:

```java
new GameFacade(scanner).start();
```

---


### **Facade start method**

```java
public void start() {
    IFighter player = createPlayer();
    IFighter enemy = chooseEnemy();

    player = applyPlayerBuffs(player);
    enemy = applyEnemyEffects(enemy);

    Enemy realEnemy = unwrapEnemy(enemy);
    if (realEnemy != null)
        realEnemy.taunt();

    combatManager.battle(player, enemy);
}
```

### **Main Idea of the Pattern**

The Facade hides all internal complexity (Builder, Factories, Decorators, Combat logic) and exposes **one simple method** to start the entire game.

### **Why Facade is used**

* Reduces coupling
* Makes the system easier to use
* Gives a single point of control
* Makes extensions (Decorator, Adapter) trivial to integrate
* Follows the requirement: *“There should only be one client”*

---

# # **3. Structural Pattern #2 — Decorator**


Player and Enemy had fixed stats (HP, ATK, DEF).
To introduce enhancements such as:

* +Attack
* +Defense
* Poison damage
* Magic buffs

…modifying Player or Enemy directly would violate **Open/Closed Principle**.

**Decorator** allows adding new behavior dynamically by wrapping objects.

---

## ## **3.2 Implementation & Explanation**

### **Location:**

`lab1/domain/decorator/`

### **Base Interface (required for Decorator)**

`lab1/domain/combat/IFighter.java`

### **Base Decorator**

`lab1/domain/decorator/FighterDecorator.java`

```java
public abstract class FighterDecorator implements IFighter {
    protected final IFighter wrapped;

    public FighterDecorator(IFighter wrapped) {
        this.wrapped = wrapped;
    }

    public IFighter getWrapped() {
        return wrapped;
    }
}
```

### **Concrete Decorators**

Attack boost:

```java
public int getAttack() {
    return wrapped.getAttack() + bonus;
}
```

Defense boost:

```java
public int getDefense() {
    return wrapped.getDefense() + bonus;
}
```

Poison effect:

```java
public void takeDamage(int dmg) {
    wrapped.takeDamage(dmg);
    wrapped.takeDamage(poisonDamage);
}
```

### **Main Idea of the Pattern**

Decoration adds **new behavior without changing existing classes**, allowing multiple enhancements to be stacked transparently.

### **Why Decorator is used**

* Adds buffs without modifying Player/Enemy
* Extensible and modular
* Perfectly fits combat dynamics
* Works seamlessly with Facade

---

# # **4. Structural Pattern #3 — Adapter**

The game needed to support **external enemies** loaded from JSON files.
However, the JSON structure did not match the `Enemy` class interface.

**Adapter** converts incompatible structures into objects usable by the game.

---

## ## **4.2 Implementation & Explanation**

### **Location:**

`lab1/domain/adapter/`

### **External Data Model**

`ExternalEnemyData.java`

```java
public class ExternalEnemyData {
    public String name, taunt;
    public int hp, attack, defense;
}
```

### **JSON Loader (no external libraries)**

`JsonEnemyLoader.java`

```java
String json = Files.readString(path);
data.name = extractString(json, "name");
data.hp = extractInt(json, "hp");
...
```

### **Adapter: Converts JSON → Enemy**

`JsonEnemyAdapter.java`

```java
public class JsonEnemyAdapter extends Enemy {
    public JsonEnemyAdapter(ExternalEnemyData d) {
        super(d.name, d.hp, d.attack, d.defense);
    }

    @Override
    public void taunt() {
        System.out.println(data.taunt);
    }
}
```

### **Factory Integration**

`ExternalEnemyFactory` wraps the adapter and connects it to `EnemyFactory`.

### **Main Idea of the Pattern**

Adapter converts external JSON-based enemy data into a class that behaves exactly like an internal Enemy, **without modifying the game engine**.

### **Why Adapter is used**

* Integrates external data
* Keeps Enemy hierarchy untouched
* Perfectly compatible with Decorator + Facade
* Demonstrates real-world adaptivity (file-based configuration)

---

# # **5. Results / Screenshots / Conclusions**

- The Facade pattern simplified the game startup process
- The Decorator pattern enabled stacking buffs on players and enemies
- The Adapter pattern integrated external enemies from JSON files
- The combat engine needed **no modifications**

### **Example Run Output**

```
== Dungeon Duel ==
Enter your name: lor
Choose your class:
1 Warrior
2 Mage
3 Rogue
> 1

Player created:
lor the Warrior
HP: 120 | ATK: 15 | DEF: 10

Choose your enemy:
1 Goblin
2 Skeleton
3 Orc
> 2

== Player Enhancement ==
You found an enchanted sword (+5 ATK)!
You equipped reinforced armor (+3 DEF)!

== Enemy Status Effect ==
Skeleton is poisoned (+1 damage per hit)!

An enemy appears!
Skeleton (HP: 70, ATK: 15, DEF: 8)
The Skeleton rattles its bones ominously!

Battle started between lor and Skeleton
>> Skeleton suffers 1 poison damage!
lor hits Skeleton for 9 damage.
Skeleton hits lor for 1 damage.
>> Skeleton suffers 1 poison damage!
lor hits Skeleton for 9 damage.
Skeleton hits lor for 1 damage.
>> Skeleton suffers 1 poison damage!
lor hits Skeleton for 16 damage.
Skeleton hits lor for 1 damage.
>> Skeleton suffers 1 poison damage!
lor hits Skeleton for 13 damage.
Skeleton hits lor for 3 damage.
>> Skeleton suffers 1 poison damage!
lor hits Skeleton for 8 damage.
Skeleton hits lor for 2 damage.
>> Skeleton suffers 1 poison damage!
lor hits Skeleton for 13 damage.
== Battle Over ==
lor wins!
```

---

# # **Conclusion**

This laboratory demonstrates how structural design patterns significantly improve software architecture.
By integrating **Facade**, **Decorator**, and **Adapter**, the Dungeon Duel system became:

* easier to understand
* easier to extend
* cleaner internally
* more flexible to future changes

These patterns show how complex systems can remain simple at the top level while being powerful under the hood.

