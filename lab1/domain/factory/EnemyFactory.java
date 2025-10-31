package lab1.domain.factory;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class EnemyFactory {
    private static final Map<String, Supplier<EnemyFactory>> registry = new HashMap<>();

    public static void register(String key, Supplier<EnemyFactory> supplier) {
        registry.put(key, supplier);
    }

    public static EnemyFactory getFactory(String key) {
        Supplier<EnemyFactory> supplier = registry.get(key);
        if (supplier == null)
            throw new IllegalArgumentException("No factory registered for: " + key);
        return supplier.get();
    }

    public abstract Enemy createEnemy();
}


