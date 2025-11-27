package lab1.domain.models;

import lab1.domain.core.PlayerStats;


public class PlayerBuilder implements IBuilder {
    private String name;
    private String playerClass;
    private int hp;
    private int attack;
    private int defense;

    
    @Override
    public IBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public IBuilder setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
        return this;
    }

    @Override
    public IBuilder applyClassDefaults() {
        PlayerStats.Stats stats = PlayerStats.CLASS_STATS.getOrDefault(
            playerClass,
            new PlayerStats.Stats(90, 10, 10)
        );
        this.hp = stats.hp;
        this.attack = stats.attack;
        this.defense = stats.defense;
        return this;
    }

    @Override
    public Player build() {
        return new Player(name, playerClass, hp, attack, defense);
    }
}
