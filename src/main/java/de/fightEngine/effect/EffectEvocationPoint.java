package de.fightEngine.effect;

/*
 * This Enum Represents all possible points where an effect can get activated.
 */
public enum EffectEvocationPoint {
    PRE_TURN,
    PAST_TURN,
    PRE_ACTION,
    PAST_ACTION,
    PRE_DAMAGE_CALCULATION,
    PAST_DAMAGE_CALCULATION,
    PAST_DAMAGE_CALCULATION_TARGET,
    PRE_DAMAGE_REDUCTION,
    PAST_DAMAGE_REDUCTION,
    PAST_DAMAGE_REDUCTION_TARGET,
    PRE_EFFECT_EVOCATION,
    PAST_EFFECT_EVOCATION,
    PAST_EFFECT_CALCULATION_TARGET;
}
