package lab1.domain.observer;

public class ConsoleBattleLoger implements ISubscriber {
    @Override
    public void update(BattleEvent event){
        System.out.println("[LOG]" + event.message);
    }
}
    