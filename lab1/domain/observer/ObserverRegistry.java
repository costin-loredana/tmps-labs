package lab1.domain.observer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import lab1.domain.services.ObserverSettingsService;

public class ObserverRegistry {

    private final Map<BooleanSupplier, Supplier<ISubscriber>> registry = new LinkedHashMap<>();

    public ObserverRegistry(ObserverSettingsService.Settings settings) {

        registry.put(() -> settings.enableLogging, ConsoleBattleLoger::new);
        registry.put(() -> settings.enableStats, StatisticsObserver::new);
        registry.put(() -> settings.enableAchievements, AchievementObserver::new);
        registry.put(() -> settings.enableBuffExpiration, BuffExpirationObserver::new);
    }

    public Map<BooleanSupplier, Supplier<ISubscriber>> getRegistry() {
        return registry;
    }
}
