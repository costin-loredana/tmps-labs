package lab1.domain.models;

public class PlayerDirector {

    public Player createDefault(String name, String playerClass) {
        return new PlayerBuilder()
                .setName(name)
                .setPlayerClass(playerClass)
                .applyClassDefaults()
                .build();
    }

    public Player createWarrior(String name) {
        return createDefault(name, "Warrior");
    }

    public Player createMage(String name) {
        return createDefault(name, "Mage");
    }

    public Player createRogue(String name) {
        return createDefault(name, "Rogue");
    }
}
