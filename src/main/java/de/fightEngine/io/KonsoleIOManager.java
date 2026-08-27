package de.fightEngine.io;

import de.game.bean.MessageResolvable;

import java.util.Scanner;

public class KonsoleIOManager extends IOManager {

    private final Scanner scanner = new Scanner(System.in);

    public KonsoleIOManager (IOLevel ioLevel) {
        super(ioLevel);
    }

    @Override
    public void printMsg (String msg) {
        System.out.println(msg);
    }

    @Override
    public void printDebugMsg (String msg) {
        if (messageIOLevel.getValue() >= IOLevel.DEBUG.getValue())
            printMsg(msg);

    }

    @Override
    public void printTraceMsg (String msg) {
        if (messageIOLevel.getValue() >= IOLevel.TRACE.getValue())
            printMsg(msg);

    }

    @Override
    public void printMsgResolvable (MessageResolvable messageResolvable) {
        printMsg("MessageResolvable is not Supported for KonsoleIOManager");
        return;
    }

    @Override
    public void printDebugMsgResolvable (MessageResolvable messageResolvable) {
        printMsg("MessageResolvable is not Supported for KonsoleIOManager");
        return;
    }

    @Override
    public void printTraceMsgResolvable (MessageResolvable messageResolvable) {
        printMsg("MessageResolvable is not Supported for KonsoleIOManager");
        return;
    }

    @Override
    public String getInput () {
        return scanner.next();
    }
}
