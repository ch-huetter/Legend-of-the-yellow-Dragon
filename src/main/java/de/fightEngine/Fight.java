package de.fightEngine;

import de.fightEngine.action.Action;
import de.fightEngine.action.ActionResult;
import de.fightEngine.action.implementation.defensive.Rest;
import de.fightEngine.action.implementation.offensive.Player.HeavyAttack;
import de.fightEngine.action.implementation.offensive.Player.LightAttack;
import de.fightEngine.action.implementation.offensive.Wolf.LightWolfBite;
import de.fightEngine.calculator.DamageReductionCalculator;
import de.fightEngine.effect.EffectEvokationContext.*;
import de.fightEngine.effect.EffectManager;
import de.fightEngine.helper.PrototypeCombatTextCreator;
import de.fightEngine.helper.PrototypeFightDataGetter;
import de.fightEngine.io.IOLevel;
import de.fightEngine.io.IOManager;
import de.fightEngine.io.KonsoleIOManager;
import de.fightEngine.result.ResultApplicator;
import de.fightEngine.result.ResultDamagerReducer;
import de.fightEngine.targetSelector.Target;
import de.fightEngine.targetSelector.TargetSelector;
import de.fightEngine.turnOrder.TurnOrderManager;
import de.game.model.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Prototype for working out the Rules and Concept of Fighting
 */
public class Fight {

    private final Action[] playerAction = new Action[]{new HeavyAttack(), new LightAttack(), new Rest()};

    private final TargetSelector targetSelector;
    private final ResultDamagerReducer damagerReducer;
    private final ResultApplicator resultApplicator;
    private final EventBus eventBus;

    private final TurnOrderManager turnOrderManager;
    private final EffectManager effectManager;
    private final CombatantManager combatantManager;

    private boolean fightOngoing = true;

    private final IOManager ioManager;
    private final PrototypeCombatTextCreator prototypeCombatTextCreator = new PrototypeCombatTextCreator();
    private static final Scanner scanner = new Scanner(System.in);


    public Fight () {
        this.eventBus = new EventBus();
        this.ioManager = new KonsoleIOManager(IOLevel.TRACE);

        List<CombatantEntry> combatantList = new ArrayList<>();
        combatantList.add(new CombatantEntry(PrototypeFightDataGetter.getHero(), "Heroes", true, eventBus));
        combatantList.add(new CombatantEntry(PrototypeFightDataGetter.getMonster1(), "Monsters", eventBus));
        combatantList.add(new CombatantEntry(PrototypeFightDataGetter.getMonster2(), "Monsters", eventBus));
        combatantList.add(new CombatantEntry(PrototypeFightDataGetter.getMonster3(), "Monsters", eventBus));

        FightContext              fightContext              = new FightContext();
        DamageReductionCalculator damageReductionCalculator = new DamageReductionCalculator();

        this.targetSelector = new TargetSelector(fightContext, ioManager);
        this.combatantManager = new CombatantManager(ioManager, eventBus, combatantList);
        this.effectManager = new EffectManager(combatantList, ioManager, fightContext);
        this.turnOrderManager = new TurnOrderManager(fightContext, eventBus, ioManager, combatantList);
        this.resultApplicator = new ResultApplicator(fightContext);
        this.damagerReducer = new ResultDamagerReducer(damageReductionCalculator, ioManager);

        fightContext.initializeFightContext(this, effectManager, combatantManager, resultApplicator, turnOrderManager);
        eventBus.registerOnTurnEnd(this::onNextRound);
    }

    public static void main (String[] args0) {
        new Fight().fight();
    }

