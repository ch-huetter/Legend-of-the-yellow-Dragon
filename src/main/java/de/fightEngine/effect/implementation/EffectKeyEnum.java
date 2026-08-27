package de.fightEngine.effect.implementation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EffectKeyEnum {
    REJUVENATION("Verjüngung"),
    EXHAUSTION("Erschöpfung"),
    POISON("Gift"),
    BLEED("Blutung"),
    GREATER_BLUNT_DAMAGE("Schadensteigerung Stumpf");

    final String name;


}
