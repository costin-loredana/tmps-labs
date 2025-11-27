package lab1.domain.observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private final List<ISubscriber> subscribers = new ArrayList<>();
    public void subscribe(ISubscriber s){
        if(!subscribers.contains(s)){
            subscribers.add(s);
        }
    }
    public void unsubscribe(ISubscriber s){
        subscribers.remove(s);
    }
    public void notifySubscribers(BattleEvent event){
        for (ISubscriber s: subscribers){
            s.update(event);
        }
    }
    public void printSubscribers() {
        System.out.println("Active subscribers:");
        if (subscribers.isEmpty()) {
            System.out.println(" - (none)");
            return;
        }

        for (ISubscriber s : subscribers) {
            System.out.println(" - " + s.getClass().getSimpleName());
        }
    }

}
