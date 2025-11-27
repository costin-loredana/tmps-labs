package lab1.domain.decorator;

import lab1.domain.combat.IFighter;
import lab1.domain.strategy.IAttackStrategy;
import lab1.domain.state.IFighterState;

public abstract class FighterDecorator implements IFighter {

    protected final IFighter wrapped;

    public FighterDecorator(IFighter wrapped) {
        this.wrapped = wrapped;
    }


    @Override
    public String getName() {
        return wrapped.getName();
    }

    @Override
    public int getHp() {
        return wrapped.getHp();
    }

    @Override
    public int getAttack() {
        return wrapped.getAttack();
    }

    @Override
    public int getDefense() {
        return wrapped.getDefense();
    }

    @Override
    public boolean isAlive() {
        return wrapped.isAlive();
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription();
    }

    @Override
    public String getFighterType() {
        return wrapped.getFighterType();
    }


    @Override
    public void takeDamage(int dmg) {
        wrapped.takeDamage(dmg);
    }

    @Override
    public int processAttack(IFighter target) {
        return wrapped.processAttack(target);
    }


    @Override
    public void setAttackStrategy(IAttackStrategy strategy) {
        wrapped.setAttackStrategy(strategy);
    }

    @Override
    public IAttackStrategy getAttackStrategy() {
        return wrapped.getAttackStrategy();
    }


    @Override
    public IFighterState getState() {
        return wrapped.getState();
    }

    @Override
    public void changeState(IFighterState newState) {
        wrapped.changeState(newState);
    }

    @Override
    public boolean canAct() {
        return wrapped.canAct();
    }

    @Override
    public int applyOutgoingDamageModifiers(int dmg) {
        return wrapped.applyOutgoingDamageModifiers(dmg);
    }

    @Override
    public int applyIncomingDamageModifiers(int dmg) {
        return wrapped.applyIncomingDamageModifiers(dmg);
    }
}
