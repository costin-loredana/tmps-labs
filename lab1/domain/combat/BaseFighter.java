package lab1.domain.combat;

import lab1.domain.state.DeadState;
import lab1.domain.state.IFighterState;
import lab1.domain.strategy.IAttackStrategy;

public abstract class BaseFighter implements IFighter {

    protected String name;
    protected int hp;
    protected int attack;
    protected int defense;

    protected IAttackStrategy attackStrategy;
    protected IFighterState state;

    public BaseFighter(
            String name,
            int hp,
            int attack,
            int defense,
            IAttackStrategy attackStrategy,
            IFighterState initialState
    ) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.attackStrategy = attackStrategy;

        changeState(initialState);
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

    @Override public void setAttackStrategy(IAttackStrategy attackStrategy) { this.attackStrategy = attackStrategy; }
    @Override public IAttackStrategy getAttackStrategy() { return attackStrategy; }
}
