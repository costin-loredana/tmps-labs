package lab0.products;

public enum MeatCategory {
    WHITE_MEAT("White Meat"),
    RED_MEAT("Red Meat");
    private final String displayName;
    MeatCategory(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}
//get product
