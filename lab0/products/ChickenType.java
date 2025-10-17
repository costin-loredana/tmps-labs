package lab0.products;

public enum ChickenType implements MeatType {
    BREAST("Chicken Breast", MeatCategory.WHITE_MEAT),
    WING("Chicken Wing", MeatCategory.WHITE_MEAT),
    BROILER("Broiler Chicken", MeatCategory.WHITE_MEAT),
    LIVER("Chicken Liver", MeatCategory.WHITE_MEAT),
    SAUSAGE("Chicken Sausage", MeatCategory.WHITE_MEAT);

    private final String displayName;
    private final MeatCategory category;

    ChickenType(String displayName, MeatCategory category) {
        this.displayName = displayName;
        this.category = category;
    }   
    @Override
    public String getDisplayName() {
        return displayName;
    }
    @Override
    public MeatCategory getCategory() {
        return category;
    }
}
