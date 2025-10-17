package lab0.shop;
import lab0.products.Product;
public class CartItem {
    private final Product product;
    private final double kg;

    public CartItem(Product product, double kg) {
        this.product = product;
        this.kg = kg;
    }

    public double getSubtotal() {
        return product.getPricePerKg() * kg;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2fkg x $%.2f/kg = $%.2f\n", product.getName(), kg, product.getPricePerKg(), getSubtotal());
    }
}
