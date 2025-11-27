package lab1.domain.models;

import lab1.domain.combat.IFighter;
import lab1.domain.strategy.IAttackStrategy;
import lab1.domain.state.*;

public class Player implements IFighter {

    private String name;
    private String playerClass;
    private int hp;
    private int attack;
    private int defense;

    private IAttackStrategy attackStrategy;

    private IFighterState state = new NormalState();

    public Player(String name, String playerClass, int hp, int attack, int defense) {
        this.name = name;
        this.playerClass = playerClass;
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
            System.out.println(name + " cannot act due to a state effect!");
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

    @Override
    public boolean isAlive() {
        return hp > 0;
    }


    @Override
    public String getName() { return name; }

    public String getPlayerClass() { return playerClass; }

    @Override
    public int getHp() { return hp; }

    @Override
    public int getAttack() { return attack; }

    @Override
    public int getDefense() { return defense; }

    @Override
    public String getFighterType() { return playerClass; }

    @Override
    public void setAttackStrategy(IAttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    @Override
    public IAttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    @Override
    public String toString() {
        return name + " the " + playerClass +
            "\nHP: " + hp + " | ATK: " + attack + " | DEF: " + defense;
    }
}
