package de.fightEngine;

import de.fightEngine.flag.Flag;
import de.game.model.entity.LivingEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class CombatantEntry {

    private EventBus eventBus;
    private List<Flag> setFlags;

    private final LivingEntity combatant;
    private final String team;
    private final boolean playerControlled;
    private int combatantActions;
    private int baseInitiative;
    private int initiative;
    private CombatantStatus status;

    public CombatantEntry (LivingEntity combatant, String team, EventBus eventBus) {
        this.combatant = combatant;
        this.team = team;
        this.playerControlled = false;
        init(combatant, eventBus);
    }

    public CombatantEntry (LivingEntity combatant, String team, boolean playerControlled, EventBus eventBus) {
        this.combatant = combatant;
        this.team = team;
        this.playerControlled = playerControlled;
        init(combatant, eventBus);
    }

    private void init (LivingEntity combatant, EventBus eventBus) {
        this.combatantActions = combatant.getActivationsPerTurn();
        this.status = CombatantStatus.NORMAL;

        int newInitiative = combatant.getLevel() * 2;
        this.initiative = newInitiative;
        this.baseInitiative = newInitiative;
        this.setFlags = new ArrayList<>();
        this.eventBus = eventBus;
    }

    public void modifyInitiative (int modifier) {
        initiative += modifier;
        eventBus.onInitiativeChange(this);
    }
}
