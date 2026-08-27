package de.fightEngine.action.implementation.offensive.Player;

import de.fightEngine.CombatantEntry;
import de.fightEngine.action.Action;
import de.fightEngine.action.ActionResult;
import de.fightEngine.targetSelector.Target;

public class HeavyAttack extends Action {


    public HeavyAttack () {
        this.actionName = "Schwere Attacke";

        this.staminaCost = 60;

        this.targetEnemy = true;


    }

    @Override
    public ActionResult evoke (CombatantEntry source, Target selectedTarget) {

        double bluntDamage = Math.round(source.getCombatant().getStrength() * 2.5);

        return ActionResult.builder().source(source).target(selectedTarget.combatantEntry()).bluntDamage(bluntDamage).baseBluntDamage(bluntDamage).bluntPenetration(20)
                .baseBluntPenetration(20)
                .build();
    }

    @Override
    public String createMsg (ActionResult actionResult) {
        return actionResult.getSource().getCombatant().getName() + " führt Schweren Angriff gegen " + actionResult.getTarget().getCombatant().getName() + " und richtet insgesamt" +
               (actionResult.getBluntDamage()) + " Schaden an";
    }
}
