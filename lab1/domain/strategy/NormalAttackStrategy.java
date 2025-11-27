package lab1.domain.strategy;

import java.util.Random;

import lab1.domain.combat.IFighter;

public class NormalAttackStrategy implements IAttackStrategy {
    private static final Random rand = new Random();

    @Override
    public int calculateDamage(IFighter attacker, IFighter defender){
        int variance = rand.nextInt(9) - 4;
        return Math.max(attacker.getAttack() - defender.getDefense() + variance, 1);
    }

}
