package lab1.domain.models;

public class Player {
    private String name;
    private String playerClass;
    private int hp;
    private int attack;
    private int defense;

    Player(String name, String playerClass, int hp, int attack, int defense) {
        this.name = name;
        this.playerClass = playerClass;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public String toString() {
        return name + " the " + playerClass +
                "\nHP: " + hp + " | ATK: " + attack + " | DEF: " + defense;
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
    }
    public boolean isAlive() {
        return hp > 0;
    }
    
    public String getName() { return name; }
    public String getPlayerClass() { return playerClass; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }

}
