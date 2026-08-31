package de.fightEngine.io.IOStateContext;

import de.fightEngine.io.UIState;
import de.fightEngine.targetSelector.SelectableTarget;
import lombok.Getter;

import java.util.List;

@Getter
public class UIStateTargetSelectionContext extends AbstractUIStateContext {

    private final List<SelectableTarget> selectableTargets;

    public UIStateTargetSelectionContext (List<SelectableTarget> selectableTargets) {
        super(UIState.TARGET_SELECTION);
        this.selectableTargets = selectableTargets;
    }
}
