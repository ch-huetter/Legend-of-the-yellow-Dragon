package de.fightEngine.io.konsoleIOManager;

import de.fightEngine.action.Action;
import de.fightEngine.io.IOLevel;
import de.fightEngine.io.IOManager;
import de.fightEngine.io.IOStateContext.AbstractUIStateContext;
import de.fightEngine.io.IOStateContext.UIStateActionSelectionContext;
import de.fightEngine.io.IOStateContext.UIStateTargetSelectionContext;
import de.game.bean.MessageResolvable;

import java.util.Scanner;

public class KonsoleIOManager extends IOManager {

    private final UIStateToKonsolePrinter uiStateToKonsolePrinter;
    private final Scanner scanner = new Scanner(System.in);


    public KonsoleIOManager (IOLevel ioLevel) {
        super(ioLevel);
        this.uiStateToKonsolePrinter = new UIStateToKonsolePrinter();
    }

    public void showUIState (AbstractUIStateContext uIStateContext) {
        switch (uIStateContext.getContextUIState()) {
            case ACTION_SELECTION -> printStateActionSelection((UIStateActionSelectionContext) uIStateContext);
            case TARGET_SELECTION -> printStateTargetSelection((UIStateTargetSelectionContext) uIStateContext);
        }
    }

    private void printStateActionSelection (UIStateActionSelectionContext actionSelectionContext) {
        uiStateToKonsolePrinter.printActionDisplay(actionSelectionContext.getActionsToChoose().toArray(new Action[0]));
    }

    private void printStateTargetSelection (UIStateTargetSelectionContext uiStateTargetSelectionContext) {
        uiStateToKonsolePrinter.printSelectableTargetListAsCombatantList(uiStateTargetSelectionContext.getSelectableTargets());
    }

    @Override
    public void printMsg (String msg) {
        System.out.println(msg);
    }

    @Override
    public void printDebugMsg (String msg, IOLevel ioLevelOverride) {
        IOLevel ioLevel = ioLevelOverride.equals(IOLevel.DEFAULT) ? messageIOLevel : ioLevelOverride;
        if (ioLevel.getValue() >= IOLevel.DEBUG.getValue())
            System.out.println(msg);

    }

    @Override
    public void printTraceMsg (String msg, IOLevel ioLevelOverride) {
        IOLevel ioLevel = ioLevelOverride.equals(IOLevel.DEFAULT) ? messageIOLevel : ioLevelOverride;
        if (ioLevel.getValue() >= IOLevel.TRACE.getValue())
            System.out.println(msg);
    }

    @Override
    public void printMsgResolvable (MessageResolvable messageResolvable) {
        printMsg("MessageResolvable is not Supported for KonsoleIOManager");
    }


    @Override
    public String getInput () {
        return scanner.next();
    }
}
