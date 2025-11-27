package lab1.domain.strategy;

import java.util.Map;
import java.util.function.Supplier;

public class StrategyRegistry {

    public static final Supplier<IAttackStrategy> DEFAULT_STRATEGY = NormalAttackStrategy::new;

    public static final Map<String, Supplier<IAttackStrategy>> PLAYER_STRATEGIES =
        Map.of(
            "Warrior", NormalAttackStrategy::new,
            "Mage", CriticalAttackStrategy::new,
            "Rogue", AggressiveAttackStrategy::new
        );

    public static final Map<String, Supplier<IAttackStrategy>> ENEMY_STRATEGIES =
        Map.of(
            "Goblin", DefensiveAttackStrategy::new,
            "Skeleton", DefensiveAttackStrategy::new,
            "Orc", AggressiveAttackStrategy::new,
            "Vampire", CriticalAttackStrategy::new
        );
}
