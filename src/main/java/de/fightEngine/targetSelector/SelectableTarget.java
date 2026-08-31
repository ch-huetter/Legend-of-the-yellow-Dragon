package de.fightEngine.targetSelector;

import de.fightEngine.CombatantEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SelectableTarget {
    protected final CombatantEntry target;
    protected boolean targetAlly;
    protected boolean targetSelf;
    protected boolean targetEnemy;
}
