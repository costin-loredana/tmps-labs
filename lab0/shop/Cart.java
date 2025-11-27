package lab0.shop;
import java.util.ArrayList;
import java.util.List;

import lab0.products.Product;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, double kg){
        items.add(new CartItem(product, kg));
    }
    public double getTotal(){
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
    public void printReceipt(){
        System.out.println("\n ------Receipt: ------");
        for (CartItem item : items) {
            System.out.println(item);
        }
        System.out.printf("Total: $%.2f\n", getTotal());
        System.out.println("---------------------");
    }
}
