package lab1.domain.services;

import lab1.domain.combat.IFighter;
import lab1.domain.decorator.PoisonDecorator;

public class EnemyEffectService {

    public IFighter applyEffects(IFighter enemy) {
        System.out.println("\n== Enemy Status Effects ==");
        System.out.println(enemy.getName() + " is poisoned!");

        return new PoisonDecorator(enemy, 1);
    }
}
