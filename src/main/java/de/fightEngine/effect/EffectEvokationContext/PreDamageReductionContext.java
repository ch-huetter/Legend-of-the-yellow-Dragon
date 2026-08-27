package de.fightEngine.effect.EffectEvokationContext;

import de.fightEngine.CombatantEntry;
import de.fightEngine.action.ActionResult;
import de.fightEngine.effect.EffectEvocationPoint;
import lombok.Getter;

@Getter
public class PreDamageReductionContext extends EffectEvocationContext {

    private final ActionResult actionResult;

    public PreDamageReductionContext (CombatantEntry activeCombatant, ActionResult actionResult) {
        super(EffectEvocationPoint.PRE_DAMAGE_REDUCTION, activeCombatant);
        this.actionResult = actionResult;
    }
}
