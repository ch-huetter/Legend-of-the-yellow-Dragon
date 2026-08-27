package de.fightEngine.effect.EffectEvokationContext;

import de.fightEngine.CombatantEntry;
import de.fightEngine.action.ActionResult;
import de.fightEngine.effect.EffectEvocationPoint;
import lombok.Getter;

@Getter
public class PastDamageReductionContext extends EffectEvocationContext {

    private final ActionResult actionResult;

    public PastDamageReductionContext (CombatantEntry currentActiveCombatant, ActionResult actionResult) {
        super(EffectEvocationPoint.PAST_DAMAGE_REDUCTION, currentActiveCombatant);
        this.actionResult = actionResult;
    }
}
