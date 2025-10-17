# Laboratory work 0: SOLID Principles
## Theory
The SOLID principles are five essential guidelines that enhance software design, making code more maintainable and scalable.
1. Single Responsibility Principle - every class should have a single responsibility or single job or single purpose. 
2. Open/Closed Principle - Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification, meaning that you should be able to extend a class behavior, without modifying it.
3. Liskov's Substitution Principle - Derived or child classes must be substitutable for their base or parent classes. This principle ensures that any class that is the child of a parent class should be usable in place of its parent without any unexpected behaviour.
4. Interface Segregation Principle - This principle is the first principle that applies to Interfaces instead of classes in SOLID and it is similar to the single responsibility principle. It states that "do not force any client to implement an interface which is irrelevant to them".
5. Dependency Inversion Principle - High-level modules should not depend on low-level modules. Both should depend on abstractions. Additionally, abstractions should not depend on details. Details should depend on abstractions.
## Implementation
The project structure looks like:
```
lab0
├── products
│   ├── ChickenType.java
│   ├── Meat.java
│   ├── MeatCategory.java
│   ├── MeatType.java
│   ├── Pork.java
│   ├── PorkType.java
│   └── Product.java
├── shop
│   ├── Cart.java
│   ├── CartItem.java
│   ├── MeatFactory.java
│   └── Shop.java
└── Main.java
```
1. S – (SRP):
Each class has one clear responsibility:
- **Meat** represents a product.
- **Shop** manages available products.
- **Cart** handles customer selections and totals.
- **Main** manages user interaction and flow.
For example, we have our **Shop** class which has only 1 responsability:
```java
public class Shop {
    private final List<Product> products = new ArrayList<>();
    public void addProduct(Product product) {products.add(product);}
    public void showProductsByCategory(MeatCategory category) {
        System.out.println("---- " + category.getDisplayName() + " Products ----");
        int count = 1;
        for (Product product : products) {
            if (product instanceof Meat meat) {
                if (meat.getCategory() == category) {
                    System.out.print(count + ". ");
                    meat.displayInfo();
                    count++;}}}
        if (count == 1) { System.out.println("No products found in this category.");}
        System.out.println("-------------------------");}}
```
2. O – (OCP):
- Meat types can be added without changing the logic, since all products depend on the interface **Product**.
```java
public class MeatFactory {
    public static Product create(MeatType type, double pricePerKg) {
        return new Meat(type, pricePerKg);}}
```
Adding a new type of meat only requires adding another enum enum class, without modifying any existing code, for example:
```java
public enum BeefType implements MeatType {RIB("Beef Rib", MeatCategory.RED_MEAT);}
```
3. L – (LSP):
- We have the interface **Product**, and the subclass **Meat**. We can replace this subclass anywhere in the program where is called **Product** and still the code won't break
```java
public enum ChickenType implements MeatType {
    BREAST("Chicken Breast", MeatCategory.WHITE_MEAT),
    WING("Chicken Wing", MeatCategory.WHITE_MEAT);}

public enum PorkType implements MeatType {
    RIBS("Pork Ribs", MeatCategory.RED_MEAT),
    CHUMP("Pork Chump", MeatCategory.RED_MEAT);}
```
Moreover, every **Product** type can be used interchangeably — whether it’s chicken, pork, or a new type, for example: 
```java
Product chicken = new Meat(ChickenType.BREAST, 10.0);
Product pork = new Meat(PorkType.RIBS, 15.0);
cart.addItem(chicken, 1.5);
cart.addItem(pork, 2.0);
```
4. I - (ISP):
- **Product** and **MeatType** are minimal interfaces and the client can choose if they want to see the products of meat type or just see the available products. The client is not forced to choose to see just a meat type compulsory.
- **Product** deals with product behaviour
```java
public interface Product {
    String getName();
    double getPricePerKg();
    void displayInfo();}
```
- **MeatType** deals with meat-specific data(display name and category)
```java
public interface MeatType {
    String getDisplayName();
    MeatCategory getCategory();}
```
5. D - (DIP):
- **Shop**, **Cart** depend on abstraction **Product**, not on **Meat** class.
```java
// from Shop class
public void addProduct(Product product){products.add(product);}
```
Here, **Shop** doesn’t care what kind of product it gets — it just depends on the **Product** interface.
## Results
```
Welcome to the Meat Shop 
------------------------------

Choose category:
1. White Meat
2. Red Meat
3. Show All Products
0. Finish and See Bill
Your choice: 1
---- White Meat Products ----
1. Chicken Breast (White Meat) - $10.00 per kg
2. Chicken Wing (White Meat) - $6.00 per kg
3. Chicken Liver (White Meat) - $8.00 per kg
4. Broiler Chicken (White Meat) - $12.00 per kg
5. Chicken Sausage (White Meat) - $14.00 per kg
-------------------------

Enter product number to buy (0 to go back): 2
Enter kilograms of Chicken Wing: 3
Added 3.00 kg of Chicken Wing to your cart.
Would you like to buy another product from this category? (y/n): n

Choose category:
1. White Meat
2. Red Meat
3. Show All Products
0. Finish and See Bill
Your choice: 2
---- Red Meat Products ----
1. Pork Chump (Red Meat) - $12.00 per kg
2. Pork Ribs (Red Meat) - $15.00 per kg
-------------------------

Enter product number to buy (0 to go back): 3
Invalid product number!

Enter product number to buy (0 to go back): 1
Enter kilograms of Pork Chump: 3
Added 3.00 kg of Pork Chump to your cart.
Would you like to buy another product from this category? (y/n): n

Choose category:
1. White Meat
2. Red Meat
3. Show All Products
0. Finish and See Bill
Your choice: 3
---- Product List ----
1. Chicken Breast (White Meat) - $10.00 per kg
2. Chicken Wing (White Meat) - $6.00 per kg
3. Chicken Liver (White Meat) - $8.00 per kg
4. Broiler Chicken (White Meat) - $12.00 per kg
5. Chicken Sausage (White Meat) - $14.00 per kg
6. Pork Chump (Red Meat) - $12.00 per kg
7. Pork Ribs (Red Meat) - $15.00 per kg
-------------------------

Enter product number to buy (0 to go back): 7
Enter kilograms of Pork Ribs: 2
Added 2.00 kg of Pork Ribs to your cart.
Would you like to buy another product from this category? (y/n): n

Choose category:
1. White Meat
2. Red Meat
3. Show All Products
0. Finish and See Bill
Your choice: 0

Your Final Bill:

 ------Receipt: ------
Chicken Wing: 3.00kg x $6.00/kg = $18.00

Pork Chump: 3.00kg x $12.00/kg = $36.00

Pork Ribs: 2.00kg x $15.00/kg = $30.00

Total: $84.00
---------------------
 Thank you for shopping with us!
```
## Conclusion
The project demonstrates the application of SOLID principles. Therefore, we obtained:
*extendable code;
*separation of concerns;
*reusable components;
By abstracting behavior (Product) and characteristics (MeatType), the application is easily extensible — adding BeefType, FishType, or new categories (like “Seafood”) requires no modifications to existing classes.Therefore, this project respects OOP principles.
