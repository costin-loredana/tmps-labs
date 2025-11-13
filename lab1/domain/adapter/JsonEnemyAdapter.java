package lab1.domain.adapter;

import lab1.domain.factory.Enemy;

public class JsonEnemyAdapter extends Enemy {

    private final ExternalEnemyData data;

    public JsonEnemyAdapter(ExternalEnemyData data) {
        super(data.name, data.hp, data.attack, data.defense);
        this.data = data;
    }

    @Override
    public void taunt() {
        System.out.println(data.taunt);
    }
}
