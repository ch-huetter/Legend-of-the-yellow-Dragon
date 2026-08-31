package de.fightEngine.effect.implementation.buff;

import de.fightEngine.CombatantEntry;
import de.fightEngine.action.ActionResult;
import de.fightEngine.effect.EffectEvocationPoint;
import de.fightEngine.effect.EffectEvokationContext.EffectEvocationContext;
import de.fightEngine.effect.EffectEvokationContext.PastDamageCalculationContext;
import de.fightEngine.effect.EffectEvokationContext.PastTurnContext;
import de.fightEngine.effect.EffectResult;
import de.fightEngine.effect.implementation.Effect;
import de.fightEngine.effect.implementation.EffectKeyEnum;
import lombok.Getter;

@Getter
public class GreaterBluntDamage extends Effect {

    private final int bonusDamagePercent;
    private int lastDamageBonusValue;

    public GreaterBluntDamage (int durationTurns, CombatantEntry source, CombatantEntry target, int bonusDamagePercent) {
        super(durationTurns, source, target, EffectKeyEnum.GREATER_BLUNT_DAMAGE);
        this.bonusDamagePercent = bonusDamagePercent;
    }

    @Override
    public EffectResult evokeEffect (EffectEvocationContext evocationContext) {
        return switch (evocationContext.getEvocationPoint()) {
            case PAST_DAMAGE_CALCULATION -> evokePastDamageCalculation((PastDamageCalculationContext) evocationContext);
            case PAST_TURN -> evokePastTurn((PastTurnContext) evocationContext);
            default -> throw new IllegalArgumentException("Evocation Points is not supported");
        };
    }

    @Override
    public String getRegistrationMsg () {
        return effectKeyEnum.getName() + " wurde für " + durationTurns + " Runden für " + target.getCombatant().getName() + " gewährt";
    }

    @Override
    public String getEvokationMsg (EffectEvocationPoint evocationPoint, EffectResult effectResult) {
        return switch (evocationPoint) {
            case PAST_DAMAGE_CALCULATION -> "Schaden um " + bonusDamagePercent + " gesteigert";
            case PAST_TURN -> "Duration reduced by 1 Turn";
            default -> null;
        };

    }

    @Override
    public EffectEvocationPoint[] getEvocationPoints () {
        return new EffectEvocationPoint[]{EffectEvocationPoint.PAST_DAMAGE_CALCULATION, EffectEvocationPoint.PAST_TURN};
    }


    private EffectResult evokePastDamageCalculation (PastDamageCalculationContext pastDamageContext) {
        ActionResult actionResult = pastDamageContext.getActionResult();

        double baseBluntDamage       = actionResult.getBaseBluntDamage();
        double bluntDamage           = actionResult.getBluntDamage();
        double additionalBluntDamage = baseBluntDamage / 100 * bonusDamagePercent;
        lastDamageBonusValue = (int) additionalBluntDamage;

        actionResult.setBluntDamage(bluntDamage + additionalBluntDamage);

        return null;
    }

    private EffectResult evokePastTurn (PastTurnContext pastTurnContext) {
        durationTurns--;
        return null;
    }
}
