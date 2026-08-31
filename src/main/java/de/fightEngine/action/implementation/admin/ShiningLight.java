package de.fightEngine.action.implementation.admin;

import de.fightEngine.CombatantEntry;
import de.fightEngine.action.Action;
import de.fightEngine.action.ActionResult;
import de.fightEngine.effect.implementation.damage.Bleed;
import de.fightEngine.effect.implementation.heal.Rejuvenation;
import de.fightEngine.targetSelector.Target;
import de.fightEngine.targetSelector.TargetSelectionType;

public class ShiningLight extends Action {

    public ShiningLight () {
        this.actionName = "Göttlicher Schein";
        this.staminaCost = 100;
        this.targetEnemy = true;
        this.targetSelf = true;
        this.targetAlly = true;
        this.targetSelectionType = TargetSelectionType.ALL;
    }

    @Override
    public ActionResult evoke (CombatantEntry source, Target selectedTarget) {
        ActionResult actionResult = new ActionResult.Builder(source, selectedTarget.getTarget()).build();
        actionResult.setSource(source);
        actionResult.setTarget(selectedTarget.getTarget());
        if (selectedTarget.isTargetAlly() || selectedTarget.isTargetSelf()) {
            actionResult.setHealthHealAmount(source.getCombatant().getIntelligence() * 3);
            actionResult.getEffectsToRegister().add(new Rejuvenation(3, source, selectedTarget.getTarget(), 25));
        } else {
            actionResult.setMagicDamage(source.getCombatant().getIntelligence() * 3);
            actionResult.getEffectsToRegister().add(new Bleed(3, source, selectedTarget.getTarget(), 10));
        }
        return actionResult;
    }

    @Override
    public String createMsg (ActionResult actionResult) {
        return "Shining holy light healing allies and burning enemies";
    }
}
