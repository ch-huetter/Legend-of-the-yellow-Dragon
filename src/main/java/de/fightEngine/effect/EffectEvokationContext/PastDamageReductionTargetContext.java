package de.fightEngine.effect.EffectEvokationContext;

import de.fightEngine.CombatantEntry;
import de.fightEngine.action.ActionResult;
import de.fightEngine.effect.EffectEvocationPoint;
import lombok.Getter;

@Getter
public class PastDamageReductionTargetContext extends EffectEvocationContext {
    private final ActionResult actionResult;

    public PastDamageReductionTargetContext (CombatantEntry combatantToEvokeEffects, ActionResult actionResult) {
        super(EffectEvocationPoint.PAST_DAMAGE_REDUCTION_TARGET, combatantToEvokeEffects);
        this.actionResult = actionResult;
    }
}
