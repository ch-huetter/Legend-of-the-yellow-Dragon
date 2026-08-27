package de.fightEngine.effect.EffectEvokationContext;

import de.fightEngine.CombatantEntry;
import de.fightEngine.effect.EffectEvocationPoint;
import de.fightEngine.effect.implementation.Effect;
import lombok.Getter;

@Getter
public class PreEffectEvocationContext extends EffectEvocationContext {

    private final Effect effectToInvoke;

    public PreEffectEvocationContext (EffectEvocationPoint evokationPoint, CombatantEntry currentActiveCombatant, Effect effectToInvoke) {
        super(evokationPoint, currentActiveCombatant);
        this.effectToInvoke = effectToInvoke;
    }
}
