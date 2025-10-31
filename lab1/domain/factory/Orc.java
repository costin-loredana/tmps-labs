package lab1.domain.factory;

public class Orc extends Enemy {
    public Orc() {
        super("Orc", 100, 20, 12);
    }

    @Override
    public void taunt() {
        System.out.println("The Orc roars with fury, shaking the ground!");
    }
    
}
