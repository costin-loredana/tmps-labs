package lab1.domain.decorator;

import lab1.domain.combat.IFighter;

public class PoisonDecorator extends FighterDecorator {

    private final int poisonDamage;

    public PoisonDecorator(IFighter wrapped, int poisonDamage) {
        super(wrapped);
        this.poisonDamage = poisonDamage;
    }

    @Override
    public void takeDamage(int dmg) {
        wrapped.takeDamage(dmg);
        wrapped.takeDamage(poisonDamage);
        System.out.println(">> " + wrapped.getName() + " suffers " + poisonDamage + " poison damage!");
    }
}
