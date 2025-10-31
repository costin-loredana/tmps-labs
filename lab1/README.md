# Laboratory work 1: Creational Design patterns

## Objectives
1. Get familiar with the Creational DPs;
2. Choose a specific domain;
3. Implement at least 3 CDPs for the specific domain;

## Used Design Patterns:

- Singleton
- Factory Method
- Builder

## Implementation
The chosen domain is a simple console RPG game called Dungeon Duel, where a player selects a class (Warrior, Mage, Rogue) and battles an enemy, which, in our case is orc.

The project structure looks like:

```
lab1/
 ├── client/
 │    └── Main.java                      ← entry point (runs GameManager)
 │
 ├── domain/
 │    ├── core/
 │    │    ├── GameManager.java          ← Singleton (core controller)
 │    │    ├── GameConfig.java           ← Global constants (menu options, etc.)
 │    │    └── PlayerStats.java          ← Player class stats config
 │    │
 │    ├── factory/
 │    │    ├── Enemy.java                ← Abstract product
 │    │    ├── EnemyFactory.java         ← Factory Method
 │    │    ├── Goblin.java               ← Concrete product
 │    │    ├── Orc.java                  ← Concrete product
 │    │    └── Skeleton.java             ← Concrete product
 │    │
 │    └── models/
 │         ├── Player.java               ← Product (built by Builder)
 │         ├── PlayerBuilder.java        ← Concrete Builder
 │         └── PlayerDirector.java       ← Director for Builder
 │
 └── README.md

```

How can we create game objects (like the player and enemies) flexibly and independently from their concrete classes,
so that adding new types or changing their creation process does not require modifying the main game logic?

No patterns, meaning that the code would probably look like this:

```java
if (choice == 1)
    player = new Warrior();
else if (choice == 2)
    player = new Mage();
else if (choice == 3)
    player = new Rogue();

Enemy enemy;
if (stage == 1)
    enemy = new Goblin();
else if (stage == 2)
    enemy = new Skeleton();
```

1. Factory Mehod:
Centralize and abstract enemy creation, so that the game can spawn different types without depending on concrete classes.
```java
public abstract class EnemyFactory {
    public abstract Enemy createEnemy();
    public static EnemyFactory getFactory(String type) {
        return switch (type) {
            case "Goblin" -> new GoblinFactory();
            case "Skeleton" -> new SkeletonFactory();
            case "Orc" -> new OrcFactory();
            default -> new GoblinFactory();};}}
```
- Here, the **EnemyFactory** is the creation interface. Its subclasses : **Orc**, **Goblin** decide which concrete enemy to instantiate.
- The main game code (in GameManager) simply requests a factory by name — no new Orc() or new Skeleton() anywhere else.
Therefore, it is easier to add new enemy types by adding one class. It also keeps the main logic independent of concrete classes.

2. Builder:

Simplify complex player creation by separating construction (name, class, stats) from representation. 
When creating a **Player**, there are multiple properties:
- name
- playerClass
- hp, attack, defense (from the stats table)

It’s hard to read, easy to mix up, and you’d need new constructors every time you add or change stats.

- **Builder** separates construction (how to build) from representation (the finished Player).

```java
public class PlayerBuilder {
    private String name;
    private String playerClass;
    private int hp, attack, defense;
    public PlayerBuilder setName(String name) { this.name = name; return this; }
    public PlayerBuilder setPlayerClass(String playerClass) { this.playerClass = playerClass; return this; }
    public PlayerBuilder applyClassDefaults() {
        PlayerStats.Stats stats = PlayerStats.CLASS_STATS.get(playerClass);
        this.hp = stats.hp;
        this.attack = stats.attack;
        this.defense = stats.defense;
        return this;}
    public Player build() {return new Player(name, playerClass, hp, attack, defense);}}
```

```java
public class PlayerDirector {
    public Player createDefault(String name, String playerClass) {
        return new PlayerBuilder()
            .setName(name)
            .setPlayerClass(playerClass)
            .applyClassDefaults()
            .build();}}
```
- **PlayerDirector** provides reusable templates (e.g., createMage(name)).
- Encapsulates predefined sequences of construction

This means that this game logic doesn’t need to know how to build a Player — only what kind of Player to request. Also, the SRP is respected: builder only builds; Open/Closed: new build logic = new Director method

3. Singleton: 

Ensure there’s only one instance controlling the game flow, so that the game state and setup logic are consistent.
For showing the **Singleton**, we have 2 classes: **GameManager** - overall controller, **CombatManager** - battle controller. These classes preven multiple instances. It also respects the Single Responsibility principle: each singleton controls exactly one subsystem. It also assures Open-Closed principle: new features can be added without altering the instantiation logic

Here is an example of how GameManager is created:
```java
private static volatile GameManager instance;
private GameManager() {}
public static GameManager getInstance() {
    if (instance == null) {
        synchronized (GameManager.class) { if (instance == null) instance = new GameManage();}
    }
    return instance;
}
```



## Results
```
== Dungeon Duel ==
Enter your name: lor
Choose your class:
1 Warrior
2 Mage
3 Rogue
> 2

Player created:
lor the Mage
HP: 80 | ATK: 25 | DEF: 5

Choose your enemy:
1 Goblin
2 Skeleton
3 Orc
> 2

Game setup complete!

An enemy appears!
Skeleton (HP: 70, ATK: 15, DEF: 8)
The Skeleton rattles its bones ominously!

Battle started between lor the Mage
HP: 80 | ATK: 25 | DEF: 5 and Skeleton (HP: 70, ATK: 15, DEF: 8)
lor hits Skeleton for 17 damage.
Skeleton hits lor for 13 damage.
lor hits Skeleton for 14 damage.
Skeleton hits lor for 8 damage.
lor hits Skeleton for 15 damage.
Skeleton hits lor for 13 damage.
lor hits Skeleton for 20 damage.
Skeleton hits lor for 8 damage.
lor hits Skeleton for 20 damage.
==Battle Over==
lor wins!
```
## Conclusion
In this laboratory work, I successfully implemented three Creational Design Patterns — Singleton, Factory Method, and Builder — in the context of a simple RPG game. These patterns helped separate responsibilities, reduce coupling, and make the system easier to extend. For example, adding a new enemy now only requires one new class, while player creation remains flexible through the builder. This exercise demonstrated how Creational Patterns improve scalability, maintainability, and adherence to SOLID principles in real-world scenarios.
