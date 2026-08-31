package de.fightEngine.targetSelector;

import de.fightEngine.CombatantEntry;
import de.fightEngine.CombatantStatus;
import de.fightEngine.FightContext;
import de.fightEngine.action.Action;
import de.fightEngine.io.IOManager;
import de.fightEngine.io.IOPrinter;
import de.fightEngine.io.IOStateContext.UIStateTargetSelectionContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TargetSelector {

    private final FightContext fightContext;
    private final IOPrinter ioPrinter;
    private final IOManager ioManager;

    public TargetSelector (FightContext fightContext, IOManager ioManager) {
        this.fightContext = fightContext;
        this.ioManager = ioManager;
        this.ioPrinter = ioManager.getIOPrinterInstance();
    }

    public List<SelectableTarget> getTargetsForAttack (Action action, CombatantEntry actionSource) {
        ArrayList<SelectableTarget> targets = new ArrayList<>();

        for (CombatantEntry combatantEntry : fightContext.getCombatantList()) {
            ioPrinter.printTraceMsg("Checking if " + combatantEntry.getCombatant().getName() + " is a valid target");
            if (combatantEntry.getStatus() == CombatantStatus.DEAD) {
                ioPrinter.printTraceMsg("Combatant " + combatantEntry.getCombatant().getName() + " is Dead and cant be targeted");
                continue;
            }

            final boolean validSelfTarget  = action.isTargetSelf() && actionSource.getCombatant().getName().equals(combatantEntry.getCombatant().getName());
            final boolean validEnemyTarget = action.isTargetEnemy() && !actionSource.getTeam().equals(combatantEntry.getTeam());
            final boolean validAllyTarget  = action.isTargetAlly() && actionSource.getTeam().equals(combatantEntry.getTeam());

            ioPrinter.printTraceMsg("Is validSelfTarget " + validSelfTarget);
            ioPrinter.printTraceMsg("Is validEnemyTarget " + validEnemyTarget);
            ioPrinter.printTraceMsg("Is validAlliedTarget " + validAllyTarget);

            if (validSelfTarget || validEnemyTarget || validAllyTarget) {
                ioPrinter.printTraceMsg("Combatant " + combatantEntry.getCombatant().getName() + " is a valid Target");
                targets.add(new SelectableTarget(combatantEntry, validAllyTarget, validSelfTarget, validEnemyTarget));
            }
        }
        ioPrinter.printDebugMsg("Found " + targets.size() + " targets for Action " + action.getActionName());
        return targets;
    }

    public List<Target> selectTargets (Action action, CombatantEntry actionSource) {
        List<SelectableTarget> validTargets = getTargetsForAttack(action, actionSource);
        ioPrinter.printTraceMsg("validTargets " + validTargets.size());

        //For TargetType.ALL there is no need for TargetSelection we just directly build a List with all Selectable Targets
        if (action.getTargetSelectionType().equals(TargetSelectionType.ALL)) {
            ArrayList<Target> targets = new ArrayList<>();
            for (SelectableTarget selectableTarget : validTargets) {
                targets.add(new Target(selectableTarget, TargetType.PRIMARY));
            }
            return targets;
        }


        if (validTargets.size() <= action.getTargetAmount() || !actionSource.isPlayerControlled()) {
            ioPrinter.printDebugMsg("The action targets as many or more targets as are available");
            return autoSelection(action, validTargets);
        } else {
            ioPrinter.printDebugMsg("The action targets less targets than available. Player controlled Character. Requiring Player Selection");
            return playerSelection(action, validTargets);
        }
    }

    private List<Target> autoSelection (Action action, List<SelectableTarget> validTargets) {
        List<Target> targetResult = new ArrayList<>();
        int          tries        = 0;
        int          maxTries     = action.getTargetAmount() * 2;
        for (int i = 0; i < action.getTargetAmount() && i < validTargets.size(); i = targetResult.size()) {
            targetResult.addAll(collectTargets(action, validTargets, i, i));
            tries++;
            if (tries >= maxTries) {
                ioPrinter.printDebugMsg("Too many tries for Auto Selection. Aborting");
                break;
            }
        }
        ioPrinter.printTraceMsg("Auto Selection selected " + targetResult.size() + " targets");
        return targetResult;
    }

    private List<Target> playerSelection (Action action, List<SelectableTarget> validTargets) {
        List<Target> targetResult = new ArrayList<>();

        ioPrinter.printDebugMsg("Player needs to select " + action.getTargetAmount() + " targets from " + validTargets.size() + " valid Targets");
        for (int i = 0; i < action.getTargetAmount() && i < validTargets.size(); i++) {
            ioManager.showUIState(new UIStateTargetSelectionContext(validTargets));
            int selectedTargetIndex = getPlayerInput(validTargets.size());
            targetResult.addAll(collectTargets(action, validTargets, selectedTargetIndex, i));
        }
        return targetResult;
    }

    private List<Target> collectTargets (Action action, List<SelectableTarget> validTargets, int selectedTargetIndex, int loopIndex) {
        List<Target> targetResult     = new ArrayList<>();
        List<UUID>   selectableFilter = new ArrayList<>();

        if (!selectableFilter.contains(validTargets.get(selectedTargetIndex).getTarget().getUuid())) {
            targetResult.add(new Target(validTargets.get(selectedTargetIndex), TargetType.indexToTargetType(loopIndex)));
        }

        if (action.getTargetSelectionType().equals(TargetSelectionType.MULTI_TARGET_EXCLUSIVE)) {
            ioPrinter.printTraceMsg("Exclusive Multi Target adding " + validTargets.get(selectedTargetIndex).getTarget().getCombatant().getName() + " to Filter");
            selectableFilter.add(validTargets.get(selectedTargetIndex).getTarget().getUuid());
        }

        if (action.getTargetSelectionType() == TargetSelectionType.AREA_OF_EFFECT) {
            ioPrinter.printTraceMsg("AreaOfEffect targeting in use");
            for (int j = 1; j <= action.getAreaOfEffect(); j++) {
                int upperTarget = selectedTargetIndex + j;
                if (upperTarget <= validTargets.size()) {
                    ioPrinter.printTraceMsg(validTargets.get(upperTarget).getTarget().getCombatant().getName() + " added as upper target");
                    targetResult.add(new Target(validTargets.get(upperTarget), TargetType.indexToTargetType(j)));
                }

                int lowerTarget = selectedTargetIndex - j;
                if (lowerTarget >= 0) {
                    ioPrinter.printTraceMsg(validTargets.get(lowerTarget).getTarget().getCombatant().getName() + " added as lower target");
                    targetResult.add(new Target(validTargets.get(lowerTarget), TargetType.indexToTargetType(j)));
                }

            }

        }
        return targetResult;
    }

    private int getPlayerInput (int maxValue) {
        String input = ioManager.getInput();
        ioPrinter.printTraceMsg("Player Input is " + input);
        try {
            int inputAsInt = Integer.parseInt(input) - 1;
            if (inputAsInt < 0 || inputAsInt > maxValue)
                throw new IllegalArgumentException();
            return inputAsInt;
        } catch (IllegalArgumentException e) {
            ioPrinter.printDebugMsg("Wrong input " + input);
            return getPlayerInput(maxValue);
        }
    }
}
