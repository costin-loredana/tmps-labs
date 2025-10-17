package lab0;

import lab0.shop.*;
import lab0.products.*;
import java.util.List;
import java.util.Scanner;
import lab0.products.ChickenType;
import lab0.products.PorkType;
import lab0.products.MeatCategory;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Cart cart = new Cart();

        shop.addProduct(MeatFactory.create(ChickenType.BREAST, 10.0));
        shop.addProduct(MeatFactory.create(ChickenType.WING, 6.0));
        shop.addProduct(MeatFactory.create(ChickenType.LIVER, 8.0));
        shop.addProduct(MeatFactory.create(ChickenType.BROILER, 12.0));
        shop.addProduct(MeatFactory.create(ChickenType.SAUSAGE, 14.0));
        shop.addProduct(MeatFactory.create(PorkType.CHUMP, 12.0));
        shop.addProduct(MeatFactory.create(PorkType.RIBS, 15.0));

        Scanner scanner = new Scanner(System.in);
        boolean shopping = true;

        System.out.println(" Welcome to the Meat Shop ");
        System.out.println("------------------------------");

        while (shopping) {
            System.out.println("\nChoose category:");
            System.out.println("1. White Meat");
            System.out.println("2. Red Meat");
            System.out.println("3. Show All Products");
            System.out.println("0. Finish and See Bill");
            System.out.print("Your choice: ");

            int categoryChoice = scanner.nextInt();
            if (categoryChoice == 0) break;

            List<Product> currentList;

            switch (categoryChoice) {
                case 1:
                    currentList = shop.getProductsByCategory(MeatCategory.WHITE_MEAT);
                    shop.showProductsByCategory(MeatCategory.WHITE_MEAT);
                    break;
                case 2:
                    currentList = shop.getProductsByCategory(MeatCategory.RED_MEAT);
                    shop.showProductsByCategory(MeatCategory.RED_MEAT);
                    break;
                case 3:
                    currentList = shop.getAllProducts();
                    shop.displayProducts();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            boolean choosingProduct = true;
            while (choosingProduct) {
                System.out.print("\nEnter product number to buy (0 to go back): ");
                int choice = scanner.nextInt();

                if (choice == 0) {
                    choosingProduct = false;
                    break;
                }

                if (choice < 1 || choice > currentList.size()) {
                    System.out.println("Invalid product number!");
                    continue;
                }

                Product chosen = currentList.get(choice - 1);
                System.out.print("Enter kilograms of " + chosen.getName() + ": ");
                double kg = scanner.nextDouble();

                cart.addItem(chosen, kg);
                System.out.printf("Added %.2f kg of %s to your cart.%n", kg, chosen.getName());

                System.out.print("Would you like to buy another product from this category? (y/n): ");
                String again = scanner.next();
                if (!again.equalsIgnoreCase("y")) {
                    choosingProduct = false;
                }
            }
        }
        System.out.println("\nYour Final Bill:");
        cart.printReceipt();
        System.out.println(" Thank you for shopping with us!");
        scanner.close();
    }
}
