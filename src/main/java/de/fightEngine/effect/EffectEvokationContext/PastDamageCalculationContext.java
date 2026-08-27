package de.fightEngine.effect.EffectEvokationContext;

import de.fightEngine.CombatantEntry;
import de.fightEngine.action.ActionResult;
import de.fightEngine.effect.EffectEvocationPoint;
import lombok.Getter;

@Getter
public class PastDamageCalculationContext extends EffectEvocationContext {

    private final ActionResult actionResult;

    public PastDamageCalculationContext (CombatantEntry activeCombatant, ActionResult actionResult) {
        super(EffectEvocationPoint.PAST_DAMAGE_CALCULATION, activeCombatant);
        this.actionResult = actionResult;
    }

}
