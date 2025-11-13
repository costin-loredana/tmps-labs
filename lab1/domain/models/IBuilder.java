package lab1.domain.models;

public interface IBuilder {
    IBuilder setName(String name);
    IBuilder setPlayerClass(String playerClass);
    IBuilder applyClassDefaults();
    Player build();
}
