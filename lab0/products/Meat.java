package lab0.products;

public class Meat implements Product {
    private final MeatType type;
    private final double pricePerKg;

    public Meat(MeatType type, double pricePerKg) {
        this.type = type;
        this.pricePerKg = pricePerKg;
    }
    @Override
    public String getName() {
        return type.getDisplayName();
    }
    @Override
    public double getPricePerKg() {
        return pricePerKg;
    }
    @Override
    public void displayInfo() {
        System.out.printf("%s (%s) - $%.2f per kg%n",
                getName(), type.getCategory().getDisplayName(), getPricePerKg());
    }
    //do we need to add this method here?
    public MeatCategory getCategory() {
        return type.getCategory();
    }
}
