package lab1.domain.observer;

public interface ISubscriber {
    void update(BattleEvent event);
}
