package lab1.domain.services;

import lab1.domain.factory.Enemy;
import lab1.domain.factory.EnemyFactory;
import lab1.domain.core.GameConfig;
import lab1.domain.strategy.StrategyRegistry;

import java.util.Scanner;

public class EnemyCreationService {

    public Enemy chooseEnemy(Scanner scanner) {
        System.out.println("\nChoose your enemy:");
        GameConfig.ENEMY_OPTIONS.forEach((k, v) -> System.out.println(k + ") " + v));

        System.out.print("> ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String type = GameConfig.ENEMY_OPTIONS.getOrDefault(choice, "Goblin");
        EnemyFactory factory = EnemyFactory.getFactory(type);

        Enemy enemy = factory.createEnemy();

        enemy.setAttackStrategy(
            StrategyRegistry.ENEMY_STRATEGIES
                .getOrDefault(enemy.getName(), StrategyRegistry.DEFAULT_STRATEGY)
                .get()
        );

        return enemy;
    }
}
