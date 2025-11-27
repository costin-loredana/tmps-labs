package lab1.domain.observer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class BuffExpirationObserver implements ISubscriber {

    private final Map<BattleEvent.Type, Consumer<BattleEvent>> handlers = new HashMap<>();

    public BuffExpirationObserver() {
        handlers.put(BattleEvent.Type.BUFF_EXPIRED, this::onBuffExpired);
    }

    @Override
    public void update(BattleEvent event) {
        handlers.getOrDefault(event.type, e -> {}).accept(event);
    }

    private void onBuffExpired(BattleEvent event) {
        System.out.println("[OBSERVER] Buff expired for: " + event.attacker.getName());
    }
}
