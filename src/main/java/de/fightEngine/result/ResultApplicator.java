package de.fightEngine.result;

import de.fightEngine.CombatantEntry;
import de.fightEngine.CombatantStatus;
import de.fightEngine.FightContext;
import de.fightEngine.action.ActionResult;
import de.fightEngine.effect.EffectResult;
import de.fightEngine.io.IOManager;
import de.fightEngine.io.IOPrinter;
import de.fightEngine.round.RoundEndResult;
import de.game.model.entity.LivingEntity;

public class ResultApplicator {

    private final FightContext fightContext;
    private final IOPrinter ioPrinter;

    public ResultApplicator (FightContext fightContext, IOManager ioManager) {
        this.fightContext = fightContext;
        this.ioPrinter = ioManager.getIOPrinterInstance();
    }

    public void applyResult (AbstractResult abstractResult) {
        applyAbstractResult(abstractResult);

        switch (abstractResult) {
            case ActionResult actionResult:
                applyActionResult(actionResult);
                break;
            case EffectResult effectResult:
                applyEffectResult(effectResult);
                break;
            case RoundEndResult roundEndResult:
                applyRoundEndResult(roundEndResult);
                break;
            default:
                throw new IllegalArgumentException("Type of Result is not supported");
        }

    }

    private void applyAbstractResult (AbstractResult abstractResult) {
        CombatantEntry targetEntry  = abstractResult.getTarget();
        LivingEntity   targetEntity = targetEntry.getCombatant();

        double health = targetEntity.getCurrentHealth();
        double dmg    = Math.round(abstractResult.getBluntDamage() + abstractResult.getPiercingDamage() + abstractResult.getMagicDamage() + abstractResult.getTrueDamage());
        double heal   = abstractResult.getHealthHealAmount();

        health -= dmg;
        ioPrinter.printTraceMsg("Dealing " + dmg + "  Dmg reducing health to " + health);
        health += heal;
        ioPrinter.printTraceMsg("Healing for " + heal + " to " + health);
        health = Math.min(targetEntity.getMaxHealth(), Math.max(health, 0));
        ioPrinter.printTraceMsg("Final Value after min 0 " + health);
        targetEntity.setCurrentHealth((int) health);

        if (health < 1.0) {
            targetEntry.setStatus(CombatantStatus.DEAD);
            ioPrinter.printTraceMsg("Health is Zero. Combatant " + targetEntity.getName() + " status is now Dead");
            fightContext.combatantIsDead();
        }

        double stamina      = targetEntity.getCurrentStamina();
        double staminaDrain = abstractResult.getStaminaDrain();
        double staminaHeal  = abstractResult.getStaminaHealAmount();

        stamina -= staminaDrain;
        ioPrinter.printTraceMsg("Draining " + staminaDrain + " Stamina and reducing it to " + stamina);
        stamina += staminaHeal;
        ioPrinter.printTraceMsg("Healing " + staminaHeal + " Stamina and increasing it to " + stamina);
        stamina = Math.min(targetEntity.getMaxStamina(), Math.max(0, stamina));
        ioPrinter.printTraceMsg("Final value after min 0 " + stamina);
        targetEntity.setCurrentStamina((int) stamina);

        ioPrinter.printDebugMsg("Applied result to " + targetEntity.getName() + " setting Health to " + health + ", stamina to " + stamina);
        if (abstractResult.getEffectsToRegister() != null && !abstractResult.getEffectsToRegister().isEmpty()) {
            ioPrinter.printDebugMsg("Trying to Register " + abstractResult.getEffectsToRegister().size() + " Effects");
            abstractResult.getEffectsToRegister().forEach(fightContext::registerEffect);
        } else {
            ioPrinter.printDebugMsg("No Effects to Register");
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

    private void applyRoundEndResult (RoundEndResult roundEndResult) {
        roundEndResult.getTarget().setCombatantActions(roundEndResult.getActionValue());
    }

}
