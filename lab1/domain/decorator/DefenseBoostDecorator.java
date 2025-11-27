package lab1.domain.decorator;

import lab1.domain.combat.IFighter;

public class DefenseBoostDecorator extends FighterDecorator {

    private final int bonus;

    public DefenseBoostDecorator(IFighter wrapped, int bonus) {
        super(wrapped);
        this.bonus = bonus;
    }

    @Override
    public int getDefense() {
        return super.getDefense() + bonus;
    }
}

