package lab1.domain.core;

import static lab1.domain.core.GameConfig.CLASS_OPTIONS;
import static lab1.domain.core.GameConfig.ENEMY_OPTIONS;

import java.util.Scanner;

import lab1.domain.factory.Enemy;
import lab1.domain.factory.EnemyFactory;
import lab1.domain.factory.FactoryInitializer;
import lab1.domain.models.Player;
import lab1.domain.models.PlayerBuilder;
import lab1.domain.models.PlayerDirector;
import lab1.domain.models.IBuilder;

public class GameManager {
    private static volatile GameManager instance;
    private final Scanner scanner;
    private final CombatManager combatManager;

    private GameManager(Scanner scanner, CombatManager combatManager) {
        this.scanner = scanner;
        this.combatManager = combatManager;
    }

    public static GameManager getInstance(Scanner scanner, CombatManager combatManager) {
        if (instance == null) {
            synchronized (GameManager.class) {
                if (instance == null) { instance = new GameManager(scanner, combatManager);}
            }
        }
        return instance;
    }

    public void startGame() {
        System.out.println("== Dungeon Duel ==");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Choose your class:");
        CLASS_OPTIONS.forEach((key, value) -> System.out.println(key + ") " + value));
        System.out.print("> ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String playerClass = CLASS_OPTIONS.getOrDefault(choice, "Adventurer");

        PlayerDirector director = new PlayerDirector();
        IBuilder builder = new PlayerBuilder();
        Player player = director.createDefault(builder, name, playerClass);

        System.out.println("\nPlayer created:");
        System.out.println(player);

        System.out.println("\nChoose your enemy:");
        ENEMY_OPTIONS.forEach((key, value) -> System.out.println(key + ") " + value));
        System.out.print("> ");
        int enemyChoice = scanner.nextInt();
        scanner.nextLine();

        FactoryInitializer.initializeFactories();

        String enemyType = ENEMY_OPTIONS.getOrDefault(enemyChoice, "Goblin");
        System.out.println("\nGame setup complete!");

        EnemyFactory enemyFactory = EnemyFactory.getFactory(enemyType);
        Enemy enemy = enemyFactory.createEnemy();

        System.out.println("\nAn enemy appears!");
        System.out.println(enemy);
        enemy.taunt();

        combatManager.battle(player, enemy);
    }
}
