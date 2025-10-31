package lab1.domain.factory;

public class Goblin extends Enemy {
    public Goblin() {
        super("Goblin", 50, 10, 5);
    }

    @Override
    public void taunt() {
        System.out.println("Goblin snarls: You won't get past me!");
    }
    
}
