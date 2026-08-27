package de.fightEngine.effect.implementation.heal;

import de.fightEngine.CombatantEntry;
import de.fightEngine.effect.EffectEvocationPoint;
import de.fightEngine.effect.EffectEvokationContext.EffectEvocationContext;
import de.fightEngine.effect.EffectEvokationContext.PreActionContext;
import de.fightEngine.effect.EffectResult;
import de.fightEngine.effect.implementation.Effect;
import de.fightEngine.effect.implementation.EffectKeyEnum;
import lombok.Getter;

@Getter
public class Rejuvenation extends Effect {

    private final int healPercent;

    public Rejuvenation (int durationTurns, CombatantEntry source, CombatantEntry target, int healPercent) {
        super(durationTurns, source, target, EffectKeyEnum.REJUVENATION);
        this.healPercent = healPercent;
    }

    @Override
    public EffectResult evokeEffect (EffectEvocationContext evocationContext) {
        switch (evocationContext.getEvocationPoint()) {
            case PRE_ACTION:
                return evokePreTurn((PreActionContext) evocationContext);
            default:
                throw new IllegalArgumentException(getErrorMessageForNoEvokationPoint(evocationContext.getEvocationPoint()));
        }
    }

    @Override
    public String getRegistrationMsg () {
        return source.getCombatant() + " wirkt " + effectKeyEnum.getName() + " auf " + target.getCombatant().getName() + " für " + durationTurns + " Runden";
    }

    @Override
    public String getEvokationMsg (EffectEvocationPoint evocationPoint, EffectResult effectResult) {
        return switch (evocationPoint) {
            case PRE_ACTION -> target.getCombatant().getName() + " regeneriert " + effectResult.getHealthHealAmount() + " Lebenspunkte";
            default -> null;
        };
    }

    @Override
    public EffectEvocationPoint[] getEvocationPoints () {
        return new EffectEvocationPoint[]{EffectEvocationPoint.PRE_ACTION};
    }

    private EffectResult evokePreTurn (PreActionContext preTurnContext) {
        double healAmount = Math.round((float) source.getCombatant().getMaxHealth() / 100 * 10);

        durationTurns--;

        return EffectResult.builder().healthHealAmount(healAmount).build();
    }

}
