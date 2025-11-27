package lab1.domain.state;

import lab1.domain.combat.IFighter;

public abstract class FighterStateBase implements IFighterState {

    protected IFighter fighter;

    @Override
    public void setContext(IFighter fighter) {
        this.fighter = fighter;
    }

    @Override
    public void enter() {}

    @Override
    public void handleTurn() {}

    @Override
    public boolean canAct() {
        return true;
    }

    @Override
    public int modifyOutgoingDamage(int dmg) {
        return dmg;
    }

    @Override
    public int modifyIncomingDamage(int dmg) {
        return dmg;
    }
}
