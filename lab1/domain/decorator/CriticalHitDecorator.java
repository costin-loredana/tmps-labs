package lab1.domain.decorator;

import lab1.domain.combat.IFighter;

public class CriticalHitDecorator extends FighterDecorator {

    private final double chance;
    private final double multiplier;

    public CriticalHitDecorator(IFighter wrapped, double chance, double multiplier) {
        super(wrapped);
        this.chance = chance;
        this.multiplier = multiplier;
    }

    @Override
    public int getAttack() {
        int base = super.getAttack();
        if (Math.random() < chance) {
            int crit = (int)(base * multiplier);
            System.out.println(">> CRITICAL HIT: " + crit);
            return crit;
        }
        return base;
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() +
            " +Crit(" + (int)(chance*100) + "% x" + multiplier + ")";
    }
}

