package de.fightEngine.effect;

import de.fightEngine.CombatantEntry;
import de.fightEngine.result.AbstractResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EffectResult extends AbstractResult {

    private double actionDrain;

    private EffectResult (Builder builder) {
        super(builder);
        this.actionDrain = builder.actionDrain;
    }

    public static EffectResult.Builder builder (CombatantEntry source, CombatantEntry target) {
        return new EffectResult.Builder(source, target);
    }

    public static class Builder extends AbstractResult.Builder<Builder, EffectResult> {

        private double actionDrain;

        protected Builder (CombatantEntry source, CombatantEntry target) {
            super(source, target);
        }

        @Override
        protected Builder self () {
            return this;
        }

        public Builder actionDrain (double amount) {
            this.actionDrain = amount;
            return this;
        }

        public EffectResult build () {
            return new EffectResult(this);
        }
    }


}
