package lab1.domain.state;

public class DefensiveState extends FighterStateBase {

    @Override
    public void enter() {
        System.out.println(fighter.getName() + " takes a DEFENSIVE stance!");
    }

    @Override
    public int modifyIncomingDamage(int dmg) {
        return (int)(dmg * 0.7);
    }
}
