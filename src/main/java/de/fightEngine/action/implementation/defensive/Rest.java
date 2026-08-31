package de.fightEngine.action.implementation.defensive;

import de.fightEngine.CombatantEntry;
import de.fightEngine.action.Action;
import de.fightEngine.action.ActionResult;
import de.fightEngine.targetSelector.Target;


public class Rest extends Action {

    public Rest () {
        this.actionName = "Warten";

        this.staminaCost = 0;

        this.targetSelf = true;
    }

    @Override
    public ActionResult evoke (CombatantEntry source, Target selectedTarget) {
        return ActionResult.builder(source, selectedTarget.getTarget()).staminaHeal(75).build();
    }

    @Override
    public String createMsg (ActionResult actionResult) {
        return actionResult.getSource().getCombatant().getName() + " rested and regenerated " + actionResult.getStaminaHealAmount() + " Stamina";
    }
}
