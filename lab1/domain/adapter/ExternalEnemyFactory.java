package lab1.domain.adapter;

import lab1.domain.factory.Enemy;
import lab1.domain.factory.EnemyFactory;

public class ExternalEnemyFactory extends EnemyFactory {

    private final ExternalEnemyData data;

    public ExternalEnemyFactory(ExternalEnemyData data) {
        this.data = data;
    }

    @Override
    public Enemy createEnemy() {
        return new ExternalEnemyAdapter(data);
    }
}

