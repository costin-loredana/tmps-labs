package lab1.domain.factory;

import java.nio.file.Path;

import lab1.domain.adapter.ExternalEnemyData;
import lab1.domain.adapter.ExternalEnemyFactory;
import lab1.domain.adapter.JsonEnemyLoader;

public class FactoryInitializer {

    public static void initializeFactories() {
        try {
            // Register built-in enemies
            Class.forName("lab1.domain.factory.GoblinFactory");
            Class.forName("lab1.domain.factory.SkeletonFactory");
            Class.forName("lab1.domain.factory.OrcFactory");

            // Load external JSON enemy
            JsonEnemyLoader loader = new JsonEnemyLoader();
            ExternalEnemyData data = loader.load(Path.of("vampire.json"));

            // Register adapted enemy
            EnemyFactory.register("Vampire", () -> new ExternalEnemyFactory(data));

            System.out.println("[FactoryInitializer] Loaded external enemy: " + data.name);

        } catch (Exception e) {
            System.err.println("[FactoryInitializer] ERROR loading external enemy:");
            e.printStackTrace();
        }
    }
}