    private void fight () {
        //TODO Remove fight Method from Fight Class and add it to the IOManager. So an Outside Service can trigger new Rounds
        //TODO ActionSelector. Lets Players choose out of the actions they can take and handles ressource cost and advanced conditions for an Ability to be used
        //TODO Add Debug Messages to EffectManager, ResultApplicator, DamageReducer
        //TODO Add ActionManager/PatternManager that manages Action Selection and decides when the player chooses an Action and when the AI chooses an Action
        //TODO Outsource the ActionSelection and make it smarter so it can Recognize special Conditions for Action Activation and additional ressource costs like Mana, Energie etc
        //TODO Write PersistenceManager Interface that will implement persisting Objekts
        //TODO Introduce a TurnResult Object to reflect changes between turns. And open the possibility for Effect to Change Results of Turn Changes
        //TODO Extend KonsoleIOManager to print the komplette Fight Status into Konsole
        //TODO Test Balancing and add more Classes/Abilities/Enemies to the game
        //TODO (Optional) Make smart enemies with attack patterns or a logic that decides on which attack to use
        //TODO (Optional) Introduce Cooldown as a Ressource for Balancing Purposes. Only when spamming distinct Actions is to effective

        preCreateTurnOrder();
        while (fightOngoing) {

            prototypeCombatTextCreator.printCombatantStatus(combatantManager.getCombatantList());
            doTurn();

        }
    }

    private void doTurn () {
        while (fightOngoing) {
            CombatantEntry currentActiveCombatant = turnOrderManager.getCurrentCombatant();

            effectManager.evokeEffects(new PreTurnContext(currentActiveCombatant, combatantManager.getCombatantList()));

            if (currentActiveCombatant.getStatus().equals(CombatantStatus.DEAD))
                continue;

            ioManager.printMsg("It is " + currentActiveCombatant.getCombatant().getName() + " turn");

            for (int turns = currentActiveCombatant.getCombatant().getActivationsPerTurn(); turns > 0; turns--) {
                combatantTurn(currentActiveCombatant);
            }

            effectManager.evokeEffects(new PastTurnContext(currentActiveCombatant, combatantManager.getCombatantList()));
            eventBus.onTurnEnd();
        }

        ioManager.printMsg("Round End\n");
        ioManager.printMsg("");
    }

    private void combatantTurn (CombatantEntry currentActiveCombatant) {

        effectManager.evokeEffects(new PreActionContext(currentActiveCombatant));

        Action selectedAction = null;
        //Action Selection

        if (currentActiveCombatant.isPlayerControlled()) {
            while (selectedAction == null) {
                ioManager.printMsg("Select Action");
                prototypeCombatTextCreator.printActionDisplay(playerAction);
                String input = ioManager.getInput();
                try {
                    int inputAsNumber = Integer.parseInt(input) - 1;
                    if (playerAction[inputAsNumber].getStaminaCost() <= currentActiveCombatant.getCombatant().getCurrentStamina() &&
                        playerAction[inputAsNumber].getActionCost() <= currentActiveCombatant.getCombatant().getActivationsPerTurn()) {
                        selectedAction = playerAction[inputAsNumber];
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    ioManager.printMsg("Wrong input. Try again");
                }
            }
        } else {
            //Temporary Hard Coded Action for Wolves only.
            selectedAction = new LightWolfBite();
        }

        //Target Selection And Action Resolve
        for (int i = 0; i < selectedAction.getTargetAmount(); i++) {
            ioManager.printMsg("Select target " + (i + 1) + " for " + selectedAction.getActionName());

            List<Target> selectedTargets = targetSelector.selectTargets(selectedAction, currentActiveCombatant);
            ioManager.printMsg(currentActiveCombatant.getCombatant().getName() + " has Selected " + selectedTargets.size() + " Targets for " +
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
        ioManager.printMsg("New Round started");
        for (CombatantEntry combatantEntry : combatantManager.getCombatantList()) {
            LivingEntity entity = combatantEntry.getCombatant();

            int regeneratedStamina = Math.min(Math.round((float) entity.getMaxStamina() / 3), entity.getMaxStamina());

            entity.setCurrentStamina(entity.getCurrentStamina() + regeneratedStamina);
        }
    }

    private void preCreateTurnOrder () {
        ioManager.printMsg("Before CreateTurnOrder");
        ioManager.printMsg("Checking teams");
        ioManager.printMsg("Amount of Combatants is : " + combatantManager.getCombatantList().size());
        ioManager.printMsg("");
    }

}
