package de.fightEngine;

import de.fightEngine.action.Action;
import de.fightEngine.action.ActionResult;
import de.fightEngine.action.implementation.admin.ShiningLight;
import de.fightEngine.action.implementation.defensive.Rest;
import de.fightEngine.action.implementation.offensive.Player.HeavyAttack;
import de.fightEngine.action.implementation.offensive.Player.LightAttack;
import de.fightEngine.action.implementation.offensive.Wolf.LightWolfBite;
import de.fightEngine.calculator.DamageReductionCalculator;
import de.fightEngine.effect.EffectEvokationContext.*;
import de.fightEngine.effect.EffectManager;
import de.fightEngine.helper.PrototypeFightDataGetter;
import de.fightEngine.io.IOLevel;
import de.fightEngine.io.IOManager;
import de.fightEngine.io.IOPrinter;
import de.fightEngine.io.konsoleIOManager.KonsoleIOManager;
import de.fightEngine.io.konsoleIOManager.UIStateToKonsolePrinter;
import de.fightEngine.result.ResultApplicator;
import de.fightEngine.result.ResultDamagerReducer;
import de.fightEngine.round.RoundManager;
import de.fightEngine.targetSelector.Target;
import de.fightEngine.targetSelector.TargetSelector;

import java.util.ArrayList;
import java.util.List;

/**
 * Prototype for working out the Rules and Concept of Fighting
 */
public class Fight {

    private final Action[] playerAction = new Action[]{new HeavyAttack(), new LightAttack(), new Rest(), new ShiningLight()};

    private final TargetSelector targetSelector;
    private final ResultDamagerReducer damagerReducer;
    private final ResultApplicator resultApplicator;
    private final EventBus eventBus;

    private final RoundManager roundManager;
    private final EffectManager effectManager;
    private final CombatantManager combatantManager;

    private boolean fightOngoing = true;

    private final IOManager ioManager;
    private final IOPrinter ioPrinter;
    private final UIStateToKonsolePrinter UIStateToKonsolePrinter = new UIStateToKonsolePrinter();

    public Fight () {
        this.eventBus = new EventBus();
        this.ioManager = new KonsoleIOManager(IOLevel.DEBUG);
        this.ioPrinter = ioManager.getIOPrinterInstance(IOLevel.TRACE);
        List<CombatantEntry> combatantList = new ArrayList<>();
        combatantList.add(new CombatantEntry(PrototypeFightDataGetter.getHero(), "Heroes", true, eventBus, ioManager));
        combatantList.add(new CombatantEntry(PrototypeFightDataGetter.getMonster1(), "Monsters", eventBus, ioManager));
        combatantList.add(new CombatantEntry(PrototypeFightDataGetter.getMonster2(), "Monsters", eventBus, ioManager));
        combatantList.add(new CombatantEntry(PrototypeFightDataGetter.getMonster3(), "Monsters", eventBus, ioManager));

        FightContext              fightContext              = new FightContext();
        DamageReductionCalculator damageReductionCalculator = new DamageReductionCalculator();

        this.targetSelector = new TargetSelector(fightContext, ioManager);
        this.combatantManager = new CombatantManager(ioManager, eventBus, combatantList);
        this.effectManager = new EffectManager(combatantList, ioManager, fightContext);
        this.roundManager = new RoundManager(fightContext, eventBus, ioManager, combatantList);
        this.resultApplicator = new ResultApplicator(fightContext, ioManager);
        this.damagerReducer = new ResultDamagerReducer(damageReductionCalculator, ioManager);

        fightContext.initializeFightContext(this, effectManager, combatantManager, resultApplicator, roundManager);
        eventBus.registerOnTurnEnd(this::onNextRound);
    }

    public static void main (String[] args0) {
        new Fight().fight();
    }

    private void fight () {
        //TODO ActionSelector. Lets Players choose out of the actions they can take and handles ressource cost and advanced conditions for an Ability to be used
        //TODO Add ActionManager/PatternManager that manages Action Selection and decides when the player chooses an Action and when the AI chooses an Action
        //TODO Outsource the ActionSelection and make it smarter so it can Recognize special Conditions for Action Activation and additional ressource costs like Mana, Energie etc

        //TODO Remove fight Method from Fight Class and add it to the IOManager. So an Outside Service can trigger new Rounds
        //TODO Handle application of Stacks for Effects. F.E: Bleeding can have multiple aktive Stacks per Fighter. Poison only one
        //TODO Add Debug Messages to EffectManager, ResultApplicator, DamageReducer

        //TODO Write PersistenceManager Interface that will implement persisting Objekts
        //TODO Introduce a TurnResult Object to reflect changes between turns. And open the possibility for Effect to Change Results of Turn Changes
        //TODO Extend KonsoleIOManager to print the komplette Fight Status into Konsole
        //TODO Test Balancing and add more Classes/Abilities/Enemies to the game
        //TODO (Optional) Make smart enemies with attack patterns or a logic that decides on which attack to use
        //TODO (Optional) Introduce Cooldown as a Ressource for Balancing Purposes. Only when spamming distinct Actions is to effective

        while (fightOngoing) {
            UIStateToKonsolePrinter.printCombatantList(combatantManager.getCombatantList());
            UIStateToKonsolePrinter.printTurnList(roundManager.getDisplayedOrder());

            doTurn();
        }
        ioPrinter.printMsg("\n Team " + combatantManager.getTeamsAlive().getFirst() + " has won the fight \n");
    }

