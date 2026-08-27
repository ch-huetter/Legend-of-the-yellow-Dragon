package de.fightEngine.effect.EffectEvokationContext;

import de.fightEngine.CombatantEntry;
import de.fightEngine.action.Action;
import de.fightEngine.effect.EffectEvocationPoint;
import lombok.Getter;

@Getter
public class PastActionContext extends EffectEvocationContext {

    private final Action combatantAction;

    public PastActionContext (CombatantEntry combatantToEvokeEffect, Action combatantAction) {
        super(EffectEvocationPoint.PAST_ACTION, combatantToEvokeEffect);
        this.combatantAction = combatantAction;
    }
}
