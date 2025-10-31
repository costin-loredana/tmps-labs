package lab1.domain.factory;

public class OrcFactory extends EnemyFactory {

    static {EnemyFactory.register("Orc", OrcFactory::new);}

    @Override
    public Enemy createEnemy() {
        return new Orc();
    }
}

