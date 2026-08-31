package de.fightEngine.io;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IOPrinter {

    private final IOManager ioManager;
    private IOLevel printerIOLevel;

    IOPrinter (IOManager ioManager, IOLevel printerIOLevel) {
        this.ioManager = ioManager;
        this.printerIOLevel = printerIOLevel;
    }

    IOPrinter (IOManager ioManager) {
        this.ioManager = ioManager;
        this.printerIOLevel = IOLevel.DEFAULT;
    }

    public void printMsg (String msg) {
        ioManager.printMsg(msg);
    }

    public void printDebugMsg (String msg) {
        ioManager.printDebugMsg(msg, printerIOLevel);
    }

    public void printTraceMsg (String msg) {
        ioManager.printTraceMsg(msg, printerIOLevel);
    }


}
