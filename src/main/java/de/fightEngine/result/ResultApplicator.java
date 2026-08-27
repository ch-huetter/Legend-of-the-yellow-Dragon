package de.fightEngine.result;

import de.fightEngine.CombatantEntry;
import de.fightEngine.CombatantStatus;
import de.fightEngine.FightContext;
import de.fightEngine.action.ActionResult;
import de.fightEngine.effect.EffectResult;
import de.game.model.entity.LivingEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResultApplicator {

    private final FightContext fightContext;

    public void applyResult (AbstractResult abstractResult) {
        applyAbstractResult(abstractResult);

        switch (abstractResult) {
            case ActionResult actionResult:
                applyActionResult(actionResult);
                break;
            case EffectResult effectResult:
                applyEffectResult(effectResult);
                break;
            default:
                throw new IllegalArgumentException("Type of Result is not supported");
        }

    }

    private void applyAbstractResult (AbstractResult abstractResult) {
        //TODO EvocationPoints einfügen für das Ziel eines Results
        CombatantEntry targetEntry  = abstractResult.getTarget();
        LivingEntity   targetEntity = targetEntry.getCombatant();

        double health = targetEntity.getCurrentHealth();

        health -= (abstractResult.getBluntDamage() + abstractResult.getPiercingDamage() + abstractResult.getMagicDamage() + abstractResult.getTrueDamage());
        health += abstractResult.getHealthHealAmount();
        health = Math.min(targetEntity.getMaxHealth(), Math.max(health, 0));

        targetEntity.setCurrentHealth((int) health);
        if (health < 1.0) {
            targetEntry.setStatus(CombatantStatus.DEAD);
            fightContext.combatantIsDead();
        }

        double stamina = targetEntity.getCurrentStamina();

        stamina -= abstractResult.getStaminaDrain();
        stamina += abstractResult.getStaminaHealAmount();
        stamina = Math.min(targetEntity.getMaxStamina(), Math.max(0, stamina));

        targetEntity.setCurrentStamina((int) stamina);

        if (abstractResult.getEffectsToRegister() != null && !abstractResult.getEffectsToRegister().isEmpty()) {
            abstractResult.getEffectsToRegister().forEach(fightContext::registerEffect);
        }
    }

    private void applyActionResult (ActionResult actionResult) {
        //Empty
    }

    private void applyEffectResult (EffectResult effectResult) {
        CombatantEntry combatantEntry = effectResult.getTarget();
        int            newActions     = combatantEntry.getCombatantActions();

        newActions -= (int) Math.max(effectResult.getActionDrain(), combatantEntry.getCombatantActions() * -1);

        combatantEntry.setCombatantActions(newActions);
    }

}
