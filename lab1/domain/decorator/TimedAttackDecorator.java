package lab1.domain.decorator;

import lab1.domain.combat.IFighter;

public class TimedAttackDecorator extends FighterDecorator {

    private final int bonus;
    private int remainingTurns;

    public TimedAttackDecorator(IFighter wrapped, int bonus, int turns) {
        super(wrapped);
        this.bonus = bonus;
        this.remainingTurns = turns;
    }

    @Override
    public int getAttack() {
        if (remainingTurns > 0) {
            return super.getAttack() + bonus;
        }
        return super.getAttack();
    }

    @Override
    public void takeDamage(int dmg) {
        super.takeDamage(dmg);

        if (remainingTurns > 0) {
            remainingTurns--;
            if (remainingTurns == 0) {
                System.out.println("[BUFF EXPIRED] Attack boost worn off.");
            }
        }
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() +
                " +TimedAttackBoost(" + bonus + ", " + remainingTurns + " turns)";
    }
}

