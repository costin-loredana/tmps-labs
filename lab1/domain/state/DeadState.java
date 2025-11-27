package lab1.domain.state;

public class DeadState extends FighterStateBase {

    @Override
    public void enter() {
    }

    @Override
    public boolean canAct() {
        return false;
    }

    @Override
    public int modifyOutgoingDamage(int dmg) {
        return 0;
    }

    @Override
    public int modifyIncomingDamage(int dmg) {
        return dmg;
    }
}
