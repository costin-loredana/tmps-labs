package lab1.domain.observer;

public class StatisticsObserver implements ISubscriber{
    private int totalDamage = 0;

    @Override
    public void update(BattleEvent event) {
        if (event.type == BattleEvent.Type.DAMAGE) {
            totalDamage += event.damage;
            System.out.println("[STATS] Total damage so far: " + totalDamage);
        }
    }

}
