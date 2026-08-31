package de.fightEngine.action.implementation.offensive.Wolf;

import de.fightEngine.CombatantEntry;
import de.fightEngine.action.Action;
import de.fightEngine.action.ActionResult;
import de.fightEngine.targetSelector.Target;

public class LightWolfBite extends Action {

    public LightWolfBite () {
        this.actionName = "Leichter Wolfs Biss";

        this.staminaCost = 20;

        this.targetEnemy = true;
    }


    @Override
    public ActionResult evoke (CombatantEntry source, Target selectedTarget) {

        int bluntDamage = source.getCombatant().getStrength() * 2;

        return ActionResult.builder(source, selectedTarget.getTarget()).bluntDamage(bluntDamage, 0).build();
    }

    @Override
    public String createMsg (ActionResult actionResult) {
        return "";
    }
}
