package lab1.domain.state;

public class NormalState extends FighterStateBase {

    @Override
    public void enter() {
        System.out.println(fighter.getName() + " is now in a Normal state.");
    }
}
