package de.fightEngine.effect.implementation;

import de.fightEngine.CombatantEntry;
import de.fightEngine.effect.EffectEvocationPoint;
import de.fightEngine.effect.EffectEvokationContext.EffectEvocationContext;
import de.fightEngine.effect.EffectResult;
import lombok.Getter;

@Getter
/*
 * Abstract Effect Class.
 */
public abstract class Effect {

    protected int durationTurns;
    protected final CombatantEntry source;
    protected final CombatantEntry target;
    protected final EffectKeyEnum effectKeyEnum;

    public Effect (int durationTurns, CombatantEntry source, CombatantEntry target, EffectKeyEnum effectKeyEnum) {
        this.durationTurns = durationTurns;
        this.source = source;
        this.target = target;
        this.effectKeyEnum = effectKeyEnum;
    }

    /**
     * Gets an EffectEvocationContext and handles based on the EvocationPoint provided by the context what needs to be done
     */
    public abstract EffectResult evokeEffect (EffectEvocationContext evocationContext);

    /**
     * @return true if the effect has ended. False if it is ongoing
     */
    public boolean isDismissable () {
        return durationTurns < 1;
    }

    /**
     * Default implementation. Gets called when the effect is dismissed. This can happen when the duration values reaches Zero. The Condition is set in the isDismissable Method
     * and can be overwritten
     */
    public void dismissEffect () {
        //Nothing
    }

    public abstract String getRegistrationMsg ();

    public abstract String getEvokationMsg (EffectEvocationPoint evocationPoint, EffectResult effectResult);

    public abstract EffectEvocationPoint[] getEvocationPoints ();

    protected final String getErrorMessageForNoEvokationPoint (EffectEvocationPoint evocationPoint) {
        return "Evocation Point is not supported " + evocationPoint;
    }
}
