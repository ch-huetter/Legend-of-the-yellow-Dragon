package de.fightEngine.io;

import de.fightEngine.io.IOStateContext.AbstractUIStateContext;
import de.game.bean.MessageResolvable;
import lombok.Getter;

@Getter
public abstract class IOManager {
    protected final IOLevel messageIOLevel;

    public IOManager (IOLevel messageIOLevel) {
        if (messageIOLevel.equals(IOLevel.DEFAULT)) {
            throw new IllegalArgumentException("IOManager cannot be initialized with IOLevel Default");
        }
        this.messageIOLevel = messageIOLevel;
    }

    /**
     * Displays a certain UI State
     *
     * @param abstractUIStateContext of the new UI State
     */
    public abstract void showUIState (AbstractUIStateContext abstractUIStateContext);

    protected abstract void printMsg (String msg);

    protected abstract void printDebugMsg (String msg, IOLevel ioLevelOverride);

    protected abstract void printTraceMsg (String msg, IOLevel ioLevelOverride);

    protected abstract void printMsgResolvable (MessageResolvable messageResolvable);

    public abstract String getInput ();

    /**
     * Creates a IOPrinter Instance which will print at the same IOLevel as the IOManager
     *
     * @return default IOPrinter Instance
     */
    public IOPrinter getIOPrinterInstance () {
        return createPrinterInstance(IOLevel.DEFAULT);
    }

    /**
     * Create a IOPrinter Instance with the given IOLevel. The IOLevel of the printer will override the IOLevel of the IOManager
     *
     * @param ioLevel that will be used for printing
     * @return IOPrinter Instance printing at the given IOLevel
     */
    public IOPrinter getIOPrinterInstance (IOLevel ioLevel) {
        return createPrinterInstance(ioLevel);
    }

    private IOPrinter createPrinterInstance (IOLevel ioLevel) {
        return new IOPrinter(this, ioLevel);
    }


}
