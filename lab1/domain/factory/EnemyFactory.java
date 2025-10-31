package lab1.domain.factory;

public abstract class EnemyFactory {
    public abstract Enemy createEnemy();

    public static EnemyFactory getFactory(String type) {
        return switch (type) {
            case "Goblin" -> new GoblinFactory();
            case "Skeleton" -> new SkeletonFactory();
            case "Orc" -> new OrcFactory();
            default -> new GoblinFactory();
        };
    }
}

class GoblinFactory extends EnemyFactory {
    @Override
    public Enemy createEnemy() {
        return new Goblin();
    }
}

class SkeletonFactory extends EnemyFactory {
    @Override
    public Enemy createEnemy() {
        return new Skeleton();
    }
}

class OrcFactory extends EnemyFactory {
    @Override
    public Enemy createEnemy() {
        return new Orc();
    }
}
