package lab1.domain.observer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AchievementObserver implements ISubscriber {

    private boolean firstBlood = false;
    private int highestHit = 0;

    private final Map<BattleEvent.Type, Consumer<BattleEvent>> handlers = new HashMap<>();

    public AchievementObserver() {

        handlers.put(BattleEvent.Type.DAMAGE, this::onDamage);
        handlers.put(BattleEvent.Type.DEATH, this::onDeath);
    }

    @Override
    public void update(BattleEvent event) {
        handlers.getOrDefault(event.type, e -> {}).accept(event);
    }

    private void onDamage(BattleEvent event) {
        if (!firstBlood) {
            System.out.println("[ACHIEVEMENT] First Blood!");
            firstBlood = true;
        }

        if (event.damage > highestHit) {
            highestHit = event.damage;

            if (event.damage >= 20) {
                System.out.println("[ACHIEVEMENT] Heavy Hitter! (" + event.damage + ")");
            }
        }
    }

    private void onDeath(BattleEvent event) {
        System.out.println("[ACHIEVEMENT] Killing Blow!");
    }
}
