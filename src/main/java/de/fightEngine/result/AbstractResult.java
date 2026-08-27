package de.fightEngine.result;

import de.fightEngine.CombatantEntry;
import de.fightEngine.effect.implementation.Effect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class AbstractResult {

    CombatantEntry source;
    CombatantEntry target;

    private double bluntDamage;
    private double baseBluntDamage;
    private double piercingDamage;
    private double basePiercingDamage;
    private double magicDamage;
    private double baseMagicDamage;
    private double trueDamage;
    private double baseTrueDamage;

    private double bluntPenetration;
    private double baseBluntPenetration;
    private double piercingPenetration;
    private double basePiercingPenetration;
    private double magicPenetration;
    private double baseMagicPenetration;

    private double healthHealAmount;
    private double staminaHealAmount;
    private double staminaDrain;

    List<Effect> effectsToRegister;
}
