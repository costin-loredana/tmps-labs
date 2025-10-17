package lab0.products;

public class Pork implements Product {
    private final String name;
    private final double pricePerKg;

    public Pork(String name, double pricePerKg) {
        this.name = name;
        this.pricePerKg = pricePerKg;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPricePerKg() {
        return pricePerKg;
    }

    @Override
    public void displayInfo() {
        System.out.println(name + " - $" + pricePerKg + " per kg");
    }
    
}
