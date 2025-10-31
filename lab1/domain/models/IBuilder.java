package lab1.domain.models;
import lab1.domain.models.Player;

public interface IBuilder {
    IBuilder setName(String name);
    IBuilder setPlayerClass(String playerClass);
    IBuilder applyClassDefaults();
    Player build();
}
