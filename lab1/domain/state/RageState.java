package lab1.domain.state;

public class RageState extends FighterStateBase {

    @Override
    public void enter() {
        System.out.println(fighter.getName() + " enters RAGE MODE!");
    }

    @Override
    public int modifyOutgoingDamage(int dmg) {
        int boosted = (int)(dmg * 1.5);
        System.out.println("[STATE] " + fighter.getName() +
        " boosts damage " + dmg + " → " + boosted);
        return boosted;
    }   
}
