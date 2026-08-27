package de.fightEngine.effect.EffectEvokationContext;

import de.fightEngine.CombatantEntry;
import de.fightEngine.effect.EffectEvocationPoint;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class EffectEvocationContext {

    protected final EffectEvocationPoint evocationPoint;
    protected CombatantEntry combatantToEvokeEffects;
}
