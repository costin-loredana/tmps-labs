package lab0.shop;
import lab0.products.Meat;
import lab0.products.MeatType;
import lab0.products.Product;
public class MeatFactory {
    public static Product create(MeatType type, double pricePerKg) {
        return new Meat(type, pricePerKg);
    }
}
