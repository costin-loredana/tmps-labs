package lab1.domain.adapter;

import lab1.domain.factory.Enemy;

public class ExternalEnemyAdapter extends Enemy {

    private final ExternalEnemyData data;

    public ExternalEnemyAdapter(ExternalEnemyData data) {
        super(
            data.name != null ? data.name : "Unknown Enemy",
            data.hp > 0 ? data.hp : 60,
            data.attack > 0 ? data.attack : 10,
            data.defense > 0 ? data.defense : 5
        );

        this.data = data;
    }

    @Override
    public void taunt() {
        System.out.println("[Adapter] >> " + data.taunt);
    }
}

