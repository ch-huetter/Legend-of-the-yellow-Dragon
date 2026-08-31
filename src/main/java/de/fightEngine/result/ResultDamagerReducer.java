package de.fightEngine.result;

import de.fightEngine.calculator.DamageReductionCalculator;
import de.fightEngine.io.IOManager;
import de.fightEngine.io.IOPrinter;
import de.game.model.entity.LivingEntity;

public class ResultDamagerReducer {

    private final DamageReductionCalculator damageReductionCalculator;
    private final IOPrinter ioPrinter;

    public ResultDamagerReducer (DamageReductionCalculator damageReductionCalculator, IOManager ioPrinter) {
        this.damageReductionCalculator = damageReductionCalculator;
        this.ioPrinter = ioPrinter.getIOPrinterInstance();
    }

    /**
     * @param abstractResult of an Action or Effect. Reduces damage values of the given Result
     */
    public void reduceResultDamage (AbstractResult abstractResult) {
        double       modifier           = 100.0;
        LivingEntity source             = abstractResult.getSource().getCombatant();
        LivingEntity target             = abstractResult.getTarget().getCombatant();
        double       armorReduction     = damageReductionCalculator.calculateArmorDamageReduction(target.getArmor(), modifier);
        double       dexterityReduction = damageReductionCalculator.calculateDexterityDamageReduction(target.getDexterity(), modifier);
        double       magicReduction     = damageReductionCalculator.calculateDexterityMagicDamageReduction(target.getDexterity(), modifier);
        double       agilityPenetration = damageReductionCalculator.calculateAgilityPenetration(source.getAgility(), modifier);

        double bluntDamage = abstractResult.getBluntDamage();
        if (bluntDamage > 0.0) {
            double bluntBR = bluntDamage;
            bluntDamage = Math.max(0, bluntDamage - (Math.round(Math.min(80, armorReduction + dexterityReduction - abstractResult.getBluntPenetration()) / 100 * bluntDamage)));
            abstractResult.setBluntDamage(bluntDamage);
            ioPrinter.printMsg("Blunt Damage before/after Reduction (" + bluntBR + "/" + bluntDamage + ")");
        }

        double piercingDamage = abstractResult.getPiercingDamage();
        if (piercingDamage > 0.0) {
            double piercingDamageBR        = piercingDamage;
            double piercingDamageReduction = Math.max(dexterityReduction, armorReduction + dexterityReduction - agilityPenetration - abstractResult.getPiercingPenetration());
            piercingDamage = Math.max(0, piercingDamage - (Math.round(Math.min(80, piercingDamageReduction) / 100 * piercingDamage)));
            abstractResult.setPiercingDamage(piercingDamage);
            ioPrinter.printMsg("Piercing Damage before/after Reduction (" + piercingDamageBR + "/" + piercingDamage + ")" + " with " + piercingDamageReduction + "% Reduction");
        }

        double magicDamage = abstractResult.getMagicDamage();
        if (magicDamage > 0.0) {
            double magicDamageBR        = magicDamage;
            double magicDamageReduction = Math.min(80, Math.max(0, magicReduction - abstractResult.getMagicPenetration()));
            magicDamage = Math.max(0, Math.round(magicDamage - magicDamageReduction / 100 * magicDamage));
            abstractResult.setMagicDamage(magicDamage);
            ioPrinter.printMsg("Magic Damage before/after Reduction (" + magicDamageBR + "/" + magicDamage + ") with " + magicDamageReduction + "% Reduction");
        }

    }

}