    private void doTurn () {
        CombatantEntry currentActiveCombatant = roundManager.getCurrentCombatant();

        if (currentActiveCombatant.getStatus().equals(CombatantStatus.DEAD))
            return;

        effectManager.evokeEffects(new PreTurnContext(currentActiveCombatant, combatantManager.getCombatantList()));

        ioPrinter.printDebugMsg("It is " + currentActiveCombatant.getCombatant().getName() + " turn");

        for (int turns = currentActiveCombatant.getCombatant().getActionsPerTurn(); turns > 0; turns--) {
            combatantTurn(currentActiveCombatant);
        }

        effectManager.evokeEffects(new PastTurnContext(currentActiveCombatant, combatantManager.getCombatantList()));
        eventBus.onTurnEnd();
        ioPrinter.printTraceMsg("Turn End \n");


    }

    private void combatantTurn (CombatantEntry currentActiveCombatant) {

        effectManager.evokeEffects(new PreActionContext(currentActiveCombatant));

        Action selectedAction = null;
        //Action Selection

        if (currentActiveCombatant.isPlayerControlled()) {
            while (selectedAction == null) {
                ioPrinter.printMsg("Select Action");
                UIStateToKonsolePrinter.printActionDisplay(playerAction);
                String input = ioManager.getInput();
                try {
                    int inputAsNumber = Integer.parseInt(input) - 1;
                    if (playerAction[inputAsNumber].getStaminaCost() <= currentActiveCombatant.getCombatant().getCurrentStamina() &&
                        playerAction[inputAsNumber].getActionCost() <= currentActiveCombatant.getCombatant().getActionsPerTurn()) {
                        selectedAction = playerAction[inputAsNumber];
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    ioPrinter.printMsg("Wrong input. Try again");
                }
            }
        } else {
            //Temporary Hard Coded Action for Wolves only.
            selectedAction = new LightWolfBite();
        }

        //Target Selection And Action Resolve
        for (int i = 0; i < selectedAction.getTargetAmount(); i++) {
            ioPrinter.printMsg("Select target " + (i + 1) + " for " + selectedAction.getActionName());

            List<Target> selectedTargets = targetSelector.selectTargets(selectedAction, currentActiveCombatant);
            ioPrinter.printMsg(currentActiveCombatant.getCombatant().getName() + " has Selected " + selectedTargets.size() + " Targets for " +
                               selectedAction.getActionName());

            effectManager.evokeEffects(new PreDamageCalculationContext(currentActiveCombatant));

            List<ActionResult> actionResults = new ArrayList<>();
            for (Target selectedTarget : selectedTargets) {
                actionResults.add(selectedAction.evoke(currentActiveCombatant, selectedTarget));
            }

            for (ActionResult actionResult : actionResults) {
                effectManager.evokeEffects(new PastDamageCalculationContext(actionResult.getSource(), actionResult));
                effectManager.evokeEffects(new PastDamageCalculationTargetContext(actionResult.getTarget(), actionResult));

                effectManager.evokeEffects(new PreDamageReductionContext(actionResult.getSource(), actionResult));

                damagerReducer.reduceResultDamage(actionResult);

                effectManager.evokeEffects(new PastDamageReductionContext(actionResult.getSource(), actionResult));
                effectManager.evokeEffects(new PastDamageReductionTargetContext(actionResult.getTarget(), actionResult));
                resultApplicator.applyResult(actionResult);
            }
            currentActiveCombatant.getCombatant().setCurrentStamina(currentActiveCombatant.getCombatant().getCurrentStamina() - selectedAction.getStaminaCost());
        }
        effectManager.evokeEffects(new PastActionContext(currentActiveCombatant, selectedAction));
    }

    /*
     * Start of a new Round
     */
    void onNextRound () {
        fightOngoing = combatantManager.moreThanOneTeamAlive();
    }
}
