package de.fightEngine.effect.EffectEvokationContext;

import de.fightEngine.CombatantEntry;
import de.fightEngine.effect.EffectEvocationPoint;
import de.fightEngine.effect.EffectResult;
import de.fightEngine.effect.implementation.Effect;
import lombok.Getter;

@Getter
public class PastEffectEvocationContext extends EffectEvocationContext {

    private final Effect effectToInvoke;
    private final EffectResult effectResult;

    public PastEffectEvocationContext (EffectEvocationPoint evokationPoint, CombatantEntry currentActiveCombatant, Effect effectToInvoke, EffectResult effectResult) {
        super(evokationPoint, currentActiveCombatant);
        this.effectToInvoke = effectToInvoke;
        this.effectResult = effectResult;
    }

}
