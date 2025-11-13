package lab1.domain.decorator;

import lab1.domain.combat.IFighter;

public abstract class FighterDecorator implements IFighter {

    protected final IFighter wrapped;

    public FighterDecorator(IFighter wrapped) {
        this.wrapped = wrapped;
    }

    public IFighter getWrapped() {
        return wrapped;
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
    public void takeDamage(int dmg) {
        wrapped.takeDamage(dmg);
    }

    @Override
    public boolean isAlive() {
        return wrapped.isAlive();
    }
}
