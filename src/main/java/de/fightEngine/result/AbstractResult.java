package de.fightEngine.result;

import de.fightEngine.CombatantEntry;
import de.fightEngine.effect.implementation.Effect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AbstractResult {

    private CombatantEntry source;
    private CombatantEntry target;

    private double bluntDamage;
    private double baseBluntDamage;
    private double piercingDamage;
    private double basePiercingDamage;

    private double bluntPenetration;
    private double baseBluntPenetration;
    private double piercingPenetration;
    private double basePiercingPenetration;

    private double magicDamage;
    private double baseMagicDamage;
    private double magicPenetration;
    private double baseMagicPenetration;

    private double trueDamage;
    private double baseTrueDamage;

    private double healthHealAmount;
    private double staminaHealAmount;
    private double staminaDrain;

    List<Effect> effectsToRegister;

    protected AbstractResult (AbstractResult.Builder<?, ?> builder) {
        this.source = builder.source;
        this.target = builder.target;

        this.bluntDamage = builder.bluntDamage;
        this.baseBluntDamage = builder.bluntDamage;
        this.bluntPenetration = builder.bluntPenetration;
        this.baseBluntPenetration = builder.bluntPenetration;

        this.piercingDamage = builder.piercingDamage;
        this.basePiercingDamage = builder.piercingDamage;
        this.piercingPenetration = builder.piercingPenetration;
        this.basePiercingPenetration = builder.piercingPenetration;

        this.magicDamage = builder.magicDamage;
        this.baseMagicDamage = builder.magicDamage;
        this.magicPenetration = builder.magicPenetration;
        this.baseMagicPenetration = builder.magicPenetration;

        this.trueDamage = builder.trueDamage;
        this.baseTrueDamage = builder.trueDamage;

        this.healthHealAmount = builder.healthHealAmount;
        this.staminaHealAmount = builder.staminaHealAmount;
        this.staminaDrain = builder.staminaDrain;

        this.effectsToRegister = builder.effectsToRegister;
    }

    public abstract static class Builder<B extends Builder<B, T>, T extends AbstractResult> {

        private final CombatantEntry source;
        private final CombatantEntry target;

        private double bluntDamage;
        private double bluntPenetration;

        private double piercingDamage;
        private double piercingPenetration;

        private double magicDamage;
        private double magicPenetration;

        private double trueDamage;

        private double healthHealAmount;
        private double staminaHealAmount;
        private double staminaDrain;

        List<Effect> effectsToRegister;

        protected Builder (CombatantEntry source, CombatantEntry target) {
            this.source = source;
            this.target = target;
            this.effectsToRegister = new ArrayList<>();
        }

        public B bluntDamage (double damage, double penetration) {
            this.bluntDamage = damage;
            this.bluntPenetration = penetration;
            return self();
        }

        public B piercingDamage (double damage, double penetration) {
            this.piercingDamage = damage;
            this.piercingPenetration = penetration;
            return self();
        }

        public B magicDamage (double damage, double penetration) {
            this.magicDamage = damage;
            this.magicPenetration = penetration;
            return self();
        }

        public B trueDamage (double damage) {
            this.trueDamage = damage;
            return self();
        }

        public B healthHeal (double amount) {
            this.healthHealAmount = amount;
            return self();
        }

        public B staminaHeal (double amount) {
            this.staminaHealAmount = amount;
            return self();
        }

        public B staminaDrain (double amount) {
            this.staminaDrain = amount;
            return self();
        }

        public B addEffectToRegister (Effect effect) {
            this.effectsToRegister.add(effect);
            return self();
        }

        protected abstract B self ();

        public abstract T build ();

    }
}
