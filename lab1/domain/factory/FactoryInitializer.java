package lab1.domain.factory;

public class FactoryInitializer {
    public static void initializeFactories() {
        try {
            Class.forName("lab1.domain.factory.GoblinFactory");
            Class.forName("lab1.domain.factory.SkeletonFactory");
            Class.forName("lab1.domain.factory.OrcFactory");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
