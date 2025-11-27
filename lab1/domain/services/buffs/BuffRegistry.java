package lab1.domain.services.buffs;

import lab1.domain.combat.IFighter;
import lab1.domain.decorator.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class BuffRegistry {

    public static final Map<Integer, IBuffOption> BUFFS = new LinkedHashMap<>();

    static {
        BUFFS.put(1, new IBuffOption() {
            @Override public String getLabel() { return "+5 Attack"; }
            @Override public IFighter apply(IFighter f) { return new AttackBoostDecorator(f, 5); }
        });

        BUFFS.put(2, new IBuffOption() {
            @Override public String getLabel() { return "+3 Defense"; }
            @Override public IFighter apply(IFighter f) { return new DefenseBoostDecorator(f, 3); }
        });

        BUFFS.put(3, new IBuffOption() {
            @Override public String getLabel() { return "Critical Hit (25%)"; }
            @Override public IFighter apply(IFighter f) { return new CriticalHitDecorator(f, 0.25, 2.0); }
        });

        BUFFS.put(4, new IBuffOption() {
            @Override public String getLabel() { return "Poison Blade (+2 dmg)"; }
            @Override public IFighter apply(IFighter f) { return new PoisonDecorator(f, 2); }
        });

        BUFFS.put(5, new IBuffOption() {
            @Override public String getLabel() { return "Rage Mode (+10 ATK for 3 turns)"; }
            @Override public IFighter apply(IFighter f) { return new TimedAttackDecorator(f, 10, 3); }
        });
    }
}
