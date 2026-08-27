package de.fightEngine.effect.EffectEvokationContext;

import de.fightEngine.CombatantEntry;
import de.fightEngine.effect.EffectEvocationPoint;
import lombok.Getter;

import java.util.List;

@Getter
public class PastTurnContext extends EffectEvocationContext {

    List<CombatantEntry> combatantList;

    public PastTurnContext (CombatantEntry activeCombatant, List<CombatantEntry> combatantList) {
        super(EffectEvocationPoint.PAST_TURN, activeCombatant);
        this.combatantList = combatantList;
    }

}
