package lab0.shop;

import java.util.ArrayList;
import java.util.List;
import lab0.products.MeatCategory;
import lab0.products.Product;
import lab0.products.Meat;

public class Shop {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void displayProducts() {
        System.out.println("---- Product List ----");
        for (int i = 0; i < products.size(); i++) {
            System.out.print((i + 1) + ". ");
            products.get(i).displayInfo();
        }
        System.out.println("-------------------------");
    }

    public void showProductsByCategory(MeatCategory category) {
        System.out.println("---- " + category.getDisplayName() + " Products ----");
        int count = 1;
        for (Product product : products) {
            if (product instanceof Meat meat) {
                if (meat.getCategory() == category) {
                    System.out.print(count + ". ");
                    meat.displayInfo();
                    count++;
                }
            }
        }
        if (count == 1) {
            System.out.println("No products found in this category.");
        }
        System.out.println("-------------------------");
    }

    public Product getProduct(int index) {
        if (index >= 0 && index < products.size()) {
            return products.get(index);
        }
        return null;
    }

    public List<Product> getProductsByCategory(MeatCategory category) {
        List<Product> filtered = new ArrayList<>();
        for (Product product : products) {
            if (product instanceof Meat meat) {
                if (meat.getCategory() == category) {
                    filtered.add(meat);
                }
            }
        }
        return filtered;
    }

    public List<Product> getAllProducts() {
        return products;
    }
}
