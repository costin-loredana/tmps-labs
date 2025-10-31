package lab1.domain.factory;

public class GoblinFactory extends EnemyFactory {
    static { EnemyFactory.register("Goblin", GoblinFactory::new); }

    @Override
    public Enemy createEnemy() {
        return new Goblin();
    }
}
