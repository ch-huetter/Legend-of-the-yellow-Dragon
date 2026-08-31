package de.fightEngine.round;

import de.fightEngine.CombatantEntry;
import de.fightEngine.result.AbstractResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoundEndResult extends AbstractResult {
    private int ActionValue;

    private RoundEndResult (Builder builder) {
        super(builder);
        this.ActionValue = builder.actionValue;
    }

    public static RoundEndResult.Builder builder (CombatantEntry source, CombatantEntry target) {
        return new RoundEndResult.Builder(source, target);
    }

    public static class Builder extends AbstractResult.Builder<Builder, RoundEndResult> {
        private int actionValue;

        protected Builder (CombatantEntry source, CombatantEntry target) {
            super(source, target);
        }

        @Override
        protected Builder self () {
            return this;
        }

        public Builder actionValue (int value) {
            this.actionValue = value;
            return this;
        }

        public RoundEndResult build () {
            return new RoundEndResult(this);
        }
    }

}
