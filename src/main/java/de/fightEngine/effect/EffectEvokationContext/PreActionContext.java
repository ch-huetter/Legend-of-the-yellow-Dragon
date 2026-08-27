package de.fightEngine.effect.EffectEvokationContext;

import de.fightEngine.CombatantEntry;
import de.fightEngine.effect.EffectEvocationPoint;
import lombok.Getter;

@Getter
public class PreActionContext extends EffectEvocationContext {

    public PreActionContext (CombatantEntry activeCombatant) {
        super(EffectEvocationPoint.PRE_ACTION, activeCombatant);
    }
}
