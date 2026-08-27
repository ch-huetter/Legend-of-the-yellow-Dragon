package de.fightEngine.flag;

import de.fightEngine.CombatantEntry;
import de.fightEngine.EventBus;
import de.fightEngine.FightContext;

import java.util.ArrayList;
import java.util.List;

public class FlagManager {

    FightContext fightContext;
    EventBus eventBus;


    public FlagManager (FightContext fightContext, EventBus eventBus) {
        this.fightContext = fightContext;
        this.eventBus = eventBus;

        eventBus.registerOnTurnEnd(this::onTurnEnd);
    }

    /**
     * @param flagKey of the flag you want to find
     * @return all Flags with the given Key. Null if none found
     */
    public List<Flag> hasFlag (FlagKey flagKey, CombatantEntry combatantEntry) {
        ArrayList<Flag> flagArrayList = new ArrayList<>();
        return combatantEntry.getSetFlags().stream().filter(flag -> flag.getFlagKey().equals(flagKey)).toList();
    }

    private void onTurnEnd () {
        CombatantEntry currentCombatant = fightContext.getCurrentCombatant();
    }

}
