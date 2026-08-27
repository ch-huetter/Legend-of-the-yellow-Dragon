package de.fightEngine.effect.EffectEvokationContext;

import de.fightEngine.CombatantEntry;
import de.fightEngine.effect.EffectEvocationPoint;
import lombok.Getter;

import java.util.List;

@Getter
public class PreTurnContext extends EffectEvocationContext {
    List<CombatantEntry> combatantList;

    public PreTurnContext (CombatantEntry activeCombatant, List<CombatantEntry> combatantList) {
        super(EffectEvocationPoint.PRE_TURN, activeCombatant);
        this.combatantList = combatantList;
    }

}
