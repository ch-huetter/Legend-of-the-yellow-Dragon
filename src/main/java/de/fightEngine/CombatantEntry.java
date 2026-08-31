package de.fightEngine;

import de.fightEngine.flag.Flag;
import de.fightEngine.flag.implementation.InitiativeModifier;
import de.fightEngine.io.IOManager;
import de.fightEngine.io.IOPrinter;
import de.game.model.entity.LivingEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class CombatantEntry {

    private EventBus eventBus;
    private List<Flag> setFlags;

    private final IOPrinter ioPrinter;
    private final LivingEntity combatant;
    private final String team;
    private final boolean playerControlled;
    private final UUID uuid;
    private int combatantActions;
    private int baseInitiative;
    private int initiative;
    private CombatantStatus status;


    public CombatantEntry (LivingEntity combatant, String team, EventBus eventBus, IOManager ioManager) {
        this.combatant = combatant;
        this.team = team;
        this.playerControlled = false;
        this.uuid = UUID.randomUUID();
        this.ioPrinter = ioManager.getIOPrinterInstance();
        init(combatant, eventBus);
    }

    public CombatantEntry (LivingEntity combatant, String team, boolean playerControlled, EventBus eventBus, IOManager ioManager) {
        this.combatant = combatant;
        this.team = team;
        this.playerControlled = playerControlled;
        this.uuid = UUID.randomUUID();
        this.ioPrinter = ioManager.getIOPrinterInstance();
        init(combatant, eventBus);
    }

    private void init (LivingEntity combatant, EventBus eventBus) {
        this.combatantActions = combatant.getActionsPerTurn();
        this.status = CombatantStatus.NORMAL;

        int newInitiative = combatant.getLevel() * 2;
        this.initiative = newInitiative;
        this.baseInitiative = newInitiative;
        this.setFlags = new ArrayList<>();
        this.eventBus = eventBus;
    }

    public void modifyInitiative (int modifier, int duration) {

        initiative += modifier;
        getSetFlags().add(new InitiativeModifier(modifier, duration));

        eventBus.onInitiativeChange(this);
    }
}
