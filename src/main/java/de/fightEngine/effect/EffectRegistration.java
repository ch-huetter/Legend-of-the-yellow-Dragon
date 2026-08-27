package de.fightEngine.effect;

import de.fightEngine.CombatantEntry;
import de.fightEngine.effect.implementation.Effect;

public record EffectRegistration(EffectEvocationPoint evocationPoint, Effect effectToRegister, CombatantEntry combatantEntry) {
}
