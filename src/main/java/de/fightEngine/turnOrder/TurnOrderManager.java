package de.fightEngine.turnOrder;

import de.fightEngine.CombatantEntry;
import de.fightEngine.EventBus;
import de.fightEngine.FightContext;
import de.fightEngine.flag.Flag;
import de.fightEngine.flag.FlagKey;
import de.fightEngine.flag.implementation.InitiativeModifier;
import de.fightEngine.io.IOManager;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TurnOrderManager {

    private final List<List<Turn>> rounds;
    private final FightContext fightContext;
    private final IOManager ioManager;
    private final int shownRounds;

    @Setter
    @Getter
    private int amountShownTurns;
    @Getter
    private int roundIndex;
    private int combatantIndex;


    public TurnOrderManager (FightContext fightContext, EventBus eventBus, IOManager ioManager, List<CombatantEntry> combatantList) {

        this.rounds = new ArrayList<>();
        this.shownRounds = 3;
        this.fightContext = fightContext;
        this.ioManager = ioManager;

        eventBus.registerOnCombatantListChange(this::buildRounds);
        eventBus.registerOnInitiativeChange(this::initiativeChanged);
        eventBus.registerOnTurnEnd(() -> {
            combatantIndex++;
            if ((combatantIndex) >= rounds.get(roundIndex).size()) {
                eventBus.onRoundEnd();
                combatantIndex = 0;
                roundIndex++;
            }
        });

        this.combatantIndex = 0;
        this.roundIndex = 0;
        buildRounds(combatantList);
    }

    /**
     * @return the actual CombatantList for the actual turn and the shownRounds additional Lists
     */
    public List<List<Turn>> getDisplayedOrder () {
        List<List<Turn>> turnList = new ArrayList<>();
        for (int i = roundIndex; i < roundIndex + shownRounds; i++) {
            turnList.add(rounds.get(roundIndex + i));
        }
        return turnList;
    }

    /**
     * Creates new rounds until roundIndex + shownRounds rounds have been created
     */
    private void buildRounds (List<CombatantEntry> combatantList) {
        ArrayList<Turn> newTurnOrder;
        Turn            newTurn;

        for (int i = rounds.size() - 1; i < (roundIndex + 1) + shownRounds; i++) {
            newTurnOrder = new ArrayList<>();
            for (CombatantEntry combatantEntry : combatantList) {
                newTurn = new Turn(combatantEntry);
                if (i == roundIndex) {
                    newTurn.setInitiativeThisTurn(combatantEntry.getInitiative());
                } else {
                    newTurn.setInitiativeThisTurn(calculateTurnInitiative(combatantEntry, i - roundIndex, false));
                }
                newTurnOrder.add(newTurn);
            }
            rounds.add(newTurnOrder);
        }
    }

    private void initiativeChanged (CombatantEntry combatantInitiativeChanged) {
        ioManager.printDebugMsg("initiative Changed for " + combatantInitiativeChanged.getCombatant().getName());
        ioManager.printTraceMsg(this.getTurnOrderStringBuilderRepresentation().toString());
        for (int loopRoundIndex = roundIndex; loopRoundIndex < roundIndex + shownRounds; loopRoundIndex++) {
            for (int turnIndex = 0; turnIndex < rounds.get(loopRoundIndex).size(); turnIndex++) {
                ioManager.printTraceMsg("Round " + loopRoundIndex + " turn " + turnIndex + " is Combatants " +
                                        rounds.get(loopRoundIndex).get(turnIndex).getCombatantEntry().getCombatant().getName() +
                                        " Turn.");
                if (rounds.get(loopRoundIndex).get(turnIndex).getCombatantEntry().equals(combatantInitiativeChanged)) {
                    ioManager.printTraceMsg("Found changed Combatant");
                    //Check if round is actualRound we need to check if the changed CombatantEntry did his action this round and if there are 2 or more turns left that
                    // did not do their action this round we need to refresh initiative and sort those.
                    if (loopRoundIndex == roundIndex && (turnIndex <= combatantIndex || rounds.get(loopRoundIndex).size() <= turnIndex + 1)) {
                        StringBuilder stringBuilder = getInitiativeChangedDebugMsg(turnIndex, loopRoundIndex);
                        ioManager.printDebugMsg(stringBuilder.toString());
                        continue;
                    }
                    //Actual Round. We only change the positions for Combatants that did not do their turn this round.
                    if (loopRoundIndex == roundIndex) {
                        ioManager.printDebugMsg("It is the actual Round so we change only Combatants that did not do their turn ");
                        ArrayList<Turn> unactivatedTurns = new ArrayList<>();
                        while (rounds.get(loopRoundIndex).size() - 1 > combatantIndex) {
                            unactivatedTurns.add(rounds.get(loopRoundIndex).getLast());
                            rounds.get(loopRoundIndex).removeLast();
                        }
                        ioManager.printTraceMsg(unactivatedTurns.size() + " turns are remaining in the actual Round");
                        combatantInitiativeChanged.setInitiative(calculateTurnInitiative(combatantInitiativeChanged, 0, true));
                        sortCombatantListForTurnOrder(unactivatedTurns);
                        rounds.get(loopRoundIndex).addAll(unactivatedTurns);
                    }
                    //Future Rounds. We refresh the combatants initiative for that turn and check if initiative changing effects are still aktive on this turn!
                    else {
                        ioManager.printDebugMsg("It is a future Round so we refresh the changedCombatants Initiative and resort the Order.");
                        rounds.get(loopRoundIndex).get(turnIndex).setInitiativeThisTurn(calculateTurnInitiative(combatantInitiativeChanged, loopRoundIndex - roundIndex,
                                                                                                                false));
                        sortCombatantListForTurnOrder(rounds.get(loopRoundIndex));
                    }

                }
            }
        }
    }

    private int calculateTurnInitiative (CombatantEntry combatantEntry, int roundsInFuture, boolean wasActiveInActualRound) {
        //We use baseInitiative. This can Conflict with things that change initiative without using Flags
        int turnInitiative = combatantEntry.getBaseInitiative();
        ioManager.printTraceMsg("Initial turnInitiative is " + turnInitiative);
        List<InitiativeModifier> initiativeModifiers = new ArrayList<>();
        for (Flag flag : combatantEntry.getSetFlags()) {
            if (flag.getFlagKey().equals(FlagKey.INITIATIVE_MODIFIED) && flag.getDuration() > roundsInFuture + (wasActiveInActualRound ? 1 : 0)) {
                initiativeModifiers.add((InitiativeModifier) flag);
            }
        }
        ioManager.printTraceMsg("Found " + initiativeModifiers.size() + " InitiativeModifier Flags");
        if (!initiativeModifiers.isEmpty()) {
            for (InitiativeModifier initiativeModifier : initiativeModifiers) {
                if (initiativeModifier.getDuration() - roundsInFuture + (wasActiveInActualRound ? 1 : 0) > 0) {
                    ioManager.printTraceMsg("Modified turnInitiative by " + initiativeModifier.getValue());
                    turnInitiative += initiativeModifier.getValue();
                }
            }
        }
        ioManager.printDebugMsg("calculated new initiative Value for " + combatantEntry.getCombatant().getName() + ". Previous value is " + combatantEntry.getBaseInitiative() +
                                " new value is " + turnInitiative);
        return turnInitiative;
    }

    private void sortCombatantListForTurnOrder (List<Turn> combatantList) {
        combatantList.sort(Comparator.comparingInt(Turn::getInitiativeThisTurn));
    }

    public CombatantEntry getCurrentCombatant () {
        return rounds.get(roundIndex).get(combatantIndex).getCombatantEntry();
    }

    private StringBuilder getTurnOrderStringBuilderRepresentation () {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Current turn order is {");
        for (int roundIndex = this.roundIndex; roundIndex < rounds.size(); roundIndex++) {
            stringBuilder.append("{");
            for (int turnIndex = 0; turnIndex < rounds.get(roundIndex).size(); turnIndex++) {
                String combatantName = rounds.get(roundIndex).get(turnIndex).getCombatantEntry().getCombatant().getName();
                stringBuilder.append((roundIndex == this.roundIndex && turnIndex == combatantIndex) ? combatantName.toUpperCase() : combatantName)
                        .append((roundIndex + 1 == rounds.size()) ? "}" : " ,");
            }
            stringBuilder.append("}");
        }
        return stringBuilder;
    }

    private StringBuilder getInitiativeChangedDebugMsg (int turnIndex, int loopRoundIndex) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("It is the actualRound. ");
        if (turnIndex <= combatantIndex)
            stringBuilder.append("changedCombatant did their turn. ");
        if (rounds.get(loopRoundIndex).size() <= turnIndex + 1)
            stringBuilder.append("Not enough combatants left for resort. ");
        stringBuilder.append(" Skipping resorting");
        return stringBuilder;
    }
}
