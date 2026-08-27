package de.fightEngine.effect.EffectEvokationContext;

import de.fightEngine.CombatantEntry;
import de.fightEngine.effect.EffectEvocationPoint;
import lombok.Getter;

@Getter
public class PreDamageCalculationContext extends EffectEvocationContext {

    public PreDamageCalculationContext (CombatantEntry activeCombatant) {
        super(EffectEvocationPoint.PRE_DAMAGE_CALCULATION, activeCombatant);
    }

}
