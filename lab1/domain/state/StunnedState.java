package lab1.domain.state;

public class StunnedState extends FighterStateBase {

    private int turns = 1;

    @Override
    public void enter() {
        System.out.println(fighter.getName() + " is STUNNED and cannot act!");
    }

    @Override
    public boolean canAct() {
        return false;
    }

    @Override
    public void handleTurn() {
        if (turns > 0) {
            turns--;
        } else {
            fighter.changeState(new NormalState());
        }
    }

    @Override
    public int modifyOutgoingDamage(int dmg) {
        System.out.println("[STATE] " + fighter.getName() + " outgoing dmg blocked (STUNNED)");
        return 0; 
    }
}
