package lab1.domain.combat;

public interface IFighter {
    String getName();
    int getHp();
    int getAttack();
    int getDefense();
    boolean isAlive();
    void takeDamage(int dmg);
}
