package lab1.domain.factory;

public abstract class Enemy {
    protected String name;
    protected int hp;
    protected int attack;
    protected int defense;

    public Enemy(String name, int hp, int attack, int defense) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public abstract void taunt();

    @Override
    public String toString() {
        return name + " (HP: " + hp + ", ATK: " + attack + ", DEF: " + defense + ")";
    }
}
