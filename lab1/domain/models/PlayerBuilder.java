package lab1.domain.models;

import lab1.domain.core.PlayerStats;

public class PlayerBuilder {
    private String name;
    private String playerClass;
    private int hp;
    private int attack;
    private int defense;

    public PlayerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PlayerBuilder setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
        return this;
    }

    public PlayerBuilder applyClassDefaults() {
        PlayerStats.Stats stats = PlayerStats.CLASS_STATS.getOrDefault(
            playerClass,
            new PlayerStats.Stats(90, 10, 10) 
        );
        this.hp = stats.hp;
        this.attack = stats.attack;
        this.defense = stats.defense;
        return this;
    }

    public Player build() {
        if (name == null || playerClass == null)
            throw new IllegalStateException("Player must have a name and class");
        return new Player(name, playerClass, hp, attack, defense);
    }
}
