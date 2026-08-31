package de.fightEngine.targetSelector;

import de.fightEngine.CombatantEntry;
import lombok.Getter;

@Getter
public class Target extends SelectableTarget {

    private final TargetType targetType;

    public Target (CombatantEntry target, TargetType targetType, boolean targetIsAlly, boolean targetIsSelf, boolean targetIsEnemy) {
        super(target, targetIsAlly, targetIsSelf, targetIsEnemy);
        this.targetType = targetType;
    }

    public Target (SelectableTarget selectableTarget, TargetType targetType) {
        super(selectableTarget.getTarget(), selectableTarget.isTargetAlly(), selectableTarget.isTargetSelf(), selectableTarget.isTargetEnemy());
        this.targetType = targetType;
    }
}
