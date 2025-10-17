package lab0.products;

public enum PorkType implements MeatType {
    CHUMP("Pork Chump", MeatCategory.RED_MEAT),
    SHOULDER("Pork Shoulder", MeatCategory.RED_MEAT),
    RIBS("Pork Ribs", MeatCategory.RED_MEAT),
    LOIN("Pork Loin", MeatCategory.RED_MEAT);
    private final String displayName;
    private final MeatCategory category;

    PorkType(String displayName, MeatCategory category) {
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



