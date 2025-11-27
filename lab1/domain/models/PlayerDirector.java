package lab1.domain.models;

public class PlayerDirector {

    public Player createDefault(IBuilder builder, String name, String playerClass) {
        return builder
            .setName(name)
            .setPlayerClass(playerClass)
            .applyClassDefaults()
            .build();
    }

}
