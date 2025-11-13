package lab1.domain.factory;

import java.nio.file.Path;

import lab1.domain.adapter.ExternalEnemyData;
import lab1.domain.adapter.ExternalEnemyFactory;
import lab1.domain.adapter.JsonEnemyLoader;

public class FactoryInitializer {
    public static void initializeFactories() {
        try {
            Class.forName("lab1.domain.factory.GoblinFactory");
            Class.forName("lab1.domain.factory.SkeletonFactory");
            Class.forName("lab1.domain.factory.OrcFactory");

            // Load external JSON enemy
            JsonEnemyLoader loader = new JsonEnemyLoader();
            ExternalEnemyData data = loader.load(Path.of("vampire.json"));

            EnemyFactory.register("Vampire", () -> new ExternalEnemyFactory(data));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
