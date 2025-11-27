package lab1.domain.combat;

import lab1.domain.state.IFighterState;
import lab1.domain.strategy.IAttackStrategy;

public interface IFighter {

    String getName();
    int getHp();
    int getAttack();
    int getDefense();
    boolean isAlive();
    String getFighterType();

    void takeDamage(int dmg);    
    int processAttack(IFighter target); 

    void setAttackStrategy(IAttackStrategy attackStrategy);
    IAttackStrategy getAttackStrategy();

    void changeState(IFighterState newState);

    int applyOutgoingDamageModifiers(int dmg);
    int applyIncomingDamageModifiers(int dmg);
    boolean canAct();

    IFighterState getState();

    default String getDescription() {
        return getName();
    }
}
