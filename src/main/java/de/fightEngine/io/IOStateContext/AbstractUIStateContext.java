package de.fightEngine.io.IOStateContext;

import de.fightEngine.io.UIState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AbstractUIStateContext {

    protected final UIState contextUIState;

}
