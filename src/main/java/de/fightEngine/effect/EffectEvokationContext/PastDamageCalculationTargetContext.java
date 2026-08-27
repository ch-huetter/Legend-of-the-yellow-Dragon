package de.fightEngine.effect.EffectEvokationContext;

import de.fightEngine.CombatantEntry;
import de.fightEngine.action.ActionResult;
import de.fightEngine.effect.EffectEvocationPoint;
import lombok.Getter;

@Getter
public class PastDamageCalculationTargetContext extends EffectEvocationContext {

    private final ActionResult actionResult;

    public PastDamageCalculationTargetContext (CombatantEntry combatantToEvokeEffect, ActionResult actionResult) {
        super(EffectEvocationPoint.PAST_DAMAGE_CALCULATION_TARGET, combatantToEvokeEffect);
        this.actionResult = actionResult;
    }
}
