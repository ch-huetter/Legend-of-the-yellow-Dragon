package de.fightEngine.targetSelector;

import de.fightEngine.CombatantEntry;
import de.fightEngine.CombatantStatus;
import de.fightEngine.FightContext;
import de.fightEngine.action.Action;
import de.fightEngine.io.IOManager;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TargetSelector {

    private final FightContext fightContext;
    private final IOManager ioManager;

    public List<CombatantEntry> getTargetsForAttack (Action action, CombatantEntry actionSource) {
        ArrayList<CombatantEntry> targets = new ArrayList<>();

        for (CombatantEntry combatantEntry : fightContext.getCombatantList()) {
            ioManager.printTraceMsg("Checking if " + combatantEntry.getCombatant().getName() + " is a valid target");
            if (combatantEntry.getStatus() == CombatantStatus.DEAD) {
                ioManager.printDebugMsg("Combatant Dead skipping his turn");
                continue;
            }

            final boolean validSelfTarget  = action.isTargetSelf() && actionSource.getCombatant().getName().equals(combatantEntry.getCombatant().getName());
            final boolean validEnemyTarget = action.isTargetEnemy() && !actionSource.getTeam().equals(combatantEntry.getTeam());
            final boolean validAllyTarget  = action.isTargetAlly() && actionSource.getTeam().equals(combatantEntry.getTeam());

            ioManager.printTraceMsg("Is validSelfTarget " + validSelfTarget);
            ioManager.printTraceMsg("Is validEnemyTarget " + validEnemyTarget);
            ioManager.printTraceMsg("Is validAlliedTarget " + validAllyTarget);

            if (validSelfTarget || validEnemyTarget || validAllyTarget) {
                ioManager.printDebugMsg("Combatant " + combatantEntry.getCombatant().getName() + " is a valid Target");
                targets.add(combatantEntry);
            }
        }
        return targets;
    }

    public List<Target> selectTargets (Action action, CombatantEntry actionSource) {
        List<CombatantEntry> validTargets = getTargetsForAttack(action, actionSource);
        ioManager.printTraceMsg("validTargets " + validTargets.size());
        if (validTargets.size() <= action.getTargetAmount() || !actionSource.isPlayerControlled()) {
            ioManager.printDebugMsg("The action targets as many or more targets as are available");
            return autoSelection(action, validTargets);
        } else {
            ioManager.printDebugMsg("The action targets less targets than available. Player controlled Character. Requiring Player Selection");
            return playerSelection(action, validTargets);
        }
    }

    private List<Target> autoSelection (Action action, List<CombatantEntry> validTargets) {
        List<Target> targetResult = new ArrayList<>();
        int          tries        = 0;
        int          maxTries     = action.getTargetAmount() * 2;
        for (int i = 0; i < action.getTargetAmount() && i < validTargets.size(); i = targetResult.size()) {
            targetResult.addAll(collectTargets(action, validTargets, i, i));
            tries++;
            if (tries >= maxTries) {
                ioManager.printDebugMsg("Too many tries for Auto Selection. Aborting");
                break;
            }
        }
        ioManager.printTraceMsg("Auto Selection selected " + targetResult.size() + " targets");
        return targetResult;
    }

    private List<Target> playerSelection (Action action, List<CombatantEntry> validTargets) {
        List<Target> targetResult = new ArrayList<>();

        ioManager.printDebugMsg("Player needs to select " + action.getTargetAmount() + " targets from " + validTargets.size() + " valid Targets");
        for (int i = 0; i < action.getTargetAmount() && i < validTargets.size(); i++) {
            int selectedTargetIndex = getPlayerInput(validTargets.size());
            targetResult.addAll(collectTargets(action, validTargets, selectedTargetIndex, i));
        }
        return targetResult;
    }

    private List<Target> collectTargets (Action action, List<CombatantEntry> validTargets, int selectedTargetIndex, int loopIndex) {
        List<Target>         targetResult     = new ArrayList<>();
        List<CombatantEntry> selectableFilter = new ArrayList<>();

        if (!selectableFilter.contains(validTargets.get(selectedTargetIndex))) {
            targetResult.add(new Target(validTargets.get(selectedTargetIndex), TargetType.indexToTargetType(loopIndex)));
        }

        if (action.getTargetSelectionType() == TargetSelectionType.MULTI_TARGET_EXCLUSIVE) {
            ioManager.printTraceMsg("Exclusive Multi Target adding " + validTargets.get(selectedTargetIndex).getCombatant().getName() + " to Filter");
            selectableFilter.add(validTargets.get(selectedTargetIndex));
        }

        if (action.getTargetSelectionType() == TargetSelectionType.AREA_OF_EFFECT) {
            ioManager.printTraceMsg("AreaOfEffect targeting in use");
            for (int j = 1; j <= action.getAreaOfEffect(); j++) {
                int upperTarget = selectedTargetIndex + j;
                if (upperTarget <= validTargets.size()) {
                    ioManager.printTraceMsg(validTargets.get(upperTarget).getCombatant().getName() + " added as upper target");
                    targetResult.add(new Target(validTargets.get(upperTarget), TargetType.indexToTargetType(j)));
                }

                int lowerTarget = selectedTargetIndex - j;
                if (lowerTarget >= 0) {
                    ioManager.printTraceMsg(validTargets.get(lowerTarget).getCombatant().getName() + " added as lower target");
                    targetResult.add(new Target(validTargets.get(lowerTarget), TargetType.indexToTargetType(j)));
                }

            }

        }
        return targetResult;
    }

    private int getPlayerInput (int maxValue) {
        String input = ioManager.getInput();
        ioManager.printTraceMsg("Player Input is " + input);
        try {
            int inputAsInt = Integer.parseInt(input) - 1;
            if (inputAsInt < 0 || inputAsInt > maxValue)
                throw new IllegalArgumentException();
            return inputAsInt;
        } catch (IllegalArgumentException e) {
            ioManager.printDebugMsg("Wrong input " + input);
            return getPlayerInput(maxValue);
        }
    }
}
