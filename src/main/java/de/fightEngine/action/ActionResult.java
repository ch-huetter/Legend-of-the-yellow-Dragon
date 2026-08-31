package de.fightEngine.action;

import de.fightEngine.CombatantEntry;
import de.fightEngine.result.AbstractResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionResult extends AbstractResult {

    private ActionResult (ActionResult.Builder builder) {
        super(builder);
    }

    public static ActionResult.Builder builder (CombatantEntry source, CombatantEntry target) {
        return new Builder(source, target);
    }

    public static class Builder extends AbstractResult.Builder<Builder, ActionResult> {
        public Builder (CombatantEntry source, CombatantEntry target) {
            super(source, target);
        }

        @Override
        protected Builder self () {
            return this;
        }

        public ActionResult build () {
            return new ActionResult(this);
        }

    }


}
