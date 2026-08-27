package de.fightEngine.effect.implementation.damage;

import de.fightEngine.CombatantEntry;
import de.fightEngine.effect.EffectEvocationPoint;
import de.fightEngine.effect.EffectEvokationContext.EffectEvocationContext;
import de.fightEngine.effect.EffectEvokationContext.PastActionContext;
import de.fightEngine.effect.EffectEvokationContext.PastTurnContext;
import de.fightEngine.effect.EffectResult;
import de.fightEngine.effect.implementation.Effect;
import de.fightEngine.effect.implementation.EffectKeyEnum;


public class Bleed extends Effect {

    private int durationActions;

    public Bleed (int durationTurns, CombatantEntry source, CombatantEntry target, int durationActions) {
        super(durationTurns, source, target, EffectKeyEnum.BLEED);
        this.durationActions = durationActions;

    }

    @Override
    public EffectResult evokeEffect (EffectEvocationContext evocationContext) {
        return switch (evocationContext.getEvocationPoint()) {
            case PAST_ACTION -> evokePastAction((PastActionContext) evocationContext);
            case PAST_TURN -> evokePastTurn((PastTurnContext) evocationContext);
            default -> throw new IllegalArgumentException("Evocation Point is not supported " + evocationContext.getEvocationPoint());
        };
    }

    @Override
    public boolean isDismissable () {
        return durationTurns < 1 || durationActions < 1;
    }

    @Override
    public String getRegistrationMsg () {
        return source.getCombatant().getName() + " fügt " + target.getCombatant().getName() + " eine Blutung für " + durationTurns + " Runden oder " + durationActions + " " +
               "Aktionen zu";
    }

    @Override
    public String getEvokationMsg (EffectEvocationPoint evocationPoint, EffectResult effectResult) {
        return switch (evocationPoint) {
            case PAST_ACTION -> target.getCombatant().getName() + " bekommt " + effectResult.getBluntDamage() + " Schaden zu. Blutung hält noch für " + durationActions + " " +
                                "Aktionen an";
            case PAST_TURN -> "Blutungs dauer reduziert. Noch " + durationTurns + " Runden übrig";
            default -> null;
        };
    }

    @Override
    public EffectEvocationPoint[] getEvocationPoints () {
        return new EffectEvocationPoint[]{EffectEvocationPoint.PAST_ACTION, EffectEvocationPoint.PAST_TURN};
    }

    private EffectResult evokePastAction (PastActionContext pastActionContext) {
        double bluntDamage = getBluntDamagePerActivation();

        durationActions--;
        return EffectResult.builder().bluntDamage(bluntDamage).baseBluntDamage(bluntDamage).build();
    }

    private EffectResult evokePastTurn (PastTurnContext pastTurnContext) {

        durationTurns--;
        return new EffectResult();
    }

    private double getBluntDamagePerActivation () {
        return (double) this.source.getCombatant().getStrength() / 5 * 2;
    }

}
