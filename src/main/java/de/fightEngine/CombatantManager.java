package de.fightEngine;

import de.fightEngine.io.IOLevel;
import de.fightEngine.io.IOManager;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CombatantManager {

    private final IOManager ioManager;
    private final EventBus eventBus;

    private List<CombatantEntry> combatantList;
    private boolean isCombatantDead;
    private final List<String> teamsAlive;

    public CombatantManager (IOManager ioManager, EventBus eventBus, List<CombatantEntry> combatantList) {
        this.combatantList = combatantList;
        this.eventBus = eventBus;

        this.isCombatantDead = false;
        this.teamsAlive = new ArrayList<>();
        this.ioManager = ioManager;

        if (combatantList.isEmpty()) {
            throw new IllegalArgumentException("Combatant List cannot be empty");
        }

    }

    public void combatantIsDead () {
        ioManager.printDebugMsg("Combatant has died. Setting isCombatantDead true");
        this.isCombatantDead = true;
    }

    public void removeDead () {
        ArrayList<CombatantEntry> newCombatantList = new ArrayList<>();
        for (CombatantEntry combatantEntry : combatantList) {
            if (combatantEntry.getStatus().equals(CombatantStatus.NORMAL))
                newCombatantList.add(combatantEntry);
        }
        ioManager.printDebugMsg("Removed " + (combatantList.size() - newCombatantList.size()) + " Dead Combatants from CombatantList.");

        if (ioManager.getMessageIOLevel().getValue() >= IOLevel.TRACE.getValue()) {
            StringBuilder stringBuilder = new StringBuilder();
            newCombatantList.forEach(combatantEntry -> stringBuilder.append(combatantEntry.getCombatant().getName()).append(" ,"));
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
            ioManager.printTraceMsg("new Combatant List is containing " + stringBuilder);
        }

        combatantList = newCombatantList;
        isCombatantDead = false;
        ioManager.printTraceMsg("IsCombatantDead set to false");
        refreshTeamsAlive();
        eventBus.onCombatantListChange(combatantList);
    }

    public void onNewRound () {
        if (isCombatantDead) {
            removeDead();
        }
    }

    public boolean moreThanOneTeamAlive () {
        return teamsAlive.size() > 1;
    }

    private void refreshTeamsAlive () {
        if (!teamsAlive.isEmpty()) {
            teamsAlive.clear();
            ioManager.printTraceMsg("Cleared teamsAlive");
        }

        combatantList.forEach(combatantEntry -> {
            if (!teamsAlive.contains(combatantEntry.getTeam())) {
                teamsAlive.add(combatantEntry.getTeam());
                ioManager.printTraceMsg("Added " + combatantEntry.getTeam() + " to teamsAlive ");
            }

        });
        if (ioManager.getMessageIOLevel().getValue() >= IOLevel.DEBUG.getValue()) {
            StringBuilder stringBuilder = new StringBuilder();
            teamsAlive.forEach(teamName -> stringBuilder.append(teamName).append(" ,"));
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
            ioManager.printDebugMsg(teamsAlive.size() + " Teams alive (" + stringBuilder + ")");
        }

    }

}
