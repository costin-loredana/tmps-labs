package lab1.domain.decorator;

import lab1.domain.combat.IFighter;

public class AttackBoostDecorator extends FighterDecorator {

    private final int bonus;

    public AttackBoostDecorator(IFighter wrapped, int bonus) {
        super(wrapped);
        this.bonus = bonus;
    }

    @Override
    public int getAttack() {
        return super.getAttack() + bonus;
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + " +AttackBoost(" + bonus + ")";
    }
}

