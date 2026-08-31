package de.fightEngine.io.konsoleIOManager;

import de.fightEngine.CombatantEntry;
import de.fightEngine.action.Action;
import de.fightEngine.round.Turn;
import de.fightEngine.targetSelector.SelectableTarget;

import java.util.ArrayList;
import java.util.List;

public class UIStateToKonsolePrinter {
    private final int lineLength = 30;
    private final char placeholder = '#';
    private final String placeholderCharacters = "   ";

    public void printActionDisplay (Action[] actions) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < actions.length; i++) {
            stringBuilder.append(placeholderLine());
            if ((i + 1) < actions.length)
                stringBuilder.append(placeholderCharacters);
        }
        stringBuilder.append("\n");

        for (int i = 0; i < actions.length; i++) {
            stringBuilder.append(fillLineToFullLength(actions[i].getActionName()));
            if ((i + 1) < actions.length)
                stringBuilder.append(placeholderCharacters);
        }
        stringBuilder.append("\n");

        for (int i = 0; i < actions.length; i++) {
            stringBuilder.append(fillLineToFullLength("Stamina Cost : " + String.valueOf(actions[i].getStaminaCost())));
            if ((i + 1) < actions.length)
                stringBuilder.append(placeholderCharacters);
        }
        stringBuilder.append("\n");

        for (int i = 0; i < actions.length; i++) {
            stringBuilder.append(fillLineToFullLength("Enemy/Ally/Self"));
            if ((i + 1) < actions.length)
                stringBuilder.append(placeholderCharacters);
        }
        stringBuilder.append("\n");

        for (int i = 0; i < actions.length; i++) {
            Action action = actions[i];
            stringBuilder.append(
                    fillLineToFullLength(String.valueOf(action.isTargetEnemy()) + "/" + String.valueOf(action.isTargetAlly()) + "/" + String.valueOf(action.isTargetSelf())));
            if ((i + 1) < actions.length)
                stringBuilder.append(placeholderCharacters);
        }
        stringBuilder.append("\n");

        for (int i = 0; i < actions.length; i++) {
            stringBuilder.append(fillLineToFullLength("Action Cost : " + String.valueOf(actions[i].getActionCost())));
            if ((i + 1) < actions.length)
                stringBuilder.append(placeholderCharacters);
        }
        stringBuilder.append("\n");

        for (int i = 0; i < actions.length; i++) {
            stringBuilder.append(placeholderLine());
            if ((i + 1) < actions.length)
                stringBuilder.append(placeholderCharacters);
        }
        stringBuilder.append("\n");

        System.out.println(stringBuilder.toString());
    }

    public void printTurnList (List<List<Turn>> roundList) {
        List<Turn>    turnList      = roundList.getFirst();
        StringBuilder stringBuilder = new StringBuilder();
        for (int x = 0; x < turnList.size(); x++) {
            stringBuilder.append(placeholderLine());
            if ((x + 1) < turnList.size())
                stringBuilder.append(placeholderCharacters);
        }
        stringBuilder.append("\n");

        for (int x = 0; x < turnList.size(); x++) {
            stringBuilder.append(combatantNameAsLine(turnList.get(x).getCombatantEntry()));
            if ((x + 1) < turnList.size())
                stringBuilder.append(placeholderCharacters);
        }
        stringBuilder.append("\n");

        for (int x = 0; x < turnList.size(); x++) {
            stringBuilder.append(placeholderLine());
            if ((x + 1) < turnList.size())
                stringBuilder.append(placeholderCharacters);
        }
        stringBuilder.append("\n");

        System.out.println(stringBuilder.toString());
    }

    /**
     * Converts the given target list into a Combatant List and calls printCombatantList with it
     *
     * @param targetList to convert
     */
    public void printSelectableTargetListAsCombatantList (List<SelectableTarget> targetList) {
        List<CombatantEntry> combatantEntries = new ArrayList<>();
        targetList.forEach(selectableTarget -> combatantEntries.add(selectableTarget.getTarget()));
        printCombatantList(combatantEntries);
    }

    public void printCombatantList (List<CombatantEntry> combatantList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int x = 0; x < combatantList.size(); x++) {
            stringBuilder.append(placeholderLine());
            if ((x + 1) < combatantList.size())
                stringBuilder.append(placeholderCharacters);
        }
        stringBuilder.append("\n");

        for (int x = 0; x < combatantList.size(); x++) {
            stringBuilder.append(combatantNameAsLine(combatantList.get(x)));
            if ((x + 1) < combatantList.size())
                stringBuilder.append(placeholderCharacters);
        }
        stringBuilder.append("\n");

        for (int x = 0; x < combatantList.size(); x++) {
            stringBuilder.append(combatantStatusAsLine(combatantList.get(x)));
            if ((x + 1) < combatantList.size())
                stringBuilder.append(placeholderCharacters);
        }
        stringBuilder.append("\n");

        for (int x = 0; x < combatantList.size(); x++) {
            stringBuilder.append(combatantTeamAsLine(combatantList.get(x)));
            if ((x + 1) < combatantList.size())
                stringBuilder.append(placeholderCharacters);
        }
        stringBuilder.append("\n");


        for (int x = 0; x < combatantList.size(); x++) {
            stringBuilder.append(combatantHealthAsLine(combatantList.get(x)));
            if ((x + 1) < combatantList.size())
                stringBuilder.append(placeholderCharacters);
        }
        stringBuilder.append("\n");


        for (int x = 0; x < combatantList.size(); x++) {
            stringBuilder.append(combatantStaminaAsLine(combatantList.get(x)));
            if ((x + 1) < combatantList.size())
                stringBuilder.append(placeholderCharacters);
        }
        stringBuilder.append("\n");

        for (int x = 0; x < combatantList.size(); x++) {
            stringBuilder.append(placeholderLine());
            if ((x + 1) < combatantList.size())
                stringBuilder.append(placeholderCharacters);
        }

        System.out.println(stringBuilder.toString());

    }

    private String placeholderLine () {
        return String.valueOf(placeholder).repeat(lineLength + 2);
    }

    private String combatantNameAsLine (CombatantEntry combatantEntry) {
        return fillLineToFullLength(combatantEntry.getCombatant().getName());
    }

    private String combatantHealthAsLine (CombatantEntry combatantEntry) {
        String healthAsString = combatantEntry.getCombatant().getCurrentHealth().toString() + "/" + combatantEntry.getCombatant().getMaxHealth();
        return fillLineToFullLength(healthAsString);
    }

    private String combatantStaminaAsLine (CombatantEntry combatantEntry) {
        String staminaAsString = combatantEntry.getCombatant().getCurrentStamina().toString() + "/" + combatantEntry.getCombatant().getBaseStamina();
        return fillLineToFullLength(staminaAsString);
    }

    private String combatantStatusAsLine (CombatantEntry combatantEntry) {
        return fillLineToFullLength(String.valueOf(combatantEntry.getStatus()));
    }

    private String combatantTeamAsLine (CombatantEntry combatantEntry) {
        return fillLineToFullLength("Team : " + combatantEntry.getTeam());
    }

    private String fillLineToFullLength (String lineToFill) {
        int leadingEmptySpaces = (lineLength - lineToFill.length()) / 2;
        int endingEmptySpaces  = (lineLength - lineToFill.length()) - leadingEmptySpaces;
//        System.out.println("Filling line with " + leadingEmptySpaces + "/" + endingEmptySpaces + " empty Spaces");
        return placeholder +
               " ".repeat(Math.max(0, leadingEmptySpaces)) +
               lineToFill +
               " ".repeat(Math.max(0, endingEmptySpaces)) +
               placeholder;
    }

}
