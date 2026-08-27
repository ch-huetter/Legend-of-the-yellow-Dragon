package de.fightEngine.calculator;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DamageReductionCalculator {

    private final double maxReduction = 0.8;

    public double calculateArmorDamageReduction (double armor, double modifier) {
        double armorFactor = armor / 2.0;
        return Math.round(((armorFactor / (armorFactor + modifier)) * maxReduction) * 100.0);
    }

    public double calculateDexterityDamageReduction (double dexterity, double modifier) {
        double dexterityFactor = dexterity / 3.0;
        return Math.round((dexterityFactor / (dexterityFactor + modifier)) * maxReduction * 100.0);
    }

    public double calculateDexterityMagicDamageReduction (double dexterity, double modifier) {
        double dexterityFactor = dexterity / 3.0;
        return Math.round((dexterityFactor / (dexterityFactor + modifier)) * maxReduction * 100.0);
    }

    public double calculateAgilityPenetration (double agility, double modifier) {
        double agilityFactor = agility / 2.5;
        return Math.round((agilityFactor / (agilityFactor + modifier)) * maxReduction * 100.0);
    }

}
