package lab1.domain.core;

import lab1.domain.factory.Enemy;
import lab1.domain.models.Player;
import java.util.Random;

public class CombatManager {
    private static volatile CombatManager instance;
    private final Random random = new Random();

    private CombatManager() {}

    public static CombatManager getInstance() {
        if (instance == null) {
            synchronized (CombatManager.class) {
                if (instance == null)
                    instance = new CombatManager();
            }
        }
        return instance;
    }

    public void battle(Player player, Enemy enemy) {
        System.out.println("\nBattle started between " + player + " and " + enemy);

        while (player.isAlive() && enemy.isAlive()) {
            // --- Option 1: Random variation of ±2 damage ---
            int damageToEnemy = Math.max((player.getAttack() - enemy.getDefense()) + random.nextInt(9) - 4, 1);
            int damageToPlayer = Math.max((enemy.getAttack() - player.getDefense()) + random.nextInt(9) - 4, 1);

            // Apply damage
            enemy.takeDamage(damageToEnemy);
            System.out.println(player.getName() + " hits " + enemy.getName() + " for " + damageToEnemy + " damage.");

            if (!enemy.isAlive()) break;

            player.takeDamage(damageToPlayer);
            System.out.println(enemy.getName() + " hits " + player.getName() + " for " + damageToPlayer + " damage.");
        }

        System.out.println("==Battle Over==");
        if (player.isAlive())
            System.out.println(player.getName() + " wins!");
        else
            System.out.println(enemy.getName() + " wins!");
    }
}
