package de.fightEngine.action;

import de.fightEngine.CombatantEntry;
import de.fightEngine.targetSelector.Target;
import de.fightEngine.targetSelector.TargetSelectionType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class Action {

    protected String actionName;

    protected int staminaCost;
    protected int actionCost = 1;

    protected int hitCount = 1;
    protected int targetAmount = 1;
    protected int areaOfEffect = 0;

    protected boolean targetEnemy = false;
    protected boolean targetAlly = false;
    protected boolean targetSelf = false;

    protected TargetSelectionType targetSelectionType = TargetSelectionType.MULTI_TARGET_EXCLUSIVE;

    public abstract ActionResult evoke (CombatantEntry source, Target selectedTarget);

    /**
     * Creates the msg to show for the Ui to show the Status changes
     */
    public abstract String createMsg (ActionResult actionResult);

}
