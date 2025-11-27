package lab1.domain.services.buffs;

import lab1.domain.combat.IFighter;

public interface IBuffOption {
    IFighter apply(IFighter fighter);
    String getLabel();
}
