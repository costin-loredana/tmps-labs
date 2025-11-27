package lab1.domain.factory;

import lab1.domain.combat.IFighter;
import lab1.domain.strategy.IAttackStrategy;
import lab1.domain.state.*;

public abstract class Enemy implements IFighter {

    protected String name;
    protected int hp;
    protected int attack;
    protected int defense;

    protected IAttackStrategy attackStrategy;

    protected IFighterState state = new NormalState();

    public Enemy(String name, int hp, int attack, int defense) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;

        state.setContext(this);
        state.enter();
    }

    @Override
    public IFighterState getState() {
        return state;
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


    @Override
    public int processAttack(IFighter target) {
        if (!canAct()) {
            System.out.println(name + " cannot act due to state!");
            return 0;
        }

        int baseDamage = attackStrategy.calculateDamage(this, target);
        return applyOutgoingDamageModifiers(baseDamage);
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


    @Override public String getName() { return name; }
    @Override public int getHp() { return hp; }
    @Override public int getAttack() { return attack; }
    @Override public int getDefense() { return defense; }
    @Override public boolean isAlive() { return hp > 0; }


    @Override
    public void setAttackStrategy(IAttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    @Override
    public IAttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    @Override
    public String getFighterType() {
        return name; 
    }


    public abstract void taunt();

    @Override
    public String toString() {
        return name + " (HP: " + hp + ", ATK: " + attack +
                ", DEF: " + defense + ")";
    }
}
