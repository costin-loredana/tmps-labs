package lab1.domain.core;

import lab1.domain.combat.IFighter;
import lab1.domain.observer.BattleEvent;
import lab1.domain.observer.Publisher;

public class CombatManager {

    private static volatile CombatManager instance;

    private final Publisher publisher = new Publisher();
    public Publisher getPublisher() { return publisher; }

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

    public void battle(IFighter player, IFighter enemy) {

        publisher.notifySubscribers(
            new BattleEvent(
                BattleEvent.Type.ROUND_START,
                null, null, 0,
                "Battle started between " + player.getName() + " and " + enemy.getName()
            )
        );

        while (player.isAlive() && enemy.isAlive()) {

            int damageToEnemy = player.processAttack(enemy);

            enemy.takeDamage(damageToEnemy);
            publisher.notifySubscribers(
                new BattleEvent(
                    BattleEvent.Type.DAMAGE,
                    player,
                    enemy,
                    damageToEnemy,
                    player.getName() + " hits " + enemy.getName() +
                    " for " + damageToEnemy + " damage."
                )
            );

            if (!enemy.isAlive()) {
                publisher.notifySubscribers(
                    new BattleEvent(
                        BattleEvent.Type.DEATH,
                        player,
                        enemy,
                        0,
                        enemy.getName() + " has been defeated!"
                    )
                );
                break;
            }

            int damageToPlayer = enemy.processAttack(player);

            player.takeDamage(damageToPlayer);
            publisher.notifySubscribers(
                new BattleEvent(
                    BattleEvent.Type.DAMAGE,
                    enemy,
                    player,
                    damageToPlayer,
                    enemy.getName() + " hits " + player.getName() +
                    " for " + damageToPlayer + " damage."
                )
            );

            if (!player.isAlive()) {
                publisher.notifySubscribers(
                    new BattleEvent(
                        BattleEvent.Type.DEATH,
                        enemy,
                        player,
                        0,
                        player.getName() + " has been defeated!"
                    )
                );
                break;
            }

            if (player.getState() != null) player.getState().handleTurn();
            if (enemy.getState() != null) enemy.getState().handleTurn();
        }

        publisher.notifySubscribers(
            new BattleEvent(
                BattleEvent.Type.BATTLE_END,
                null, null, 0,
                "Battle ended"
            )
        );
    }

}
