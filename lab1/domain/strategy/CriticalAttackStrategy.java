package lab1.domain.strategy;

import java.util.Random;
import lab1.domain.combat.IFighter;

public class CriticalAttackStrategy implements IAttackStrategy {

    private static final Random rand = new Random();

    @Override
    public int calculateDamage(IFighter attacker, IFighter defender) {
        int base = attacker.getAttack();
        boolean crit = rand.nextDouble() < 0.25; 

        if (crit) {
            int dmg = base * 2;
            System.out.println(">> CRITICAL STRATEGY HIT: " + dmg);
            return dmg;
        }

        int variance = rand.nextInt(7) - 3;
        return Math.max(base - defender.getDefense() + variance, 1);
    }
}
