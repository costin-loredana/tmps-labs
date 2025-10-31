package lab1.domain.core;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameConfig {
    private GameConfig() {}

    public static final Map<Integer, String> CLASS_OPTIONS;
    public static final Map<Integer, String> ENEMY_OPTIONS;

    static {
        CLASS_OPTIONS = new LinkedHashMap<>();
        CLASS_OPTIONS.put(1, "Warrior");
        CLASS_OPTIONS.put(2, "Mage");
        CLASS_OPTIONS.put(3, "Rogue");

        ENEMY_OPTIONS = new LinkedHashMap<>();
        ENEMY_OPTIONS.put(1, "Goblin");
        ENEMY_OPTIONS.put(2, "Skeleton");
        ENEMY_OPTIONS.put(3, "Orc");
    }
}
