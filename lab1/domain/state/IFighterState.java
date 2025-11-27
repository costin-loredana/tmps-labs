package lab1.domain.state;

import lab1.domain.combat.IFighter;

public interface IFighterState {

    void setContext(IFighter fighter);

    void enter();
    void handleTurn(); 

    boolean canAct();
    int modifyOutgoingDamage(int dmg);
    int modifyIncomingDamage(int dmg);
}
