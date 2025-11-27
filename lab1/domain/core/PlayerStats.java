package lab1.domain.core;

import java.util.Map;

public class PlayerStats {
    private PlayerStats() {}

    public static final class Stats {
        public final int hp;
        public final int attack;
        public final int defense;

        public Stats(int hp, int attack, int defense) {
            this.hp = hp;
            this.attack = attack;
            this.defense = defense;
        }
    }

    public static final Map<String, Stats> CLASS_STATS = Map.of(
        "Warrior", new Stats(120, 15, 10),
        "Mage", new Stats(80, 25, 5),
        "Rogue", new Stats(100, 20, 8)
    );

}
