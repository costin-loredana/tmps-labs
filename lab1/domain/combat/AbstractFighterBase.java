package lab1.domain.combat;

import lab1.domain.strategy.IAttackStrategy;
import lab1.domain.state.*;

public abstract class AbstractFighterBase implements IFighter {

    protected String name;
    protected int hp;
    protected int attack;
    protected int defense;
    protected IAttackStrategy attackStrategy;

    protected IFighterState state;

    public AbstractFighterBase(String name, int hp, int attack, int defense) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;

        this.state = new NormalState();
        this.state.setContext(this);
        this.state.enter();
    }


    @Override
    public void changeState(IFighterState newState) {
        this.state = newState;
        newState.setContext(this);
        newState.enter();
    }

    @Override
    public boolean canAct() {
        return state.canAct();
    }

    @Override
    public int applyOutgoingDamageModifiers(int dmg) {
        return state.modifyOutgoingDamage(dmg);
    }

    @Override
    public int applyIncomingDamageModifiers(int dmg) {
        return state.modifyIncomingDamage(dmg);
    }

    // ---------------- COMBAT ----------------

    @Override
    public int processAttack(IFighter target) {
        if (!canAct()) return 0;
        int base = attackStrategy.calculateDamage(this, target);
        return applyOutgoingDamageModifiers(base);
    }

    @Override
    public void takeDamage(int dmg) {
        dmg = applyIncomingDamageModifiers(dmg);
        hp -= dmg;

        if (hp <= 0) {
            hp = 0;
            changeState(new DeadState());
        }
    }

    @Override public boolean isAlive() { return hp > 0; }
    @Override public String getName() { return name; }
    @Override public int getHp() { return hp; }
    @Override public int getAttack() { return attack; }
    @Override public int getDefense() { return defense; }

    @Override
    public void setAttackStrategy(IAttackStrategy strategy) {
        this.attackStrategy = strategy;
    }

    @Override
    public IAttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    @Override
    public String getFighterType() {
        return name;
    }
}
