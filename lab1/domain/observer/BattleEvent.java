package lab1.domain.observer;

import lab1.domain.combat.IFighter;

public class BattleEvent {
    public enum Type {
        DAMAGE, DEATH, ROUND_START, BATTLE_END, BUFF_EXPIRED
    }
    public final Type type;
    public final IFighter attacker;
    public final IFighter defender;
    public final int damage;
    public final String message;

    public BattleEvent(Type type, IFighter attacker, IFighter defender, int damage, String message){
        this.type = type;
        this.attacker = attacker;
        this.defender = defender;
        this.damage = damage;
        this.message = message;
    }

}
