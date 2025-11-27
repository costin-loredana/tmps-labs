package lab1.domain.facade;

import lab1.domain.services.*;
import lab1.domain.combat.IFighter;
import lab1.domain.factory.FactoryInitializer;
import lab1.domain.factory.Enemy;
import lab1.domain.core.CombatManager;
import lab1.domain.observer.*;
import lab1.domain.strategy.*;

import java.util.Scanner;

public class GameFacade {

    private final Scanner scanner;
    private final PlayerCreationService playerService = new PlayerCreationService();
    private final EnemyCreationService enemyService = new EnemyCreationService();
    private final BuffService buffService = new BuffService();
    private final EnemyEffectService effectService = new EnemyEffectService();
    private final CombatService combatService = new CombatService();

    public GameFacade(Scanner scanner) {
        this.scanner = scanner;
        FactoryInitializer.initializeFactories();
    }

    public void start() {
        System.out.println("== Dungeon Duel ==");

        IFighter player = playerService.createPlayer(scanner);
        player = buffService.applyBuffs(scanner, player);

        IFighter enemy = enemyService.chooseEnemy(scanner);
        enemy = effectService.applyEffects(enemy);

        if (enemy instanceof Enemy e) {
            System.out.println("\nAn enemy appears!");
            System.out.println(e);
            e.taunt();
        }

        IAttackStrategy playerStrategy =
            StrategyRegistry.PLAYER_STRATEGIES
                .getOrDefault(player.getFighterType(), StrategyRegistry.DEFAULT_STRATEGY)
                .get();

        IAttackStrategy enemyStrategy =
            StrategyRegistry.ENEMY_STRATEGIES
                .getOrDefault(enemy.getFighterType(), StrategyRegistry.DEFAULT_STRATEGY)
                .get();

        player.setAttackStrategy(playerStrategy);
        enemy.setAttackStrategy(enemyStrategy);

        ObserverSettingsService observerSettingsService = new ObserverSettingsService();
        ObserverSettingsService.Settings settings =
            observerSettingsService.getObserverSettings(scanner);

        CombatManager cm = CombatManager.getInstance();
        Publisher pub = cm.getPublisher();

        ObserverRegistry registry = new ObserverRegistry(settings);

        registry.getRegistry().forEach((condition, factory) -> {
            if (condition.getAsBoolean()) {
                pub.subscribe(factory.get());
            }
        });

        pub.printSubscribers();

        combatService.startBattle(player, enemy);
    }
}
