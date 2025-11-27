package lab1.domain.services;

import lab1.domain.combat.IFighter;
import lab1.domain.core.CombatManager;

public class CombatService {

    public void startBattle(IFighter player, IFighter enemy) {
        CombatManager.getInstance().battle(player, enemy);
    }
}
