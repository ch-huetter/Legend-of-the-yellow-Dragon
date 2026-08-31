package de.fightEngine.action.implementation.offensive.Player;

import de.fightEngine.CombatantEntry;
import de.fightEngine.action.Action;
import de.fightEngine.action.ActionResult;
import de.fightEngine.targetSelector.Target;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class LightAttack extends Action {

    public LightAttack () {
        this.actionName = "Leichter Angriff";

        this.staminaCost = 25;

        this.targetEnemy = true;

    }

    @Override
    public ActionResult evoke (CombatantEntry source, Target selectedTarget) {

        double bluntDamage = Math.round(source.getCombatant().getStrength() * 1.5);

        return ActionResult.builder(source, selectedTarget.getTarget()).bluntDamage(bluntDamage, 0).build();
    }

    @Override
    public String createMsg (ActionResult actionResult) {
        return actionResult.getSource().getCombatant().getName() + " führt Leichten Angriff gegen " + actionResult.getTarget().getCombatant().getName() + " und richtet " +
               "insgesamt" + actionResult.getBluntDamage() + " Schaden an";
    }
}
