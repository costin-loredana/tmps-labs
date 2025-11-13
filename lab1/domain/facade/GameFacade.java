package lab1.domain.facade;

import java.util.Scanner;

import lab1.domain.core.CombatManager;
import lab1.domain.core.GameConfig;
import lab1.domain.factory.Enemy;
import lab1.domain.factory.EnemyFactory;
import lab1.domain.factory.FactoryInitializer;
import lab1.domain.models.IBuilder;
import lab1.domain.models.Player;
import lab1.domain.models.PlayerBuilder;
import lab1.domain.models.PlayerDirector;

import lab1.domain.combat.IFighter;
import lab1.domain.decorator.FighterDecorator;
import lab1.domain.decorator.AttackBoostDecorator;
import lab1.domain.decorator.DefenseBoostDecorator;
import lab1.domain.decorator.PoisonDecorator;

public class GameFacade {

    private final Scanner scanner;
    private final PlayerDirector director = new PlayerDirector();
    private final IBuilder builder = new PlayerBuilder();
    private final CombatManager combatManager = CombatManager.getInstance();

    public GameFacade(Scanner scanner) {
        this.scanner = scanner;
        FactoryInitializer.initializeFactories();
    }

    public void start() {
        System.out.println("== Dungeon Duel ==");

        IFighter player = createPlayer();
        IFighter enemy = chooseEnemy();

        player = applyPlayerBuffs(player);
        enemy = applyEnemyEffects(enemy);

        System.out.println("\nAn enemy appears!");
        System.out.println(enemy.getName() + 
            " (HP: " + enemy.getHp() +
            ", ATK: " + enemy.getAttack() +
            ", DEF: " + enemy.getDefense() + ")");

        Enemy realEnemy = unwrapEnemy(enemy);
        if (realEnemy != null) {
            realEnemy.taunt();
        }

        combatManager.battle(player, enemy);
    }

    private IFighter createPlayer() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Choose your class:");
        GameConfig.CLASS_OPTIONS.forEach((key, value) ->
            System.out.println(key + ") " + value)
        );

        System.out.print("> ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String playerClass = GameConfig.CLASS_OPTIONS
            .getOrDefault(choice, "Adventurer");

        Player player = director.createDefault(builder, name, playerClass);

        System.out.println("\nPlayer created:");
        System.out.println(player);

        return player;
    }

    private IFighter chooseEnemy() {
        System.out.println("\nChoose your enemy:");
        GameConfig.ENEMY_OPTIONS.forEach((key, value) ->
            System.out.println(key + ") " + value)
        );

        System.out.print("> ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String enemyType = GameConfig.ENEMY_OPTIONS
            .getOrDefault(choice, "Goblin");

        EnemyFactory factory = EnemyFactory.getFactory(enemyType);
        return factory.createEnemy();
    }

    private IFighter applyPlayerBuffs(IFighter player) {
        System.out.println("\n== Player Enhancement ==");
        System.out.println("You found an enchanted sword (+5 ATK)!");
        player = new AttackBoostDecorator(player, 5);

        System.out.println("You equipped reinforced armor (+3 DEF)!");
        player = new DefenseBoostDecorator(player, 3);

        return player;
    }

    private IFighter applyEnemyEffects(IFighter enemy) {
        System.out.println("\n== Enemy Status Effect ==");
        System.out.println(enemy.getName() + " is poisoned (+1 damage per hit)!");
        enemy = new PoisonDecorator(enemy, 1);
        return enemy;
    }

    private Enemy unwrapEnemy(IFighter fighter) {
        while (fighter instanceof FighterDecorator decorator) {
            fighter = decorator.getWrapped();
        }
        return (fighter instanceof Enemy e) ? e : null;
}

}
