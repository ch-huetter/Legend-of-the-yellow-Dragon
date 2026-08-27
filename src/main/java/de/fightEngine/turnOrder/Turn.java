package de.fightEngine.turnOrder;

import de.fightEngine.CombatantEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Turn {
    private CombatantEntry combatantEntry;
    @Setter
    private int initiativeThisTurn;

    public Turn (CombatantEntry combatantEntry) {
        this.combatantEntry = combatantEntry;
    }

}
