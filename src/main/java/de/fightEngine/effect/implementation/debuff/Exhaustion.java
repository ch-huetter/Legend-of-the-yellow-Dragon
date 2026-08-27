package de.fightEngine.effect.implementation.debuff;

import de.fightEngine.CombatantEntry;
import de.fightEngine.action.ActionResult;
import de.fightEngine.effect.EffectEvocationPoint;
import de.fightEngine.effect.EffectEvokationContext.EffectEvocationContext;
import de.fightEngine.effect.EffectEvokationContext.PastDamageCalculationContext;
import de.fightEngine.effect.EffectResult;
import de.fightEngine.effect.implementation.Effect;
import de.fightEngine.effect.implementation.EffectKeyEnum;
import lombok.Getter;

@Getter
public class Exhaustion extends Effect {

    private final int damageReductionPercent = 15;
    private double lastDamageReduction;

    public Exhaustion (int durationTurns, int durationActions, CombatantEntry source, CombatantEntry target) {
        super(durationTurns, source, target, EffectKeyEnum.EXHAUSTION);
    }

    @Override
    public EffectResult evokeEffect (EffectEvocationContext evocationContext) {
        return switch (evocationContext.getEvocationPoint()) {
            case PAST_DAMAGE_CALCULATION -> evokePastDamage((PastDamageCalculationContext) evocationContext);
            default -> throw new IllegalArgumentException(getErrorMessageForNoEvokationPoint(evocationContext.getEvocationPoint()));
        };

    }

    @Override
    public String getRegistrationMsg () {
        return source.getCombatant().getName() + " wirkt " + effectKeyEnum.getName() + " auf " + target.getCombatant().getName() + " für " + durationTurns + " Runden";
    }

    @Override
    public String getEvokationMsg (EffectEvocationPoint evocationPoint, EffectResult effectResult) {
        return switch (evocationPoint) {
            case PAST_DAMAGE_CALCULATION -> "";
            default -> null;
        };
    }

    @Override
    public EffectEvocationPoint[] getEvocationPoints () {
        return new EffectEvocationPoint[]{EffectEvocationPoint.PAST_DAMAGE_CALCULATION, EffectEvocationPoint.PAST_TURN};
    }


    private EffectResult evokePastDamage (PastDamageCalculationContext pastDamageCalculationContext) {
        ActionResult actionResult            = pastDamageCalculationContext.getActionResult();
        double       bluntDamageReduction    = Math.round(actionResult.getBaseBluntDamage() / 100 * damageReductionPercent);
        double       piercingDamageReduction = Math.round(actionResult.getBasePiercingDamage() / 100 * damageReductionPercent);
        double       magicDamageReduction    = Math.round(actionResult.getBaseMagicDamage() / 100 * damageReductionPercent);

        lastDamageReduction = bluntDamageReduction + piercingDamageReduction + magicDamageReduction;

        actionResult.setBluntDamage(Math.max(actionResult.getBluntDamage() - bluntDamageReduction, 0));
        actionResult.setPiercingDamage(Math.max(actionResult.getPiercingDamage() - piercingDamageReduction, 0));
        actionResult.setMagicDamage(Math.max(actionResult.getMagicDamage() - magicDamageReduction, 0));
        
        return new EffectResult();
    }

}
