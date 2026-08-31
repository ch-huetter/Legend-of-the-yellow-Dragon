package de.fightEngine.io.IOStateContext;

import de.fightEngine.action.Action;
import de.fightEngine.io.UIState;
import lombok.Getter;

import java.util.List;

@Getter
public class UIStateActionSelectionContext extends AbstractUIStateContext {

    private final List<Action> actionsToChoose;

    public UIStateActionSelectionContext (List<Action> actionsToChoose) {
        super(UIState.ACTION_SELECTION);
        this.actionsToChoose = actionsToChoose;
    }
}
