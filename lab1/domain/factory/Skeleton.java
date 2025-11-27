package lab1.domain.factory;

public class Skeleton extends Enemy {
    public Skeleton() {
        super("Skeleton", 70, 15, 8);
    }
    @Override
    public void taunt() {
        System.out.println("The Skeleton rattles its bones ominously!");
    }
}
