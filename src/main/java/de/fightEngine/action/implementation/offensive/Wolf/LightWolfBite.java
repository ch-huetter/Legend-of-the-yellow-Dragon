package de.fightEngine.action.implementation.offensive.Wolf;

import de.fightEngine.CombatantEntry;
import de.fightEngine.action.Action;
import de.fightEngine.action.ActionResult;
import de.fightEngine.targetSelector.Target;

public class LightWolfBite extends Action {

    public LightWolfBite () {
        this.actionName = "Leichter Wolfs Biss";

        this.staminaCost = 40;

        this.targetEnemy = true;
    }


    @Override
    public ActionResult evoke (CombatantEntry source, Target selectedTarget) {
        ActionResult result = new ActionResult();
        result.setSource(source);
        result.setTarget(selectedTarget.combatantEntry());
        result.setBluntDamage(source.getCombatant().getStrength() * 2);
        result.setBluntPenetration(0);

        return result;
    }

    @Override
    public String createMsg (ActionResult actionResult) {
        return "";
    }
}
