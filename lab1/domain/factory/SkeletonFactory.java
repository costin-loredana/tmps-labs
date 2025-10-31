package lab1.domain.factory;

public class SkeletonFactory extends EnemyFactory {
    static { EnemyFactory.register("Skeleton", SkeletonFactory::new); }

    @Override
    public Enemy createEnemy() {
        return new Skeleton();
    }
}
