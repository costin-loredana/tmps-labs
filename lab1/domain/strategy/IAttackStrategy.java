package lab1.domain.strategy;

import lab1.domain.combat.IFighter;

public interface IAttackStrategy {
    int calculateDamage(IFighter attacker, IFighter defender);
}
