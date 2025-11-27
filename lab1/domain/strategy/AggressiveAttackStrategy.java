package lab1.domain.strategy;

import java.util.Random;
import lab1.domain.combat.IFighter;

public class AggressiveAttackStrategy implements IAttackStrategy {

    private static final Random rand = new Random();

    @Override
    public int calculateDamage(IFighter attacker, IFighter defender) {
        int base = attacker.getAttack() + 3; 
        int variance = rand.nextInt(11) - 5; 
        return Math.max(base - defender.getDefense() + variance, 1);
    }
}
